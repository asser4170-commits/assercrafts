package com.assercraft.crafting;

 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
import com.assercraft.player.Inventory;

=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-iqjpk1
import com.assercraft.player.Inventory;

=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-6yr25b
import com.assercraft.player.Inventory;

=======
 >>>>>>> main
 >>>>>>> main
 >>>>>>> main
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
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-iqjpk1
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-6yr25b
 >>>>>>> main
 >>>>>>> main

    public boolean craft(Inventory inventory, List<String> grid, int width, int height) {
        Optional<Recipe> match = match(grid, width, height);
        if (match.isEmpty()) {
            return false;
        }
        Recipe recipe = match.get();
        for (String input : recipe.pattern()) {
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
            if ("air".equals(input)) {
                continue;
            }
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-iqjpk1
            if ("air".equals(input)) {
                continue;
            }
=======
 >>>>>>> main
 >>>>>>> main
            if (!inventory.hasItem(input, 1)) {
                return false;
            }
        }
        for (String input : recipe.pattern()) {
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-iqjpk1
 >>>>>>> main
            if (!"air".equals(input)) {
                inventory.removeItem(input, 1);
            }
        }
        return inventory.addItem(recipe.result(), recipe.resultCount());
    }
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
=======
=======
            inventory.removeItem(input, 1);
        }
        return inventory.addItem(recipe.result(), recipe.resultCount());
    }
=======
 >>>>>>> main
 >>>>>>> main
 >>>>>>> main
}
