package io.project.model.gearbox.calculator;

import io.project.model.gearbox.GasThreshold;
import io.project.model.gearbox.AggressiveMode;
import io.project.model.gearbox.adapter.AngularSpeedRange;
import io.project.model.gearbox.Gear;
import io.project.model.gearbox.RPM;

public class MDynamicGearCalculator implements GearCalculator {

    private AngularSpeedRange angularSpeedRange;

    public MDynamicGearCalculator(AngularSpeedRange angularSpeedRange) {
        this.angularSpeedRange = angularSpeedRange;
    }

    @Override
    public Gear calculateGear(RPM currentRpm, Gear currentGear, GasThreshold gasThreshold, AggressiveMode aggressiveMode, double angularSpeed) {
        if (angularSpeedRange.covers(angularSpeed)) {
            return currentGear;
        }
        return currentGear; //it's awful but there is no proper characteristics provided. So, here should be standard handling like in other calculators
    }

}
