package org.playbox.lobby;

import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoginEvent;
import org.playbox.lobby.instances.Lobby;
import org.playbox.lobby.utils.ResourceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public final class Server {
    public static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    public static void main(final String[] args) throws IOException {
        MinecraftServer server = MinecraftServer.init();
        ResourcePackServer resourcepackServer = new ResourcePackServer();

        // Trying to unzip our lobby map
        try {
            ResourceUtils.extractResource("world");
        } catch(Throwable error) {
            throw new RuntimeException(error);
        }

        // Configuring our server
        // VelocityProxy.enable("vwIiIPs6iqAp");

        // > Handlers
        {
            GlobalEventHandler handler = MinecraftServer.getGlobalEventHandler();

            handler.addListener(PlayerLoginEvent.class, event -> {
                event.setSpawningInstance(Lobby.INSTANCE);
                event.getPlayer().setRespawnPoint(new Pos(0, 157, 0));
            });
        }

        // Starting server
        server.start("0.0.0.0", 25565);
        Server.LOGGER.info("Server started on 0.0.0.0:25565");

        // Starting resourcepack server
        resourcepackServer.SERVER.start();
        Server.LOGGER.info("Resourcepack server started on 0.0.0.0:25567");
    }
}