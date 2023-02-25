package org.playbox.utils;

import net.minestom.server.instance.Instance;
import org.playbox.Server;
import org.reflections.Reflections;

import java.util.Set;

public class InstanceUtils {

    public static void initializeEvents(String eventsPackagePath, Instance instance) {
        Reflections reflections = new Reflections(eventsPackagePath);
        Set<Class<? extends InstanceEvent>> events = reflections.getSubTypesOf(InstanceEvent.class);

        events.forEach(event -> {
            InstanceEvent instanceEvent;

            try {
                instanceEvent = event.getDeclaredConstructor().newInstance();

                // Adding this event to LobbyInstance's eventNode
                instance.eventNode().addListener(
                        instanceEvent.EVENT_CLASS,
                        instanceEvent::handle
                );

                Server.LOGGER.info(String.format("Initialized %s instance event: %s", instance.getClass().getName(), event.getName()));
            } catch (Throwable error) {
                Server.LOGGER.warn(String.format("Could not load %s instance event %s", instance.getClass().getName(), event.getName()));
            };
        });
    }
}
