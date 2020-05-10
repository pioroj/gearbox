package io.project.model.gashandler;

public class GasThreshold {

    private static final int MIN_LEVEL = 0;
    private static final int MAX_LEVEL = 100;

    private int pressLevel;

    public GasThreshold(int pressLevel) {
        if (pressLevel < MIN_LEVEL || pressLevel > MAX_LEVEL) {
            throw new IllegalArgumentException("Press level must be form " + MIN_LEVEL + " to " + MAX_LEVEL);
        }
        this.pressLevel = pressLevel;
    }

    public int getPressLevel() {
        return pressLevel;
    }
}
