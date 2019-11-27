package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RadiusResponse {
    public ArrayList<StopPoint> stopPoints;

}

@JsonIgnoreProperties(ignoreUnknown = true)
class StopPoint {
    public String naptanId;
    public float distance;
}
