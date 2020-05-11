package io.project.model.gearbox.drivemode;

import java.util.Map;

public class DriveModeStrategyFactory {

    private final Map<DriveMode, ? extends DriveModeStrategy> strategiesMap = Map.of(
            DriveMode.ECO, new EcoDriveModeStrategy(),
            DriveMode.COMFORT, new ComfortDriveModeStrategy(),
            DriveMode.SPORT, new SportDriveModeStrategy()
    );

    public DriveModeStrategy getHandlingStrategyForMode(DriveMode driveMode) {
        return strategiesMap.get(driveMode);
    }

}