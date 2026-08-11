package edu.eci.dosw.reto7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdjustBlindTest {

    @Test
    void shouldOpenBlindsWhenExecuteIsCalledOnClosedBlinds() {
        WindowBlinds blinds = new WindowBlinds("WB1", false);
        AdjustBlind adjustBlind = new AdjustBlind(blinds);

        String result = adjustBlind.execute();

        assertEquals("Open blinds", result);
        assertTrue(blinds.isOpen());
    }

    @Test
    void shouldCloseBlindsWhenExecuteIsCalledOnOpenBlinds() {
        WindowBlinds blinds = new WindowBlinds("WB1", true);
        AdjustBlind adjustBlind = new AdjustBlind(blinds);

        String result = adjustBlind.execute();

        assertEquals("Close blinds", result);
        assertFalse(blinds.isOpen());
    }

    @Test
    void shouldRestorePreviousClosedStateWhenUndoIsCalledAfterExecute() {
        WindowBlinds blinds = new WindowBlinds("WB1", false);
        AdjustBlind adjustBlind = new AdjustBlind(blinds);

        adjustBlind.execute();
        String undoResult = adjustBlind.undo();

        assertEquals("Close blinds", undoResult);
        assertFalse(blinds.isOpen());
    }

    @Test
    void shouldRestorePreviousOpenStateWhenUndoIsCalledAfterExecute() {
        WindowBlinds blinds = new WindowBlinds("WB1", true);
        AdjustBlind adjustBlind = new AdjustBlind(blinds);

        adjustBlind.execute();
        String undoResult = adjustBlind.undo();

        assertEquals("Open blinds", undoResult);
        assertTrue(blinds.isOpen());
    }

    @Test
    void shouldReturnUnderlyingWindowBlindsAsDevice() {
        WindowBlinds blinds = new WindowBlinds("WB1", false);
        AdjustBlind adjustBlind = new AdjustBlind(blinds);

        assertEquals(blinds, adjustBlind.getDevice());
    }

    @Test
    void shouldReturnSimpleClassNameAsCommandName() {
        WindowBlinds blinds = new WindowBlinds("WB1", false);
        AdjustBlind adjustBlind = new AdjustBlind(blinds);

        assertEquals("AdjustBlind", adjustBlind.getCName());
    }
}
