package org.playbox.managers.player;

import net.kyori.adventure.text.Component;
import org.playbox.resourcepack.bits.badges.AdminBadge;
import org.playbox.resourcepack.bits.badges.ModeratorBadge;
import org.playbox.resourcepack.bits.badges.PlayerBadge;

public enum PlayerRole {
    PLAYER,
    MODERATOR,
    ADMIN;

    public Component getChatBadge() {
        return switch (this) {
            default -> PlayerBadge.asTextComponent();
//            case PLAYER -> PlayerBadge.asTextComponent();
//            case MODERATOR -> ModeratorBadge.asTextComponent();
//            case ADMIN -> AdminBadge.asTextComponent();
        };
    };
}
