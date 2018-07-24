package codes.matthewp.hypepvp.event.world;

import codes.matthewp.hypepvp.HypePvP;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropEvent implements Listener {

    private HypePvP pvp;

    public DropEvent(HypePvP pvp) {
        this.pvp = pvp;
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }
}
