package TeDDie.generator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class ProjectGeneratorTest {
    @TempDir
    Path tempDir;

    private ProjectGenerator generator;

    @BeforeEach
    void setUp() {
        generator = new ProjectGenerator();
    }

    @DisplayName("템플릿을 복사하여 프로젝트 생성")
    @Test
    void 템플릿을_복사하여_프로젝트_생성() {
        //given
        String projectName = "java-lotto";
        String packageName = "lotto";

        //when
        Path projectPath = generator.createProject(tempDir, projectName, packageName);

        //then
        assertThat(Files.exists(projectPath)).isTrue();
        assertThat(Files.exists(projectPath.resolve("build.gradle"))).isTrue();
        assertThat(Files.exists(projectPath.resolve("settings.gradle"))).isTrue();
        assertThat(Files.exists(projectPath.resolve("src/main/java"))).isTrue();
        assertThat(Files.exists(projectPath.resolve("src/test/java"))).isTrue();
    }

    @DisplayName("setting.gradle의 프로젝트명을 미션명으로 변경")
    @Test
    void setting_gradle의_프로젝트명을_미션명으로_변경() throws IOException {
        //given
        String projectName = "java-lotto";
        String packageName = "lotto";

        //when
        Path projectPath = generator.createProject(tempDir, projectName, packageName);

        //then
        Path settingsGradle = projectPath.resolve("settings.gradle");
        String content = Files.readString(settingsGradle);

        assertThat(content).contains("rootProject.name = 'java-lotto'");
        assertThat(content).doesNotContain("{{PROJECT_NAME}}");
    }

    @DisplayName("패키지 디렉토리를 생성하고 Application.java/ApplicationTest.java를 이동")
    @Test
    void 패키지_디렉토리를_생성하고_Application_java_ApplicationTest_java를_이동() {
        //given
        String projectName = "java-lotto";
        String packageName = "lotto";

        //when
        Path projectPath = generator.createProject(tempDir, projectName, packageName);

        //then
        Path mainPackage = projectPath.resolve("src/main/java" + packageName);
        Path testPackage = projectPath.resolve("src/test/java" + packageName);

        assertThat(Files.exists(mainPackage.resolve("Application.java"))).isTrue();
        assertThat(Files.exists(testPackage.resolve("ApplicationTest.java"))).isTrue();

        assertThat(Files.exists(projectPath.resolve("src/main/java/Application.java"))).isFalse();
        assertThat(Files.exists(projectPath.resolve("src/test/java/ApplicationTest.java"))).isFalse();
    }
}
