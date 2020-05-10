package io.project.model.gearbox;

import io.project.api.ExternalSystems;
import io.project.api.Gearbox;
import io.project.model.gashandler.GasThreshold;
import io.project.model.observer.AcceleratorObserver;

public class GearboxDriver implements AcceleratorObserver {

    private Object[] characteristics = new Object[]{2000d, 1000d, 1000d, 0.5d, 2500d, 4500d, 1500d, 0.5d, 5000d, 0.7d,
    5000d, 5000d, 1500d, 2000d, 3000d, 6500d};

    private final Gearbox gearbox;
    private final ExternalSystems externalSystems;

    public GearboxDriver(Gearbox gearbox, ExternalSystems externalSystems) {
        this.gearbox = gearbox;
        this.externalSystems = externalSystems;
    }

    @Override
    public void handleAccelerationWith(GasThreshold gasThreshold) {
        if ((int) gearbox.getState() == 1) {
            double currentRpm = externalSystems.getCurrentRpm();

            if (currentRpm > (Double) characteristics[0]) {
                if (ensureMaxDriveNotReached()) {
                    gearbox.setCurrentGear(getCurrentGear() + 1);
                }
            }
            if (currentRpm < (Double) characteristics[1]) {
                if (ensureLowestDriveNotReached()) {
                    gearbox.setCurrentGear(getCurrentGear() - 1);
                }
            }
        }
    }

    private boolean ensureLowestDriveNotReached() {
        return getCurrentGear() != 1;
    }

    private boolean ensureMaxDriveNotReached() {
        return getCurrentGear() != gearbox.getMaxDrive();
    }

    private int getCurrentGear() {
        return (int) gearbox.getCurrentGear();
    }
}
