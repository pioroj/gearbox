package io.project.model.gearbox.drivemode;

import io.project.api.Gearbox;
import io.project.model.gashandler.GasThreshold;

public class ComfortDriveModeStrategy implements DriveModeStrategy {

    @Override
    public void handleAccelerationWith(GasThreshold gasThreshold, Gearbox gearbox, Object[] characteristics, double currentRpm) {
        Integer currentGear = (Integer) gearbox.getCurrentGear();
        Integer maxDrive = gearbox.getMaxDrive();
        if (currentRpm > (Double) characteristics[4]) {
            if (ensureMaxDriveNotReached(currentGear, maxDrive)) {
                gearbox.setCurrentGear(currentGear + 1);
            }
        }
        if (currentRpm < (Double) characteristics[2]) {
            if (ensureLowestDriveNotReached(currentGear)) {
                gearbox.setCurrentGear(currentGear - 1);
            }
        }
    }

    private boolean ensureLowestDriveNotReached(Integer currentGear) {
        return currentGear != 1;
    }

    private boolean ensureMaxDriveNotReached(Integer currentGear, Integer maxDrive) {
        return !currentGear.equals(maxDrive);
    }

}
