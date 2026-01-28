package baseball;

public class BaseballGame {
    private static final int GAME_SIZE = 3;

    private final String targetNumber;

    public BaseballGame(String targetNumber) {
        this.targetNumber = targetNumber;
    }

    public Result play(String inputNumber) {
        int strikeCount = countStrikes(inputNumber);
        int ballCount = countBalls(inputNumber);
        return new Result(strikeCount, ballCount);
    }

    private int countStrikes(String inputNumber) {
        int strikeCount = 0;
        for (int i = 0; i < GAME_SIZE; i++) {
            if (isStrike(inputNumber.charAt(i), i)) {
                strikeCount++;
            }
        }
        return strikeCount;
    }

    private int countBalls(String inputNumber) {
        int ballCount = 0;
        for (int i = 0; i < GAME_SIZE; i++) {
            if (isBall(inputNumber.charAt(i), i)) {
                ballCount++;
            }
        }
        return ballCount;
    }

    private boolean isStrike(char inputChar, int index) {
        return targetNumber.charAt(index) == inputChar;
    }

    private boolean isBall(char inputChar, int index) {
        return !isStrike(inputChar, index) && targetNumber.contains(String.valueOf(inputChar));
    }
}