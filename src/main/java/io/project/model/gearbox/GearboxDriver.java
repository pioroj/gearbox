package io.project.model.gearbox;

import io.project.model.gashandler.GasThreshold;
import io.project.model.gearbox.calculator.GearCalculatorFactory;
import io.project.model.observer.AcceleratorObserver;

public class GearboxDriver implements AcceleratorObserver {

    enum DriverState {
        REVERSE, NEUTRAL, PARK, DRIVE
    }

    private final GearboxACL gearboxACL;
    private final RPMProvider rpmProvider;
    private final GearCalculatorFactory gearCalculatorFactory;

    private DriverState driverState = DriverState.DRIVE;
    private DriveMode driveMode = DriveMode.COMFORT;

    GearboxDriver(GearboxACL gearboxACL, RPMProvider rpmProvider, GearCalculatorFactory gearCalculatorFactory) {
        this.gearboxACL = gearboxACL;
        this.rpmProvider = rpmProvider;
        this.gearCalculatorFactory = gearCalculatorFactory;
    }

    @Override
    public void handleAccelerationWith(GasThreshold gasThreshold) {
        if (driverState == DriverState.DRIVE) {
            Gear newGear = gearCalculatorFactory.getHandlingStrategyFor(driveMode)
                    .calculateGear(rpmProvider.current(), gearboxACL.currentGear(), gasThreshold);
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
