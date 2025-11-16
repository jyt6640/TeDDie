package TeDDie.domain;

public class UserPrompt {
    private static final String TEMPLATE = """
            - 주제: %s
            - 난이도: %s
            """;

    private final String content;

    public UserPrompt(Topic topic, Difficulty difficulty) {
        this.content = String.format(
                TEMPLATE,
                topic.getValue(),
                difficulty.getValue()
        );
    }

    public String getContent() {
        return content;
    }
}
