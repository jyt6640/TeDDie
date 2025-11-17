package teddie.service;

record Message(String content) {
    String extractContent() {
        return content;
    }
}
