package org.playbox.instances.lobby.managers;

import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentBuilder;
import net.kyori.adventure.text.TextComponent;
import net.minestom.server.entity.Player;
import net.minestom.server.timer.Task;
import net.minestom.server.timer.TaskSchedule;
import org.playbox.instances.Lobby;
import org.playbox.managers.PlayerManager;
import org.playbox.managers.player.ManagedPlayer;
import org.playbox.managers.player.PlayerRole;
import org.playbox.resourcepack.bits.hotbar.AccountButton;
import org.playbox.resourcepack.bits.hotbar.AdminButton;
import org.playbox.resourcepack.bits.hotbar.MoreButton;
import org.playbox.resourcepack.bits.hotbar.PlayButton;
import org.playbox.utils.resourcepack.FontTexture;

import java.lang.reflect.Method;
import java.util.*;

public class HotbarButtonsManager {
    public static Map<UUID, ManagedPlayer> players = new HashMap<>();

    public static ManagedPlayer getManagedPlayer(Player player) {
        // Checking if we have this player in our storage
        if (players.get(player.getUuid()) != null) return players.get(player.getUuid());

        ManagedPlayer managedPlayer = new ManagedPlayer(player);
        players.put(player.getUuid(), managedPlayer);

        return managedPlayer;
    };

    public static void setSelectedSlot(Player player, Byte slot) {
        ManagedPlayer managedPlayer = getManagedPlayer(player);
        managedPlayer.setCurrentlySelectedSlot(slot);
    };

    public record ButtonEntry(Class<? extends FontTexture> activeTexture, Class<? extends FontTexture> inactiveTexture) {};

    public static class ManagedPlayer {
        @Getter()
        private final Player player;

        @Getter() @Setter()
        private Byte currentlySelectedSlot;

        @Getter()
        public final List<ButtonEntry> availableButtons;

        @Getter()
        private final Task task;

        public ManagedPlayer(Player player) {
            this.player = player;

            org.playbox.managers.player.ManagedPlayer globallyManagedPlayer = PlayerManager.getByUUID(player.getUuid());

            // Adding availableButtons to this player (depending on his rank)
            this.availableButtons = new ArrayList<>(List.of(
                    new ButtonEntry(PlayButton.Active.class, PlayButton.Inactive.class),
                    new ButtonEntry(AccountButton.Active.class, AccountButton.Inactive.class),
                    new ButtonEntry(MoreButton.Active.class, MoreButton.Inactive.class)
            ));

            if (globallyManagedPlayer.getRole() == PlayerRole.ADMIN) this.availableButtons.add(
                    new ButtonEntry(AdminButton.Active.class, AdminButton.Inactive.class)
            );

            // Creating new task fot this player
            this.task = Lobby.INSTANCE.scheduler().scheduleTask(() -> {
                // Checking if our player still online
                if (!player.isOnline() || player.getInstance() != Lobby.INSTANCE) {
                    getTask().cancel();
                    return;
                };

                // Getting currently selected slot information
                Byte currentlySelectedSlot = getCurrentlySelectedSlot();
                TextComponent.Builder textureComponent = Component.text();

                for (int i = 0; i < getAvailableButtons().size(); i++) {
                    try {
                        ButtonEntry button = getAvailableButtons().get(i);
                        Method asTextComponent = currentlySelectedSlot == i ?
                                button.activeTexture.getMethod("asTextComponent") :
                                button.inactiveTexture.getMethod("asTextComponent");

                        Component texture = (Component) asTextComponent.invoke(null);
                        textureComponent.append(texture);
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    };
                };

                // Sending this hotbar to our player
                player.sendActionBar(
                        textureComponent.build()
                );
            }, TaskSchedule.immediate(), TaskSchedule.tick(1));
        };
    };
}
