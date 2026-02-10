package com.assercraft.ui;

import com.assercraft.player.Inventory;

public final class InventoryScreen {
    public String render(Inventory inventory) {
        return "Inventory slots: " + inventory.slots().size() + " (hotbar: " + Inventory.HOTBAR_COUNT + ")";
    }
}
