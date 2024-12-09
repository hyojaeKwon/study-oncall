package oncall.config;

import oncall.controller.OnCallController;
import oncall.controller.ProcessController;
import oncall.controller.input.ConsoleInputProvider;
import oncall.service.DayService;
import oncall.service.WorkerService;
import oncall.controller.input.InputParser;
import oncall.controller.input.InputProvider;
import oncall.controller.output.OutputFormatter;
public class Config {

    public OnCallController onCallController() {
        return new OnCallController(processController(), outputFormatter());
    }

    private ProcessController processController() {
        return new ProcessController(dayService(),workerService(), inputProvider(), inputParser());
    }

    private InputProvider inputProvider() {
        return new ConsoleInputProvider();
    }

    private InputParser inputParser() {
        return new InputParser();
    }

    private OutputFormatter outputFormatter() {
        return new OutputFormatter();
    }

    private DayService dayService() {
        return new DayService();
    }

    private WorkerService workerService() {
        return new WorkerService();
    }
}
