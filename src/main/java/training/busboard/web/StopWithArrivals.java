package training.busboard.web;

import training.busboard.client.ArrivalPrediction;

import java.util.List;

public class StopWithArrivals {
    private final String name;
    private final List<ArrivalPrediction> arrivals;

    public StopWithArrivals(String name, List<ArrivalPrediction> arrivals) {
        this.name = name;
        this.arrivals = arrivals;
    }

    public String getName() {
        return name;
    }

    public List<ArrivalPrediction> getArrivals() {
        return arrivals;
    }
}
