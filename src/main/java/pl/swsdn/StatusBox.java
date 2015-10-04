package pl.swsdn;

import com.vaadin.ui.TextArea;
import pl.swsdn.command.Command;

import java.io.Serializable;

class StatusBox implements Serializable {

    private final Command command;
    private final TextArea status;

    StatusBox(Command command) {
        this.command = command;
        status = new TextArea("status");
    }

    TextArea getStatusComponent() {
        status.setWordwrap(true);
        status.setEnabled(false);
        status.setSizeFull();
        updateComponent();
        return status;
    }

    void updateComponent() {
        String status = command.execute();
        this.status.setValue(status);
    }
}