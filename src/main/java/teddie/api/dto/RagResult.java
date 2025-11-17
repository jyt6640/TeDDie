package teddie.api.dto;

public record RagResult(
        String repo,
        String text,
        String url,
        double similarityScore
) {
}
