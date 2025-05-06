import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;

public class App {
    private static final String APP_TOKEN = "aVp6dsWoHxQeA4FQPU08f4S6O";
    private static final String BASE_URL = "https://data.cityofchicago.org/resource/ijzp-q8t2.json/";

    public static void main(String[] args) {
        try {
            String select = URLEncoder.encode("block,count(*) as count", StandardCharsets.UTF_8);
            String where = URLEncoder.encode("year=2024", StandardCharsets.UTF_8);
            String group = URLEncoder.encode("block", StandardCharsets.UTF_8);
            String order = URLEncoder.encode("count DESC", StandardCharsets.UTF_8);
            String limit = URLEncoder.encode("10", StandardCharsets.UTF_8);

            String queryUrl = BASE_URL + "?" +
                    "$select=" + select +
                    "&$where=" + where +
                    "&$group=" + group +
                    "&$order=" + order +
                    "&$limit=" + limit;
            
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(queryUrl))
                            .header("Accept", "application/json")
                            .header("X-App-Token", APP_TOKEN)
                            .GET()
                            .build();
            
            // Zeitmessung starten
            Instant start = Instant.now();
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Zeitmessung beenden
            Instant end = Instant.now();
            Duration duration = Duration.between(start, end);
            System.out.println("Download-Zeit: " + duration.toMillis() + " ms");

            // Debug-Ausgabe
            System.out.println("HTTP Status: " + response.statusCode());

            String responseBody = response.body();

            if (response.statusCode() == 200) {
                if (responseBody.trim().startsWith("[")) {
                    JSONArray jsonArray = new JSONArray(responseBody);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject crime = jsonArray.getJSONObject(i);
                        String block = crime.optString("block", "Unbekannt");
                        int count = crime.optInt("count", 0);
                        System.out.println((i + 1) + ". " + block + " - " + count + " Verbrechen");
                    }
                } else {
                    System.err.println("Antwort war kein JSON-Array:\n" + responseBody);
                }
            } else {
                System.err.println("Fehler beim Abrufen der Daten. HTTP-Code: " + response.statusCode());
                System.err.println(responseBody);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}