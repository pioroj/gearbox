package io.project.model.gearbox.calculator;

import io.project.model.gashandler.GasThreshold;
import io.project.model.gearbox.AggressiveMode;
import io.project.model.gearbox.Gear;
import io.project.model.gearbox.RPM;

public interface GearCalculator {

    Gear calculateGear(RPM currentRpm, Gear currentGear, GasThreshold gasThreshold, AggressiveMode aggressiveMode, double angularSpeed);

}
