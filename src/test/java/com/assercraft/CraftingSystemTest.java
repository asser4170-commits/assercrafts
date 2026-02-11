package com.assercraft;

import com.assercraft.crafting.CraftingSystem;
import com.assercraft.crafting.Recipe;
import com.assercraft.player.Inventory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CraftingSystemTest {
    @Test
    void craftsPlanksFromLog() {
        CraftingSystem crafting = new CraftingSystem();
        crafting.addRecipe(new Recipe("planks", 1, 1, List.of("oak_log"), "oak_planks", 4));

        Inventory inventory = new Inventory();
        inventory.addItem("oak_log", 1);

        boolean crafted = crafting.craft(inventory, List.of("oak_log"), 1, 1);
        assertTrue(crafted);
        assertEquals(4, inventory.countItem("oak_planks"));
    }
}
