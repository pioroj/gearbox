package io.project.model.gearbox;

import io.project.api.ExternalSystems;
import io.project.api.Gearbox;
import io.project.model.gashandler.GasThreshold;
import io.project.model.gearbox.drivemode.DriveMode;
import io.project.model.gearbox.drivemode.DriveModeStrategyFactory;
import io.project.model.observer.AcceleratorObserver;

public class GearboxDriver implements AcceleratorObserver {

    private Object[] characteristics = new Object[]{2000d, 1000d, 1000d, 0.5d, 2500d, 4500d, 1500d, 0.5d, 5000d, 0.7d,
    5000d, 5000d, 1500d, 2000d, 3000d, 6500d};

    private final Gearbox gearbox;
    private final ExternalSystems externalSystems;
    private final DriveModeStrategyFactory driveModeStrategyFactory;

    private DriveMode driveMode = DriveMode.COMFORT;

    public GearboxDriver(Gearbox gearbox, ExternalSystems externalSystems, DriveModeStrategyFactory driveModeStrategyFactory) {
        this.gearbox = gearbox;
        this.externalSystems = externalSystems;
        this.driveModeStrategyFactory = driveModeStrategyFactory;
    }

    @Override
    public void handleAccelerationWith(GasThreshold gasThreshold) {
        if ((int) gearbox.getState() == 1) {
            double currentRpm = externalSystems.getCurrentRpm();

            driveModeStrategyFactory.getHandlingStrategyForMode(driveMode).handleAccelerationWith(gasThreshold, gearbox, characteristics, currentRpm);
        }
    }
}
