package training.busboard.client;

import javax.ws.rs.core.MediaType;
import java.util.List;

public class TransportClient extends ApiClient {

    private static final String APP_ID = "<app ID goes here>";
    private static final String APP_KEY = "<app key goes here>";

    @Override
    protected String getApiUrl() {
        return "http://transportapi.com";
    }

    public List<StopPoint> getStopsNear(Coordinates coordinates) {
        return getClient()
                .path("/v3/uk/places.json")
                .queryParam("type", "bus_stop")
                .queryParam("lat", coordinates.getLatitude())
                .queryParam("lon", coordinates.getLongitude())
                .queryParam("app_id", APP_ID)
                .queryParam("app_key", APP_KEY)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(NearbyStopsResult.class)
                .getStopPoints();
    }

    public List<ArrivalPrediction> getArrivalPredictions(String atcocode) {
        return getClient()
                .path("/v3/uk/bus/stop/" + atcocode + "/live.json")
                .queryParam("app_id", APP_ID)
                .queryParam("app_key", APP_KEY)
                .queryParam("group", "no")
                .queryParam("limit", 5)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(NextBusesResult.class)
                .getArrivalPredictions();
    }

    private static class NearbyStopsResult {
        public List<StopPoint> member;

        private List<StopPoint> getStopPoints() {
            return member;
        }
    }

    private static class NextBusesResult {
        public Departures departures;

        private List<ArrivalPrediction> getArrivalPredictions() {
            return departures.all;
        }
    }

    private static class Departures {
        public List<ArrivalPrediction> all;
    }
}
