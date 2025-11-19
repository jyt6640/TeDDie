package teddie.common.config;

import io.github.cdimascio.dotenv.Dotenv;

public class ApiConfig {
    private static final String DEFAULT_LM_STUDIO_URL = "http://localhost:1234/v1/chat/completions";
    private static final String DEFAULT_RAG_API_URL = "http://localhost:8000/api/search";

    private final String lmStudioUrl;
    private final String ragApiUrl;

    public ApiConfig() {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        this.lmStudioUrl = dotenv.get("LM_STUDIO_URL", DEFAULT_LM_STUDIO_URL);
        this.ragApiUrl = dotenv.get("RAG_API_URL", DEFAULT_RAG_API_URL);
    }

    public String getLmStudioUrl() {
        return lmStudioUrl;
    }

    public String getRagApiUrl() {
        return ragApiUrl;
    }
}