package edu.eci.dosw.reto7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ToggleDoorTest {

    @Test
    void shouldOpenClosedDoorWhenExecuteIsCalled() {
        Door door = new Door("D1", false);
        ToggleDoor toggleDoor = new ToggleDoor(door);

        String result = toggleDoor.execute();

        assertEquals("Open door", result);
        assertTrue(door.isOpen());
    }

    @Test
    void shouldCloseOpenDoorWhenExecuteIsCalled() {
        Door door = new Door("D1", true);
        ToggleDoor toggleDoor = new ToggleDoor(door);

        String result = toggleDoor.execute();

        assertEquals("Close door", result);
        assertFalse(door.isOpen());
    }

    @Test
    void shouldRestorePreviousClosedStateWhenUndoIsCalledAfterExecute() {
        Door door = new Door("D1", false);
        ToggleDoor toggleDoor = new ToggleDoor(door);

        toggleDoor.execute();
        String undoResult = toggleDoor.undo();

        assertEquals("Close door", undoResult);
        assertFalse(door.isOpen());
    }

    @Test
    void shouldRestorePreviousOpenStateWhenUndoIsCalledAfterExecute() {
        Door door = new Door("D1", true);
        ToggleDoor toggleDoor = new ToggleDoor(door);

        toggleDoor.execute();
        String undoResult = toggleDoor.undo();

        assertEquals("Open door", undoResult);
        assertTrue(door.isOpen());
    }

    @Test
    void shouldReturnUnderlyingDoorAsDevice() {
        Door door = new Door("D1", false);
        ToggleDoor toggleDoor = new ToggleDoor(door);

        assertEquals(door, toggleDoor.getDevice());
    }

    @Test
    void shouldReturnSimpleClassNameAsCommandName() {
        Door door = new Door("D1", false);
        ToggleDoor toggleDoor = new ToggleDoor(door);

        assertEquals("ToggleDoor", toggleDoor.getCName());
    }
}
