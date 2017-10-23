package training.busboard;

import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public abstract class ApiClient {

    private Client client = ClientBuilder.newBuilder()
            .register(ObjectMapperProvider.class)
            .register(JacksonFeature.class)
            .build();

    protected WebTarget getClient() {
        return client.target(getApiUrl());
    }

    protected abstract String getApiUrl();
}
