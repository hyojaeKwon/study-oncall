package oncall.domain.day;

import java.time.DayOfWeek;

public class Day {
    private final int dayNumber;
    private final DayOfWeek dayOfWeek;
    private final boolean isSubstHoliday;

    private Day(int dayNumber, DayOfWeek dayOfWeek, boolean isSubstHoliday) {
        this.dayNumber = dayNumber;
        this.dayOfWeek = dayOfWeek;
        this.isSubstHoliday = isSubstHoliday;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public static Day create(int dayNumber, DayOfWeek dayOfWeek, boolean isSubstHoliday) {
        return new Day(dayNumber, dayOfWeek, isSubstHoliday);
    }

    public boolean isSubstHoliday() {
        return isSubstHoliday;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public boolean isWeekEnd() {
        return dayOfWeek.equals(DayOfWeek.of(6)) || dayOfWeek.equals(DayOfWeek.of(7));
    }
}
