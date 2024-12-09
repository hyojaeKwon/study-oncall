package oncall.controller;

import oncall.controller.output.OutputFormatter;
import oncall.domain.OnCall;
import oncall.domain.day.Month;

public class OnCallController {

    private final ProcessController processController;
    private final OutputFormatter outputFormatter;

    public OnCallController(ProcessController processController, OutputFormatter outputFormatter) {
        this.processController = processController;
        this.outputFormatter = outputFormatter;
    }

    public void control() {
        Month month = monthControl();
        OnCall result = workerControl(month);
        System.out.println(outputFormatter.format(result));
    }

    private Month monthControl() {
        while (true) {
            try {
                return processController.createMonth();
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    private OnCall workerControl(Month month) {
        while (true) {
            try {
                return processController.generateWorker(month);
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e);
            }
        }
    }
}
