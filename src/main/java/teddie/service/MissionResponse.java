package teddie.service;

import java.util.List;

public record MissionResponse(
    String mission,
    List<TestCase> testCases
) {
    public MissionResponse(String mission) {
        this(mission, List.of());
    }
}
