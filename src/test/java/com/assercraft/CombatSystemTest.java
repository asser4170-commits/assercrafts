package com.assercraft;

import com.assercraft.entity.EntityInstance;
import com.assercraft.game.CombatSystem;
import com.assercraft.game.Difficulty;
import com.assercraft.player.Player;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CombatSystemTest {
    @Test
    void hostileAttackDamagesPlayer() {
        Player player = new Player();
        EntityInstance zombie = new EntityInstance("zombie", "hostile", 20);
        zombie.tick(1.0);

        CombatSystem combat = new CombatSystem();
        int attacks = combat.applyHostileAttacks(player, List.of(zombie), Difficulty.NORMAL);

        assertTrue(attacks > 0);
        assertTrue(player.health() < 20);
    }
}
