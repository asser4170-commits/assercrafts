package com.assercraft.world;

 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
import com.assercraft.dimension.DimensionType;
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-iqjpk1
import com.assercraft.dimension.DimensionType;
=======
 >>>>>>> main
 >>>>>>> main
import com.assercraft.world.gen.WorldGenerator;

import java.util.HashMap;
import java.util.Map;

public final class World {
    private final Map<ChunkPos, Chunk> chunks = new HashMap<>();
    private final WorldGenerator generator;
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
    private DimensionType dimension = DimensionType.OVERWORLD;
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-iqjpk1
    private DimensionType dimension = DimensionType.OVERWORLD;
=======
 >>>>>>> main
 >>>>>>> main

    public World(long seed) {
        this.generator = new WorldGenerator(seed);
    }

    public Chunk getOrCreateChunk(ChunkPos pos) {
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
        return chunks.computeIfAbsent(pos, p -> generator.generate(p, dimension));
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-iqjpk1
        return chunks.computeIfAbsent(pos, p -> generator.generate(p, dimension));
=======
        return chunks.computeIfAbsent(pos, generator::generate);
 >>>>>>> main
 >>>>>>> main
    }

    public int loadedChunkCount() {
        return chunks.size();
    }

 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-iqjpk1
 >>>>>>> main
    public void setDimension(DimensionType dimension) {
        if (this.dimension != dimension) {
            this.dimension = dimension;
            chunks.clear();
        }
    }

    public DimensionType dimension() {
        return dimension;
    }

 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
=======
=======
 >>>>>>> main
 >>>>>>> main
    public void streamAround(int centerX, int centerZ, int radius) {
        for (int x = centerX - radius; x <= centerX + radius; x++) {
            for (int z = centerZ - radius; z <= centerZ + radius; z++) {
                getOrCreateChunk(new ChunkPos(x, z));
            }
        }
    }
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-iqjpk1
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-6yr25b
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-9heqsw
 >>>>>>> main
 >>>>>>> main
 >>>>>>> main

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
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
            BlockState state = getBlock(x, y, z);
            if (!state.equals(BlockState.AIR) && !"water".equals(state.blockId()) && !"lava".equals(state.blockId())) {
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-iqjpk1
            BlockState state = getBlock(x, y, z);
            if (!state.equals(BlockState.AIR) && !"water".equals(state.blockId()) && !"lava".equals(state.blockId())) {
=======
            if (!getBlock(x, y, z).equals(BlockState.AIR) && !"water".equals(getBlock(x, y, z).blockId())) {
 >>>>>>> main
 >>>>>>> main
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
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-iqjpk1
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-6yr25b
=======
======= 
  
 >>>>>>> main
 >>>>>>> main
 >>>>>>> main
 >>>>>>> main
}
