package com.assercraft.world.gen;

import com.assercraft.dimension.DimensionType;
import com.assercraft.world.BlockState;
import com.assercraft.world.Chunk;
import com.assercraft.world.ChunkPos;

import java.util.Random;

public final class WorldGenerator {
    private final long seed;

    public WorldGenerator(long seed) {
        this.seed = seed;
    }

    public Chunk generate(ChunkPos pos, DimensionType dimension) {
        return switch (dimension) {
            case OVERWORLD -> generateOverworld(pos);
            case NETHER -> generateNether(pos);
            case END -> generateEnd(pos);
        };
    }

    private Chunk generateOverworld(ChunkPos pos) {
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

    private Chunk generateNether(ChunkPos pos) {
        Chunk chunk = new Chunk();
        Random random = new Random((seed + 7) ^ (pos.x() * 918273645L) ^ (pos.z() * 123456789L));
        for (int x = 0; x < Chunk.SIZE; x++) {
            for (int z = 0; z < Chunk.SIZE; z++) {
                int floor = 24 + random.nextInt(20);
                int ceiling = 96 + random.nextInt(20);
                for (int y = 0; y < Chunk.HEIGHT; y++) {
                    if (y == 0) {
                        chunk.set(x, y, z, new BlockState("bedrock"));
                    } else if (y < floor) {
                        chunk.set(x, y, z, new BlockState(random.nextDouble() < 0.12 ? "soul_sand" : "netherrack"));
                    } else if (y > ceiling) {
                        chunk.set(x, y, z, new BlockState("netherrack"));
                    } else if (y < 20 && random.nextDouble() < 0.08) {
                        chunk.set(x, y, z, new BlockState("lava"));
                    }
                }
            }
        }
        return chunk;
    }

    private Chunk generateEnd(ChunkPos pos) {
        Chunk chunk = new Chunk();
        Random random = new Random((seed + 13) ^ (pos.x() * 75681234L) ^ (pos.z() * 92837465L));
        for (int x = 0; x < Chunk.SIZE; x++) {
            for (int z = 0; z < Chunk.SIZE; z++) {
                int height = 48 + random.nextInt(10);
                for (int y = 0; y < Chunk.HEIGHT; y++) {
                    if (y == 0) {
                        chunk.set(x, y, z, new BlockState("bedrock"));
                    } else if (y < height) {
                        chunk.set(x, y, z, new BlockState("end_stone"));
                    } else if (y == height && random.nextDouble() < 0.003) {
                        chunk.set(x, y, z, new BlockState("obsidian"));
                    }
                }
            }
        }
        return chunk;
    }
}
