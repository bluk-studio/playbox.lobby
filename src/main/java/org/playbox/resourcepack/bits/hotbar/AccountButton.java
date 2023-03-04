package org.playbox.resourcepack.bits.hotbar;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.playbox.resourcepack.bits.Fonts;
import org.playbox.utils.ResourcePackUtils;
import org.playbox.utils.resourcepack.FontTexture;
import team.unnamed.creative.font.FontProvider;
import team.unnamed.creative.texture.Texture;

public class AccountButton {

    public static class Active extends FontTexture {
        public Active() {
            this.withTexture(
                    Texture.builder()
                            .key(Key.key("playbox", "gui/hotbar/account_button/active"))
                            .data(ResourcePackUtils.getResourceWritable("textures/gui/hotbar/account_button/active.png"))
                            .build()
            );

            this.withProvider(
                    FontProvider.bitMap()
                            .ascent(-35)
                            .height(30)
            );
        }

        public static Component asTextComponent() {
            return FontTexture.asTextComponentWrapper(AccountButton.Active.class);
        };
    }
}
