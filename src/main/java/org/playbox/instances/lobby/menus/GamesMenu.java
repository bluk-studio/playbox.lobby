package org.playbox.instances.lobby.menus;

import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.inventory.click.ClickType;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import org.javatuples.Pair;
import org.playbox.services.GamesService;
import org.playbox.services.games.Game;
import org.playbox.services.games.GameMenuConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GamesMenu {
    public static Inventory INVENTORY;

    static {
        Inventory inventoryBuilder = new Inventory(InventoryType.CHEST_6_ROW, "Games menu");

        // Building our menu
        setItems(inventoryBuilder);
        generateTitle(inventoryBuilder);

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
        Integer currentRow = 0;
        Integer currentColumn = 0;

        for (Game game : games) {
            setInventoryButton(inventory, game, coordinatesToSlot(currentRow, currentColumn));

            // Determining next row and column positions based
            // on current values and current button
            Pair<Integer, Integer> updatedCurrentSlot = calculateNextSlot(currentRow, currentColumn, game.getGameMenuConfiguration().getButtonDimension());

            currentRow = updatedCurrentSlot.getValue0();
            currentColumn = updatedCurrentSlot.getValue1();
        };
    }
    
    private static Pair<Integer, Integer> calculateNextSlot(Integer row, Integer column, GameMenuConfiguration.ButtonDimension buttonDimension) {
        Integer width = buttonDimension.getWidth();

        if (column + width > 9) {
            column = 0;
            row += 1;
        } else {
            column += width;
        };

        return new Pair<Integer, Integer>(row, column);
    }

    private static void setInventoryButton(Inventory inventory, Game game, Integer startingSlot) {
        GameMenuConfiguration menuConfiguration = game.getGameMenuConfiguration();
        ArrayList<Integer> buttonSlots = getButtonSlots(menuConfiguration.getButtonDimension(), startingSlot);

        // Generating item
        ItemStack fillerItem = ItemStack.builder(Material.PAPER)
                .displayName(Component.text("Hello!"))
                .lore(Component.text("Line 1"), Component.text("Line 2"))
                .build();

        buttonSlots.forEach(slot -> {
            inventory.setItemStack(slot, fillerItem);
        });
    }

    private static Integer coordinatesToSlot(Integer row, Integer column) {
        Integer slot = column;

        while (row > 0) {
            slot += 9;
            row--;
        };

        return slot;
    }

    private static ArrayList<Integer> getButtonSlots(GameMenuConfiguration.ButtonDimension dimensions, Integer startingSlot) {
        ArrayList<Integer> slots = new ArrayList<>(Collections.singletonList(startingSlot));

        switch (dimensions) {
            case THREE_TO_TWO -> {
                slots.addAll(Arrays.asList(
                        startingSlot + 1,
                        startingSlot + 2,
                        startingSlot + 9,
                        startingSlot + 10,
                        startingSlot + 11
                ));
            }

            case THREE_TO_THREE -> {
                slots.addAll(Arrays.asList(
                        startingSlot + 1,
                        startingSlot + 2,

                        startingSlot + 9,
                        startingSlot + 10,
                        startingSlot + 11,

                        startingSlot + 18,
                        startingSlot + 19,
                        startingSlot + 20
                ));
            }
        };

        return slots;
    }

    private static void generateTitle(Inventory inventory) {

    }

    // Events
    public static void handleGameListUpdate() {
        // Repainting our menu
        setItems(INVENTORY);
        generateTitle(INVENTORY);
    }

    // Button actions
}
