package com.assercraft.player;

public final class ItemStack {
    public static final ItemStack EMPTY = new ItemStack("air", 0);
    private final String itemId;
    private int count;

    public ItemStack(String itemId, int count) {
        this.itemId = itemId;
        this.count = count;
    }

    public String itemId() {
        return itemId;
    }

    public int count() {
        return count;
    }

    public void add(int amount) {
        count += amount;
    }

    public void remove(int amount) {
        count = Math.max(0, count - amount);
    }

    public boolean isEmpty() {
        return count <= 0 || "air".equals(itemId);
    }
}
