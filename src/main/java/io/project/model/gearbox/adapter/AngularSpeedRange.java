package io.project.model.gearbox.adapter;

public class AngularSpeedRange {

    private final double left;
    private final double right;

    public AngularSpeedRange(double left, double right) {
        if (right < left) {
            throw new IllegalArgumentException("Wrong AngularSpeedRange - max lower than min");
        }
        this.left = left;
        this.right = right;
    }

    public boolean covers(double angularSpeed) {
        return angularSpeed >= left && angularSpeed <= right;
    }
}
