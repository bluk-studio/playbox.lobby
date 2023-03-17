package org.playbox.resourcepack.bits.fonts;

import org.playbox.Server;
import org.playbox.utils.ResourcePackBit;
import org.playbox.utils.resourcepack.FontTexture;
import org.reflections.Reflections;
import team.unnamed.creative.file.FileTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LobbyFontTextures extends ResourcePackBit {
    public void apply(FileTree tree) {
        LIST.forEach((fontTexture) -> {
            tree.write(
                    fontTexture.getTexture()
            );
        });
    }

    public static List<FontTexture> LIST;

    static {
        ArrayList<FontTexture> fetchedTextures = new ArrayList<>();

        // Using reflections to get all FontTexture classes
        Reflections reflections = new Reflections("org.playbox.resourcepack.bits");
        Set<Class<? extends FontTexture>> textureClasses = reflections.getSubTypesOf(FontTexture.class);

        textureClasses.forEach((fontTextureClass) -> {
            FontTexture fontTexture;

            try {
                fontTexture = fontTextureClass.getDeclaredConstructor().newInstance();

                // Adding this texture
                if (fontTexture.getTexture() != null && fontTexture.getProvider() != null) {
                    fetchedTextures.add(fontTexture);
                } else {
                    Server.LOGGER.error(String.format("FontTexture %s doesn not contain texture or provider", fontTexture.getClass().getName()));
                };

                Server.LOGGER.info(String.format("Applied resourcepack FontTexture %s", fontTextureClass.getName()));
            } catch(Throwable error) {
                Server.LOGGER.warn(String.format("Could not load resourcepack FontTexture %s", fontTextureClass.getName()));
                Server.LOGGER.error("Error:", error);
            };
        });

        LIST = fetchedTextures;
    };
}
