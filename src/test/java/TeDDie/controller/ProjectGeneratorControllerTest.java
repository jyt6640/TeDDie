package TeDDie.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class ProjectGeneratorControllerTest {
    @TempDir
    Path tempDir;

    private ProjectGeneratorController generator;

    @BeforeEach
    void setUp() {
        generator = new ProjectGeneratorController();
    }


}
