package com.assercraft.crafting;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class FurnaceSystem {
    private final Map<String, String> smeltingMap = new HashMap<>();

    public FurnaceSystem() {
        smeltingMap.put("iron_ore", "iron_ingot");
        smeltingMap.put("gold_ore", "gold_ingot");
        smeltingMap.put("sand", "glass");
        smeltingMap.put("raw_pork", "cooked_pork");
        smeltingMap.put("raw_beef", "steak");
        smeltingMap.put("raw_chicken", "cooked_chicken");
    }

    public Optional<String> smelt(String input) {
        return Optional.ofNullable(smeltingMap.get(input));
    }
}
