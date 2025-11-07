package TeDDie.controller;

import TeDDie.service.MissionService;
import TeDDie.view.OutputView;
import java.util.HashMap;
import java.util.Map;

public class TeDDieController {
    private final MissionService missionService;
    private final OutputView outputView;

    public TeDDieController(MissionService missionService, OutputView outputView) {
        this.missionService = missionService;
        this.outputView = outputView;
    }

    public void run(String args[]) {
        try {
            Map<String, String> argMap = parseArgs(args);
            String topic = argMap.get("--topic");
            String difficulty = argMap.get("--difficulty");
            String missionResult = missionService.generateMission(topic, difficulty);
            outputView.printMission(missionResult);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private Map<String, String> parseArgs(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            if(i + 1 < args.length) {
                map.put(args[i], args[i + 1]);
            }
        }
        return map;
    }
}
