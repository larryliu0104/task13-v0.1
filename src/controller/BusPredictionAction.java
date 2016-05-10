package controller;

import DataBean.Bus;
import DataBean.RouteOfStop;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BusPredictionAction extends Action {
    private static final String ACTION_NAME = "busPrediction.do";
    private static final String BUS_PREDICTION_JSP = "busPrediction.jsp";

    @Override
    public String getName() {
        return ACTION_NAME;
    }

    @Override
    public String perform(HttpServletRequest request) {
        String stopId = request.getParameter("stopid");
        List<RouteOfStop> routesOfStop = getRoutes(stopId);
        for (RouteOfStop routeOfStop : routesOfStop) {

        }
        return BUS_PREDICTION_JSP;
    }

    /**
     * Get all routes for the stop, display in front end after user clicks one stop on map
     *
     * @param stopId
     * @reurn List of routes
     */
    private List<RouteOfStop> getRoutes(String stopId) {

    }

    /**
     * Get all buses for each route passing by the stop, sort by arriving time
     *
     * @param stopId
     * @param routes
     * @return List of
     */
    private List<Bus> getPredictedBuses(String stopId, List<RouteOfStop> routes) throws MalformedURLException {
        List<Bus> busList = new ArrayList<>();
        for (RouteOfStop route : routes) {
            String routeId = route.getRouteId();
            Bus newBus = getBusInfo(getUrl(routeId, stopId));
            busList.add(newBus);
        }
        return busList;
    }

    private long getWaitTime(Bus bus) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm", Locale.US);
        Date curTime = dateFormat.parse(bus.getCurrentTime());
        Date predictTime = dateFormat.parse(bus.getPredictTime());
        return predictTime.getTime() - curTime.getTime();
    }

    private Bus getBusInfo(String apiAddress) throws MalformedURLException {
        String response = getHttpResponse(apiAddress);
        Gson gson = new Gson();
        Bus bus = gson.fromJson(response, Bus.class);
        return bus;
    }

    private String getHttpResponse(String apiAddress) throws MalformedURLException {
        String submitString = apiAddress;
        URL url = new URL(submitString);
        HttpURLConnection httpConnection = null;

        int responseCode = 0;
        while (true) {
            try {
                while (responseCode != HttpURLConnection.HTTP_OK) {
                    Thread.sleep(1);
                    httpConnection = (HttpURLConnection) url.openConnection();
                    responseCode = httpConnection.getResponseCode();
                }

                BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
                String response;
                StringBuilder builder = new StringBuilder();

                while ((response = br.readLine()) != null) {
                    builder.append(response);
                }
            } catch (Exception e) {
                System.out.println("Response Code:" + responseCode);
                System.out.println("Try Again");
            }
        }
    }

    private String getUrl(String routeId, String stopId) {
        return String.format("http://truetime.portauthority" +
                        ".org/bustime/api/v2/getpredictions?key=8u3mEzmXtEHmZMDMY4js4XGre&format=json&rt=%s&stpid=%s",
                routeId, stopId);
    }
}
