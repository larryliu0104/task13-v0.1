package DataBean;

import com.google.gson.annotations.SerializedName;

/**
 * @author Siqi Wang siqiw1 on 5/10/16.
 */
public class Bus implements Comparable<Bus> {
    @SerializedName("rtdd")
    private String routeId;

    @SerializedName("tmstmp")
    private String currentTime;

    @SerializedName("prdtm")
    private String predictTime;

    @SerializedName("rtdir")
    private String direction;

    private long waitTime;

    public Bus(String routeId) {
        setRouteId(routeId);
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getPredictTime() {
        return predictTime;
    }

    public void setPredictTime(String predictTime) {
        this.predictTime = predictTime;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public long getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }

    @Override
    public int compareTo(Bus o) {
        return this.waitTime - o.waitTime > 0 ? 1 : -1;
    }


}
