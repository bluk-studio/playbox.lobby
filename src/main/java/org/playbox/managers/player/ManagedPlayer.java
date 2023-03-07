package org.playbox.managers.player;

import lombok.Getter;
import lombok.Setter;
import net.minestom.server.entity.Player;
import org.playbox.Server;
import org.playbox.services.auth.responses.ProfileResponse;

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

    public void handleProfileResponse(ProfileResponse profile) {
        // Updating our managed fields
        this.role = profile.getRole();
    };
}
