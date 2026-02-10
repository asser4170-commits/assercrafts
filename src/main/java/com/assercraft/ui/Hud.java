package com.assercraft.ui;

import com.assercraft.player.Player;

public final class Hud {
    public String render(Player player) {
        return "HP: " + player.health() + " | Hotbar slots: 9";
    }
}
