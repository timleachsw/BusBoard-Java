package training.busboard.client;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PostcodeClient extends ApiClient {
    private static final String POSTCODE_PATH = "postcodes/{postcode}";

    public Coordinates getCoordinatesForPostCode(String postcode) {
        Response response = getClient()
                .path(POSTCODE_PATH)
                .resolveTemplate("postcode", postcode)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            return null;
        }

        return response
                .readEntity(PostcodeResult.class)
                .getResult();
    }

    @Override
    protected String getApiUrl() {
        return "https://api.postcodes.io";
    }

    private static class PostcodeResult {
        private Coordinates result;

        public Coordinates getResult() {
            return result;
        }
    }
}
