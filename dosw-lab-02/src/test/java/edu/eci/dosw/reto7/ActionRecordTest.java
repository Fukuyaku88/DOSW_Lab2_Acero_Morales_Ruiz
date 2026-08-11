package edu.eci.dosw.reto7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ActionRecordTest {

    @Test
    void shouldExposeAllComponentsGivenInConstructor() {
        Light light = new Light("L1");
        ToggleLight toggleLight = new ToggleLight(light);
        User user = new User("0001", "mom");

        ActionRecord record = new ActionRecord(toggleLight, user, "On", false);

        assertEquals(toggleLight, record.rcCommand());
        assertEquals(user, record.user());
        assertEquals("On", record.deviceStateAtTime());
        assertFalse(record.isUndone());
    }

    @Test
    void shouldExposeIsUndoneAsTrueWhenBuiltAsUndoRecord() {
        Door door = new Door("D1", false);
        ToggleDoor toggleDoor = new ToggleDoor(door);
        User user = new User("0002", "dad");

        ActionRecord record = new ActionRecord(toggleDoor, user, "Close", true);

        assertTrue(record.isUndone());
    }

    @Test
    void shouldConsiderTwoRecordsWithSameComponentsEqual() {
        Light light = new Light("L1");
        ToggleLight toggleLight = new ToggleLight(light);
        User user = new User("0001", "mom");

        ActionRecord record1 = new ActionRecord(toggleLight, user, "On", false);
        ActionRecord record2 = new ActionRecord(toggleLight, user, "On", false);

        assertEquals(record1, record2);
        assertEquals(record1.hashCode(), record2.hashCode());
    }

    @Test
    void shouldProduceToStringContainingComponentValues() {
        Light light = new Light("L1");
        ToggleLight toggleLight = new ToggleLight(light);
        User user = new User("0001", "mom");

        ActionRecord record = new ActionRecord(toggleLight, user, "On", false);

        assertTrue(record.toString().contains("On"));
    }
}
