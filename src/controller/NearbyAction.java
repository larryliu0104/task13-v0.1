package controller;

import DataBean.Bus;
import DataBean.Stop;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class NearbyAction extends Action {
    private static final String ACTION_NAME = "nearby.do";
    private static final String NEARBY_JSP = "nearby.jsp";

    @Override
    public String getName() {
        return ACTION_NAME;
    }

    @Override
    public String perform(HttpServletRequest request) {
        double latitude = Double.valueOf(request.getParameter("latitude"));
        double longitude = Double.valueOf(request.getParameter("longitutde"));
        List<Bus> closestBuses = getClosestBuses(latitude, longitude);
        request.setAttribute("nearbyBuses", closestBuses);
        return NEARBY_JSP;
    }

    private List<Stop> getAllStops() {
        List<Stop> allStops = new ArrayList<>();
        JsonArray allStopsJson = (JsonArray) new JsonParser().parse("all-stops.json");
        for (JsonElement stopJson : allStopsJson) {
            Gson gson = new Gson();
            Stop stop = gson.fromJson(stopJson, Stop.class);
            allStops.add(stop);
        }
        return allStops;
    }

    private List<Bus> getClosestBuses(double latitude, double longitude) {
        List<Stop> nearbyStops = getNearbyStops(latitude, longitude);
        List<Bus> closestBuses = new ArrayList<>();
        getNearbyStops(latitude, longitude);
        BusPredictionAction busPredictObject = new BusPredictionAction();
        PriorityQueue<Bus> allBuses = new PriorityQueue<>();

        for (Stop stop : nearbyStops) {
            try {
                allBuses.addAll(busPredictObject.getPredictedBuses(stop.getStopId()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < 5; i++) {
            closestBuses.add(allBuses.poll());
        }
        return closestBuses;
    }

    private List<Stop> getNearbyStops(double latitude, double longitude) {
        List<Stop> stops = getAllStops();
        List<Stop> nearbyStops = new ArrayList<>();
        for (Stop stop : stops) {
            double distance = getDistance(stop.getLatitude(), latitude, stop.getLongitude(), longitude);
            stop.setDistance(distance);
        }
        Collections.sort(stops);
        for (int i = 0; i < 5; i++) {
            nearbyStops.add(stops.get(i));
        }
        return nearbyStops;
    }

    private double getDistance(double lat1, double lat2, double lon1, double lon2) {
        double sqrSum = Math.pow(lat1 - lat2, 2.0) + Math.pow(lon1 - lon2, 2.0);
        return Math.sqrt(sqrSum);
    }
}
