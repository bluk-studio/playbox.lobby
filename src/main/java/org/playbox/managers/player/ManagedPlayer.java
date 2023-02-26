package org.playbox.managers.player;

import lombok.Getter;
import lombok.Setter;
import net.minestom.server.entity.Player;

public class ManagedPlayer {
    @Getter()
    private final Player player;

    @Getter()
    @Setter()
    public Boolean isRegistered = false;

    @Getter()
    @Setter()
    public Boolean isAuthorized = false;

    public ManagedPlayer(Player player) {
        this.player = player;
    };
}
