package ch.zhaw.swen1.lightingapp.ui.swing;

import ch.zhaw.swen1.lightingapp.domain.RGBColorPercentage;

import javax.swing.*;
import java.awt.*;

public abstract class Lamp extends JComponent {
    public abstract void setColor(RGBColorPercentage percentage);

    public abstract RGBColorPercentage getColor();

    @Override
    public abstract void paintComponent(Graphics g);
}
