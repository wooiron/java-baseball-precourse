package baseball;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BaseballGameTest {

    private final BaseballGame game = new BaseballGame("123");

    @ParameterizedTest
    @MethodSource("gameScenarios")
    @DisplayName("스트라이크와 볼 개수를 정확히 계산한다")
    void play_calculatesStrikesAndBallsCorrectly(String input, int expectedStrikes, int expectedBalls) {
        Result result = game.play(input);

        assertThat(result.getStrikeCount()).isEqualTo(expectedStrikes);
        assertThat(result.getBallCount()).isEqualTo(expectedBalls);
    }

    static Stream<Arguments> gameScenarios() {
        return Stream.of(
                // 계획된 테스트 케이스
                Arguments.of("123", 3, 0), // 3 스트라이크
                Arguments.of("312", 0, 3), // 3 볼
                Arguments.of("132", 1, 2), // 1 스트라이크 2 볼
                Arguments.of("456", 0, 0), // 낫싱

                // 견고성을 위한 추가 케이스
                Arguments.of("124", 2, 0), // 2 스트라이크
                Arguments.of("415", 0, 1), // 1 볼
                Arguments.of("142", 1, 1)  // 1 스트라이크 1 볼
        );
    }
}