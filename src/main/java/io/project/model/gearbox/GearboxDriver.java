package io.project.model.gearbox;

import io.project.model.gearbox.adapter.AngularSpeedProvider;
import io.project.model.gearbox.adapter.GearboxACL;
import io.project.model.gearbox.adapter.RPMProvider;
import io.project.model.gearbox.adapter.VehicleAngleProvider;
import io.project.model.gearbox.calculator.CalculatorInputData;
import io.project.model.gearbox.calculator.GearCalculatorFactory;
import io.project.model.observer.AcceleratorObserver;

public class GearboxDriver implements AcceleratorObserver {

    enum DriverState {
        REVERSE, NEUTRAL, PARK, DRIVE
    }

    private final GearboxACL gearboxACL;
    private final RPMProvider rpmProvider;
    private final AngularSpeedProvider angularSpeedProvider;
    private final GearCalculatorFactory gearCalculatorFactory;
    private final VehicleAngleProvider vehicleAngleProvider;

    private DriverState driverState = DriverState.DRIVE;
    private DriveMode driveMode = DriveMode.COMFORT;
    private AggressiveMode aggressiveMode = AggressiveMode.BASIC;
    private boolean isTrailerAttached = false;

    GearboxDriver(GearboxACL gearboxACL, RPMProvider rpmProvider, AngularSpeedProvider angularSpeedProvider, GearCalculatorFactory gearCalculatorFactory, VehicleAngleProvider vehicleAngleProvider) {
        this.gearboxACL = gearboxACL;
        this.rpmProvider = rpmProvider;
        this.angularSpeedProvider = angularSpeedProvider;
        this.gearCalculatorFactory = gearCalculatorFactory;
        this.vehicleAngleProvider = vehicleAngleProvider;
    }

    @Override
    public void handleAccelerationWith(GasThreshold gasThreshold) {
        if (driverState == DriverState.DRIVE) {
            CalculatorInputData calculatorInputData = new CalculatorInputData(
                    rpmProvider.current(), gearboxACL.currentGear(), gasThreshold, aggressiveMode,
                    angularSpeedProvider.current(), isTrailerAttached, vehicleAngleProvider.isVehicleDrivingDown());
            Gear newGear = gearCalculatorFactory.getGearCalculatorStrategyFor(driveMode)
                    .calculateGear(calculatorInputData);
            gearboxACL.changeGearTo(newGear);
        }
    }

    Gear manualChangeGearDown() {
        return prepareCurrentGearRange().downshift(gearboxACL.currentGear());
    }

    Gear manualChangeGearUp() {
        return prepareCurrentGearRange().upshift(gearboxACL.currentGear());
    }

    private GearRange prepareCurrentGearRange() {
        return new GearRange(Gear.minDrivingGear(), gearboxACL.getMaxDrive());
    }
}
