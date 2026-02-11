package com.assercraft.world;

public final class LightEngine {
    public int skylightAt(World world, int x, int y, int z, boolean isNight) {
        int top = world.findTopSolidY(x, z);
        int sky = isNight ? 4 : 15;
        if (y > top) {
            return sky;
        }
        int blocked = Math.max(0, top - y + 1);
        return Math.max(0, sky - blocked / 2);
    }

    public int blockLightAt(World world, int x, int y, int z) {
        BlockState state = world.getBlock(x, y, z);
        if ("torch".equals(state.blockId()) || "glowstone".equals(state.blockId())) {
            return 14;
        }
        return 0;
    }
}
