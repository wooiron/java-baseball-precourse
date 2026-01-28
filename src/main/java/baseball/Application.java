package baseball;

import java.util.Scanner;

public class Application {
    private static final int GAME_SIZE = 3;
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String INPUT_PROMPT = "숫자를 입력해주세요 : ";
    private static final String RESTART_PROMPT = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
    private static final String GAME_OVER_MESSAGE = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";

    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            play(scanner);
        }
    }

    private void play(Scanner scanner) {
        boolean restart = true;
        while (restart) {
            String targetNumber = new NumberGenerator().generate();
            playOneGame(scanner, targetNumber);
            restart = shouldRestart(scanner);
        }
    }

    private void playOneGame(Scanner scanner, String targetNumber) {
        BaseballGame game = new BaseballGame(targetNumber);
        boolean isFinished = false;
        while (!isFinished) {
            String inputNumber = readUserNumber(scanner);
            if (!isValidUserNumber(inputNumber)) {
                continue;
            }
            Result result = game.play(inputNumber);
            isFinished = processGameResult(result);
        }
    }

    private boolean processGameResult(Result result) {
        System.out.println(formatResult(result));
        if (result.isWin()) {
            System.out.println(GAME_OVER_MESSAGE);
            return true;
        }
        return false;
    }

    private String readUserNumber(Scanner scanner) {
        System.out.print(INPUT_PROMPT);
        return scanner.nextLine();
    }

    private boolean isValidUserNumber(String inputNumber) {
        if (inputNumber == null) {
            System.out.println(ERROR_PREFIX + "입력값이 올바르지 않습니다.");
            return false;
        }
        if (inputNumber.length() != GAME_SIZE) {
            System.out.println(ERROR_PREFIX + "3자리 숫자를 입력해야 합니다.");
            return false;
        }
        if (!areDigitsValid(inputNumber)) {
            return false;
        }
        if (hasDuplicateDigits(inputNumber)) {
            System.out.println(ERROR_PREFIX + "서로 다른 숫자를 입력해야 합니다.");
            return false;
        }
        return true;
    }

    private boolean areDigitsValid(String inputNumber) {
        for (int i = 0; i < GAME_SIZE; i++) {
            char c = inputNumber.charAt(i);
            if (c < '1' || c > '9') {
                System.out.println(ERROR_PREFIX + "1부터 9까지 숫자만 입력해야 합니다.");
                return false;
            }
        }
        return true;
    }

    private boolean hasDuplicateDigits(String inputNumber) {
        for (int i = 0; i < GAME_SIZE; i++) {
            if (isCharDuplicatedInRestOfString(inputNumber, i)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCharDuplicatedInRestOfString(String inputNumber, int index) {
        char currentChar = inputNumber.charAt(index);
        for (int j = index + 1; j < GAME_SIZE; j++) {
            if (currentChar == inputNumber.charAt(j)) {
                return true;
            }
        }
        return false;
    }

    private boolean shouldRestart(Scanner scanner) {
        while (true) {
            System.out.println(RESTART_PROMPT);
            String input = scanner.nextLine();
            if ("1".equals(input)) {
                return true;
            }
            if ("2".equals(input)) {
                return false;
            }
            System.out.println(ERROR_PREFIX + "1 또는 2를 입력해야 합니다.");
        }
    }

    private String formatResult(Result result) {
        if (result.isNothing()) {
            return "낫싱";
        }
        StringBuilder res = new StringBuilder();
        if (result.getStrikeCount() > 0) {
            res.append(result.getStrikeCount()).append(" 스트라이크 ");
        }
        if (result.getBallCount() > 0) {
            res.append(result.getBallCount()).append(" 볼");
        }
        return res.toString().trim();
    }
}
