package com.assercraft.world.gen;

import com.assercraft.world.BlockState;
import com.assercraft.world.Chunk;
import com.assercraft.world.ChunkPos;

import java.util.Random;

public final class WorldGenerator {
    private final long seed;

    public WorldGenerator(long seed) {
        this.seed = seed;
    }

    public Chunk generate(ChunkPos pos) {
        Chunk chunk = new Chunk();
        Random random = new Random(seed ^ (pos.x() * 341873128712L) ^ (pos.z() * 132897987541L));
        for (int x = 0; x < Chunk.SIZE; x++) {
            for (int z = 0; z < Chunk.SIZE; z++) {
                int height = 52 + random.nextInt(20);
                for (int y = 0; y < Chunk.HEIGHT; y++) {
                    if (y == 0) {
                        chunk.set(x, y, z, new BlockState("bedrock"));
                    } else if (y < height - 4) {
                        chunk.set(x, y, z, new BlockState("stone"));
                    } else if (y < height - 1) {
                        chunk.set(x, y, z, new BlockState("dirt"));
                    } else if (y == height - 1) {
                        chunk.set(x, y, z, new BlockState(height < 56 ? "sand" : "grass"));
                    } else if (y < 62) {
                        chunk.set(x, y, z, new BlockState("water"));
                    }
                }
            }
        }
        return chunk;
    }
}
