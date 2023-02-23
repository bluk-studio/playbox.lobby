package org.playbox.lobby;

import org.playbox.lobby.resourcepack.Packs;

import java.io.IOException;

public class ResourcePackServer {
    public final team.unnamed.creative.server.ResourcePackServer SERVER;

    public ResourcePackServer() throws IOException {
        this.SERVER = team.unnamed.creative.server.ResourcePackServer.builder()
                .address("0.0.0.0", 25567)
                .pack(Packs.LOBBY)
                .build();
    };
}
