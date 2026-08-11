package edu.eci.dosw.reto7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DoorTest {

    private Door closedDoor;
    private Door openDoor;

    @BeforeEach
    void setUp() {
        closedDoor = new Door("D1", false);
        openDoor = new Door("D2", true);
    }

    @Test
    void shouldStoreNameGivenInConstructor() {
        assertEquals("D1", closedDoor.getName());
    }

    @Test
    void shouldStoreInitialOpenStateGivenInConstructor() {
        assertFalse(closedDoor.isOpen());
        assertTrue(openDoor.isOpen());
    }

    @Test
    void shouldReturnOpenDoorMessageAndSetOpenStateWhenOpenIsCalled() {
        String result = closedDoor.open();

        assertEquals("Open door", result);
        assertTrue(closedDoor.isOpen());
    }

    @Test
    void shouldReturnCloseDoorMessageAndSetClosedStateWhenCloseIsCalled() {
        String result = openDoor.close();

        assertEquals("Close door", result);
        assertFalse(openDoor.isOpen());
    }

    @Test
    void shouldKeepOpenStateWhenOpenIsCalledOnAlreadyOpenDoor() {
        openDoor.open();

        assertTrue(openDoor.isOpen());
    }

    @Test
    void shouldKeepClosedStateWhenCloseIsCalledOnAlreadyClosedDoor() {
        closedDoor.close();

        assertFalse(closedDoor.isOpen());
    }

    @Test
    void shouldReturnOpenStateStringWhenDoorIsOpen() {
        assertEquals("Open", openDoor.getState());
    }

    @Test
    void shouldReturnCloseStateStringWhenDoorIsClosed() {
        assertEquals("Close", closedDoor.getState());
    }

    @Test
    void shouldReflectStateChangeInGetStateAfterOpening() {
        closedDoor.open();

        assertEquals("Open", closedDoor.getState());
    }

    @Test
    void shouldReflectStateChangeInGetStateAfterClosing() {
        openDoor.close();

        assertEquals("Close", openDoor.getState());
    }

    @Test
    void shouldBeInstanceOfDevice() {
        assertTrue(closedDoor instanceof Device);
    }
}
