package io.project.model.gearbox.calculator;

import io.project.model.gashandler.GasThreshold;
import io.project.model.gearbox.GasThresholdRange;
import io.project.model.gearbox.Gear;
import io.project.model.gearbox.GearRange;
import io.project.model.gearbox.RPM;
import io.project.model.gearbox.RpmRange;

public class ComfortGearCalculator implements GearCalculator {

    private RpmRange optimalRpmRange;
    private GearRange gearRange;
    private GasThresholdRange gasThresholdRange;

    ComfortGearCalculator(RpmRange rpmRange, GearRange gearRange, GasThresholdRange gasThresholdRange) {
        this.optimalRpmRange = rpmRange;
        this.gearRange = gearRange;
        this.gasThresholdRange = gasThresholdRange;
    }

    @Override
    public Gear calculateGear(RPM currentRpm, Gear currentGear, GasThreshold gasThreshold) {
        if (gasThresholdRange.isNoKickdown(gasThreshold)) {
            if (currentRpm.isAbove(optimalRpmRange)) {
                return gearRange.upshift(currentGear);
            }
            if (currentRpm.isBelow(optimalRpmRange)) {
                return gearRange.downshift(currentGear);
            }
        } else if (gasThresholdRange.isSingleKickdown(gasThreshold)) {
            return gearRange.downshift(currentGear);
        }
        return currentGear;
    }
}
