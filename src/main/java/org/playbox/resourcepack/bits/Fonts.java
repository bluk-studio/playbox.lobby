package org.playbox.resourcepack.bits;

import net.kyori.adventure.key.Key;
import net.minestom.server.MinecraftServer;
import org.playbox.Server;
import org.playbox.utils.ResourcePackBit;
import org.playbox.utils.resourcepack.FontTexture;
import org.reflections.Reflections;
import team.unnamed.creative.file.FileTree;
import team.unnamed.creative.font.FontProvider;

import java.util.ArrayList;
import java.util.Set;

public class Fonts extends ResourcePackBit {
    public void apply(FileTree tree) {
        // Applying textures
        LOBBY_FONT_TEXTURES.forEach((fontTexture) -> {
            tree.write(
                    fontTexture.getTexture()
            );
        });

        // Applying pre-created font
        tree.write(
                LOBBY
        );
    };

    private static final ArrayList<FontTexture> LOBBY_FONT_TEXTURES;
    public static team.unnamed.creative.font.Font LOBBY;

    static {
        ArrayList<FontTexture> fetchedTextures = new ArrayList<>();

        // Using reflections to get all FontTexture classes
        Reflections reflections = new Reflections("org.playbox.resourcepack.bits");
        Set<Class<? extends FontTexture>> textureClasses = reflections.getSubTypesOf(FontTexture.class);

        ArrayList<FontProvider> providers = new ArrayList<>();

        textureClasses.forEach((fontTextureClass) -> {
            FontTexture fontTexture;

            try {
                fontTexture = fontTextureClass.getDeclaredConstructor().newInstance();

                // Adding this texture
                fetchedTextures.add(fontTexture);

                Server.LOGGER.info(String.format("Applied resourcepack FontTexture %s", fontTextureClass.getName()));
            } catch(Throwable error) {
                Server.LOGGER.warn(String.format("Could not load resourcepack FontTexture %s", fontTextureClass.getName()));
                Server.LOGGER.error("Error:", error);
            };
        });

        LOBBY_FONT_TEXTURES = fetchedTextures;

        // Creating new font with this providers
        LOBBY = team.unnamed.creative.font.Font.of(
                Key.key("playbox", "font_textures"),
                LOBBY_FONT_TEXTURES.stream().map(FontTexture::getProvider).toList()
        );
    };
}
