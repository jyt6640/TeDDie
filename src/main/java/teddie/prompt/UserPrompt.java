package teddie.prompt;

import teddie.api.dto.RagResult;
import teddie.domain.Difficulty;
import teddie.domain.Topic;
import java.util.List;

public class UserPrompt {
    private static final String TEMPLATE = """
            - 주제: %s
            - 난이도: %s
            
            참고할 우테코 과제 예시
            %s
            
            위 예시를 참고하여 비슷한 스타일의 TDD 연습 문제를 생성해주세요.
            """;

    private final String content;

    public UserPrompt(Topic topic, Difficulty difficulty, List<RagResult> ragResult) {
        this.content = String.format(
                TEMPLATE,
                topic.getValue(),
                difficulty.getValue(),
                buildRagContext(ragResult)
        );
    }

    private String buildRagContext(List<RagResult> ragResults) {
        if (ragResults.isEmpty()) {
            return "(참고 자료 없음)";
        }

        StringBuilder ragResult = new StringBuilder();
        for (int i = 0; i < ragResults.size(); i++) {
            ragResult.append(ragResults.get(i).buildFormat(i + 1));
        }
        return ragResult.toString();
    }

    public String getContent() {
        return content;
    }
}
