package com.assercraft;

import com.assercraft.entity.EntityInstance;
import com.assercraft.game.CombatSystem;
import com.assercraft.game.Difficulty;
import com.assercraft.player.Player;
import org.junit.jupiter.api.Test;

 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
=======
import java.util.List;

 >>>>>>> main
import static org.junit.jupiter.api.Assertions.assertTrue;

class CombatSystemTest {
    @Test
    void hostileAttackDamagesPlayer() {
        Player player = new Player();
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
        EntityInstance zombie = new EntityInstance("zombie", "hostile", 20, List.of("rotten_flesh"));
=======
        EntityInstance zombie = new EntityInstance("zombie", "hostile", 20);
 >>>>>>> main
        zombie.tick(1.0);

        CombatSystem combat = new CombatSystem();
        int attacks = combat.applyHostileAttacks(player, List.of(zombie), Difficulty.NORMAL);

        assertTrue(attacks > 0);
        assertTrue(player.health() < 20);
    }
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67

    @Test
    void deadEntityUsesItsOwnDropTable() {
        EntityInstance skeleton = new EntityInstance("skeleton", "hostile", 20, List.of("bone", "arrow"));
        skeleton.damage(999);

        CombatSystem combat = new CombatSystem();
        List<String> drops = new ArrayList<>();
        int dropCount = combat.collectDeathDrops(List.of(skeleton), item -> drops.add(item.itemId()));

        assertEquals(2, dropCount);
        assertTrue(drops.contains("bone"));
        assertTrue(drops.contains("arrow"));
    }
=======
 >>>>>>> main
}
