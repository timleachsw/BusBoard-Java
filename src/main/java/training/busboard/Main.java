package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String args[]) {
        // get bus code
        if (args.length != 1) {
            System.out.println("usage: main [postcode]");
            System.out.println("eg:    main SL97JQ");
            return;
        }
        String postCode = args[0];

        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        PostCodeResponse postCodeResponse = client.target(String.format("https://api.postcodes.io/postcodes/%s",postCode))
                .request(MediaType.APPLICATION_JSON)
                .get(PostCodeResponse.class);

        // extract lat and long
        float lat = postCodeResponse.result.latitude;
        float lon = postCodeResponse.result.longitude;

        RadiusResponse radiusResponse = client.target(String.format("https://api.tfl.gov.uk/StopPoint?stopTypes=NaptanPublicBusCoachTram&radius=1000&modes=bus&lat=%f&lon=%f",
                lat, lon))
                .request(MediaType.APPLICATION_JSON)
                .get(RadiusResponse.class);

        // extract bus code
        for (int i = 0; i < 2; i ++) {
            String busCode = radiusResponse.stopPoints.get(i).naptanId;

            StopDataResponse[] stopDataResponses = client.target(String.format("https://api.tfl.gov.uk/StopPoint/%s/Arrivals",
                    busCode))
                    .request(MediaType.APPLICATION_JSON)
                    .get(StopDataResponse[].class);

            Stream<StopDataResponse> busStream = Arrays.stream(stopDataResponses);
            System.out.println("At bus stop " + stopDataResponses[0].stationName);
            busStream.sorted(Comparator.comparingInt(r -> r.timeToStation)).limit(5).forEach(r -> r.print());
        }

    }
}	
