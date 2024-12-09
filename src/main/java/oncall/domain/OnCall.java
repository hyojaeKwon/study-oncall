package oncall.domain;

import java.util.ArrayList;
import java.util.List;
import oncall.domain.cycle.OnCallCycle;
import oncall.domain.cycle.Person;
import oncall.domain.day.Month;

public class OnCall {

    private final Month month;
    private final List<OnCallWorker> onCallWorkers;
    private final OnCallCycle onCallCycle;

    public OnCall(Month month, List<OnCallWorker> onCallWorkers, OnCallCycle onCallCycle) {
        this.month = month;
        this.onCallWorkers = onCallWorkers;
        this.onCallCycle = onCallCycle;
    }

    public static OnCall create(Month month, OnCallCycle onCallCycle) {
        List<OnCallWorker> workers = generateWorkers(onCallCycle, month);
        return new OnCall(month, workers, onCallCycle);
    }

    private static List<OnCallWorker> generateWorkers(OnCallCycle onCallCycle, Month month) {
        List<OnCallWorker> workers = month.getDays().stream().map(OnCallWorker::create).toList();
        List<OnCallWorker> workersCreated = new ArrayList<>(workers);

        for (OnCallWorker worker : workers) {
            onCallCycle = generateWorkersByCycle(onCallCycle, worker, workersCreated);
        }
        return workersCreated;
    }

    private static OnCallCycle generateWorkersByCycle(OnCallCycle onCallCycle, OnCallWorker worker,
                                                      List<OnCallWorker> workersCreated) {
        if (worker.isWeekEndCycle()) {
            return generateWeekEndWorker(onCallCycle, worker, workersCreated);
        }
        return generateWeekDayWorker(onCallCycle, worker, workersCreated);
    }

    private static OnCallCycle generateWeekDayWorker(OnCallCycle onCallCycle, OnCallWorker worker,
                                                     List<OnCallWorker> workersCreated) {
        OnCallWorker onCallWorker = worker.updateWorker(onCallCycle.peekWeekDayWorker());
        workersCreated.add(onCallWorker);
        return onCallCycle.updateWeekDayCycle();
    }

    private static OnCallCycle generateWeekEndWorker(OnCallCycle onCallCycle, OnCallWorker worker,
                                                     List<OnCallWorker> workersCreated) {
        OnCallWorker onCallWorker = worker.updateWorker(onCallCycle.peekWeekEndWorker());
        workersCreated.add(onCallWorker);
        return onCallCycle.updateWeekEndCycle();
    }

    public OnCall resolveWorkerConflict() {
        for (int i = 1; i < onCallWorkers.size(); i++) {
            if (checkWorkerContinuous(i)) {
                swapNextWorker(onCallWorkers.get(i).isWeekEndCycle(), i);
            }
        }
        return new OnCall(month, onCallWorkers, onCallCycle);
    }

    private boolean checkWorkerContinuous(int today) {
        if (month.getDays().size() <= today) {
            throw new IllegalStateException("잘못된 접근입니다.");
        }
        return onCallWorkers.get(today - 1).getWorkerName().equals(onCallWorkers.get(today).getWorkerName());
    }

    private void swapNextWorker(boolean isWeekEndCycle, int originalIdx) {
        if (isWeekEndCycle) {
            swapWeekEndCycle(originalIdx);
            return;
        }
        swapWeekDayCycle(originalIdx);
    }

    private void swapWeekDayCycle(int originalIdx) {
        for (int i = originalIdx + 1; i < onCallWorkers.size(); i++) {
            if (!onCallWorkers.get(i).isWeekEndCycle() && (checkNameAndSwap(originalIdx, i))) {
                return;
            }
        }
        Person nearestWorker = onCallCycle.findNearestWorker(false, onCallWorkers.get(originalIdx).getWorkerName());
        swapTargetWorker(originalIdx, nearestWorker);
    }

    private void swapWeekEndCycle(int originalIdx) {
        for (int i = originalIdx + 1; i < onCallWorkers.size(); i++) {
            if (onCallWorkers.get(i).isWeekEndCycle() && (checkNameAndSwap(originalIdx, i))) {
                return;
            }
        }
        Person nearestWorker = onCallCycle.findNearestWorker(true, onCallWorkers.get(originalIdx).getWorkerName());
        swapTargetWorker(originalIdx, nearestWorker);
    }

    private boolean checkNameAndSwap(int originalIdx, int i) {
        if (!onCallWorkers.get(i).getWorkerName().equals(onCallWorkers.get(originalIdx).getWorkerName())) {
            swapWorkers(originalIdx, i);
            return true;
        }
        return false;
    }

    private void swapWorkers(int targetIdx, int swapIdx) {
        Person targetWorker = onCallWorkers.get(targetIdx).getWorker();
        Person swapWorker = onCallWorkers.get(swapIdx).getWorker();
        onCallWorkers.set(swapIdx, onCallWorkers.get(swapIdx).updateWorker(targetWorker));
        onCallWorkers.set(targetIdx, onCallWorkers.get(targetIdx).updateWorker(swapWorker));
    }

    private void swapTargetWorker(int target, Person person) {
        onCallWorkers.set(target, onCallWorkers.get(target).updateWorker(person));
    }

}
