package DataBean;

/**
 * @author Siqi Wang siqiw1 on 5/10/16.
 */
public class RouteOfStop {
    private String stopId;
    private String stopName;
    private String direction;
    private String routeId;
    private double latitude;
    private double longitude;

    public RouteOfStop(String stopId) {
        setStopId(stopId);
    }

    public RouteOfStop(String stopId, String stopName, String direction, String routeId, double latitude, double longitude) {
        this.stopId = stopId;
        this.stopName = stopName;
        this.direction = direction;
        this.routeId = routeId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
