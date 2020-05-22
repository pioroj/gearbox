package io.project.model.gearbox;

import io.project.model.gashandler.GasThreshold;

public class Characteristics {

    private Object[] characteristics = new Object[]{2000d, 1000d, 1000d, 0.5d, 2500d, 4500d, 1500d, 0.5d, 5000d, 0.7d,
            5000d, 5000d, 1500d, 2000d, 3000d, 6500d};

    public RpmRange optimalRpmRangeInEcoMode() {
        return new RpmRange(RPM.rpm((double) characteristics[1]), RPM.rpm((double) characteristics[0]));
    }

    public RpmRange optimalRpmRangeInComfortMode() {
        return new RpmRange(RPM.rpm((double) characteristics[2]), RPM.rpm((double) characteristics[4]));
    }

    public RpmRange optimalRpmRangeInSportMode() {
        return new RpmRange(RPM.rpm((double) characteristics[6]), RPM.rpm((double) characteristics[8]));
    }

    public GasThreshold singleComfortGasThreshold() {
        return new GasThreshold((Double) characteristics[3]);
    }

    public GasThreshold lowerSportGasThreshold() {
        return new GasThreshold((Double) characteristics[7]);
    }

    public GasThreshold higherSportGasThreshold() {
        return new GasThreshold((Double) characteristics[9]);
    }
}
