package training.busboard.client;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class TflClient extends ApiClient {
    private static final String ARRIVALS_PATH = "StopPoint/{stopId}/Arrivals";
    private static final String STOP_POINT_PATH = "StopPoint";

    public List<ArrivalPrediction> getArrivalPredictions(String stopId) {
        return getClient()
                .path(ARRIVALS_PATH)
                .resolveTemplate("stopId", stopId)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<ArrivalPrediction>>() {});
    }

    public List<StopPoint> getStopsNear(Coordinates coordinates) {
        return getClient()
                .path(STOP_POINT_PATH)
                .queryParam("stopTypes", "NaptanPublicBusCoachTram")
                .queryParam("lat", coordinates.getLatitude())
                .queryParam("lon", coordinates.getLongitude())
                .queryParam("radius", 1000)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(StopPointResult.class)
                .getStopPoints();
    }

    @Override
    protected String getApiUrl() {
        return "https://api.tfl.gov.uk";
    }

    private static class StopPointResult {
        private List<StopPoint> stopPoints;

        public List<StopPoint> getStopPoints() {
            return stopPoints;
        }
    }
}
