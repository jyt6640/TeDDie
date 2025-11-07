package TeDDie.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import TeDDie.service.MissionService;
import TeDDie.view.OutputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TeDDieControllerTest {
    @Mock
    private MissionService mockService;

    @Mock
    private OutputView mockView;

    @InjectMocks
    private TeDDieController controller;

    @DisplayName("CLI 인자를 파싱하여 Service와 View를 올바르게 호출")
    @Test
    void CLI_인자를_파싱하여_Service와_View를_올바르게_호출() throws Exception {
        //given
        String[] args = {"--topic", "collection", "--difficulty", "easy"};
        String missionResult = "## 미션";
        when(mockService.generateMission("collection", "easy"))
                .thenReturn(missionResult);

        //when
        controller.run(args);

        //then
        verify(mockService).generateMission("collection", "easy");
        verify(mockView).printMission(missionResult);
    }
}
