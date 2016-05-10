package DataBean;

/**
 * @author Siqi Wang siqiw1 on 5/10/16.
 */
public class Route {
    private String routeId;
    private String direction;

    public Route(String routeId, String direction) {
        setRouteId(routeId);
        setRouteId(direction);
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
