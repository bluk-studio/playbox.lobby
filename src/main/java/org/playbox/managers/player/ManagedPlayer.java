package org.playbox.managers.player;

import lombok.Getter;
import lombok.Setter;
import net.minestom.server.entity.Player;

public class ManagedPlayer {
    @Getter()
    private final Player player;

    @Getter() @Setter()
    private Boolean isRegistered = false;

    @Getter() @Setter()
    private Boolean isAuthorized = false;

    @Getter() @Setter()
    private Boolean isChatEnabled = true;

    @Getter() @Setter()
    private PlayerRole role = PlayerRole.PLAYER;

    public ManagedPlayer(Player player) {
        this.player = player;
    };
}
