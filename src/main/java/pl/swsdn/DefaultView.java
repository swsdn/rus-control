package pl.swsdn;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import pl.swsdn.command.motion.MotionCommands;

import javax.annotation.PostConstruct;
import java.io.*;

@SpringView(name = DefaultView.VIEW_NAME)
public class DefaultView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "";

    @PostConstruct
    void init() throws IOException {
        setMargin(true);
        setSpacing(true);
        addComponent(new Label("This is the default view"));

        addComponents(
                new Label("status: "),
                new Label(MotionCommands.STATUS.execute()));

        CheckBox enabledBox = new CheckBox("enabled");
        enabledBox.setValue(isMotionEnabled());
        enabledBox.addValueChangeListener(event -> {
            if (isMotionEnabled()) {
                MotionCommands.DISABLE.execute();
            } else {
                MotionCommands.ENABLE.execute();
            }
            enabledBox.setValue(isMotionEnabled());
            Notification.show("Motion is", MotionCommands.IS_ENABLED.execute(), Notification.Type.HUMANIZED_MESSAGE);
        });

        CheckBox activeBox = new CheckBox("active");
        activeBox.setValue(isMotionActive());
        activeBox.addValueChangeListener(event -> {
            if (isMotionActive()) {
                MotionCommands.STOP.execute();
            } else {
                MotionCommands.START.execute();
            }
            activeBox.setValue(isMotionActive());
            Notification.show("Motion is", MotionCommands.IS_ACTIVE.execute(), Notification.Type.HUMANIZED_MESSAGE);
        });

        addComponent(enabledBox);
        addComponent(activeBox);
    }

    private boolean isMotionEnabled() {
        return "enabled".equals(MotionCommands.IS_ENABLED.execute());
    }

    private boolean isMotionActive() {
        return "active".equals(MotionCommands.IS_ACTIVE.execute());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // the view is constructed in the init() method()
    }

}
