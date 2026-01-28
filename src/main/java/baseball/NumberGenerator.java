package baseball;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberGenerator {
    public String generate() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            result.append(numbers.get(i));
        }
        return result.toString();
    }
}