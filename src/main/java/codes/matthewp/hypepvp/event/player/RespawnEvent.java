package codes.matthewp.hypepvp.event.player;

import codes.matthewp.hypepvp.HypePvP;
import codes.matthewp.hypepvp.data.SpawnData;
import codes.matthewp.hypepvp.kit.KitSelector;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnEvent implements Listener {

    private HypePvP pvp;

    public RespawnEvent(HypePvP pvp) {
        this.pvp = pvp;
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        e.setRespawnLocation(new Location(Bukkit.getWorld("world"), SpawnData.getX(), SpawnData.getY(), SpawnData.getZ(), SpawnData.getYaw(), SpawnData.getPitch()));
        pvp.getUserManager().getUser(e.getPlayer()).restPlayer();

    }
}
