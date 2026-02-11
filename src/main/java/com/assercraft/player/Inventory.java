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

 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-iqjpk1
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-6yr25b
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-9heqsw
 >>>>>>> main
 >>>>>>> main
 >>>>>>> main
    public boolean hasItem(String itemId, int amount) {
        return slots.stream()
                .filter(s -> s != ItemStack.EMPTY)
                .filter(s -> s.itemId().equals(itemId))
                .mapToInt(ItemStack::count)
                .sum() >= amount;
    }

    public boolean removeItem(String itemId, int amount) {
        int remaining = amount;
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
        for (int i = 0; i < slots.size() && remaining > 0; i++) {
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-iqjpk1
        for (int i = 0; i < slots.size() && remaining > 0; i++) {
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-6yr25b
        for (int i = 0; i < slots.size() && remaining > 0; i++) {
=======
      for (int i = 0; i < slots.size() && remaining > 0; i++) {
 >>>>>>> main
 >>>>>>> main
 >>>>>>> main
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

 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-iqjpk1
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-6yr25b
 >>>>>>> main
 >>>>>>> main

    public int countItem(String itemId) {
        return slots.stream()
                .filter(s -> s != ItemStack.EMPTY)
                .filter(s -> s.itemId().equals(itemId))
                .mapToInt(ItemStack::count)
                .sum();
    }

 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-iqjpk1
=======
=======
=======
 >>>>>>> main
 >>>>>>> main
 >>>>>>> main
 >>>>>>> main
    public List<ItemStack> hotbar() {
        return slots.subList(0, HOTBAR_COUNT);
    }

    public List<ItemStack> slots() {
        return List.copyOf(slots);
    }
}
