package io.project.model.gearbox.calculator;

import io.project.model.gearbox.GasThreshold;
import io.project.model.gearbox.AggressiveMode;
import io.project.model.gearbox.GasThresholdRange;
import io.project.model.gearbox.Gear;
import io.project.model.gearbox.GearRange;
import io.project.model.gearbox.RPM;
import io.project.model.gearbox.RpmRange;

public class EcoGearCalculator implements GearCalculator {

    private RpmRange optimalRpmRange;
    private GearRange gearRange;
    private GasThresholdRange gasThresholdRange;

    EcoGearCalculator(RpmRange rpmRange, GearRange gearRange, GasThresholdRange gasThresholdRange) {
        this.optimalRpmRange = rpmRange;
        this.gearRange = gearRange;
        this.gasThresholdRange = gasThresholdRange;
    }

    @Override
    public Gear calculateGear(RPM currentRpm, Gear currentGear, GasThreshold gasThreshold, AggressiveMode aggressiveMode, double angularSpeed) {
        optimalRpmRange = optimalRpmRange.apply(aggressiveMode);
        if (gasThresholdRange.isNoKickdown(gasThreshold)) {
            if (currentRpm.isAbove(optimalRpmRange)) {
                return gearRange.upshift(currentGear);
            }
            if (currentRpm.isBelow(optimalRpmRange)) {
                return gearRange.downshift(currentGear);
            }
        }
        return currentGear;
    }
}
