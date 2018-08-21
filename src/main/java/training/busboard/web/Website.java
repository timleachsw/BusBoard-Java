package training.busboard.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import training.busboard.client.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@EnableAutoConfiguration
public class Website {

    private final PostcodeClient postcodeClient = new PostcodeClient();
    private final TransportClient transportClient = new TransportClient();

    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping("/busInfo")
    ModelAndView busInfo(@RequestParam("postcode") String postcode) {
        Coordinates coordinates = postcodeClient.getCoordinatesForPostCode(postcode);
        List<StopWithArrivals> stops = transportClient.getStopsNear(coordinates).stream()
                .sorted(Comparator.comparing(StopPoint::getDistance))
                .limit(2).map(stopPoint -> new StopWithArrivals(
                        stopPoint.getName(),
                        getSortedPredictions(stopPoint.getAtcocode())))
                .collect(Collectors.toList());
        return new ModelAndView("info", "busInfo", new BusInfo(postcode, stops));
    }

    private List<ArrivalPrediction> getSortedPredictions(String naptanId) {
        List<ArrivalPrediction> predictions = transportClient.getArrivalPredictions(naptanId);
        predictions.sort(Comparator.comparing(ArrivalPrediction::getBestDepartureEstimate));
        return predictions;
    }

    public static void main(String[] args) {
        SpringApplication.run(Website.class, args);
    }
}
