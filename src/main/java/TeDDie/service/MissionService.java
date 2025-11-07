package TeDDie.service;

import TeDDie.api.HttpRequestSender;
import TeDDie.api.RequestBodyBuilder;
import com.google.gson.Gson;

public class MissionService {
    private static final String PROMPT_TEMPLATE = """
            당신은 TDD 연습 문제를 출제하는 AI 튜터입니다.
            Java 프로그래밍 21버전의 환경을 기준으로 합니다.
            
            다음 요구사항에 맞는 마크다운 형식의 TDD 과제를 생성해주세요.
            
            - 주제: %s
            - 난이도: %s
            """;

    private final HttpRequestSender sender;
    private final RequestBodyBuilder builder;
    private final Gson gson;

    public MissionService(HttpRequestSender sender, RequestBodyBuilder builder) {
        this.sender = sender;
        this.builder = builder;
        this.gson = new Gson();
    }

    public String generateMission(String topic, String difficulty) throws Exception {
        String prompt = String.format(PROMPT_TEMPLATE, topic, difficulty);
        String requestJson = builder.createJSONBody(prompt);
        String responseJson = sender.post("https://localhost:1234/v1/", requestJson);
        return parseContentFromResponse(responseJson);
    }

    public String parseContentFromResponse(String responseJson) {
        ApiResponse response = gson.fromJson(responseJson, ApiResponse.class);
        return response.choices().get(0).message().content();
    }
}
