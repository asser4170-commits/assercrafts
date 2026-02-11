package com.assercraft.game;

import com.assercraft.player.Player;
import com.assercraft.world.World;

public final class PhysicsSystem {
    public void applyPlayerPhysics(Player player, World world) {
        int top = world.findTopSolidY((int) Math.floor(player.x()), (int) Math.floor(player.z()));
        double targetY = top + 1.0;
        if (player.y() < targetY) {
            player.move(0, targetY - player.y(), 0);
        } else if (player.y() > targetY + 3.0) {
            player.move(0, -0.2, 0);
        }
    }
}
