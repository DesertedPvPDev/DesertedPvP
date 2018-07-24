package codes.matthewp.desertedpvp.event.world;

import codes.matthewp.desertedpvp.DesertedPvP;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropEvent implements Listener {

    private DesertedPvP pvp;

    public DropEvent(DesertedPvP pvp) {
        this.pvp = pvp;
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }
}
