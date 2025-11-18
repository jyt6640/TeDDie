package teddie.generator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import teddie.service.TestCase;

public class TestGeneratorTest {
    @DisplayName("기능 테스트와 예외 테스트가 포함된 ApplicationTest 생성")
    @Test
    void 기능_테스트와_예외_테스트가_포함된_ApplicationTest_생성() {
        //given
        TestGenerator testGenerator = new TestGenerator();
        String packageName = "racingcar";
        List<TestCase> testCases = List.of(
                new TestCase("기능_테스트", "기능 테스트", "pobi,woni\\n1", "pobi : -", false),
                new TestCase("예외_테스트", "예외 테스트", "pobi,javaji\\n1", "", true)
        );

        //when
        String testCode = testGenerator.generateTestCode(packageName, testCases);

        //then
        assertThat(testCode).contains("package racingcar;");
        assertThat(testCode).contains("class ApplicationTest extends NsTest");
        assertThat(testCode).contains("@Test");
        assertThat(testCode).contains("void 기능_테스트()");
        assertThat(testCode).contains("void 예외_테스트()");
        assertThat(testCode).contains("run(\"pobi,woni\\n1\")");
        assertThat(testCode).contains("runException(\"pobi,javaji\\n1\")");
    }
}
