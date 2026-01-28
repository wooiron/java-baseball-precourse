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
            strikeCount += getStrikeIncrement(inputNumber.charAt(i), i);
        }
        return strikeCount;
    }

    private int getStrikeIncrement(char inputChar, int index) {
        if (isStrike(inputChar, index)) {
            return 1;
        }
        return 0;
    }

    private int countBalls(String inputNumber) {
        int ballCount = 0;
        for (int i = 0; i < GAME_SIZE; i++) {
            ballCount += getBallIncrement(inputNumber.charAt(i), i);
        }
        return ballCount;
    }

    private int getBallIncrement(char inputChar, int index) {
        if (isBall(inputChar, index)) {
            return 1;
        }
        return 0;
    }

    private boolean isStrike(char inputChar, int index) {
        return targetNumber.charAt(index) == inputChar;
    }

    private boolean isBall(char inputChar, int index) {
        return !isStrike(inputChar, index) && targetNumber.contains(String.valueOf(inputChar));
    }
}
