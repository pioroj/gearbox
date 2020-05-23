package io.project.model.gearbox.calculator;

import io.project.model.gearbox.GasThresholdRange;
import io.project.model.gearbox.Gear;
import io.project.model.gearbox.GearRange;
import io.project.model.gearbox.RPM;
import io.project.model.gearbox.RpmRange;

public class SportGearCalculator implements GearCalculator {

    private RpmRange optimalRpmRange;
    private GearRange gearRange;
    private GasThresholdRange gasThresholdRange;
    private RPM reductionRpm;

    SportGearCalculator(RpmRange rpmRange, GearRange gearRange, GasThresholdRange gasThresholdRange, RPM reductionRpm) {
        this.optimalRpmRange = rpmRange;
        this.gearRange = gearRange;
        this.gasThresholdRange = gasThresholdRange;
        this.reductionRpm = reductionRpm;
    }

    @Override
    public Gear calculateGear(CalculatorInputData calculatorInputData) {
        optimalRpmRange = optimalRpmRange.apply(calculatorInputData.getAggressiveMode());

        if (calculatorInputData.isTrailerAttached() && calculatorInputData.isVehicleDrivingDown()) {
            if (calculatorInputData.getCurrentRpm().isBelow(reductionRpm)) {
                return gearRange.downshift(calculatorInputData.getCurrentGear());
            }
        }

        if (gasThresholdRange.isNoKickdown(calculatorInputData.getGasThreshold())) {
            if (calculatorInputData.getCurrentRpm().isAbove(optimalRpmRange)) {
                return gearRange.upshift(calculatorInputData.getCurrentGear());
            }
            if (calculatorInputData.getCurrentRpm().isBelow(optimalRpmRange)) {
                return gearRange.downshift(calculatorInputData.getCurrentGear());
            }
        } else if (gasThresholdRange.isSingleKickdown(calculatorInputData.getGasThreshold())) {
            return gearRange.downshift(calculatorInputData.getCurrentGear());
        } else if (gasThresholdRange.isDoubleKickdown(calculatorInputData.getGasThreshold())) {
            Gear downshiftedOnceGear = gearRange.downshift(calculatorInputData.getCurrentGear());
            return gearRange.downshift(downshiftedOnceGear);
        }

        return calculatorInputData.getCurrentGear();
    }
}
