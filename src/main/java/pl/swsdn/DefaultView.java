package pl.swsdn;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import pl.swsdn.command.Command;
import pl.swsdn.command.motion.MotionCommands;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.function.Supplier;

@SpringView(name = DefaultView.VIEW_NAME)
public class DefaultView extends VerticalLayout implements View {
    public static final String VIEW_NAME = "";
    private final StatusBox statusBox = new StatusBox(MotionCommands.STATUS);

    @PostConstruct
    void init() throws IOException {
        setMargin(true);
        setSpacing(true);
        addComponents(
                getTitleComponent(),
                getStatusBox("start on boot", this::isMotionEnabled, MotionCommands.ENABLE, MotionCommands.DISABLE),
                getStatusBox("is active", this::isMotionActive, MotionCommands.START, MotionCommands.STOP),
                statusBox.getStatusComponent()
        );
    }

    private Component getStatusBox(String text, Supplier<Boolean> isEnabled, Command enable, Command disable) {
        boolean enabled = isEnabled.get();
        Button button = new Button();
        setButtonCaption(button, enabled);
        button.setHeight("24px");
        button.setDisableOnClick(true);

        button.addClickListener(e -> {
            if (isEnabled.get()) {
                disable.execute();
            } else {
                enable.execute();
            }
            button.setEnabled(true);
            boolean isNowEnabled = isEnabled.get();
            setButtonCaption(button, isNowEnabled);
            Notification.show("New status is", getButtonCaption(isNowEnabled), Notification.Type.HUMANIZED_MESSAGE);
            statusBox.updateComponent();
        });
        HorizontalLayout layout = new HorizontalLayout(new Label(text), button);
        layout.setSpacing(true);
        return layout;
    }

    private void setButtonCaption(Button button, boolean enabled) {
        button.setCaption(getButtonCaption(enabled));
        button.setStyleName(enabled ? ValoTheme.BUTTON_FRIENDLY : ValoTheme.BUTTON_DANGER);
    }

    private String getButtonCaption(boolean enabled) {
        return enabled ? "ON" : "OFF";
    }

    private Label getTitleComponent() {
        Label label = new Label("Motion service status");
        label.addStyleName(ValoTheme.LABEL_H1);
        label.setSizeFull();
        return label;
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
