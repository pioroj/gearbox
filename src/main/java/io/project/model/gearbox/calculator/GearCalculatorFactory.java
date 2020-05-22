package io.project.model.gearbox.calculator;

import io.project.model.gearbox.adapter.AngularSpeedRange;
import io.project.model.gearbox.Characteristics;
import io.project.model.gearbox.DriveMode;
import io.project.model.gearbox.Gear;
import io.project.model.gearbox.GearRange;

import java.util.Map;

import static io.project.model.gearbox.GasThresholdRange.doubleKickdownRange;
import static io.project.model.gearbox.GasThresholdRange.noKickdownRange;
import static io.project.model.gearbox.GasThresholdRange.singleKickdownRange;

public class GearCalculatorFactory {

    private final Characteristics characteristics = new Characteristics();

    private EcoGearCalculator ecoGearCalculator = new EcoGearCalculator(
            characteristics.optimalRpmRangeInEcoMode(),
            new GearRange(Gear.of(1), Gear.of(8)),
            noKickdownRange());

    private ComfortGearCalculator comfortGearCalculator = new ComfortGearCalculator(
            characteristics.optimalRpmRangeInComfortMode(),
            new GearRange(Gear.of(1), Gear.of(8)),
            singleKickdownRange(characteristics.singleComfortGasThreshold()));

    private SportGearCalculator sportGearCalculator = new SportGearCalculator(
            characteristics.optimalRpmRangeInSportMode(),
            new GearRange(Gear.of(1), Gear.of(8)),
            doubleKickdownRange(characteristics.lowerSportGasThreshold(), characteristics.higherSportGasThreshold()));

    private MDynamicGearCalculator mDynamicGearCalculator = new MDynamicGearCalculator(new AngularSpeedRange(100, 200));

    private final Map<DriveMode, ? extends GearCalculator> strategiesMap = Map.of(
            DriveMode.ECO, ecoGearCalculator,
            DriveMode.COMFORT, comfortGearCalculator,
            DriveMode.SPORT, sportGearCalculator,
            DriveMode.M_DYNAMIC,mDynamicGearCalculator
    );

    public GearCalculator getGearCalculatorStrategyFor(DriveMode driveMode) {
        return strategiesMap.get(driveMode);
    }

}