package training.busboard;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        String id = promptForStopId();

        List<ArrivalPrediction> arrivalPredictions = new TflClient().getArrivalPredictions(id);

        arrivalPredictions.stream().sorted(Comparator.comparing(ArrivalPrediction::getTimeToStation)).limit(5).forEach(prediction ->
                System.out.println(formatPrediction(prediction))
        );
    }

    private static String promptForStopId() {
        System.out.print("Enter your stop ID: ");
        // Example: 490008660N
        return new Scanner(System.in).nextLine();
    }

    private static String formatPrediction(ArrivalPrediction prediction) {
        return String.format("%d minutes: %s to %s",
                prediction.getTimeToStation() / 60,
                prediction.getLineName(),
                prediction.getDestinationName());
    }
}	
