package oncall.domain.day;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import oncall.domain.day.value.MaxDayOfMonth;
import oncall.domain.day.value.SubstituteHoliday;

public class Month {

    private final int monthNumber;
    private final List<Day> days;

    private Month(int monthNumber, List<Day> days) {
        this.monthNumber = monthNumber;
        this.days = days;
    }

    public static Month create(int monthNum, int startDate) {
        int maxDay = MaxDayOfMonth.getMaxDay(monthNum);
        List<Day> dayList = createDayList(monthNum, startDate, maxDay);
        return new Month(monthNum, dayList);
    }

    private static List<Day> createDayList(int monthNum, int startDate, int maxDay) {
        List<Day> dayList = new ArrayList<>();
        for (int i = 1; i <= maxDay; i++) {
            dayList.add(Day.create(i, calculateDayOfWeek(i, startDate),
                    SubstituteHoliday.isSubstituteHoliday(monthNum, i)));
        }
        return dayList;
    }

    private static DayOfWeek calculateDayOfWeek(int dayNum, int startDate) {
        int calculateDayOfWeek = ((dayNum + startDate) - 1) % 7 + 1;
        return DayOfWeek.of(calculateDayOfWeek);
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public List<Day> getDays() {
        return days;
    }
}

