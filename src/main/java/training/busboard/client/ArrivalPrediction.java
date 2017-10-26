package training.busboard.client;

public class ArrivalPrediction {
    private String lineName;
    private String destinationName;
    private int timeToStation;

    private ArrivalPrediction() {}

    public String getLineName() {
        return lineName;
    }
    public String getDestinationName() {
        return destinationName;
    }

    public int getTimeToStation() {
        return timeToStation;
    }
}
