package io.project.model.observer;

import io.project.model.gashandler.GasThreshold;

public interface AcceleratorObservable {

    void registerObserver(AcceleratorObserver acceleratorObserver);

    void notifyObserverAcceleration(GasThreshold gasThreshold);

}
