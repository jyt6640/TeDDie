package TeDDie.api;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RagClientTest {
    @DisplayName("RagClient 생성")
    @Test
    void RagClient_생성() {
        //given
        HttpRequestSender sender = new HttpRequestSender();

        //when
        RagClient ragClient = new RagClient(sender);

        //then
        assertThat(ragClient).isNotNull();
    }

    @DisplayName("빈 결과 리스트를 반환")
    @Test
    void 빈_결과_리스트를_반화() throws Exception {
        //given
        HttpRequestSender mockSender = mock(HttpRequestSender.class);
        String responseJson = """
            {
                "query": "테스트용검색어",
                "results": []
            }
            """;
        when(mockSender.post(anyString(), anyString()))
                .thenReturn(responseJson);
        RagClient ragClient = new RagClient(mockSender);

        //when
        List<RagResult> results = ragClient.search("테스트용검색어", 3);

        //then
        assertThat(results).isNotNull();
        assertThat(results.size()).isEqualTo(0);
    }
}