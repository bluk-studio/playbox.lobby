package org.playbox.instances;

import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.AnvilLoader;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.WorldBorder;
import net.minestom.server.world.DimensionType;
import org.playbox.utils.InstanceUtils;

import java.nio.file.Path;

public class Lobby {
    public static final Instance INSTANCE;

    static {
        final Instance instance = MinecraftServer.getInstanceManager().createInstanceContainer(
                DimensionType.OVERWORLD,
                new AnvilLoader(Path.of("world"))
        );

        // Instance settings
        instance.setTimeRate(0);

        // > WorldBorder settings
        {
            WorldBorder border = instance.getWorldBorder();

            border.setCenterX(0);
            border.setCenterZ(0);
            border.setDiameter(250.0);
        }

        // Initializing events using reflections
        InstanceUtils.initializeEvents("org.playbox.instances.lobby.events", instance);

        INSTANCE = instance;
    }
}
