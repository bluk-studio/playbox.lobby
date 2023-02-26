package org.playbox.managers;

import net.minestom.server.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.playbox.managers.player.ManagedPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {
    public static final Map<UUID, ManagedPlayer> players = new HashMap<>();

    public static @NotNull ManagedPlayer getByUUID(UUID id) {
        return players.get(id);
    };

    public static void initializePlayer(Player player) {
        if (!players.containsKey(player.getUuid())) {
            players.put(player.getUuid(), new ManagedPlayer(player));
        };
    };
}
