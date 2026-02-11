package com.assercraft.player;

import java.util.ArrayList;
import java.util.List;

public final class Inventory {
    public static final int SLOT_COUNT = 36;
    public static final int HOTBAR_COUNT = 9;
    private final List<ItemStack> slots = new ArrayList<>(SLOT_COUNT);

    public Inventory() {
        for (int i = 0; i < SLOT_COUNT; i++) {
            slots.add(ItemStack.EMPTY);
        }
    }

    public boolean addItem(String itemId, int amount) {
        for (int i = 0; i < slots.size(); i++) {
            ItemStack stack = slots.get(i);
            if (stack != ItemStack.EMPTY && stack.itemId().equals(itemId)) {
                stack.add(amount);
                return true;
            }
        }
        for (int i = 0; i < slots.size(); i++) {
            if (slots.get(i) == ItemStack.EMPTY || slots.get(i).isEmpty()) {
                slots.set(i, new ItemStack(itemId, amount));
                return true;
            }
        }
        return false;
    }

 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-9heqsw
    public boolean hasItem(String itemId, int amount) {
        return slots.stream()
                .filter(s -> s != ItemStack.EMPTY)
                .filter(s -> s.itemId().equals(itemId))
                .mapToInt(ItemStack::count)
                .sum() >= amount;
    }

    public boolean removeItem(String itemId, int amount) {
        int remaining = amount;
      for (int i = 0; i < slots.size() && remaining > 0; i++) {
            ItemStack stack = slots.get(i);
            if (stack == ItemStack.EMPTY || !stack.itemId().equals(itemId)) {
                continue;
            }
            int take = Math.min(remaining, stack.count());
            stack.remove(take);
            remaining -= take;
            if (stack.isEmpty()) {
                slots.set(i, ItemStack.EMPTY);
            }
        }
        return remaining == 0;
    }

=======
 >>>>>>> main
    public List<ItemStack> hotbar() {
        return slots.subList(0, HOTBAR_COUNT);
    }

    public List<ItemStack> slots() {
        return List.copyOf(slots);
    }
}
