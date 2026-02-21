import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherApiClient {

    public static void main(String[] args) {
        try {
            // Open-Meteo API URL (Bengaluru coordinates)
            String apiUrl =
                    "https://api.open-meteo.com/v1/forecast" +
                    "?latitude=12.97&longitude=77.59&current_weather=true";

            // Create HTTP client
            HttpClient client = HttpClient.newHttpClient();

            // Create HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .build();

            // Send request and get response
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse JSON response
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.body());

            JsonNode currentWeather = rootNode.get("current_weather");

            // Display structured output
            System.out.println("====== Current Weather Report ======");
            System.out.println("Location       : Bengaluru");
            System.out.println("Temperature    : " +
                    currentWeather.get("temperature").asText() + " °C");
            System.out.println("Wind Speed     : " +
                    currentWeather.get("windspeed").asText() + " km/h");
            System.out.println("Wind Direction : " +
                    currentWeather.get("winddirection").asText() + "°");
            System.out.println("Observation Time: " +
                    currentWeather.get("time").asText());
            System.out.println("====================================");

        } catch (Exception e) {
            System.err.println("Error fetching weather data");
            e.printStackTrace();
        }
    }
}
