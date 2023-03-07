package org.playbox.instances.limbo.events;

import net.minestom.server.event.player.PlayerChatEvent;
import org.playbox.utils.InstanceEvent;

public class PlayerChat extends InstanceEvent<PlayerChatEvent> {
    public PlayerChat() { super(PlayerChatEvent.class); };

    public void handle(PlayerChatEvent event) {
        event.setCancelled(true);
    };
}
