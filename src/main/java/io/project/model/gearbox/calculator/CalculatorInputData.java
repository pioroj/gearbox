package io.project.model.gearbox.calculator;

import io.project.model.gearbox.AggressiveMode;
import io.project.model.gearbox.GasThreshold;
import io.project.model.gearbox.Gear;
import io.project.model.gearbox.RPM;

public class CalculatorInputData {

    private RPM currentRpm;
    private Gear currentGear;
    private GasThreshold gasThreshold;
    private AggressiveMode aggressiveMode;
    private double angularSpeed;
    private boolean isTrailerAttached;
    private boolean isVehicleDrivingDown;

    public CalculatorInputData(RPM currentRpm, Gear currentGear, GasThreshold gasThreshold, AggressiveMode aggressiveMode, double angularSpeed, boolean isTrailerAttached, boolean isVehicleDrivingDown) {
        this.currentRpm = currentRpm;
        this.currentGear = currentGear;
        this.gasThreshold = gasThreshold;
        this.aggressiveMode = aggressiveMode;
        this.angularSpeed = angularSpeed;
        this.isTrailerAttached = isTrailerAttached;
        this.isVehicleDrivingDown = isVehicleDrivingDown;
    }

    public RPM getCurrentRpm() {
        return currentRpm;
    }

    public Gear getCurrentGear() {
        return currentGear;
    }

    public GasThreshold getGasThreshold() {
        return gasThreshold;
    }

    public AggressiveMode getAggressiveMode() {
        return aggressiveMode;
    }

    public double getAngularSpeed() {
        return angularSpeed;
    }

    public boolean isTrailerAttached() {
        return isTrailerAttached;
    }

    public boolean isVehicleDrivingDown() {
        return isVehicleDrivingDown;
    }
}
