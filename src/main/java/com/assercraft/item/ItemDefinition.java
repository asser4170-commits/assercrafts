package com.assercraft.item;

public record ItemDefinition(
        String id,
        String category,
        int maxStack,
        int maxDurability
) {}
