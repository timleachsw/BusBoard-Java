package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class TflClient {
    private static final String API_URL = "https://api.tfl.gov.uk";
    private static final String ARRIVALS_PATH = "StopPoint/{stopId}/Arrivals";

    private Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();

    public List<ArrivalPrediction> getArrivalPredictions(String stopId) {
         return client.target(API_URL)
                 .path(ARRIVALS_PATH)
                 .resolveTemplate("stopId", stopId)
                 .request(MediaType.APPLICATION_JSON_TYPE)
                 .get(new GenericType<List<ArrivalPrediction>>() {});
    }
}
