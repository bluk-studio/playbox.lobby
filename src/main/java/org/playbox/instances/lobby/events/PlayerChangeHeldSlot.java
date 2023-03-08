package org.playbox.instances.lobby.events;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerChangeHeldSlotEvent;
import org.playbox.instances.lobby.managers.HotbarButtonsManager;
import org.playbox.utils.InstanceEvent;

public class PlayerChangeHeldSlot extends InstanceEvent<PlayerChangeHeldSlotEvent> {
    public PlayerChangeHeldSlot() { super(PlayerChangeHeldSlotEvent.class); };

    public void handle(PlayerChangeHeldSlotEvent event) {
        Player player = event.getPlayer();
        HotbarButtonsManager.ManagedPlayer managedPlayer = HotbarButtonsManager.getManagedPlayer(player);

        if (event.getSlot() >= managedPlayer.availableButtons.size()) {
            event.setCancelled(true);
            return;
        };

        // Playing some dinky ass sound
        event.getPlayer().playSound(
                Sound.sound(Key.key("block.note_block.hat"), Sound.Source.PLAYER, 0.65f, 0.5f)
        );

        // Updating hotbar texture
        HotbarButtonsManager.setSelectedSlot(event.getPlayer(), event.getSlot());
    };
}
