package oncall.service;

import java.util.List;
import oncall.domain.day.Month;

public class DayService {

    private static final List<String> DAY_NAME = List.of("월", "화", "수", "목", "금", "토", "일");

    public Month createMonthAndDay(int monthNum, String dayName) {
        return Month.create(monthNum, convertDayNameToInt(dayName));
    }

    private int convertDayNameToInt(String dayName) {
        int value = DAY_NAME.indexOf(dayName);
        if (value < 0) {
            throw new IllegalStateException("잘못된 입력입니다.");
        }
        return value + 1;
    }
}
