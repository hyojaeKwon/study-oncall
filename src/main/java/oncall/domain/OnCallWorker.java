package oncall.domain;

import oncall.domain.cycle.Person;
import oncall.domain.day.Day;

public class OnCallWorker {
    private final Day day;
    private final Person worker;
    private final OnCallWorkerStatus status;

    private OnCallWorker(Day day, Person worker, OnCallWorkerStatus status) {
        this.day = day;
        this.worker = worker;
        this.status = status;
    }

    public static OnCallWorker create(Day day) {
        return new OnCallWorker(day, null, OnCallWorkerStatus.UNDETERMINED);
    }

    public Day getDay() {
        return day;
    }

    public Person getWorker() {
        if (status == OnCallWorkerStatus.UNDETERMINED) {
            throw new IllegalStateException("아직 결졍되지 않아서 접근할 수 없습니다.");
        }
        return worker;
    }

    public OnCallWorker updateWorker(Person worker) {
        return new OnCallWorker(day, worker, OnCallWorkerStatus.CONFLICT);
    }

    public OnCallWorker updateWorkerStatus() {
        return new OnCallWorker(day, worker, OnCallWorkerStatus.DETERMINED);
    }

    public boolean isWeekEndCycle() {
        return day.isWeekEnd() || day.isSubstHoliday();
    }
}
