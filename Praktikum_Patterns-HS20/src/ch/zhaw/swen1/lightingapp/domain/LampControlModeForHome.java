package ch.zhaw.swen1.lightingapp.domain;

public class LampControlModeForHome implements LampControlMode {
    private int increasePercentage;

    public LampControlModeForHome() {
        this.increasePercentage = 5;
    }

    @Override
    public int getIncreasePercentage() {
        return increasePercentage;
    }

    @Override
    public int getNextPercentage(int currentPercentage) {
        if (currentPercentage >= 0 && currentPercentage < 45) {
            return 45-currentPercentage;
        } else if (currentPercentage >= 45 && currentPercentage < 70) {
            return 70-currentPercentage;
        } else if (currentPercentage >= 70 && currentPercentage < 85) {
            return 85-currentPercentage;
        } else if (currentPercentage >= 85 && currentPercentage < 100) {
            return 100-currentPercentage;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Wohnung " + increasePercentage + "%";
    }
}
