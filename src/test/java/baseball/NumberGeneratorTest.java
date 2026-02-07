package baseball;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NumberGeneratorTest {

    private final NumberGenerator generator = new NumberGenerator();

    @RepeatedTest(10)
    @DisplayName("생성된 숫자는 3자리이다")
    void generates_a_3_digit_number() {
        String number = generator.generate();
        assertThat(number).hasSize(3);
    }

    @RepeatedTest(10)
    @DisplayName("생성된 숫자는 1-9 사이의 값이다")
    void generates_digits_between_1_and_9() {
        String number = generator.generate();
        for (char c : number.toCharArray()) {
            assertThat(c).isGreaterThanOrEqualTo('1').isLessThanOrEqualTo('9');
        }
    }

    @RepeatedTest(10)
    @DisplayName("생성된 숫자는 서로 다른 수로 이루어져 있다")
    void generates_a_number_with_unique_digits() {
        String number = generator.generate();
        Set<Character> uniqueDigits = new HashSet<>();
        for (char c : number.toCharArray()) {
            uniqueDigits.add(c);
        }
        assertThat(uniqueDigits).hasSize(3);
    }
}