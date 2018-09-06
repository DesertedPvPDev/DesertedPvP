package codes.matthewp.desertedpvp.event.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;

/**
 * Prevent Any Item from taking damage
 * @author Mario
 */
public class DamageEvent implements Listener {

    @EventHandler @SuppressWarnings("unused")
    public void onDamage(PlayerItemDamageEvent e) {
        if(e.isCancelled()) return;
        e.setCancelled(true);
    }
}
