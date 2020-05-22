package io.project.model.observer;

import io.project.model.gearbox.GasThreshold;

public interface AcceleratorObserver {

    void handleAccelerationWith(GasThreshold gasThreshold);

}
