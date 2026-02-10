package com.assercraft.entity;

import java.util.List;

public record EntityDefinition(
        String id,
        String type,
        int health,
        List<String> drops
) {}
