package org.playbox.instances.lobby.events;

import net.minestom.server.event.item.ItemDropEvent;
import org.playbox.utils.InstanceEvent;

public class ItemDrop extends InstanceEvent<ItemDropEvent> {
    public ItemDrop() { super(ItemDropEvent.class); };

    public void handle(ItemDropEvent event) {
        event.setCancelled(true);
    };
}
