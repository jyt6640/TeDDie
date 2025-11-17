package teddie.generator;

import java.io.IOException;
import java.nio.file.Path;

public class ProjectWriter {
    private final FileReplacer fileReplacer;
    private final ReadmeWriter readmeWriter;

    public ProjectWriter() {
        this.fileReplacer = new FileReplacer();
        this.readmeWriter = new ReadmeWriter();
    }

    public void writeProject(Path projectPath, String projectName, String packageName) throws IOException {
        fileReplacer.replaceProjectName(projectPath, projectName);
        fileReplacer.replacePackageState(projectPath, packageName);
    }

    public void writeREADME(Path projectPath, String missionContent) throws IOException {
        readmeWriter.createReadme(projectPath, missionContent);
    }
}
