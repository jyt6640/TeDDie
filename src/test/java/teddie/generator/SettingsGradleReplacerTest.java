package teddie.generator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class SettingsGradleReplacerTest {
    @TempDir
    Path tempDir;

    @DisplayName("setting.gradle의 프로젝트명을 미션명으로 변경")
    @Test
    void setting_gradle의_프로젝트명을_미션명으로_변경() throws IOException {
        //given
        SettingsGradleReplacer replacer = new SettingsGradleReplacer();
        Path settingsGradle = tempDir.resolve("settings.gradle");
        Files.writeString(settingsGradle, "rootProject.name = '{{PROJECT_NAME}}'");

        //when
        replacer.replaceGradleProjectName(tempDir, "java-lotto");

        //then
        String content = Files.readString(settingsGradle);
        assertThat(content).contains("rootProject.name = 'java-lotto'");
        assertThat(content).doesNotContain("{{PROJECT_NAME}}");
    }
}
