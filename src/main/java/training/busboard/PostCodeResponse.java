package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostCodeResponse {

    public Result result;

}

@JsonIgnoreProperties(ignoreUnknown = true)
class Result {
    public float longitude;
    public float latitude;
}
