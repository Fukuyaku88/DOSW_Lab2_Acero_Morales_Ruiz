package edu.eci.dosw.reto7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdjustVolumeTest {

    @Test
    void shouldSetTargetVolumeWhenExecuteIsCalled() {
        MusicSystem musicSystem = new MusicSystem("MS1", 50);
        AdjustVolume adjustVolume = new AdjustVolume(musicSystem, 80);

        String result = adjustVolume.execute();

        assertEquals("Volume: 80", result);
        assertEquals(80, musicSystem.getCurrentV());
    }

    @Test
    void shouldRestorePreviousVolumeWhenUndoIsCalledAfterExecute() {
        MusicSystem musicSystem = new MusicSystem("MS1", 50);
        AdjustVolume adjustVolume = new AdjustVolume(musicSystem, 80);

        adjustVolume.execute();
        String undoResult = adjustVolume.undo();

        assertEquals("Volume: 50", undoResult);
        assertEquals(50, musicSystem.getCurrentV());
    }

    @Test
    void shouldReturnUnderlyingMusicSystemAsDevice() {
        MusicSystem musicSystem = new MusicSystem("MS1", 50);
        AdjustVolume adjustVolume = new AdjustVolume(musicSystem, 80);

        assertEquals(musicSystem, adjustVolume.getDevice());
    }

    @Test
    void shouldCaptureVolumeAtExecuteTimeAsPreviousVolumeForUndo() {
        MusicSystem musicSystem = new MusicSystem("MS1", 50);
        AdjustVolume firstAdjust = new AdjustVolume(musicSystem, 70);
        firstAdjust.execute();

        AdjustVolume secondAdjust = new AdjustVolume(musicSystem, 90);
        secondAdjust.execute();

        String undoResult = secondAdjust.undo();

        assertEquals("Volume: 70", undoResult);
        assertEquals(70, musicSystem.getCurrentV());
    }

    @Test
    void shouldReturnSimpleClassNameAsCommandName() {
        MusicSystem musicSystem = new MusicSystem("MS1", 50);
        AdjustVolume adjustVolume = new AdjustVolume(musicSystem, 80);

        assertEquals("AdjustVolume", adjustVolume.getCName());
    }
}
