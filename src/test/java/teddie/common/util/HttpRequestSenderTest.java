package teddie.common.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import teddie.common.config.ApiConfig;
import teddie.common.util.HttpRequestSender;
import teddie.exception.HttpRequestException;

public class HttpRequestSenderTest {
    private MockWebServer mockWebServer;
    private HttpRequestSender sender;
    private ApiConfig mockApiConfig;

    @BeforeEach
    public void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        mockApiConfig = mock(ApiConfig.class);
        String mockUrl = mockWebServer.url("/").toString();
        when(mockApiConfig.getLmStudioUrl()).thenReturn(mockUrl);
        when(mockApiConfig.getRagApiUrl()).thenReturn(mockUrl);

        sender = new HttpRequestSender(mockApiConfig);
    }

    @AfterEach
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @DisplayName("LM Studio로 POST 요청 시 응답 본문 반환")
    @Test
    void LM_Studio로_POST_요청_시_응답_본문_반환() {
        //given
        String expectedBody = "{\"response\":\"목 테스트 성공\"}";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(expectedBody));
        String dummyRequestBody = "{}";

        //when
        String result = sender.postToLmStudio(dummyRequestBody);

        //then
        assertThat(result).isEqualTo(expectedBody);
    }

    @DisplayName("RAG API로 POST 요청 시 응답 본문 반환")
    @Test
    void RAG_API로_POST_요청_시_응답_본문_반환() {
        //given
        String expectedBody = "{\"results\":[]}";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(expectedBody));
        String dummyRequestBody = "{}";

        //when
        String result = sender.postToRagApi(dummyRequestBody);

        //then
        assertThat(result).isEqualTo(expectedBody);
    }

    @DisplayName("서버가 500 에러 응답시 HttpRequestException을 던짐")
    @Test
    void 서버가_500_에러_응답시_HttpRequestException을_던짐() {
        //given
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(500)
                .setBody("Internal Server Error")
        );
        String dummyRequestBody = "{}";

        //when&then
        assertThatThrownBy(() -> sender.postToLmStudio(dummyRequestBody))
                .isInstanceOf(HttpRequestException.class)
                .hasMessageContaining("500");
    }

    @DisplayName("POST 요청 시 올바른 메서드, 헤더, 본문 전송")
    @Test
    void POST_요청_시_올바른_메서드_헤더_본문_전송() throws Exception {
        //given
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"response\":\"ok\"}")
        );
        String input = "{\"prompt\":\"this is body\"}";

        //when
        sender.postToLmStudio(input);

        //then
        RecordedRequest request = mockWebServer.takeRequest();
        assertThat(request.getMethod()).isEqualTo("POST");
        assertThat(request.getHeader("Content-Type")).isEqualTo("application/json");
        assertThat(request.getBody().readUtf8()).isEqualTo(input);
    }

    @DisplayName("연결할 수 없는 주소로 요청시 HttpRequestException 던짐")
    @Test
    void 연결할_수_없는_주소로_요청시_HttpRequestException_던짐() {
        //given
        ApiConfig badConfig = mock(ApiConfig.class);
        when(badConfig.getLmStudioUrl()).thenReturn("http://localhost:9000/test");
        HttpRequestSender badSender = new HttpRequestSender(badConfig);
        String input = "{}";

        //when&then
        assertThatThrownBy(() -> badSender.postToLmStudio(input))
                .isInstanceOf(HttpRequestException.class);
    }
}
