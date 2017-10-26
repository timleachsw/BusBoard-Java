package training.busboard.web;

import java.util.List;

public class BusInfo {
    private final String postcode;
    private final List<StopWithArrivals> stops;

    public BusInfo(String postcode, List<StopWithArrivals> stops) {
        this.postcode = postcode;
        this.stops = stops;
    }

    public String getPostcode() {
        return postcode;
    }

    public List<StopWithArrivals> getStops() {
        return stops;
    }
}
