package org.playbox.instances.lobby.menus;

import net.minestom.server.entity.Player;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.inventory.click.ClickType;
import org.playbox.services.GamesService;

public class GamesMenu {
    public static Inventory INVENTORY;

    static {
        Inventory inventoryBuilder = new Inventory(InventoryType.CHEST_6_ROW, "Games menu");

        setItems(inventoryBuilder);

        inventoryBuilder.addInventoryCondition((player, slot, clickType, result) -> {
            // Cancelling all events (drop, move, click, etc.)
            result.setCancel(true);
        });

        INVENTORY = inventoryBuilder;
    }

    public static void open(Player player) {
        player.openInventory(INVENTORY);
    };

    // Items
    public static void setItems(Inventory inventory) {
        var games = GamesService.GAMES;
        byte currentSlot = 0;
    }

    // Button actions
}
