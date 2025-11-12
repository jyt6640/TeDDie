package TeDDie.api;

public record RagResult(
        String repo,
        String text,
        String url,
        double similarityScore
) {
}
