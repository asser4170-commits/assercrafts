package com.assercraft.ui;

import com.assercraft.player.Player;

public final class Hud {
    public String render(Player player) {
        return render(player, 0, 15, 0);
    }

    public String render(Player player, int pickups, int skylight, int fluidsMoved) {
        return "HP: " + player.health()
                + " | Hotbar slots: 9"
                + " | Pickups: " + pickups
                + " | Skylight: " + skylight
                + " | FluidMoves: " + fluidsMoved;
    }
}
