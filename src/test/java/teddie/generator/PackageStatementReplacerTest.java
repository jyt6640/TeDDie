package teddie.generator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class PackageStatementReplacerTest {
    @TempDir
    Path tempDir;

    @DisplayName("Application/ApplicationTest.java에 package 문을 추가")
    @Test
    void Application_ApplicationTest_java에_package_문을_추가() throws IOException {
        //given
        PackageStatementReplacer replacer = new PackageStatementReplacer();

        Path mainPackageDir = tempDir.resolve("src/main/java/lotto");
        Files.createDirectories(mainPackageDir);
        Path mainFile = mainPackageDir.resolve("Application.java");
        Files.writeString(mainFile, "public class Application {}");

        Path testPackageDir = tempDir.resolve("src/test/java/lotto");
        Files.createDirectories(testPackageDir);
        Path testFile = testPackageDir.resolve("ApplicationTest.java");
        Files.writeString(testFile, "public class ApplicationTest {}");

        //when
        replacer.replacePackageState(tempDir, "lotto");

        //then
        String mainContent = Files.readString(mainFile);
        String testContent = Files.readString(testFile);

        assertThat(mainContent).startsWith("package lotto;");
        assertThat(testContent).startsWith("package lotto;");
    }
}
