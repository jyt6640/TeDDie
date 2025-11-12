package TeDDie.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class RagClient {
    private static final String RAG_API_URL = "http://localhost:8000/api/search";

    private final HttpRequestSender sender;
    private final Gson gson;

    public RagClient(HttpRequestSender sender) {
        this.sender = sender;
        this.gson = new Gson();
    }

    public List<RagResult> search(String query, int topK) throws Exception {
        String requestBody = buildSearchRequset(query, topK);
        String responseJson = sender.post(RAG_API_URL, requestBody);
        return parseSearchResponse(responseJson);
    }

    private String buildSearchRequset(String query, int topK) throws Exception {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("query", query);
        requestBody.addProperty("topK", topK);
        return gson.toJson(requestBody);
    }

    private List<RagResult> parseSearchResponse(String responseJson) throws Exception {
        JsonObject response = gson.fromJson(responseJson, JsonObject.class);
        JsonArray results = response.getAsJsonArray("results");

        List<RagResult> ragResults = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            JsonObject item = results.get(i).getAsJsonObject();
            ragResults.add(new RagResult(
                    item.get("repo").getAsString(),
                    item.get("text").getAsString(),
                    item.get("url").getAsString(),
                    item.get("similarity_score").getAsDouble()
            ));
        }
        return ragResults;
    }
}
