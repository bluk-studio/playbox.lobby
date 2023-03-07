package org.playbox.utils;

public class InstanceEvent<E extends net.minestom.server.event.trait.InstanceEvent> {
    public Class<E> EVENT_CLASS;

    public InstanceEvent(Class<E> eventClass) {
        this.EVENT_CLASS = eventClass;
    };

    public void handle(E event) {};
}
