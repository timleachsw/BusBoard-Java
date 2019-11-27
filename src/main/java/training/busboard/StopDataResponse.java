package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopDataResponse {
    public int timeToStation;
    public String destinationName;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime expectedArrival;
    public String lineName;
    public String stationName;

    public void print() {
        System.out.println("Bus " + lineName + " will arrive at " + expectedArrival);
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return expectedArrival.format(formatter);
    }
}

class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser jp,
                                     DeserializationContext dc) throws IOException, JsonProcessingException {
        ObjectCodec codec = jp.getCodec();
        TextNode node = (TextNode)codec.readTree(jp);
        String dateString = node.textValue();
        Instant instant = Instant.parse(dateString);
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return dateTime;
    }

}
