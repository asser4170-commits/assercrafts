package com.assercraft;

import com.assercraft.game.AsserCraftGame;
import org.junit.jupiter.api.Test;

class AsserCraftBootstrapTest {
    @Test
    void bootsAndTicksWithoutCrash() {
        AsserCraftGame game = new AsserCraftGame();
        game.bootstrap();
        game.runForTicks(2);
    }
}
