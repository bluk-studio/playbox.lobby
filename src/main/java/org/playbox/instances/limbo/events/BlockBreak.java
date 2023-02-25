package org.playbox.instances.limbo.events;

import net.minestom.server.event.player.PlayerBlockBreakEvent;
import org.playbox.utils.InstanceEvent;

public class BlockBreak extends InstanceEvent<PlayerBlockBreakEvent> {
    public BlockBreak() { super(PlayerBlockBreakEvent.class); };

    public void handle(PlayerBlockBreakEvent event) {
        event.setCancelled(true);
    };
}
