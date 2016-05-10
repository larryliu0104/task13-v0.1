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
import java.util.List;

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
    private List<Bus> getPredictedBuses(String stopId, List<RouteOfStop> routes) {

    }

    private int getWaitTime(String stopId, String routeId) {

    }

    private int getPredictTime(String stopId, String routeId) {

    }

    private Bus getHttpResponse(String dns, String path) throws MalformedURLException {
        String submitString = "http://" + dns + path;
        URL url = new URL(submitString);
        HttpURLConnection httpConnection = null;

        int responseCode = 0;
        while (true) {
            try {
                while (responseCode != HttpURLConnection.HTTP_OK) {
                    Thread.sleep(2);
                    httpConnection = (HttpURLConnection) url.openConnection();
                    responseCode = httpConnection.getResponseCode();
                }
                BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
                String response;
                StringBuilder builder = new StringBuilder();

                while ((response = br.readLine()) != null) {
                    builder.append(response);
                }
                Gson gson = new Gson();
                Bus bus = gson.fromJson(builder.toString(), Bus.class);

                return bus;

            } catch (Exception e) {
                System.out.println("Response Code:" + responseCode);
                System.out.println("Try Again");
            }
        }
    }
}
