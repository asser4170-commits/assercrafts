package com.assercraft.game;

public enum Difficulty {
    PEACEFUL(0, 0.0),
    EASY(1, 0.4),
    NORMAL(2, 0.6),
    HARD(3, 0.85);

    private final int hostileDamage;
    private final double hostileSpawnMultiplier;

    Difficulty(int hostileDamage, double hostileSpawnMultiplier) {
        this.hostileDamage = hostileDamage;
        this.hostileSpawnMultiplier = hostileSpawnMultiplier;
    }

    public int hostileDamage() {
        return hostileDamage;
    }

    public double hostileSpawnMultiplier() {
        return hostileSpawnMultiplier;
    }
}
