package TeDDie.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ApiResponseTest {
    @DisplayName("정상적인 응답에서 content 추출")
    @Test
    void 정상적인_응답에서_content_추출() {
        //given
        Message message = new Message("미션 내용");
        Choice choice = new Choice(message);
        ApiResponse response = new ApiResponse(List.of(choice));

        //when
        String content = response.extractResponse();

        //then
        assertThat(content).isEqualTo("미션 내용");
    }
}
