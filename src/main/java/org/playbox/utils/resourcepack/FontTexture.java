package org.playbox.utils.resourcepack;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.playbox.resourcepack.bits.Font;
import team.unnamed.creative.font.BitMapFontProvider;
import team.unnamed.creative.font.FontProvider;
import team.unnamed.creative.texture.Texture;

import java.util.UUID;

public class FontTexture {
    @Getter()
    private final String id = this.getClass().getName();

    @Getter()
    private final Texture texture;

    @Getter()
    private final FontProvider provider;

    public FontTexture(Texture texture, BitMapFontProvider.Builder providerBuilder) {
        this.texture = texture;

        providerBuilder.file(texture.key());
        providerBuilder.characters(this.id);
        this.provider = providerBuilder.build();
    };
}
