package oncall.domain.day.value;

import java.util.Arrays;

public enum SubstituteHoliday {
    FIRST_DAY(1, 1), INDEPENDENCE_DAY_OF_MARCH(3, 1), CHILDREN_DAY(5, 5), MEMORIAL_DAY(6, 6), INDEPENDENCE_DAY_OF_KOREA(
            8, 15), FOUNDATION_DAY(10, 3), KOREAN_DAY(10, 9), CHRISTMAS_DAY(12, 25);

    private final int month;
    private final int day;

    SubstituteHoliday(int month, int day) {
        this.month = month;
        this.day = day;
    }

    public static boolean isSubstituteHoliday(int monthInput, int dayInput) {
        return Arrays.stream(values()).filter(i -> i.month == monthInput).anyMatch(i -> i.day == dayInput);
    }
}
