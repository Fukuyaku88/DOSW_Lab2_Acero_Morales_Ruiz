package edu.eci.dosw.reto7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WindowBlindsTest {

    private WindowBlinds closedBlinds;
    private WindowBlinds openBlinds;

    @BeforeEach
    void setUp() {
        closedBlinds = new WindowBlinds("WB1", false);
        openBlinds = new WindowBlinds("WB2", true);
    }

    @Test
    void shouldStoreNameGivenInConstructor() {
        assertEquals("WB1", closedBlinds.getName());
    }

    @Test
    void shouldStoreInitialOpenStateGivenInConstructor() {
        assertFalse(closedBlinds.isOpen());
        assertTrue(openBlinds.isOpen());
    }

    @Test
    void shouldReturnOpenBlindsMessageAndSetOpenStateWhenOpenIsCalled() {
        String result = closedBlinds.open();

        assertEquals("Open blinds", result);
        assertTrue(closedBlinds.isOpen());
    }

    @Test
    void shouldReturnCloseBlindsMessageAndSetClosedStateWhenCloseIsCalled() {
        String result = openBlinds.close();

        assertEquals("Close blinds", result);
        assertFalse(openBlinds.isOpen());
    }

    @Test
    void shouldReturnOpenStateStringWhenBlindsAreOpen() {
        assertEquals("Open", openBlinds.getState());
    }

    @Test
    void shouldReturnCloseStateStringWhenBlindsAreClosed() {
        assertEquals("Close", closedBlinds.getState());
    }

    @Test
    void shouldReflectStateChangeInGetStateAfterOpening() {
        closedBlinds.open();

        assertEquals("Open", closedBlinds.getState());
    }

    @Test
    void shouldReflectStateChangeInGetStateAfterClosing() {
        openBlinds.close();

        assertEquals("Close", openBlinds.getState());
    }

    @Test
    void shouldBeInstanceOfDevice() {
        assertTrue(closedBlinds instanceof Device);
    }
}
