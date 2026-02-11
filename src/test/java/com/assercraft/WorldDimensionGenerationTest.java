package com.assercraft;

import com.assercraft.dimension.DimensionType;
import com.assercraft.world.World;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WorldDimensionGenerationTest {
    @Test
    void switchesDimensionAndRegeneratesTerrainFlavor() {
        World world = new World(42L);
        world.streamAround(0, 0, 0);
        String overworldTop = world.getBlock(0, world.findTopSolidY(0, 0), 0).blockId();

        world.setDimension(DimensionType.NETHER);
        world.streamAround(0, 0, 0);
        String netherTop = world.getBlock(0, world.findTopSolidY(0, 0), 0).blockId();

        world.setDimension(DimensionType.END);
        world.streamAround(0, 0, 0);
        String endTop = world.getBlock(0, world.findTopSolidY(0, 0), 0).blockId();

        // deterministic seed should produce dimension-appropriate top block families
        assertTrue(overworldTop.equals("grass") || overworldTop.equals("sand") || overworldTop.equals("stone") || overworldTop.equals("dirt"));
        assertTrue(netherTop.equals("netherrack") || netherTop.equals("soul_sand"));
        assertEquals("end_stone", endTop);
    }
}
