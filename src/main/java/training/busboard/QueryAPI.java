package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

public class QueryAPI {
    public Client client;

    public QueryAPI() {
        client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
    }

    public PostCodeResponse QueryPostCode(String postCode) {
        return client.target(String.format("https://api.postcodes.io/postcodes/%s", postCode))
                .request(MediaType.APPLICATION_JSON)
                .get(PostCodeResponse.class);
    }

    public RadiusResponse QueryRadius(float lat, float lon) {
        return client.target(String.format("https://api.tfl.gov.uk/StopPoint?stopTypes=NaptanPublicBusCoachTram&radius=1000&modes=bus&lat=%f&lon=%f",
                lat, lon))
                .request(MediaType.APPLICATION_JSON)
                .get(RadiusResponse.class);
    }

    public StopDataResponse[] QueryStop(String stopCode) {
        return client.target(String.format("https://api.tfl.gov.uk/StopPoint/%s/Arrivals",
                stopCode))
                .request(MediaType.APPLICATION_JSON)
                .get(StopDataResponse[].class);
    }
}
