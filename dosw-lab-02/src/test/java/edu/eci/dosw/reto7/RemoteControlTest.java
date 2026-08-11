package edu.eci.dosw.reto7;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RemoteControlTest {

    private RemoteControl remoteControl;
    private User mom;
    private User dad;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream capturedOut;

    @BeforeEach
    void setUp() {
        remoteControl = new RemoteControl();
        mom = new User("0001", "mom");
        dad = new User("0002", "dad");
    }

    @AfterEach
    void restoreSystemOut() {
        System.setOut(originalOut);
    }

    @Test
    void shouldStartWithEmptyHistory() {
        assertTrue(remoteControl.getHistory().isEmpty());
    }

    @Test
    void shouldReturnNoActionsMessageWhenUndoIsCalledOnEmptyStack() {
        String result = remoteControl.undoLastAction(mom);

        assertEquals("No past actions to undo.", result);
    }

    @Test
    void shouldReturnCommandResultWhenExecuteActionIsCalled() {
        Light light = new Light("L1");
        ToggleLight toggleLight = new ToggleLight(light);

        String result = remoteControl.executeAction(toggleLight, mom);

        assertEquals("Light true", result);
    }

    @Test
    void shouldAddRecordToHistoryWhenExecuteActionIsCalled() {
        Light light = new Light("L1");
        ToggleLight toggleLight = new ToggleLight(light);

        remoteControl.executeAction(toggleLight, mom);

        List<ActionRecord> history = remoteControl.getHistory();
        assertEquals(1, history.size());
        ActionRecord record = history.get(0);
        assertEquals(toggleLight, record.rcCommand());
        assertEquals(mom, record.user());
        assertEquals("On", record.deviceStateAtTime());
        assertFalse(record.isUndone());
    }

    @Test
    void shouldAppendMultipleActionsToHistoryInOrder() {
        MusicSystem musicSystem = new MusicSystem("MS1", 50);

        remoteControl.executeAction(new AdjustVolume(musicSystem, 25), mom);
        remoteControl.executeAction(new AdjustVolume(musicSystem, 45), dad);
        remoteControl.executeAction(new AdjustVolume(musicSystem, 99), mom);

        List<ActionRecord> history = remoteControl.getHistory();
        assertEquals(3, history.size());
        assertEquals("25% volume", history.get(0).deviceStateAtTime());
        assertEquals("45% volume", history.get(1).deviceStateAtTime());
        assertEquals("99% volume", history.get(2).deviceStateAtTime());
    }

    @Test
    void shouldUndoMostRecentActionWhenUndoLastActionIsCalled() {
        Door door = new Door("D1", false);
        remoteControl.executeAction(new ToggleDoor(door), mom);

        String undoResult = remoteControl.undoLastAction(dad);

        assertEquals("Close door", undoResult);
        assertFalse(door.isOpen());
    }

    @Test
    void shouldAddUndoRecordToHistoryWithUndoneFlagTrue() {
        Door door = new Door("D1", false);
        remoteControl.executeAction(new ToggleDoor(door), mom);

        remoteControl.undoLastAction(dad);

        List<ActionRecord> history = remoteControl.getHistory();
        assertEquals(2, history.size());
        ActionRecord undoRecord = history.get(1);
        assertTrue(undoRecord.isUndone());
        assertEquals(dad, undoRecord.user());
        assertEquals("Close", undoRecord.deviceStateAtTime());
    }

    @Test
    void shouldNotAddRecordToHistoryWhenUndoIsCalledOnEmptyStack() {
        remoteControl.undoLastAction(mom);

        assertTrue(remoteControl.getHistory().isEmpty());
    }

    @Test
    void shouldOnlyUndoActionsThatHaveNotAlreadyBeenUndone() {
        Door door = new Door("D1", false);
        remoteControl.executeAction(new ToggleDoor(door), mom);

        remoteControl.undoLastAction(dad);
        String secondUndoResult = remoteControl.undoLastAction(mom);

        assertEquals("No past actions to undo.", secondUndoResult);
    }

    @Test
    void shouldUndoActionsInLastInFirstOutOrder() {
        Light light = new Light("L1");
        Door door = new Door("D1", false);

        remoteControl.executeAction(new ToggleLight(light), mom);
        remoteControl.executeAction(new ToggleDoor(door), dad);

        String firstUndo = remoteControl.undoLastAction(mom);
        assertEquals("Close door", firstUndo);

        String secondUndo = remoteControl.undoLastAction(mom);
        assertEquals("Light false", secondUndo);
    }

    @Test
    void shouldPrintActionHistoryWithUserActionAndSummaryDetails() {
        capturedOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturedOut));

        Light light = new Light("L1");
        remoteControl.executeAction(new ToggleLight(light), mom);
        remoteControl.undoLastAction(dad);

        remoteControl.printActionHistory();

        String output = capturedOut.toString();
        assertTrue(output.contains("User Name: mom"));
        assertTrue(output.contains("User Name: dad"));
        assertTrue(output.contains("Action: ToggleLight"));
        assertTrue(output.contains("=== SUMMARY DETAILS ==="));
        assertTrue(output.contains("Total Regular Actions: 1"));
        assertTrue(output.contains("Total Undone Operations: 1"));
        assertTrue(output.contains("Total History Records: 2"));
    }

    @Test
    void shouldPrintZeroCountsWhenHistoryIsEmpty() {
        capturedOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturedOut));

        remoteControl.printActionHistory();

        String output = capturedOut.toString();
        assertTrue(output.contains("Total Regular Actions: 0"));
        assertTrue(output.contains("Total Undone Operations: 0"));
        assertTrue(output.contains("Total History Records: 0"));
    }
}
