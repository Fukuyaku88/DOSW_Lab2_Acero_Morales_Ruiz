package edu.eci.dosw.reto7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LightTest {

    private Light light;

    @BeforeEach
    void setUp() {
        light = new Light("L1");
    }

    @Test
    void shouldStoreNameGivenInConstructor() {
        assertEquals("L1", light.getName());
    }

    @Test
    void shouldBeOffByDefault() {
        assertFalse(light.isOn());
    }

    @Test
    void shouldReturnOnMessageAndSetOnStateWhenTurnOnIsCalled() {
        String result = light.turnOn();

        assertEquals("Light true", result);
        assertTrue(light.isOn());
    }

    @Test
    void shouldReturnOffMessageAndSetOffStateWhenTurnOffIsCalled() {
        light.turnOn();

        String result = light.turnOff();

        assertEquals("Light false", result);
        assertFalse(light.isOn());
    }

    @Test
    void shouldKeepOnStateWhenTurnOnIsCalledOnAlreadyOnLight() {
        light.turnOn();
        light.turnOn();

        assertTrue(light.isOn());
    }

    @Test
    void shouldKeepOffStateWhenTurnOffIsCalledOnAlreadyOffLight() {
        light.turnOff();

        assertFalse(light.isOn());
    }

    @Test
    void shouldReturnOnStateStringWhenLightIsOn() {
        light.turnOn();

        assertEquals("On", light.getState());
    }

    @Test
    void shouldReturnOffStateStringWhenLightIsOff() {
        assertEquals("Off", light.getState());
    }

    @Test
    void shouldBeInstanceOfDevice() {
        assertTrue(light instanceof Device);
    }
}
