package controller;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;


import DataBean.RouteOfStop;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
			addStopandRouteInfo();
		}
		return JSP_NAME;
	}


	private void addStopandRouteInfo() {
		JsonArray allStops = (JsonArray) new JsonParser().parse("all-stops.json");
		for (JsonElement curr : allStops) {
			Double lat = curr.getAsJsonObject().getAsJsonPrimitive("lat").getAsDouble();
			Double lon = curr.getAsJsonObject().getAsJsonPrimitive("lon").getAsDouble();
			String stopId = curr.getAsJsonObject().getAsJsonPrimitive("stpid").getAsString();
			String stopName = curr.getAsJsonObject().getAsJsonPrimitive("stpnm").getAsString();
			JsonArray routes = curr.getAsJsonObject().getAsJsonArray("routes");
			for (JsonElement currRoute : routes) {
				String direction = currRoute.getAsJsonObject().getAsJsonPrimitive("dir").getAsString();
				String routeId = currRoute.getAsJsonObject().getAsJsonPrimitive("rt").getAsString();
				// signature of RouteOfStop(String stopId, String stopName, String direction, String routeId, double latitude, double longitude)
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
	}

}
