package controller;

import javax.servlet.http.HttpServletRequest;

public class NearbyAction extends Action {
    private static final String ACTION_NAME = "nearby.do";
    private static final String NEARBY_JSP = "nearby.jsp";

    @Override
    public String getName() {
        return ACTION_NAME;
    }

    @Override
    public String perform(HttpServletRequest request) {
        return NEARBY_JSP;

    }
}
