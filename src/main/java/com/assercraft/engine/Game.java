package com.assercraft.engine;

import com.assercraft.game.AsserCraftGame;

public final class Game {
    public static void main(String[] args) {
        AsserCraftGame game = new AsserCraftGame();
        game.bootstrap();
        game.runForTicks(200); // demo run in non-graphical environments
    }
}
