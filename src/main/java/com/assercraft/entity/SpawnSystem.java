package com.assercraft.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class SpawnSystem {
    private final Random random = new Random(99L);

    public List<EntityInstance> spawnTick(boolean isNight, int lightLevel, boolean onGrass, RegistryAccess definitions) {
        List<EntityInstance> spawned = new ArrayList<>();
        if (isNight || lightLevel < 7) {
            maybeSpawnHostile(spawned, definitions);
        }
        if (!isNight && onGrass) {
            maybeSpawnPassive(spawned, definitions);
        }
        return spawned;
    }

    private void maybeSpawnHostile(List<EntityInstance> out, RegistryAccess defs) {
        List<String> hostiles = List.of("zombie", "skeleton", "spider", "creeper", "slime", "ghast", "enderman");
        if (random.nextDouble() < 0.4) {
            String id = hostiles.get(random.nextInt(hostiles.size()));
            defs.create(id).ifPresent(out::add);
        }
    }

    private void maybeSpawnPassive(List<EntityInstance> out, RegistryAccess defs) {
        List<String> passives = List.of("cow", "pig", "sheep", "chicken");
        if (random.nextDouble() < 0.3) {
            String id = passives.get(random.nextInt(passives.size()));
            defs.create(id).ifPresent(out::add);
        }
    }

    public interface RegistryAccess {
        java.util.Optional<EntityInstance> create(String id);
    }
}
