package com.assercraft.world;

public record BlockState(String blockId) {
    public static final BlockState AIR = new BlockState("air");
}
