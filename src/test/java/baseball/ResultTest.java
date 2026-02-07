package baseball;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Result 클래스")
class ResultTest {

    @Nested
    @DisplayName("isWin 메소드는")
    class IsWinTest {

        @Test
        @DisplayName("3 스트라이크일 때 true를 반환한다")
        void returns_true_for_3_strikes() {
            Result result = new Result(3, 0);
            assertThat(result.isWin()).isTrue();
        }

        @Test
        @DisplayName("3 스트라이크가 아닐 때 false를 반환한다")
        void returns_false_for_less_than_3_strikes() {
            Result resultWith2Strikes = new Result(2, 1);
            Result resultWith0Strikes = new Result(0, 2);

            assertThat(resultWith2Strikes.isWin()).isFalse();
            assertThat(resultWith0Strikes.isWin()).isFalse();
        }
    }

    @Nested
    @DisplayName("isNothing 메소드는")
    class IsNothingTest {

        @Test
        @DisplayName("0 스트라이크, 0 볼일 때 true를 반환한다")
        void returns_true_for_0_strikes_and_0_balls() {
            Result result = new Result(0, 0);
            assertThat(result.isNothing()).isTrue();
        }

        @Test
        @DisplayName("스트라이크나 볼이 하나라도 있으면 false를 반환한다")
        void returns_false_if_strikes_or_balls_exist() {
            Result resultWithStrikes = new Result(1, 0);
            Result resultWithBalls = new Result(0, 2);
            Result resultWithBoth = new Result(1, 1);

            assertThat(resultWithStrikes.isNothing()).isFalse();
            assertThat(resultWithBalls.isNothing()).isFalse();
            assertThat(resultWithBoth.isNothing()).isFalse();
        }
    }
}
