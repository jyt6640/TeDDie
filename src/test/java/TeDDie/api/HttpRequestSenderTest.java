package TeDDie.api;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HttpRequestSenderTest {
    private MockWebServer mockWebServer;
    private HttpRequestSender sender;

    @BeforeEach
    public void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        sender = new HttpRequestSender();
    }

    @AfterEach
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @DisplayName("서버가 200 OK 응답시 응답 본문을 그대로 반환")
    @Test
    void 서버가_200_OK_응답시_응답_본문을_그대로_반환() throws Exception {
        //given
        String expectedBody = "{\"resqonse\":\"목 테스트 성공\"}";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(expectedBody));
        String mockUrl = mockWebServer.url("/").toString();
        String dummyRequestBody = "{}";

        //when
        String result = sender.post(mockUrl, dummyRequestBody);

        //then
        assertThat(result).isEqualTo(expectedBody);
    }
}
