package org.playbox.lobby.instances.lobby.events;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.instance.AddEntityToInstanceEvent;
import net.minestom.server.resourcepack.ResourcePack;
import org.playbox.lobby.resourcepack.Packs;
import org.playbox.lobby.utils.InstanceEvent;

public class PlayerSpawn extends InstanceEvent<AddEntityToInstanceEvent> {
    public PlayerSpawn() { super(AddEntityToInstanceEvent.class); };

    public void handle(AddEntityToInstanceEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        player.setResourcePack(ResourcePack.forced("http://localhost:25567", Packs.LOBBY.hash()));
    }
}
