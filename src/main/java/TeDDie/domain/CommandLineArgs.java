package TeDDie.domain;

import java.util.HashMap;
import java.util.Map;

public class CommandLineArgs {
    private final Map<String, String> args;

    public CommandLineArgs(String[] args) {
        this.args = parseArgs(args);
    }

    private Map<String, String> parseArgs(String[] args) {
        Map<String, String> arg = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            if(i + 1 < args.length) {
                arg.put(args[i], args[i+1]);
            }
        }
        return arg;
    }

    public String getTopic() {
        return args.get("--topic");
    }

    public String getDifficulty() {
        return args.get("--difficulty");
    }
}
