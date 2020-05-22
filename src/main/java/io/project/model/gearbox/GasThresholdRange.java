package io.project.model.gearbox;

import io.project.model.gashandler.GasThreshold;

public class GasThresholdRange {

    private final GasThreshold singleKickdownBoundary;
    private final GasThreshold doubleKickdownBoundary;

    private GasThresholdRange(GasThreshold singleKickdownBoundary, GasThreshold doubleKickdownBoundary) {
        this.singleKickdownBoundary = singleKickdownBoundary;
        this.doubleKickdownBoundary = doubleKickdownBoundary;
    }

    public static GasThresholdRange noKickdownRange() {
        return new GasThresholdRange(GasThreshold.max(), GasThreshold.max());
    }

    public static GasThresholdRange singleKickdownRange(GasThreshold gasThreshold) {
        return new GasThresholdRange(gasThreshold, GasThreshold.max());
    }

    public static GasThresholdRange doubleKickdownRange(GasThreshold lowerGasThreshold, GasThreshold higherGasThreshold) {
        return new GasThresholdRange(lowerGasThreshold, higherGasThreshold);
    }

    public boolean isNoKickdown(GasThreshold gasThreshold) {
        return gasThreshold.lowerThan(singleKickdownBoundary);
    }

    public boolean isSingleKickdown(GasThreshold gasThreshold) {
        return gasThreshold.greaterThanOrEqualsTo(singleKickdownBoundary) && gasThreshold.lowerThan(doubleKickdownBoundary);
    }

    public boolean isDoubleKickdown(GasThreshold gasThreshold) {
        return gasThreshold.greaterThanOrEqualsTo(doubleKickdownBoundary);
    }

}
