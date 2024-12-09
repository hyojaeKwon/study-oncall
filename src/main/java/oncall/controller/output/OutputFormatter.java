package oncall.controller.output;

import java.util.List;
import oncall.domain.OnCall;
import oncall.domain.OnCallWorker;

public class OutputFormatter {

    public static final String INDENT_1 = " ";
    public static final String MONTH = "월";
    public static final String DAY = "일";
    public static final String HOLIDAY = "(휴일)";

    public String format(OnCall result) {
        StringBuilder sb = new StringBuilder();
        int monthNumber = result.getMonth().getMonthNumber();
        List<OnCallWorker> onCallWorkers = result.getOnCallWorkers();

        formatWorkers(onCallWorkers, sb, monthNumber);
        return sb.toString();
    }

    private void formatWorkers(List<OnCallWorker> onCallWorkers, StringBuilder sb, int monthNumber) {
        onCallWorkers.forEach(i -> {
            formatDate(i, sb, monthNumber);
            formatDay(i, sb);
            sb.append(i.getWorkerName()).append("\n");
        });
    }

    private void formatDate(OnCallWorker i, StringBuilder sb, int monthNumber) {
        sb.append(monthNumber).append(MONTH).append(INDENT_1);
        sb.append(i.getDay().getDayNumber()).append(DAY).append(INDENT_1);
    }

    private void formatDay(OnCallWorker i, StringBuilder sb) {
        sb.append(i.getDayName());
        if (i.getDay().isSubstHoliday() && !i.getDay().isWeekEnd()) {
            sb.append(HOLIDAY);
        }
        sb.append(INDENT_1);
    }


}
