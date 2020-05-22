package io.project.model.observer;

import io.project.model.gearbox.GasThreshold;

public interface AcceleratorObservable {

    void registerObserver(AcceleratorObserver acceleratorObserver);

    void notifyObserverAcceleration(GasThreshold gasThreshold);

}
