package com.assercraft.ui;

import com.assercraft.player.Player;

public final class Hud {
    public String render(Player player) {
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-iqjpk1
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-6yr25b
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-9heqsw
 >>>>>>> main
 >>>>>>> main
 >>>>>>> main
        return render(player, 0, 15, 0);
    }

    public String render(Player player, int pickups, int skylight, int fluidsMoved) {
        return "HP: " + player.health()
                + " | Hotbar slots: 9"
                + " | Pickups: " + pickups
                + " | Skylight: " + skylight
                + " | FluidMoves: " + fluidsMoved;
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-iqjpk1
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-6yr25b
=======
=======
        return "HP: " + player.health() + " | Hotbar slots: 9";
 >>>>>>> main
 >>>>>>> main
 >>>>>>> main
 >>>>>>> main
    }
}
