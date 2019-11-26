package training.busboard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GetBuses {
    private int nStops;
    private int nBuses;

    public GetBuses(int nStops, int nBuses) {
        this.nStops = nStops;
        this.nBuses = nBuses;
    }

    public List<List<StopDataResponse>> getBusesNear(String postCode) {
        QueryAPI queryAPI = new QueryAPI();
        PostCodeResponse postCodeResponse = queryAPI.QueryPostCode(postCode);

        // extract lat and long
        float lat = postCodeResponse.result.latitude;
        float lon = postCodeResponse.result.longitude;

        RadiusResponse radiusResponse = queryAPI.QueryRadius(lat, lon);

        return radiusResponse.stopPoints.stream().sorted(Comparator.comparingDouble(r->r.distance)).limit(nStops).map(stopData -> {
            StopDataResponse[] stopDataResponses = queryAPI.QueryStop(stopData.naptanId);
            Stream<StopDataResponse> busStream = Arrays.stream(stopDataResponses);
            return busStream.sorted(Comparator.comparingInt(r -> r.timeToStation)).limit(nBuses).collect(Collectors.toList());
        }).collect(Collectors.toList());
    }
}
