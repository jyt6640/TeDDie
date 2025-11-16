package TeDDie.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserPromptTest {
    @DisplayName("Topic과 Difficulty로 UserPrompt 생성")
    @Test
    void Topic과_Difficulty로_UserPrompt_생성() {
        //given
        Topic topic = new Topic("random");
        Difficulty difficulty = Difficulty.from("easy");

        //when&then
        assertThatCode(() -> new UserPrompt(topic, difficulty))
                .doesNotThrowAnyException();
    }

    @DisplayName("프롬프트를 포함하여 생성")
    @Test
    void 프롬프트를_포함하여_생성() {
        //given
        Topic topic = new Topic("random");
        Difficulty difficulty = Difficulty.from("easy");

        //when
        UserPrompt result = new UserPrompt(topic, difficulty);

        //then
        assertThat(result.getContent()).isEqualTo("""
                - 주제: random
                - 난이도: easy
                """);
    }
}
