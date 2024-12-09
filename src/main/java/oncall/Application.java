package oncall;

import oncall.config.Config;
import oncall.controller.OnCallController;

public class Application {
    public static void main(String[] args) {
        OnCallController onCallController = new Config().onCallController();
        try {
            onCallController.control();
        } catch (IllegalStateException e) {
            System.out.println("[ERROR] Internal Server Error");
        }
    }
}
