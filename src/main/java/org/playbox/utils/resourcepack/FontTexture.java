package org.playbox.utils.resourcepack;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.playbox.resourcepack.bits.Fonts;
import team.unnamed.creative.font.BitMapFontProvider;
import team.unnamed.creative.font.FontProvider;
import team.unnamed.creative.texture.Texture;

public class FontTexture {
    @Getter()
    @NotNull()
    private Texture texture;

    @Getter()
    @NotNull()
    private FontProvider provider;

    public void withTexture(Texture texture) {
        this.texture = texture;
    };

    public void withProvider(BitMapFontProvider.Builder providerBuilder) {
        providerBuilder.file(this.texture.key());
        providerBuilder.characters(this.getClass().getName());

        this.provider = providerBuilder.build();
    };

    public static Component asTextComponentWrapper(Class<? extends FontTexture> clazz) {
        return Component.text()
                .content(clazz.getName())
                .font(Fonts.LOBBY.key())
                .build();
    };
}
