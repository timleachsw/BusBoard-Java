package training.busboard.web;

import training.busboard.GetBuses;
import training.busboard.StopDataResponse;

import java.util.List;

public class BusInfo {
    private final String postcode;
    private final List<List<StopDataResponse>> result;

    public List<List<StopDataResponse>> getResult() {
        return result;
    }

    public BusInfo(String postcode) {
        this.postcode = formatPostcode(postcode);
        GetBuses getBuses = new GetBuses(2, 5);
        this.result = getBuses.getBusesNear(postcode);
    }

    public String getPostcode() {
        return postcode;
    }

    public String formatPostcode(String postcode) {
        postcode = postcode.toUpperCase().replaceAll("\\s+","");
        int n = postcode.length();
        return postcode.substring(0, n-3) + " " + postcode.substring(n-3);
    }
}
