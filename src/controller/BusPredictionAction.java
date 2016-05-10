package controller;

import DataBean.Bus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BusPredictionAction extends Action{
    private static final String ACTION_NAME = "busPrediction.do";
    private static final String BUS_PREDICTION_JSP = "busPrediction.jsp";

    @Override
    public String getName() {
        return ACTION_NAME;
    }

    @Override
    public String perform(HttpServletRequest request) {

        return BUS_PREDICTION_JSP;
    }

    /**
     * Get all routes for the stop, display in front end after user clicks one stop on map
     * @param stopId
     * @return List of routes
     */
    private List<Route> getRoutes(String stopId) {

    }

    /**
     * Get all buses for each route passing by the stop, sort by arriving time
     * @param stopId
     * @param routes
     * @return List of
     */
    private List<Bus> getPredictedBuses(String stopId, List<Route> routes) {

    }
}
