package oncall.controller.input;

import java.util.List;
import java.util.regex.PatternSyntaxException;

public class InputParser {

    public static final String SPLIT_DELIMITER = ",";

    public List<String> parseStringToList(String input) {
        try {
            List<String> split = List.of(input.split(SPLIT_DELIMITER));
            validateEachString(split);
            return trimListOfString(split);
        } catch (PatternSyntaxException e) {
            throw new IllegalStateException("잘못된 입력입니다.");
        }
    }

    public int parseStringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalStateException("잘못된 숫자 입력입니다.");
        }
    }

    public List<String> trimListOfString(List<String> inputList) {
        return inputList.stream().map(String::trim).toList();
    }

    private void validateEachString(List<String> input) {
        input.forEach(InputValidator::validate);
    }


}
