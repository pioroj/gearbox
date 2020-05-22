package io.project.model.gearbox;

import io.project.model.gearbox.adapter.AngularSpeedProvider;
import io.project.model.gearbox.adapter.GearboxACL;
import io.project.model.gearbox.adapter.RPMProvider;
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

    private DriverState driverState = DriverState.DRIVE;
    private DriveMode driveMode = DriveMode.COMFORT;
    private AggressiveMode aggressiveMode = AggressiveMode.BASIC;

    GearboxDriver(GearboxACL gearboxACL, RPMProvider rpmProvider, AngularSpeedProvider angularSpeedProvider, GearCalculatorFactory gearCalculatorFactory) {
        this.gearboxACL = gearboxACL;
        this.rpmProvider = rpmProvider;
        this.angularSpeedProvider = angularSpeedProvider;
        this.gearCalculatorFactory = gearCalculatorFactory;
    }

    @Override
    public void handleAccelerationWith(GasThreshold gasThreshold) {
        if (driverState == DriverState.DRIVE) {
            Gear newGear = gearCalculatorFactory.getGearCalculatorStrategyFor(driveMode)
                    .calculateGear(rpmProvider.current(), gearboxACL.currentGear(), gasThreshold, aggressiveMode, angularSpeedProvider.current());
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
