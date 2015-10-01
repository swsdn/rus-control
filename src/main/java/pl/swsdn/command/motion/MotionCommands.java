package pl.swsdn.command.motion;

import pl.swsdn.command.BlockingCommand;
import pl.swsdn.command.Command;

public enum MotionCommands implements Command{
    STATUS("systemctl status motion"),
    IS_ACTIVE("systemctl is-active motion"),
    START("systemctl start motion"),
    STOP("systemctl stop motion"),
    IS_ENABLED("systemctl is-enabled motion"),
    ENABLE("systemctl enable motion"),
    DISABLE("systemctl disable motion"),
    ;

    private final Command command;

    MotionCommands(String cmd) {
        this.command = BlockingCommand.getCommand(cmd);
    }

    @Override
    public String execute() {
        return command.execute();
    }
}
