package training.busboard.client;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ArrivalPrediction {
    private String lineName;
    private String direction;
    private String bestDepartureEstimate;

    public String getLineName() {
        return lineName;
    }

    public String getDirection() {
        return direction;
    }

    public String getBestDepartureEstimate() {
        return bestDepartureEstimate;
    }
}
