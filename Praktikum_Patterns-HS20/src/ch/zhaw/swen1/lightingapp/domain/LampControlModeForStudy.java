package ch.zhaw.swen1.lightingapp.domain;

public class LampControlModeForStudy implements LampControlMode {
    private int increasePercentage;

    public LampControlModeForStudy() {
        this.increasePercentage = 33;
    }

    @Override
    public int getIncreasePercentage() {
        return increasePercentage;
    }

    @Override
    public int getNextPercentage(int currentPercentage) {
        if (currentPercentage >= 0 && currentPercentage < 33) {
            return 33 - currentPercentage;
        } else if (currentPercentage >= 33 && currentPercentage < 66) {
            return 66 - currentPercentage;
        } else if (currentPercentage >= 66 && currentPercentage < 100) {
            return 100 - currentPercentage;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Study " + increasePercentage + "%";
    }
}
