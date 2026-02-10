package com.assercraft.crafting;

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
}
