package com.assercraft.crafting;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class BrewingSystem {
    private final Map<String, String> transforms = new HashMap<>();

    public BrewingSystem() {
        transforms.put("awkward_potion+ghast_tear", "regen_potion");
        transforms.put("awkward_potion+fermented_spider_eye", "weakness_potion");
        transforms.put("water_bottle+nether_wart", "awkward_potion");
    }

    public Optional<String> brew(String basePotion, String ingredient) {
        return Optional.ofNullable(transforms.get(basePotion + "+" + ingredient));
    }
}
