package teddie.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestCaseTest {
    @DisplayName("기능 테스트 케이스 생성")
    @Test
    void 기능_테스트_케이스_생성() {
        //given
        String name = "기능_테스트";
        String displayName = "기능 테스트";
        String input = "1000\\n";
        String expectedOutput = "[8, 21, 23, 41, 42, 43]";
        boolean expectError = false;

        //when
        TestCase testCase = new TestCase(name, displayName, input, expectedOutput, expectError);

        //then
        assertThat(testCase.name()).isEqualTo("기능_테스트");
        assertThat(testCase.displayName()).isEqualTo("기능 테스트");
        assertThat(testCase.input()).isEqualTo("1000\\n");
        assertThat(testCase.expectedOutput()).isEqualTo("[8, 21, 23, 41, 42, 43]");
        assertThat(testCase.expectError()).isFalse();
    }

    @DisplayName("예외 테스트 케이스 생성")
    @Test
    void 예외_테스트_케이스_생성() {
        //given
        String name = "예외_테스트";
        String displayName = "예외 테스트";
        String input = "abc\\n";
        boolean expectError = true;

        //when
        TestCase testCase = new TestCase(name, displayName, input, "", expectError);

        //then
        assertThat(testCase.name()).isEqualTo("예외_테스트");
        assertThat(testCase.displayName()).isEqualTo("예외 테스트");
        assertThat(testCase.input()).isEqualTo("abc\\n");
        assertThat(testCase.expectError()).isTrue();
    }
}
