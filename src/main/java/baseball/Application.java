package baseball;

import java.util.Scanner;

public class Application {
    // 상수 처리: 대문자와 스네이크 케이스(_) 사용
    private static final int GAME_SIZE = 3;
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String INPUT_PROMPT = "숫자를 입력해주세요 : ";
    private static final String RESTART_PROMPT = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";

    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

    // 실행 로직을 main에서 분리
    public void run() {
        try (Scanner scanner = new Scanner(System.in)) { // try-with-resources로 자동 close
            play(scanner);
        }
    }

    private void play(Scanner scanner) {
        while (true) {
            String targetNumber = new NumberGenerator().generate();
            playOneGame(scanner, targetNumber);
            if (!shouldRestart(scanner)) {
                return;
            }
        }
    }

    private void playOneGame(Scanner scanner, String targetNumber) {
        BaseballGame game = new BaseballGame(targetNumber);
        while (true) {
            // 숫자 입력
            String inputNumber = readUserNumber(scanner);
            
            // TODO :유효성 검증 로직 구현
            // if (!isValidUserNumber(inputNumber)) {
            //     continue;
            // }

            Result result = game.play(inputNumber);

            // 결과값 출력
            System.out.println(formatResult(result));

            if (result.isWin()) {
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                return;
            }
        }
    }

    private String readUserNumber(Scanner scanner) {
        System.out.print(INPUT_PROMPT);
        return scanner.nextLine();
    }

    private boolean shouldRestart(Scanner scanner) {
        System.out.println(RESTART_PROMPT);
        String input = scanner.nextLine();
        if ("1".equals(input)) {
            return true;
        }
        if ("2".equals(input)) {
            return false;
        }
        System.out.println(ERROR_PREFIX + "1 또는 2를 입력해야 합니다.");
        return shouldRestart(scanner);
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