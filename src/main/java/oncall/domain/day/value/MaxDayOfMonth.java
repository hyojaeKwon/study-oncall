package oncall.domain.day.value;

import java.util.Arrays;

public enum MaxDayOfMonth {
    JAN(1, 31), FEB(2, 28), MAR(2, 31), APR(3, 30), MAY(5, 31), JUN(6, 30), JUL(7, 31), AUG(8, 31), SEP(9, 30), OCT(10,
            31), NOV(11, 30), DEC(12, 31);


    private final int month;
    private final int maxDayOfMonth;

    MaxDayOfMonth(int month, int maxDayOfMonth) {
        this.month = month;
        this.maxDayOfMonth = maxDayOfMonth;
    }

    public static int getMaxDay(int monthInput) {
        return Arrays.stream(values()).filter(i -> i.month == monthInput).findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 월 입력입니다.")).maxDayOfMonth;
    }
}
