package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import DataBean.RouteOfStop;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;

import util.Log;

public class InitializeBusInfoAction extends Action {
	// key stopId, value list of routes
	public static HashMap<String, ArrayList<RouteOfStop>> stopAndRouteMap = new HashMap<>();
	private static final String ACTION_NAME = "initializBusInfoAction.do";
	private static final String JSP_NAME = "helloworld.jsp";

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return ACTION_NAME;
	}

	@Override
	public String perform(HttpServletRequest request) {
		// TODO Auto-generated method stub
		if (stopAndRouteMap.size() == 0) {
			Log.e("init", "map is empty");
			try {
				addStopandRouteInfo();
			} catch (JsonSyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return JSP_NAME;
	}

	private void addStopandRouteInfo() throws JsonSyntaxException, IOException {
		FileReader f = new FileReader("E:\\all_stops.json");
		BufferedReader br = new BufferedReader(f);
		String currentJSONString = "";
		while ((currentJSONString = br.readLine()) != null) {
			JsonArray allStops = (JsonArray) new JsonParser().parse(currentJSONString);
			for (JsonElement curr : allStops) {
				Double lat = curr.getAsJsonObject().getAsJsonPrimitive("lat").getAsDouble();
				Double lon = curr.getAsJsonObject().getAsJsonPrimitive("lon").getAsDouble();
				String stopId = curr.getAsJsonObject().getAsJsonPrimitive("stpid").getAsString();
				String stopName = curr.getAsJsonObject().getAsJsonPrimitive("stpnm").getAsString();
				Log.i("initInfo", "stopId: " + stopId + " , stopName: " + stopName + " lat: " + lat + " lon: " + lon);
				String direction = curr.getAsJsonObject().getAsJsonPrimitive("dir").getAsString();
				String routeId = curr.getAsJsonObject().getAsJsonPrimitive("rt").getAsString();
				// signature of RouteOfStop(String stopId, String stopName,
				// String direction, String routeId, double latitude, double
				// longitude)
				RouteOfStop currRouteOfStop = new RouteOfStop(stopId, stopName, direction, routeId, lat, lon);
				if (stopAndRouteMap.containsKey(stopId)) {
					ArrayList<RouteOfStop> currRouteOfStopList = stopAndRouteMap.get(stopId);
					currRouteOfStopList.add(currRouteOfStop);
					stopAndRouteMap.put(stopId, currRouteOfStopList);
					Log.i("init", "Update stopId" + stopId + "into map");
				} else {
					ArrayList<RouteOfStop> currRouteOfStopList = new ArrayList<>();
					currRouteOfStopList.add(currRouteOfStop);
					stopAndRouteMap.put(stopId, currRouteOfStopList);
					Log.i("init", "Add stopId" + stopId + "into map");
				}
			}
		}
		br.close();
		// test buses at fifth and morewood
		// should print 28x, 58, 71B, 71D
		ArrayList<RouteOfStop> fifthMorewood = stopAndRouteMap.get("1177");
		for (RouteOfStop curr : fifthMorewood) {
			Log.e("fifth morewood: ", curr.getRouteId());
		}
	}
}
