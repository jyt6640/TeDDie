package TeDDie.domain;

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
}
