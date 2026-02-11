package com.assercraft.world;

import com.assercraft.block.BlockDefinition;
import com.assercraft.player.Inventory;
import com.assercraft.registry.Registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class BlockInteractionSystem {
    private final Registry<BlockDefinition> blocks;

    public BlockInteractionSystem(Registry<BlockDefinition> blocks) {
        this.blocks = blocks;
    }

    public Optional<BreakResult> breakBlock(World world, int x, int y, int z, float toolPower) {
        BlockState state = world.getBlock(x, y, z);
        if (state.equals(BlockState.AIR)) {
            return Optional.empty();
        }
        BlockDefinition def = blocks.get(state.blockId()).orElse(null);
        if (def == null || def.hardness() > toolPower) {
            return Optional.empty();
        }

        world.setBlock(x, y, z, BlockState.AIR);
        List<String> drops = new ArrayList<>(def.drops());
        return Optional.of(new BreakResult(state.blockId(), drops));
    }

    public boolean placeBlock(World world, Inventory inventory, int x, int y, int z, String blockItemId) {
        if (!inventory.hasItem(blockItemId, 1)) {
            return false;
        }
        if (!world.getBlock(x, y, z).equals(BlockState.AIR)) {
            return false;
        }
        if (!blocks.get(blockItemId).isPresent()) {
            return false;
        }

        if (!inventory.removeItem(blockItemId, 1)) {
            return false;
        }
        world.setBlock(x, y, z, new BlockState(blockItemId));
        return true;
    }

    public record BreakResult(String blockId, List<String> drops) {}
}
