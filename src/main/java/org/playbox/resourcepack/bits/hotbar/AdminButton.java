package org.playbox.resourcepack.bits.hotbar;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.playbox.utils.ResourcePackUtils;
import org.playbox.utils.resourcepack.FontTexture;
import team.unnamed.creative.font.FontProvider;
import team.unnamed.creative.texture.Texture;

public class AdminButton {
    public static class Active extends FontTexture {
        public static final Character character = FontTexture.getNextAvailableSymbol();

        public Active() {
            withTexture(
                    Texture.builder()
                            .key(Key.key("playbox", "gui/hotbar/admin_button/active"))
                            .data(ResourcePackUtils.getResourceWritable("textures/gui/hotbar/admin_button/active.png"))
                            .build()
            );

            withProvider(
                    FontProvider.bitMap()
                            .characters(String.valueOf(character))
                            .ascent(-35)
                            .height(30)
            );
        };

        public static Component asTextComponent() {
            return asTextComponentWrapper(character);
        };
    };

    public static class Inactive extends FontTexture {
        public static final Character character = FontTexture.getNextAvailableSymbol();

        public Inactive() {
            withTexture(
                    Texture.builder()
                            .key(Key.key("playbox", "gui/hotbar/admin_button/inactive"))
                            .data(ResourcePackUtils.getResourceWritable("textures/gui/hotbar/admin_button/inactive.png"))
                            .build()
            );

            withProvider(
                    FontProvider.bitMap()
                            .characters(String.valueOf(character))
                            .ascent(-45)
                            .height(20)
            );
        };

        public static Component asTextComponent() {
            return asTextComponentWrapper(character);
        };
    };
}
