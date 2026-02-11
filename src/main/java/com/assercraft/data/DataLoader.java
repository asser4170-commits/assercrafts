package com.assercraft.data;

import com.assercraft.block.BlockDefinition;
import com.assercraft.entity.EntityDefinition;
import com.assercraft.item.ItemDefinition;
import com.assercraft.registry.Registry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public final class DataLoader {
    public void loadBlocks(Registry<BlockDefinition> blocks, String resourcePath) {
        readLines(resourcePath).forEach(line -> {
            String[] p = line.split("\\|");
            requireParts(resourcePath, line, p, 6);
            blocks.register(p[0], new BlockDefinition(
                    p[0],
                    Float.parseFloat(p[1]),
                    p[2],
                    Arrays.stream(p[3].split(",")).filter(s -> !s.isBlank()).toList(),
                    Boolean.parseBoolean(p[4]),
                    Boolean.parseBoolean(p[5])
            ));
        });
    }

    public void loadItems(Registry<ItemDefinition> items, String resourcePath) {
        readLines(resourcePath).forEach(line -> {
            String[] p = line.split("\\|");
            requireParts(resourcePath, line, p, 4);
            items.register(p[0], new ItemDefinition(p[0], p[1], Integer.parseInt(p[2]), Integer.parseInt(p[3])));
        });
    }

    public void loadEntities(Registry<EntityDefinition> entities, String resourcePath) {
        readLines(resourcePath).forEach(line -> {
            String[] p = line.split("\\|");
            requireParts(resourcePath, line, p, 4);
            List<String> drops = Arrays.stream(p[3].split(",")).filter(s -> !s.isBlank()).toList();
            entities.register(p[0], new EntityDefinition(p[0], p[1], Integer.parseInt(p[2]), drops));
        });
    }

    private List<String> readLines(String resourcePath) {
        InputStream in = getClass().getResourceAsStream(resourcePath);
        if (in == null) {
            throw new IllegalArgumentException("Missing resource: " + resourcePath);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            return reader.lines()
                    .map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .filter(line -> !line.startsWith("#"))
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Unable to read " + resourcePath, e);
        }
    }

    private static void requireParts(String resourcePath, String line, String[] parts, int required) {
        if (parts.length < required) {
            throw new IllegalArgumentException(
                    "Malformed data in " + resourcePath + " (expected " + required + " fields): " + line
            );
        }
    }
}
