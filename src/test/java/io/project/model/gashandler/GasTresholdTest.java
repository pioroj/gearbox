package io.project.model.gashandler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GasTresholdTest {

    @Test
    void shouldCreateValidGasThreshold() {
        GasThreshold gasThreshold = new GasThreshold(12);
        assertEquals(12, gasThreshold.getPressLevel());
    }

    @Test
    void shouldThrowExceptionWhenNotValidPressLevel() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new GasThreshold(123));
    }
}
