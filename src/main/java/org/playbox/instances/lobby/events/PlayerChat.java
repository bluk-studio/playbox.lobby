package org.playbox.instances.lobby.events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.minestom.server.adventure.audience.Audiences;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerChatEvent;
import org.playbox.Server;
import org.playbox.managers.PlayerManager;
import org.playbox.managers.player.ManagedPlayer;
import org.playbox.utils.InstanceEvent;

public class PlayerChat extends InstanceEvent<PlayerChatEvent> {
    public PlayerChat() { super(PlayerChatEvent.class); };

    public void handle(PlayerChatEvent event) {
        // Cancelling this event
        event.setCancelled(true);

        Player messageSender = event.getPlayer();
        ManagedPlayer messageSenderManaged = PlayerManager.getByUUID(messageSender.getUuid());
        String message = event.getMessage();

        Audiences.players().forEachAudience(audience -> {
            Player messageReceiver = (Player) audience;
            ManagedPlayer messageReceiverManaged = PlayerManager.getByUUID(messageReceiver.getUuid());

            if (messageReceiverManaged.getIsChatEnabled() && messageReceiverManaged.getIsAuthorized()) {
                // Sending formatted message to this player
                messageReceiver.sendMessage(
                        MiniMessage.miniMessage().deserialize(
                                "<rank> <gray><name>: <white><message>",
                                Placeholder.component("rank", messageSenderManaged.getRole().getChatBadge()),
                                Placeholder.component("name", messageSender.getName()),
                                Placeholder.component("message", Component.text(message))
                        )
                );
            };
        });
    };
}
