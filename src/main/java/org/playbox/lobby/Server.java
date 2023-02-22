package org.playbox.lobby;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.adventure.audience.Audiences;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.extras.velocity.VelocityProxy;
import org.playbox.lobby.instances.Lobby;
import org.playbox.lobby.utils.ResourceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Server {
    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    public static void main(final String[] args) {
        MinecraftServer server = MinecraftServer.init();

        // Trying to unzip our lobby map
        try {
            ResourceUtils.extractResource("world");
        } catch(Throwable error) {
            throw new RuntimeException(error);
        }

        // Configuring our server
        // VelocityProxy.enable("vwIiIPs6iqAp");

        // Handlers
        {
            GlobalEventHandler handler = MinecraftServer.getGlobalEventHandler();

            handler.addListener(PlayerLoginEvent.class, event -> {
                event.setSpawningInstance(Lobby.INSTANCE);

                final Player player = event.getPlayer();
                player.setRespawnPoint(new Pos(0, 157, 0));

                Audiences.all()
                        .sendMessage(Component.text(
                                player.getUsername() + " has joined",
                                NamedTextColor.GREEN
                        ));
            });
        }

        // Starting server
        server.start("0.0.0.0", 25565);
        Server.LOGGER.info("Server started on 0.0.0.0:25565");
    }
}