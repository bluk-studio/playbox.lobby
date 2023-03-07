package org.playbox.resourcepack.bits.badges.donations;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.playbox.utils.ResourcePackUtils;
import org.playbox.utils.resourcepack.FontTexture;
import team.unnamed.creative.font.FontProvider;
import team.unnamed.creative.texture.Texture;

public class GoliafBadge extends FontTexture {
    public static final Character character = FontTexture.getNextAvailableSymbol();

    public GoliafBadge() {
        withTexture(
                Texture.builder()
                        .key(Key.key("playbox", "badges/donation/goliaf"))
                        .data(ResourcePackUtils.getResourceWritable("textures/other/badges/goliaf.png"))
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
