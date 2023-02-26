package org.playbox.instances.limbo.events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.instance.AddEntityToInstanceEvent;
import org.playbox.instances.limbo.consts.LoginMessages;
import org.playbox.instances.limbo.consts.RegisterMessages;
import org.playbox.instances.limbo.managers.RepeatableMessageManager;
import org.playbox.managers.PlayerManager;
import org.playbox.utils.InstanceEvent;
import org.playbox.services.ProfileService;

public class PlayerSpawn extends InstanceEvent<AddEntityToInstanceEvent> {
    public PlayerSpawn() { super(AddEntityToInstanceEvent.class); };

    public void handle(AddEntityToInstanceEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        player.setGameMode(GameMode.ADVENTURE);
        player.setAutoViewable(false);

        // Checking if our player is registered or no
        if (ProfileService.doProfileExists(player)) {
            RepeatableMessageManager.setMessage(player, LoginMessages.REPEATABLE_MESSAGE, null);
        } else {
            RepeatableMessageManager.setMessage(player, RegisterMessages.REPEATABLE_MESSAGE, null);
        };
    };
}
