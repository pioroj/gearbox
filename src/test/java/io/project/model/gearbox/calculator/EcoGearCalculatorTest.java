package io.project.model.gearbox.calculator;

import io.project.model.gashandler.GasThreshold;
import io.project.model.gearbox.AggressiveMode;
import io.project.model.gearbox.GasThresholdRange;
import io.project.model.gearbox.Gear;
import io.project.model.gearbox.GearRange;
import io.project.model.gearbox.RPM;
import io.project.model.gearbox.RpmRange;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EcoGearCalculatorTest {

    private EcoGearCalculator ecoGearCalculator = new EcoGearCalculator(
            new RpmRange(RPM.k(2), RPM.k(3)),
            new GearRange(Gear.of(1), Gear.of(8)),
            GasThresholdRange.noKickdownRange());

    @Test
    void shouldShiftUpWhenAboveMaxRpm() {
        Gear nextGear = ecoGearCalculator.calculateGear(RPM.rpm(3200), Gear.of(6), new GasThreshold(0.3d), AggressiveMode.BASIC, 150);

        assertEquals(Gear.of(7), nextGear);
    }

    @Test
    void shouldShiftDownWhenBelowMinRpm() {
        Gear nextGear = ecoGearCalculator.calculateGear(RPM.rpm(1200), Gear.of(6), new GasThreshold(0.3d), AggressiveMode.BASIC, 150);

        assertEquals(Gear.of(5), nextGear);
    }

    @Test
    void shouldDoNothingWhenWithinOptimalRpm() {
        Gear nextGear = ecoGearCalculator.calculateGear(RPM.rpm(2200), Gear.of(6), new GasThreshold(0.3d), AggressiveMode.BASIC, 150);

        assertEquals(Gear.of(6), nextGear);
    }

    @Test
    void shouldDoNothingWhenMaxGearReached() {
        Gear nextGear = ecoGearCalculator.calculateGear(RPM.rpm(3200), Gear.of(8), new GasThreshold(0.3d), AggressiveMode.BASIC, 150);

        assertEquals(Gear.of(8), nextGear);
    }

    @Test
    void shouldDoNothingWhenMinGearReached() {
        Gear nextGear = ecoGearCalculator.calculateGear(RPM.rpm(1200), Gear.of(1), new GasThreshold(0.3d), AggressiveMode.BASIC, 150);

        assertEquals(Gear.of(1), nextGear);
    }

}
