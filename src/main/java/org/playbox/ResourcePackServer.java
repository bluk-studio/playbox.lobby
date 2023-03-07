package org.playbox;

import org.jetbrains.annotations.NotNull;
import org.playbox.resourcepack.Packs;

import java.io.IOException;

public class ResourcePackServer {
    public final team.unnamed.creative.server.ResourcePackServer SERVER;

    public static String DOWNLOAD_LINK;

    static {
        String downloadLink = "http://localhost:25567";

        if (System.getenv().containsKey("RESOURCEPACK_DOWNLOAD_LINK")) {
            downloadLink = System.getenv("RESOURCEPACK_DOWNLOAD_LINK");
        };

        DOWNLOAD_LINK = downloadLink;
    };

    public ResourcePackServer() throws IOException {
        this.SERVER = team.unnamed.creative.server.ResourcePackServer.builder()
                .address("0.0.0.0", 25567)
                .pack(Packs.LOBBY)
                .build();
    };
}
