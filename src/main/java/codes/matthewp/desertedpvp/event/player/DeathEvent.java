package codes.matthewp.desertedpvp.event.player;

import codes.matthewp.desertedpvp.DesertedPvP;
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
        // TODO
        e.setDeathMessage("");
        Player killer = e.getEntity().getKiller();
        pvp.getUserManager().getUser(killer).addKS();
    }
}
