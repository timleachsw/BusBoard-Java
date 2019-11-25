package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopDataResponse {
    public int timeToStation;
    public String destinationName;
    public String expectedArrival;
    public String lineName;
    public String stationName;

    public void print() {
        System.out.println("Bus " + lineName + " will arrive at " + expectedArrival);
    }
}
