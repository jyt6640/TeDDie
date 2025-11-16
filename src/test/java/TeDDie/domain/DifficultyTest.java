package TeDDie.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DifficultyTest {
    @DisplayName("easy_문자열로_쉬움_난이도_생성")
    @Test
    void easy_문자열로_쉬움_난이도_생성() {
        //given
        String input = "easy";

        //when
        Difficulty difficulty = Difficulty.from(input);

        //then
        assertThat(difficulty).isEqualTo(Difficulty.EASY);
    }

    @DisplayName("medium_문자열로_중간_난이도_생성")
    @Test
    void medium_문자열로_쉬움_난이도_생성() {
        //given
        String input = "medium";

        //when
        Difficulty difficulty = Difficulty.from(input);

        //then
        assertThat(difficulty).isEqualTo(Difficulty.MEDIUM);
    }

    @DisplayName("hard_문자열로_어려움_난이도_생성")
    @Test
    void hard_문자열로_쉬움_난이도_생성() {
        //given
        String input = "HARD";

        //when
        Difficulty difficulty = Difficulty.from(input);

        //then
        assertThat(difficulty).isEqualTo(Difficulty.HARD);
    }
}
