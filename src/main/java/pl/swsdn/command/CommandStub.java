package pl.swsdn.command;

public class CommandStub implements Command {
    private final String cmd;

    CommandStub(String cmd) {
        this.cmd = cmd;
    }

    public static CommandStub getCommand(String command) {
        return new CommandStub(command);
    }

    @Override
    public String execute() {
        return cmd;
    }
}
