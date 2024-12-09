package oncall.service;

import java.util.List;
import oncall.domain.OnCall;
import oncall.domain.cycle.Cycle;
import oncall.domain.cycle.OnCallCycle;
import oncall.domain.cycle.Person;
import oncall.domain.day.Month;

public class WorkerService {

    public OnCall generateOnCall(List<String> weekDay, List<String> weekEnd, Month month) {
        OnCallCycle onCallCycle = createOnCallCycle(weekDay, weekEnd);
        OnCall onCall = OnCall.create(month, onCallCycle);
        return onCall.resolveWorkerConflict();
    }

    private OnCallCycle createOnCallCycle(List<String> weekDay, List<String> weekEnd) {
        Cycle weekDayCycle = createCycle(weekDay);
        Cycle weekEndCycle = createCycle(weekEnd);
        return OnCallCycle.create(weekDayCycle, weekEndCycle);
    }

    private Cycle createCycle(List<String> workerInput) {
        List<Person> workers = workerInput.stream().map(Person::create).toList();
        return Cycle.create(workers);
    }
}
