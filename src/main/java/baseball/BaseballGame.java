package baseball;

public class BaseballGame {
    private static final int GAME_SIZE = 3;

    private final String targetNumber;

    public BaseballGame(String targetNumber) {
        this.targetNumber = targetNumber;
    }

    public Result play(String inputNumber) {
        int strikeCount = 0;
        int ballCount = 0;

        for (int i = 0; i < GAME_SIZE; i++) {
            char inputChar = inputNumber.charAt(i);
            char targetChar = targetNumber.charAt(i);

            if (inputChar == targetChar) {
                strikeCount++;
            } else if (targetNumber.indexOf(inputChar) != -1) {
                ballCount++;
            }
        }
        return new Result(strikeCount, ballCount);
    }

    private boolean isStrike(char inputChar, int index) {
        return inputChar == targetNumber.charAt(index);
    }

    private int countBall(char inputChar) {
        if (!targetNumber.contains(String.valueOf(inputChar))) {
            return 0;
        }
        return 1;
    }
}

