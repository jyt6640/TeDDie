package TeDDie.domain;

import java.util.Arrays;

public enum Difficulty {
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard");

    private final String value;

    Difficulty(String value) {
        this.value = value;
    }

    public static Difficulty from(String input) {
        return Arrays.stream(Difficulty.values())
                .filter(difficulty -> difficulty.value.equals(input.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "[ERROR] 유효하지 않은 난이도입니다."
                ));
    }
}
