package edu.eci.dosw.reto7;

public record ActionRecord(
        RCCommand rcCommand,
        User user,
        String deviceStateAtTime,
        boolean isUndone
) { }
