package TeDDie.api;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.google.gson.Gson;
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
}
