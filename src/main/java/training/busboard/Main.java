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

        QueryAPI queryAPI = new QueryAPI();
        PostCodeResponse postCodeResponse = queryAPI.QueryPostCode(postCode);

        // extract lat and long
        float lat = postCodeResponse.result.latitude;
        float lon = postCodeResponse.result.longitude;

        RadiusResponse radiusResponse = queryAPI.QueryRadius(lat, lon);

        radiusResponse.stopPoints.stream().sorted(Comparator.comparingDouble(r->r.distance)).limit(2).forEach(stopData -> {
            StopDataResponse[] stopDataResponses = queryAPI.QueryStop(stopData.naptanId);
            Stream<StopDataResponse> busStream = Arrays.stream(stopDataResponses);
            System.out.println("At bus stop " + stopDataResponses[0].stationName);
            busStream.sorted(Comparator.comparingInt(r -> r.timeToStation)).limit(5).forEach(StopDataResponse::print);
        });
    }
}	
