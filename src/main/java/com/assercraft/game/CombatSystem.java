package com.assercraft.game;

import com.assercraft.entity.EntityInstance;
import com.assercraft.entity.ItemEntity;
import com.assercraft.entity.ai.AiState;
import com.assercraft.player.Player;

import java.util.List;
import java.util.function.Consumer;

public final class CombatSystem {
    public int applyHostileAttacks(Player player, List<EntityInstance> entities, Difficulty difficulty) {
        if (difficulty == Difficulty.PEACEFUL) {
            return 0;
        }
        int attacks = 0;
        for (EntityInstance entity : entities) {
            if (!"hostile".equals(entity.type()) && !"boss".equals(entity.type())) {
                continue;
            }
            if (entity.state() == AiState.ATTACK) {
                player.damage(difficulty.hostileDamage());
                attacks++;
            }
        }
        return attacks;
    }

    public int collectDeathDrops(List<EntityInstance> entities, Consumer<ItemEntity> dropConsumer) {
        int dropped = 0;
        for (EntityInstance entity : entities) {
            if (!entity.isAlive()) {
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
                for (String drop : entity.drops()) {
                    if (!drop.isBlank() && !"none".equals(drop)) {
                        dropConsumer.accept(new ItemEntity(drop, 1, 0.5, 2.0, 0.5));
                        dropped++;
                    }
                }
=======
                dropConsumer.accept(new ItemEntity("rotten_flesh", 1, 0.5, 2.0, 0.5));
                dropped++;
 >>>>>>> main
            }
        }
        return dropped;
    }
}
