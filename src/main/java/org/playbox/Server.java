package org.playbox;

import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.extras.velocity.VelocityProxy;
import org.playbox.instances.Limbo;
import org.playbox.utils.CommandUtils;
import org.playbox.utils.ResourceUtils;
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
        if (System.getenv().containsKey("VELOCITY_FORWARDING_SECRET")) {
            Server.LOGGER.info("Enabling velocity proxy extension");
            VelocityProxy.enable(System.getenv("VELOCITY_FORWARDING_SECRET"));
        };

        // > Handlers
        {
            GlobalEventHandler handler = MinecraftServer.getGlobalEventHandler();

            handler.addListener(PlayerLoginEvent.class, event -> {
                event.setSpawningInstance(Limbo.INSTANCE);
                event.getPlayer().setRespawnPoint(new Pos(48.5, 147, -2));
            });
        }

        // > Initializing commands
        CommandUtils.initializeCommands();

        // Starting server
        server.start("0.0.0.0", 25565);
        Server.LOGGER.info("Server started on 0.0.0.0:25565");

        // Starting resourcepack server
        resourcepackServer.SERVER.start();
        Server.LOGGER.info("Resourcepack server started on 0.0.0.0:25567");
    }
}