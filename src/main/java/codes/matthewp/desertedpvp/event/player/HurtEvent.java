package codes.matthewp.desertedpvp.event.player;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.data.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class HurtEvent implements Listener {

    private DesertedPvP pvp;

    public HurtEvent(DesertedPvP pvp) {
        this.pvp = pvp;
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player hit = (Player) e.getEntity();
            Player damager = (Player) e.getDamager();
            if (e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
                if (pvp.getUserManager().getUser(damager).getCurrentKit().intelID().equals("sniper")) {
                    double dist = hit.getLocation().distance(damager.getLocation());
                    if (dist >= 50) {
                        hit.damage(30D);

                        pvp.getUserManager().getUser(damager).addKS();

                        String msg = Messages.getMessage("sniperKill");
                        msg = msg.replaceAll("%SNIPER%", damager.getName());
                        msg = msg.replaceAll("%KILLED%", hit.getName());
                        Bukkit.broadcastMessage(msg);
                    }
                }
            }
        }
    }
}
