package org.playbox.utils;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;
import org.playbox.Server;
import org.playbox.utils.resourcepack.FontTexture;
import org.reflections.Reflections;
import team.unnamed.creative.base.Writable;
import team.unnamed.creative.file.FileTree;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Set;

public class ResourcePackUtils {
    @Nullable
    public static Component getFontTexture(Class<FontTexture> texture) {
        try {
            Method asTextComponent = texture.getMethod("asTextComponent");
            return (Component) asTextComponent.invoke(null);
        } catch(Throwable error) {
            Server.LOGGER.error("Error while getting FontTexture component using reflections:", error);
        };

        return null;
    };

    public static Writable getResourceWritable(String resource) {
        try {
            return Writable.file(new File(resource));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    };

    public static void initializeBits(String bitsPackagePath, FileTree tree) {
        Reflections reflections = new Reflections(bitsPackagePath);
        Set<Class<? extends ResourcePackBit>> bits = reflections.getSubTypesOf(ResourcePackBit.class);

        bits.forEach(bit -> {
            ResourcePackBit resourceBit;

            try {
                resourceBit = bit.getDeclaredConstructor().newInstance();

                resourceBit.apply(tree);

                Server.LOGGER.info(String.format("Applied resourcepack bit %s", bit.getName()));
            } catch (Throwable error) {
                Server.LOGGER.warn(String.format("Could not load resourcepack bit %s", bit.getName()));
                Server.LOGGER.error(String.format("Message: %s, stacktrace: %s", error.getMessage(), error.getStackTrace().toString()));
            }
        });
    }
}
