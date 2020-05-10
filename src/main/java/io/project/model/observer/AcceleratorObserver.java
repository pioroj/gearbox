package io.project.model.observer;

import io.project.model.gashandler.GasThreshold;

public interface AcceleratorObserver {

    void handleAccelerationWith(GasThreshold gasThreshold);

}
