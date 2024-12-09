package oncall.domain.cycle;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cycle {

    private final Deque<Person> workCycle;

    private Cycle(Deque<Person> workCycle) {
        this.workCycle = workCycle;
    }

    public static Cycle create(List<Person> cycleRequest) {
        validate(cycleRequest);
        Deque<Person> cycleRequestQueue = new ArrayDeque<>(cycleRequest);
        return new Cycle(cycleRequestQueue);
    }

    private static void validate(List<Person> cycleRequest) {
        validateCycleSize(cycleRequest);
        validateDuplicatePerson(cycleRequest);
    }

    private static void validateDuplicatePerson(List<Person> cycleRequest) {
        Set<Person> set = new HashSet<>();
        cycleRequest.forEach(i -> {
            if (!set.add(i)) {
                throw new IllegalArgumentException("중복된 사용자는 존재할 수 없습니다.");
            }
        });
    }

    private static void validateCycleSize(List<Person> cycleRequest) {
        if (cycleRequest.size() < 5 || cycleRequest.size() > 35) {
            throw new IllegalArgumentException("근무자는 5명 이상 35명 이하여야 합니다.");
        }
    }

    public Person peekPerson() {
        return workCycle.peekFirst();
    }

    public Cycle updateWorkCycle() {
        Person worker = workCycle.pollFirst();
        workCycle.addLast(worker);
        return new Cycle(workCycle);
    }

    public boolean contains(Person target) {
        return workCycle.contains(target);
    }

    public int size() {
        return workCycle.size();
    }

    public Person findNearestPerson(String name) {
        return workCycle.stream().filter(i -> !i.getName().equals(name)).findFirst().get();
    }
}
