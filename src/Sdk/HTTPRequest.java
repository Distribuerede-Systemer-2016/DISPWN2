package Sdk;

import com.sun.jersey.api.client.*;

public class HTTPRequest {
    static Client client = Client.create();

    public static ClientResponse get(String path) {

        ClientResponse clientResponse = null;
        try {
            WebResource webResource = client
                    .resource("http://localhost:8080/server2_0_war_exploded/")
//                    .resource(Config.getServerUrl())//det kommer fra config
                    .path(path); //book

            clientResponse = webResource.accept("application/json").get(ClientResponse.class);
        } catch (UniformInterfaceException | ClientHandlerException e) {
            e.printStackTrace();
        }
        return clientResponse;
    }

    public static ClientResponse post(String token, String path, String json) {
        ClientResponse clientResponse = null;
        try {
            WebResource webResource = client
                    .resource("http://localhost:8080/server2_0_war_exploded/")
//
                    .path(path);

            clientResponse = webResource.accept("application/json").post(ClientResponse.class, json);

        } catch (UniformInterfaceException | ClientHandlerException e) {
            e.printStackTrace();
        }
        return clientResponse;
    }

}
