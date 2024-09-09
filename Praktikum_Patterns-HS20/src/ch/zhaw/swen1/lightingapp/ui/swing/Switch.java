package ch.zhaw.swen1.lightingapp.ui.swing;

import ch.zhaw.swen1.lightingapp.domain.LampControl;

import javax.swing.*;
import java.awt.event.ActionEvent;

public abstract class Switch extends JPanel {
    public abstract LampControl getLampControl();

    public abstract void setLampControl(LampControl lampControl);

    protected abstract void pressAction(ActionEvent event);

    protected abstract void incrementAction(ActionEvent event);

    protected abstract void decrementAction(ActionEvent event);
}
