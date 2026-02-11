package com.assercraft.player;

public final class Player {
    private double x;
    private double y;
    private double z;
    private int health = 20;
    private final Inventory inventory = new Inventory();

    public void move(double dx, double dy, double dz) {
        x += dx;
        y += dy;
        z += dz;
    }

    public void damage(int amount) {
        health = Math.max(0, health - amount);
    }

    public Inventory inventory() {
        return inventory;
    }

    public int health() {
        return health;
    }

 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-9heqsw
    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double z() {
        return z;
    }

=======
 >>>>>>> main
    public int chunkX() {
        return (int) Math.floor(x / 16.0);
    }

    public int chunkZ() {
        return (int) Math.floor(z / 16.0);
    }
}
