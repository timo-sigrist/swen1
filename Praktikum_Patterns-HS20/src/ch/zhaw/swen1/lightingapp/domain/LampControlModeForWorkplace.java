package ch.zhaw.swen1.lightingapp.domain;

public class LampControlModeForWorkplace implements LampControlMode {
    private int increasePercentage;

    public LampControlModeForWorkplace() {
        this.increasePercentage = 10;
    }

    @Override
    public int getIncreasePercentage() {
        return increasePercentage;
    }

    @Override
    public int getNextPercentage(int currentPercentage) {
        if (currentPercentage > 0 && currentPercentage <= 100) {
            return 0;
        } else {
            return 100;
        }
    }

    @Override
    public String toString() {
        return "Arbeitsplatz " + increasePercentage + "%";
    }
}
