package com.assercraft.entity;

public final class ItemEntity {
    private final String itemId;
    private int count;
    private double x;
    private double y;
    private double z;
    private double vy;

    public ItemEntity(String itemId, int count, double x, double y, double z) {
        this.itemId = itemId;
        this.count = count;
        this.x = x;
        this.y = y;
        this.z = z;
        this.vy = 0.15;
    }

    public void tickPhysics() {
        vy -= 0.04;
        y += vy;
        if (y <= 0) {
            y = 0;
            vy *= -0.2;
            if (Math.abs(vy) < 0.01) {
                vy = 0;
            }
        }
    }

    public boolean canPickup(double px, double py, double pz) {
        double dx = px - x;
        double dy = py - y;
        double dz = pz - z;
        return (dx * dx + dy * dy + dz * dz) <= 2.25;
    }

    public String itemId() {
        return itemId;
    }

    public int count() {
        return count;
    }

    public void consume() {
        count = 0;
    }

    public boolean isAlive() {
        return count > 0;
    }
}
