package io.project.model.gearbox.calculator;

import io.project.model.gearbox.Gear;
import io.project.model.gearbox.adapter.AngularSpeedRange;

public class MDynamicGearCalculator implements GearCalculator {

    private AngularSpeedRange angularSpeedRange;

    public MDynamicGearCalculator(AngularSpeedRange angularSpeedRange) {
        this.angularSpeedRange = angularSpeedRange;
    }

    @Override
    public Gear calculateGear(CalculatorInputData calculatorInputData) {
        if (angularSpeedRange.covers(calculatorInputData.getAngularSpeed())) {
            return calculatorInputData.getCurrentGear();
        }
        return calculatorInputData.getCurrentGear(); //it's awful but there is no proper characteristics provided. So, here should be standard handling like in other calculators
    }

}
