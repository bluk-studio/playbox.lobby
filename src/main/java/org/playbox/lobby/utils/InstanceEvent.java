package org.playbox.lobby.utils;

public class InstanceEvent<T extends net.minestom.server.event.trait.InstanceEvent> {
    public Class<T> EVENT_CLASS;

    public InstanceEvent(Class<T> eventClass) {
        this.EVENT_CLASS = eventClass;
    };

    public void handle(T event) {};
}
