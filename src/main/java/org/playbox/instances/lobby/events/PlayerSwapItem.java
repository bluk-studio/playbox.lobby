package org.playbox.instances.lobby.events;

import net.minestom.server.event.player.PlayerSwapItemEvent;
import org.playbox.utils.InstanceEvent;

public class PlayerSwapItem extends InstanceEvent<PlayerSwapItemEvent> {
    public PlayerSwapItem() { super(PlayerSwapItemEvent.class); };

    public void handle(PlayerSwapItemEvent event) {
        event.setCancelled(true);
    };
}
