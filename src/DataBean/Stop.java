package DataBean;

import com.google.gson.annotations.SerializedName;

/**
 * @author Siqi Wang siqiw1 on 5/10/16.
 */
public class Stop implements Comparable<Stop> {
    @SerializedName("stpid")
    private String stopId;

    @SerializedName("stpnm")
    private String stopName;

    @SerializedName("lat")
    private double latitude;

    @SerializedName("lon")
    private double longitude;

    private double distance;

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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }


    @Override
    public int compareTo(Stop o) {
        return this.distance - o.distance > 0 ? 1 : -1;
    }
}
