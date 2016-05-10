package controller;

import javax.servlet.http.HttpServletRequest;

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
}
