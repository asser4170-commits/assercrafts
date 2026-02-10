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
}
