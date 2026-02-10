package com.assercraft.world;

public final class Chunk {
    public static final int SIZE = 16;
    public static final int HEIGHT = 128;
    private final BlockState[][][] blocks = new BlockState[SIZE][HEIGHT][SIZE];

    public Chunk() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                for (int z = 0; z < SIZE; z++) {
                    blocks[x][y][z] = BlockState.AIR;
                }
            }
        }
    }

    public BlockState get(int x, int y, int z) {
        return blocks[x][y][z];
    }

    public void set(int x, int y, int z, BlockState block) {
        blocks[x][y][z] = block;
    }
}
