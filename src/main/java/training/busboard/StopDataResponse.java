package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopDataResponse {
    public int timeToStation;
    public String destinationName;
    public String expectedArrival;
    public String lineName;

    public void print() {
        System.out.println(lineName);
    }
}
