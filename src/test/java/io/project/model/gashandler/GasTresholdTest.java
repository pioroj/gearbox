package io.project.model.gashandler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GasTresholdTest {

    @Test
    void shouldCreateValidGasThreshold() {
        GasThreshold gasThreshold = new GasThreshold(0.5d);
        assertEquals(0.5d, gasThreshold.getPressLevel());
    }

    @Test
    void shouldThrowExceptionWhenNotValidPressLevel() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new GasThreshold(123));
    }
}
