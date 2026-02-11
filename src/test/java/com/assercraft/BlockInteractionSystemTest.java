package com.assercraft;

import com.assercraft.block.BlockDefinition;
import com.assercraft.data.DataLoader;
import com.assercraft.player.Inventory;
import com.assercraft.registry.Registry;
import com.assercraft.world.BlockInteractionSystem;
import com.assercraft.world.BlockState;
import com.assercraft.world.World;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BlockInteractionSystemTest {
    @Test
    void supportsPlaceAndBreakWithDrops() {
        Registry<BlockDefinition> blocks = new Registry<>();
        new DataLoader().loadBlocks(blocks, "/data/assercraft/blocks/blocks.db");

        World world = new World(1L);
        Inventory inventory = new Inventory();
        inventory.addItem("oak_planks", 2);

        BlockInteractionSystem system = new BlockInteractionSystem(blocks);
        boolean placed = system.placeBlock(world, inventory, 0, 70, 0, "oak_planks");
        assertTrue(placed);
        assertEquals("oak_planks", world.getBlock(0, 70, 0).blockId());

        var breakResult = system.breakBlock(world, 0, 70, 0, 3.0f);
        assertTrue(breakResult.isPresent());
        assertTrue(breakResult.get().drops().contains("oak_planks"));
        assertEquals(BlockState.AIR, world.getBlock(0, 70, 0));
    }
}
