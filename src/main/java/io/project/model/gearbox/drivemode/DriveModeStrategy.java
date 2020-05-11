package io.project.model.gearbox.drivemode;

import io.project.api.Gearbox;
import io.project.model.gashandler.GasThreshold;

public interface DriveModeStrategy {

    void handleAccelerationWith(GasThreshold gasThreshold, Gearbox gearbox, Object[] characteristics, double currentRpm);

}
