package training.busboard;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final TflClient tflClient = new TflClient();
    private static final PostcodeClient postcodeClient = new PostcodeClient();

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            showPrompt();

            if (!scanner.hasNextLine()) {
                return;
            }

            String postcode = scanner.nextLine();
            Coordinates coordinates = postcodeClient.getCoordinatesForPostCode(postcode);

            if (coordinates == null) {
                System.out.println("Could not find postcode");
                continue;
            }

            List<StopPoint> nearbyStops = tflClient.getStopsNear(coordinates);

            if (nearbyStops.isEmpty()) {
                System.out.println("No stops near this postcode");
                continue;
            }

            nearbyStops.stream().limit(2).forEach(Main::displayDepartureBoard);
        }
    }

    private static void showPrompt() {
        System.out.print("Enter your postcode: "); // Example: NW5 1TL
    }

    private static void displayDepartureBoard(StopPoint stop) {
        System.out.println("Departure board for " + stop.getCommonName() + ":");
        List<ArrivalPrediction> arrivalPredictions = tflClient.getArrivalPredictions(stop.getNaptanId());

        if (arrivalPredictions.size() == 0) {
            System.out.println("None");
            System.out.println();
        }

        arrivalPredictions.stream().sorted(Comparator.comparing(ArrivalPrediction::getTimeToStation)).limit(5).forEach(prediction ->
                System.out.println(formatPrediction(prediction))
        );

        System.out.println();
    }

    private static String formatPrediction(ArrivalPrediction prediction) {
        return String.format("%d minutes: %s to %s",
                prediction.getTimeToStation() / 60,
                prediction.getLineName(),
                prediction.getDestinationName());
    }
}	
