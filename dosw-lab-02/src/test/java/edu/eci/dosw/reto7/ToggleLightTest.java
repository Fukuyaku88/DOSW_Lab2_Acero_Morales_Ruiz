package edu.eci.dosw.reto7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ToggleLightTest {

    @Test
    void shouldTurnOnLightWhenExecuteIsCalledOnOffLight() {
        Light light = new Light("L1");
        ToggleLight toggleLight = new ToggleLight(light);

        String result = toggleLight.execute();

        assertEquals("Light true", result);
        assertTrue(light.isOn());
    }

    @Test
    void shouldTurnOffLightWhenExecuteIsCalledOnOnLight() {
        Light light = new Light("L1");
        light.turnOn();
        ToggleLight toggleLight = new ToggleLight(light);

        String result = toggleLight.execute();

        assertEquals("Light false", result);
        assertFalse(light.isOn());
    }

    @Test
    void shouldRestorePreviousOffStateWhenUndoIsCalledAfterExecute() {
        Light light = new Light("L1");
        ToggleLight toggleLight = new ToggleLight(light);

        toggleLight.execute();
        String undoResult = toggleLight.undo();

        assertEquals("Light false", undoResult);
        assertFalse(light.isOn());
    }

    @Test
    void shouldRestorePreviousOnStateWhenUndoIsCalledAfterExecute() {
        Light light = new Light("L1");
        light.turnOn();
        ToggleLight toggleLight = new ToggleLight(light);

        toggleLight.execute();
        String undoResult = toggleLight.undo();

        assertEquals("Light true", undoResult);
        assertTrue(light.isOn());
    }

    @Test
    void shouldReturnUnderlyingLightAsDevice() {
        Light light = new Light("L1");
        ToggleLight toggleLight = new ToggleLight(light);

        assertEquals(light, toggleLight.getDevice());
    }

    @Test
    void shouldReturnSimpleClassNameAsCommandName() {
        Light light = new Light("L1");
        ToggleLight toggleLight = new ToggleLight(light);

        assertEquals("ToggleLight", toggleLight.getCName());
    }
}
