package org.playbox.utils;

import org.playbox.Server;
import org.reflections.Reflections;
import team.unnamed.creative.base.Writable;
import team.unnamed.creative.file.FileTree;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Set;

public class ResourcePackUtils {
    public static Writable getResourceWritable(String resource) throws URISyntaxException {
        return Writable.file(new File(
                Server.class.getClassLoader().getResource(resource).toURI()
        ));
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
