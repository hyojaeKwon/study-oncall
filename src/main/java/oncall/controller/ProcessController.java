package oncall.controller;

import java.util.List;
import oncall.controller.input.InputParser;
import oncall.controller.input.InputProvider;
import oncall.domain.OnCall;
import oncall.domain.day.Month;
import oncall.service.DayService;
import oncall.service.WorkerService;

public class ProcessController {

    private final DayService dayService;
    private final WorkerService workerService;
    private final InputProvider inputProvider;
    private final InputParser inputParser;

    public ProcessController(DayService dayService, WorkerService workerService, InputProvider inputProvider,
                             InputParser inputParser) {
        this.dayService = dayService;
        this.workerService = workerService;
        this.inputProvider = inputProvider;
        this.inputParser = inputParser;
    }

    public Month createMonth() {
        List<String> monthInput = getInputList();
        int monthNum = inputParser.parseStringToInt(monthInput.get(0));
        String dayName = monthInput.get(1);
        return dayService.createMonthAndDay(monthNum, dayName);
    }


    public OnCall generateWorker(Month month) {
        List<String> weekDay = getInputList();
        List<String> weekEnd = getInputList();
        return workerService.generateOnCall(weekDay, weekEnd, month);
    }

    private List<String> getInputList() {
        String input = inputProvider.readLine();
        return inputParser.parseStringToList(input);
    }

}
