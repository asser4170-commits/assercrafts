package com.assercraft.world;

import com.assercraft.world.gen.WorldGenerator;

import java.util.HashMap;
import java.util.Map;

public final class World {
    private final Map<ChunkPos, Chunk> chunks = new HashMap<>();
    private final WorldGenerator generator;

    public World(long seed) {
        this.generator = new WorldGenerator(seed);
    }

    public Chunk getOrCreateChunk(ChunkPos pos) {
        return chunks.computeIfAbsent(pos, generator::generate);
    }

    public int loadedChunkCount() {
        return chunks.size();
    }

    public void streamAround(int centerX, int centerZ, int radius) {
        for (int x = centerX - radius; x <= centerX + radius; x++) {
            for (int z = centerZ - radius; z <= centerZ + radius; z++) {
                getOrCreateChunk(new ChunkPos(x, z));
            }
        }
    }

    public BlockState getBlock(int x, int y, int z) {
        if (y < 0 || y >= Chunk.HEIGHT) {
            return BlockState.AIR;
        }
        ChunkPos pos = toChunkPos(x, z);
        Chunk chunk = getOrCreateChunk(pos);
        return chunk.get(toLocal(x), y, toLocal(z));
    }

    public void setBlock(int x, int y, int z, BlockState state) {
        if (y < 0 || y >= Chunk.HEIGHT) {
            return;
        }
        ChunkPos pos = toChunkPos(x, z);
        Chunk chunk = getOrCreateChunk(pos);
        chunk.set(toLocal(x), y, toLocal(z), state);
    }

    public int findTopSolidY(int x, int z) {
        for (int y = Chunk.HEIGHT - 1; y >= 0; y--) {
            if (!getBlock(x, y, z).equals(BlockState.AIR) && !"water".equals(getBlock(x, y, z).blockId())) {
                return y;
            }
        }
        return 0;
    }

    private static ChunkPos toChunkPos(int x, int z) {
        return new ChunkPos(Math.floorDiv(x, Chunk.SIZE), Math.floorDiv(z, Chunk.SIZE));
    }

    private static int toLocal(int worldCoord) {
        return Math.floorMod(worldCoord, Chunk.SIZE);
    }
}
