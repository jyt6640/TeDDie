package teddie.api.dto;

public record RagResult(
        String repo,
        String text,
        String url,
        double similarityScore
) {

    public String buildFormat(int index) {
        return String.format(
                "[예시 %d: %s]\n\n",
                index,
                repo
        );
    }
}
