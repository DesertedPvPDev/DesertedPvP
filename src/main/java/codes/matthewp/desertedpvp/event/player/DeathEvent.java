package codes.matthewp.desertedpvp.event.player;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.data.SpawnData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {

    private DesertedPvP pvp;

    public DeathEvent(DesertedPvP pvp) {
        this.pvp = pvp;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        e.getDrops().clear();
        e.setDroppedExp(0);

        pvp.getUserManager().getUser(e.getEntity()).restPlayer();

        e.setDeathMessage("");

        e.getEntity().teleport(new Location(Bukkit.getWorld("world"),
                SpawnData.getX(), SpawnData.getY(), SpawnData.getZ(), SpawnData.getYaw(), SpawnData.getPitch()));

        if (e.getEntity().getKiller() != null) {
            if (e.getEntity().getKiller() instanceof Player) {
                Player killer = e.getEntity().getKiller();
                pvp.getUserManager().getUser(killer).addKS();
                pvp.getUserManager().getUser(killer).getCurrentKit().gotKill(killer, e.getEntity());
            }
        }
    }

}
