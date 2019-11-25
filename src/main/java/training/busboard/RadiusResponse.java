package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RadiusResponse {
    public ArrayList<StopPoint> stopPoints;

}

@JsonIgnoreProperties(ignoreUnknown = true)
class StopPoint {
    public String naptanId;
    public float distance;
}
