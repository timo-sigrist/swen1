package ch.zhaw.swen1.lightingapp.domain;

/**
 * Provides for controlling lamps for events which are similar to those of a rotary switches
 *
 * @author fer
 */
public interface LampControlMode {
    int getIncreasePercentage();

    int getNextPercentage(int currentPercentage);

    String toString();
}
