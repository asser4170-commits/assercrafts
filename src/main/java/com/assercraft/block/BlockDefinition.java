package com.assercraft.block;

import java.util.List;

public record BlockDefinition(
        String id,
        float hardness,
        String tool,
        List<String> drops,
        boolean opaque,
        boolean fluid
) {}
