package io.project.model.observer;

import io.project.model.gearbox.GasThreshold;

import java.util.List;

public class Accelerator implements AcceleratorObservable {

    private List<AcceleratorObserver> acceleratorObservers;
    private GasThreshold gasThreshold;

    public Accelerator(List<AcceleratorObserver> acceleratorObservers, GasThreshold gasThreshold) {
        this.acceleratorObservers = acceleratorObservers;
        this.gasThreshold = gasThreshold;
    }

    public void accelerate(int pressLevel) {
        gasThreshold = new GasThreshold(pressLevel);
        notifyObserverAcceleration(gasThreshold);
    }

    public void registerObserver(AcceleratorObserver acceleratorObserver) {
        acceleratorObservers.add(acceleratorObserver);
    }

    public void notifyObserverAcceleration(GasThreshold gasThreshold) {
        acceleratorObservers.forEach(acceleratorObserver -> acceleratorObserver.handleAccelerationWith(gasThreshold));
    }
}
