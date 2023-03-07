package org.playbox.resourcepack.bits.badges;

import lombok.Getter;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.playbox.utils.ResourcePackUtils;
import org.playbox.utils.resourcepack.FontTexture;
import team.unnamed.creative.font.FontProvider;
import team.unnamed.creative.texture.Texture;

public class AdminBadge extends FontTexture {
    private static final Character character = FontTexture.getNextAvailableSymbol();

    public AdminBadge() {
        withTexture(
                Texture.builder()
                        .key(Key.key("playbox", "badges/admin"))
                        .data(ResourcePackUtils.getResourceWritable("textures/other/badges/admin.png"))
                        .build()
        );

        withProvider(
                FontProvider.bitMap()
                        .characters(String.valueOf(character))
                        .ascent(7)
                        .height(7)
        );
    };

    public static Component asTextComponent() {
        return asTextComponentWrapper(character);
    };
}
