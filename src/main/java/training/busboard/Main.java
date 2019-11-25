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
            System.out.println("usage: main [bus-code]");
            System.out.println("eg:    main 490008660N");
            return;
        }
        String busCode = args[0];

        Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
        StopDataResponse[] response = client.target("https://api.tfl.gov.uk/StopPoint/490008660N/Arrivals")
                .request(MediaType.APPLICATION_JSON)
                .get(StopDataResponse[].class);

        Stream<StopDataResponse> busStream = Arrays.stream(response);
        busStream.sorted(Comparator.comparingInt(r -> r.timeToStation)).limit(5).forEach(r -> r.print());





    }
}	
