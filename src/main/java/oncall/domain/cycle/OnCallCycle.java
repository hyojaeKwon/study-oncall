package oncall.domain.cycle;

public class OnCallCycle {
    private final Cycle weekDayCycle;
    private final Cycle weekEndCycle;

    private OnCallCycle(Cycle weekDayCycle, Cycle weekEndCycle) {
        this.weekDayCycle = weekDayCycle;
        this.weekEndCycle = weekEndCycle;
    }

    public static OnCallCycle create(Cycle weekDayCycle, Cycle weekEndCycle) {
        validation(weekEndCycle, weekDayCycle);
        return new OnCallCycle(weekDayCycle, weekEndCycle);
    }

    private static void validation(Cycle weekEndCycle, Cycle weekDayCycle) {
        if (weekDayCycle.size() != weekEndCycle.size()) {
            throw new IllegalArgumentException("두 사이클의 포함 인원 수는 같아야 합니다.");
        }
        for (int i = 0; i < weekDayCycle.size(); i++) {
            weekDayCycle = checkWorker(weekEndCycle, weekDayCycle);
        }
    }

    private static Cycle checkWorker(Cycle weekEndCycle, Cycle weekDayCycle) {
        Person worker = weekDayCycle.peekPerson();
        if (!weekEndCycle.contains(worker)) {
            throw new IllegalArgumentException("두 사이클에는 모든 근무자가 편성되어야 합니다.");
        }
        return weekDayCycle.updateWorkCycle();
    }
}
