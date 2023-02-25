package org.playbox.instances.limbo.events;

import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.instance.AddEntityToInstanceEvent;
import org.playbox.utils.InstanceEvent;
import org.playbox.services.ProfileService;

public class PlayerSpawn extends InstanceEvent<AddEntityToInstanceEvent> {
    public PlayerSpawn() { super(AddEntityToInstanceEvent.class); };

    public void handle(AddEntityToInstanceEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        player.setGameMode(GameMode.ADVENTURE);
        player.setAutoViewable(false);

        // Checking if our player is registered or no
        if (ProfileService.doProfileExists(player.getUsername())) {
            player.sendMessage("Profile exists!");
        } else {
            player.sendMessage("Profile does not exists!");
        };
    };
}
