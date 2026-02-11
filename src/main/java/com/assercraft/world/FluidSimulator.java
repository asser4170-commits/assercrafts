package com.assercraft.world;

public final class FluidSimulator {
    public int tick(World world, int centerX, int centerZ, int radius) {
        int moved = 0;
        for (int x = centerX - radius; x <= centerX + radius; x++) {
            for (int z = centerZ - radius; z <= centerZ + radius; z++) {
                for (int y = 1; y < Chunk.HEIGHT; y++) {
                    BlockState state = world.getBlock(x, y, z);
                    if (!isFluid(state)) {
                        continue;
                    }
                    if (world.getBlock(x, y - 1, z).equals(BlockState.AIR)) {
                        world.setBlock(x, y - 1, z, state);
                        world.setBlock(x, y, z, BlockState.AIR);
                        moved++;
                        continue;
                    }
                    if (spreadHorizontal(world, x, y, z, state)) {
                        moved++;
                    }
                }
            }
        }
        return moved;
    }

    private boolean spreadHorizontal(World world, int x, int y, int z, BlockState state) {
        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] direction : directions) {
            int nx = x + direction[0];
            int nz = z + direction[1];
            if (world.getBlock(nx, y, nz).equals(BlockState.AIR)) {
                world.setBlock(nx, y, nz, state);
                return true;
            }
        }
        return false;
    }

    private static boolean isFluid(BlockState state) {
        return "water".equals(state.blockId()) || "lava".equals(state.blockId());
    }
}
