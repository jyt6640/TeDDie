// src/test/java/teddie/prompt/UserPromptTest.java
package teddie.prompt;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import teddie.api.dto.RagResult;
import teddie.domain.Difficulty;
import teddie.domain.Topic;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserPromptTest {
    @DisplayName("Topic과 Difficulty로 UserPrompt 생성")
    @Test
    void Topic과_Difficulty로_UserPrompt_생성() {
        //given
        Topic topic = new Topic("random");
        Difficulty difficulty = Difficulty.from("easy");
        List<RagResult> ragResults = List.of(
                new RagResult("repo1", "text1", "url1", 0.9)
        );

        //when&then
        assertThatCode(() -> new UserPrompt(topic, difficulty, ragResults))
                .doesNotThrowAnyException();
    }

    @DisplayName("RAG 결과를 포함하여 생성")
    @Test
    void RAG_결과를_포함하여_생성() {
        //given
        Topic topic = new Topic("random");
        Difficulty difficulty = Difficulty.from("easy");
        List<RagResult> ragResults = List.of(
                new RagResult("repo1", "text1", "url1", 0.9)
        );

        //when&then
        assertThatCode(() -> new UserPrompt(topic, difficulty, ragResults))
                .doesNotThrowAnyException();
    }

    @DisplayName("RAG 결과를 포함하여 프롬프트 생성")
    @Test
    void RAG_결과를_포함하여_프롬프트_생성() {
        //given
        Topic topic = new Topic("random");
        Difficulty difficulty = Difficulty.from("easy");
        List<RagResult> ragResults = List.of(
                new RagResult("java-racingcar-6", "# 미션 - 자동차 경주\n자동차 경주 게임을 구현한다.", "url1", 0.9)
        );

        //when
        UserPrompt result = new UserPrompt(topic, difficulty, ragResults);

        //then
        String content = result.getContent();
        assertThat(content).contains("주제: random");
        assertThat(content).contains("난이도: easy");
        assertThat(content).contains("[예시 1: java-racingcar-6]");
    }

    @DisplayName("RAG 결과가 비어있으면 참고 자료 없음 표시")
    @Test
    void RAG_결과가_비어있으면_참고_자료_없음_표시() {
        //given
        Topic topic = new Topic("random");
        Difficulty difficulty = Difficulty.from("easy");
        List<RagResult> emptyRagResults = List.of();

        //when
        UserPrompt result = new UserPrompt(topic, difficulty, emptyRagResults);

        //then
        String content = result.getContent();
        assertThat(content).contains("참고 자료 없음");
    }
}