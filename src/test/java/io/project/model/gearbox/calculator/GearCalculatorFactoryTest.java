package io.project.model.gearbox.calculator;

import io.project.model.gearbox.DriveMode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GearCalculatorFactoryTest {

    private GearCalculatorFactory gearCalculatorFactory = new GearCalculatorFactory();

    @Test
    void shouldReturnProperStrategy() {
        GearCalculator ecoGearCalculator = gearCalculatorFactory.getGearCalculatorStrategyFor(DriveMode.ECO);
        GearCalculator comfortGearCalculator = gearCalculatorFactory.getGearCalculatorStrategyFor(DriveMode.COMFORT);
        GearCalculator sportGearCalculator = gearCalculatorFactory.getGearCalculatorStrategyFor(DriveMode.SPORT);

        assertEquals(EcoGearCalculator.class, ecoGearCalculator.getClass());
        assertEquals(ComfortGearCalculator.class, comfortGearCalculator.getClass());
        assertEquals(SportGearCalculator.class, sportGearCalculator.getClass());
    }
}
