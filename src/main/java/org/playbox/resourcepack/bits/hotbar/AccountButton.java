package org.playbox.resourcepack.bits.hotbar;

import lombok.Getter;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.playbox.resourcepack.bits.Font;
import org.playbox.utils.ResourcePackUtils;
import org.playbox.utils.resourcepack.FontTexture;
import team.unnamed.creative.base.Writable;
import team.unnamed.creative.font.FontProvider;
import team.unnamed.creative.texture.Texture;

import java.net.URISyntaxException;
import java.util.UUID;

public class AccountButton extends FontTexture {
    public AccountButton() {
        super(
                Texture.builder()
                        .key(Key.key("playbox", "gui/hotbar/account_button/active"))
                        .data(ResourcePackUtils.getResourceWritable("textures/gui/hotbar/account_button/active.png"))
                        .build(),

                FontProvider.bitMap()
                        .ascent(-35)
                        .height(30)
        );
    };

    public static Component asTextComponent() {
        return Component.text()
                .content(new AccountButton().getId())
                .font(Font.FONT.key())
                .build();
    };
}
