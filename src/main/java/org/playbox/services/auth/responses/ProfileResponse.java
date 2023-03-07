package org.playbox.services.auth.responses;

import lombok.Getter;
import org.playbox.managers.player.PlayerRole;

public class ProfileResponse {
    @Getter()
    private final String id;

    @Getter()
    private final PlayerRole role;

    public ProfileResponse(String id, PlayerRole role) {
        this.id = id;
        this.role = role;
    };
}
