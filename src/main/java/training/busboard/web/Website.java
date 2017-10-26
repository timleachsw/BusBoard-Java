package training.busboard.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import training.busboard.client.ArrivalPrediction;
import training.busboard.client.Coordinates;
import training.busboard.client.PostcodeClient;
import training.busboard.client.TflClient;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@EnableAutoConfiguration
public class Website {

    private final PostcodeClient postcodeClient = new PostcodeClient();
    private final TflClient tflClient = new TflClient();

    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping("/busInfo")
    ModelAndView busInfo(@RequestParam("postcode") String postcode) {
        Coordinates coordinates = postcodeClient.getCoordinatesForPostCode(postcode);
        List<StopWithArrivals> stops = tflClient.getStopsNear(coordinates).stream().limit(2).map(stopPoint ->
                new StopWithArrivals(stopPoint.getCommonName(),
                        getSortedPredictions(stopPoint.getNaptanId()))).collect(Collectors.toList()
        );
        return new ModelAndView("info", "busInfo", new BusInfo(postcode, stops));
    }

    private List<ArrivalPrediction> getSortedPredictions(String naptanId) {
        List<ArrivalPrediction> predictions = tflClient.getArrivalPredictions(naptanId);
        predictions.sort(Comparator.comparing(ArrivalPrediction::getTimeToStation));
        return predictions;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Website.class, args);
    }

}