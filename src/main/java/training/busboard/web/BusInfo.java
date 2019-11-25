package training.busboard.web;

import training.busboard.GetBuses;
import training.busboard.PostCodeResponse;
import training.busboard.QueryAPI;
import training.busboard.StopDataResponse;

import java.util.List;
import java.util.stream.Stream;

public class BusInfo {
    private final String postcode;
    private final List<List<StopDataResponse>> result;

    public List<List<StopDataResponse>> getResult() {
        return result;
    }

    public BusInfo(String postcode) {
        this.postcode = postcode;
        GetBuses getBuses = new GetBuses(2, 5);
        this.result = getBuses.getBusesNear(postcode);
    }

    public String getPostcode() {
        return postcode;
    }
}
