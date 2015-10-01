package pl.swsdn.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BlockingCommand implements Command {

    private final String command;

    BlockingCommand(String command) {
        this.command = command;
    }

    public static BlockingCommand getCommand(String command) {
        return new BlockingCommand(command);
    }

    @Override
    public String execute() {
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException |InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
