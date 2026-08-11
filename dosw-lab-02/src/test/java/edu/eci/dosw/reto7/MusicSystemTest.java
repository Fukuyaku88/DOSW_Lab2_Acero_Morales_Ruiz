package edu.eci.dosw.reto7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MusicSystemTest {

    private MusicSystem musicSystem;

    @BeforeEach
    void setUp() {
        musicSystem = new MusicSystem("MS1", 50);
    }

    @Test
    void shouldStoreNameGivenInConstructor() {
        assertEquals("MS1", musicSystem.getName());
    }

    @Test
    void shouldStoreInitialVolumeGivenInConstructor() {
        assertEquals(50, musicSystem.getCurrentV());
    }

    @Test
    void shouldUpdateVolumeAndReturnMessageWhenAdjVolumeIsCalled() {
        String result = musicSystem.adjVolume(80);

        assertEquals("Volume: 80", result);
        assertEquals(80, musicSystem.getCurrentV());
    }

    @Test
    void shouldAllowSettingVolumeToZero() {
        String result = musicSystem.adjVolume(0);

        assertEquals("Volume: 0", result);
        assertEquals(0, musicSystem.getCurrentV());
    }

    @Test
    void shouldReturnVolumeWithPercentSuffixAsState() {
        assertEquals("50% volume", musicSystem.getState());
    }

    @Test
    void shouldReflectVolumeChangeInGetStateAfterAdjustment() {
        musicSystem.adjVolume(99);

        assertEquals("99% volume", musicSystem.getState());
    }

    @Test
    void shouldBeInstanceOfDevice() {
        assertTrue(musicSystem instanceof Device);
    }
}
