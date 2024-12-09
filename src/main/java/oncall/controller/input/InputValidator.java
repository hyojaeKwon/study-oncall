package oncall.controller.input;

public class InputValidator {

    private InputValidator() {
    }

    public static void validate(String input) {
        if (input.isBlank() || input.isEmpty()) {
            throw new IllegalArgumentException("공백은 입력할 수 없습니다.");
        }
    }
}
