package com.assercraft.entity;

 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
import com.assercraft.dimension.DimensionType;

=======
 >>>>>>> main
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class SpawnSystem {
    private final Random random = new Random(99L);

 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
    public List<EntityInstance> spawnTick(
            boolean isNight,
            int lightLevel,
            boolean onGrass,
            DimensionType dimension,
            RegistryAccess definitions
    ) {
        List<EntityInstance> spawned = new ArrayList<>();
        if (isNight || lightLevel < 7) {
            maybeSpawnHostile(spawned, definitions, dimension);
        }
        if (!isNight && onGrass && dimension == DimensionType.OVERWORLD) {
            maybeSpawnPassive(spawned, definitions);
        }
        if (dimension == DimensionType.END && random.nextDouble() < 0.03) {
            defsCreate("enderman", definitions, spawned);
        }
        return spawned;
    }

    private void maybeSpawnHostile(List<EntityInstance> out, RegistryAccess defs, DimensionType dimension) {
        List<String> hostiles;
        if (dimension == DimensionType.NETHER) {
            hostiles = List.of("ghast", "slime", "zombie");
        } else if (dimension == DimensionType.END) {
            hostiles = List.of("enderman");
        } else {
            hostiles = List.of("zombie", "skeleton", "spider", "creeper", "slime", "enderman");
        }

        if (random.nextDouble() < 0.4) {
            String id = hostiles.get(random.nextInt(hostiles.size()));
            defsCreate(id, defs, out);
=======
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
 >>>>>>> main
        }
    }

    private void maybeSpawnPassive(List<EntityInstance> out, RegistryAccess defs) {
        List<String> passives = List.of("cow", "pig", "sheep", "chicken");
        if (random.nextDouble() < 0.3) {
            String id = passives.get(random.nextInt(passives.size()));
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
            defsCreate(id, defs, out);
        }
    }

    private void defsCreate(String id, RegistryAccess defs, List<EntityInstance> out) {
        defs.create(id).ifPresent(out::add);
    }

=======
            defs.create(id).ifPresent(out::add);
        }
    }

 >>>>>>> main
    public interface RegistryAccess {
        java.util.Optional<EntityInstance> create(String id);
    }
}
