package com.assercraft.crafting;

import com.assercraft.player.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class CraftingSystem {
    private final List<Recipe> recipes = new ArrayList<>();

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public Optional<Recipe> match(List<String> grid, int width, int height) {
        return recipes.stream()
                .filter(r -> r.width() == width && r.height() == height)
                .filter(r -> r.pattern().equals(grid))
                .findFirst();
    }

    public boolean craft(Inventory inventory, List<String> grid, int width, int height) {
        Optional<Recipe> match = match(grid, width, height);
        if (match.isEmpty()) {
            return false;
        }
        Recipe recipe = match.get();
        for (String input : recipe.pattern()) {
            if (!inventory.hasItem(input, 1)) {
                return false;
            }
        }
        for (String input : recipe.pattern()) {
            inventory.removeItem(input, 1);
        }
        return inventory.addItem(recipe.result(), recipe.resultCount());
    }
}
