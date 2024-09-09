package ch.zhaw.swen1.lightingapp.domain;

import ch.zhaw.swen1.lightingapp.ui.swing.Lamp;
import ch.zhaw.swen1.lightingapp.ui.swing.Switch;

import java.util.*;

/**
 * Basic implementation of lamp control. The CRUD methods for lamps and
 * switches are omitted.
 *
 * @author fer
 */
public class LampControl {
    private List<Lamp> lampList;
    private List<Switch> rotarySwitchList;
    private Map<Switch, List<Lamp>> lampsForRotarySwitch;
    private Map<Switch, LampControlMode> controlModeForRotarySwitch;
    private Set<LampControlMode> availableControlModes;

    public LampControl() {
        lampList = new ArrayList<>();
        rotarySwitchList = new ArrayList<>();
        lampsForRotarySwitch = new HashMap<>();
        controlModeForRotarySwitch = new HashMap<>();
        availableControlModes = new HashSet<>();
    }

    public void addLamp(Lamp lamp) {
        lampList.add(lamp);
    }

    public void addLampControlMode(LampControlMode controlMode) {
        availableControlModes.add(controlMode);
    }

    /**
     * Adds a new rotary switch together with the lamps it controls.
     *
     * @param rotarySwitch
     * @param lamps
     */
    public void addRotarySwitch(Switch rotarySwitch, List<Lamp> lamps) {
        rotarySwitch.setLampControl(this);
        rotarySwitchList.add(rotarySwitch);
        lampsForRotarySwitch.put(rotarySwitch, lamps);
    }

    public void setControlModeForSwitch(Switch rotarySwitch, LampControlMode controlMode) {
        controlModeForRotarySwitch.put(rotarySwitch, controlMode);
    }

    public Set<LampControlMode> getAvailableControlModes() {
        return availableControlModes;
    }

    public void buttonPressed(Switch rotarySwitch) {
        LampControlMode mode = controlModeForRotarySwitch.get(rotarySwitch);
        for (Lamp lamp : lampsForRotarySwitch.get(rotarySwitch)) {
            System.out.println("- Current Lamp value: " + lamp.getColor().getRed());
            if (lamp != null) {
                int currentValue = lamp.getColor().getRed();
                if (lamp.getColor().equals(RGBColorPercentage.WHITE)) {
                    lamp.setColor(RGBColorPercentage.BLACK);
                    System.out.println("set to Black");
                } else {
                    if (mode.getNextPercentage(currentValue) <= 0) {
                        lamp.setColor(RGBColorPercentage.WHITE);
                        System.out.println("set to White");
                    } else {
                        System.out.println("increase by: " + mode.getNextPercentage(currentValue));
                        lamp.setColor(lamp.getColor().increaseBrightness(mode.getNextPercentage(currentValue)));
                    }
                }
            }
            System.out.println("- New Lamp value: " + lamp.getColor().getRed());
        }
    }

    public void rotationChanged(Switch rotarySwitch, int i) {
        LampControlMode mode = controlModeForRotarySwitch.get(rotarySwitch);
        for (Lamp lamp : lampsForRotarySwitch.get(rotarySwitch)) {
            if (lamp != null) {
                if (i > 0) {
                    System.out.println("increase by: " + mode.getIncreasePercentage());
                    lamp.setColor(lamp.getColor().increaseBrightness(mode.getIncreasePercentage()));
                } else {
                    System.out.println("decrease by: " + -mode.getIncreasePercentage());
                    lamp.setColor(lamp.getColor().increaseBrightness(-mode.getIncreasePercentage()));
                }
            }
        }
    }
}
