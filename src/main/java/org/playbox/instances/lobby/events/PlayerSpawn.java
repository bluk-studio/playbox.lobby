package org.playbox.instances.lobby.events;

import net.kyori.adventure.text.Component;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.instance.AddEntityToInstanceEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.resourcepack.ResourcePack;
import org.playbox.ResourcePackServer;
import org.playbox.instances.limbo.consts.LoginMessages;
import org.playbox.instances.lobby.managers.HotbarButtonsManager;
import org.playbox.resourcepack.Packs;
import org.playbox.utils.InstanceEvent;

public class PlayerSpawn extends InstanceEvent<AddEntityToInstanceEvent> {
    public PlayerSpawn() { super(AddEntityToInstanceEvent.class); };

    public void handle(AddEntityToInstanceEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        // Clearing player's chat by spamming empty string...
        for (int i = 0; i < 25; i++) {
            player.sendMessage(Component.space());
        };

        player.sendMessage(LoginMessages.LOGGED_IN);

        // Player settings
        player.setAutoViewable(true);
        player.setGameMode(GameMode.ADVENTURE);
        player.setResourcePack(ResourcePack.forced(ResourcePackServer.DOWNLOAD_LINK, Packs.LOBBY.hash()));

        player.setItemInOffHand(
                ItemStack.builder(Material.PAPER)
                        .build()
        );

        // Hotbar
        player.setHeldItemSlot(Integer.valueOf(0).byteValue());
        HotbarButtonsManager.setSelectedSlot(player, Integer.valueOf(0).byteValue());
    }
}
