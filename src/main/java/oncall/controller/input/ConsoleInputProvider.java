package oncall.controller.input;

import camp.nextstep.edu.missionutils.Console;

public class ConsoleInputProvider implements InputProvider {
    @Override
    public String readLine() {
        return Console.readLine();
    }
}
