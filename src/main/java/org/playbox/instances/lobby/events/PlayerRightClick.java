package org.playbox.instances.lobby.events;

import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerBlockInteractEvent;
import net.minestom.server.event.player.PlayerUseItemEvent;
import org.playbox.instances.lobby.managers.HotbarButtonsManager;
import org.playbox.utils.InstanceEvent;

public class PlayerRightClick extends InstanceEvent<PlayerUseItemEvent> {
    public PlayerRightClick() { super(PlayerUseItemEvent.class); };

    public void handle(PlayerUseItemEvent event) {
        event.setCancelled(true);
        Player player = event.getPlayer();

        // Triggerig HotbarButtonsManager's handleInteract method
        HotbarButtonsManager.handleInteraction(player, player.getHeldSlot());
    };
}
