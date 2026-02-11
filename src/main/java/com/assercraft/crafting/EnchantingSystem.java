package com.assercraft.crafting;

import java.util.List;
import java.util.Random;

public final class EnchantingSystem {
    private final List<String> enchantments = List.of("sharpness", "efficiency", "protection", "power");

    public String enchant(String itemId, int lapisLevel, long seed) {
        Random random = new Random(seed + itemId.hashCode() + lapisLevel * 31L);
        String chosen = enchantments.get(random.nextInt(enchantments.size()));
        int level = Math.max(1, Math.min(5, 1 + lapisLevel / 3));
        return chosen + "_" + level;
    }
}
