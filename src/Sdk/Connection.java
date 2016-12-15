package Sdk;
// Denne klasse opretter forbindelse til serveren så klienten kan tilføje, opdatere, slette og skaffe sig adgang til informationer
import com.sun.jersey.api.client.*;


public class Connection {

    static Client client = Client.create();

// denne metode er med til at hente bestemte informationer ned fra serveren
    public static ClientResponse get(String path) {

        ClientResponse clientResponse = null;
        try {
            WebResource webResource = client
                    .resource("http://localhost:8080/server2_0_war_exploded/") // serverUrl
                    .path(path);

            clientResponse = webResource.accept("application/json").get(ClientResponse.class);
        } catch (UniformInterfaceException | ClientHandlerException e) {
            e.printStackTrace();
        }
        return clientResponse;
    }
// denne metode bruger jeg, når jeg opretter en bruger. Den opretter forbindelse til servern, når der er en bruger, som skal gemmes i databsen
    public static ClientResponse post(String token, String path, String json) {
        ClientResponse clientResponse = null;
        try {
            WebResource webResource = client
                    .resource("http://localhost:8080/server2_0_war_exploded/") // ServerURL
                    .path(path);

            clientResponse = webResource.accept("application/json").post(ClientResponse.class, json);

        } catch (UniformInterfaceException | ClientHandlerException e) {
            e.printStackTrace();
        }
        return clientResponse;
    }
    // denne metode skaber forbindelse til serveren, som ændrer en bruger, baseret på brugerens token
        public static ClientResponse put(String token, String path, String json) {

            ClientResponse clientResponse = null;
            try {
                WebResource webResource = client
                        .resource("http://localhost:8080/server2_0_war_exploded/")
                        .path(path);

                clientResponse = webResource.accept("application/json").header("authorization", token).put(ClientResponse.class, json);
            } catch (UniformInterfaceException | ClientHandlerException e) {
                e.printStackTrace();
            }
            return clientResponse;
        }


// denne metode skaber forbindelse til serveren, som sletter en bruger baseret på den token, brugeren har
    public static ClientResponse delete(String token, String path) {

        ClientResponse clientResponse = null;
        try {
            WebResource webResource = client
                    .resource("http://localhost:8080/server2_0_war_exploded/") // ServerURL
                    .path(path);

            clientResponse = webResource.accept("application/json").header("authorization", token).delete(ClientResponse.class);

        } catch (UniformInterfaceException | ClientHandlerException e) {
            e.printStackTrace();
        }
        return clientResponse;
    }


}