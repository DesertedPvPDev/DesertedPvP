package codes.matthewp.hypepvp.event.player;

import codes.matthewp.hypepvp.HypePvP;
import codes.matthewp.hypepvp.data.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class HurtEvent implements Listener {

    private HypePvP pvp;

    public HurtEvent(HypePvP pvp) {
        this.pvp = pvp;
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player hit = (Player) e.getEntity();
            Player damager = (Player) e.getDamager();
            if (pvp.getUserManager().getUser(damager).getCurrentKit().intelID().equals("sniper")) {
                double dist = hit.getLocation().distance(damager.getLocation());
                if (dist > 50) {
                    // TODO FIX
                    hit.damage(30D);
                    String msg = Messages.getMessage("sniperKill");
                    msg = msg.replaceAll("%SNIPER%", damager.getName());
                    msg = msg.replaceAll("%KILLED%", hit.getName());
                    Bukkit.broadcastMessage(msg);
                }
            }
        }
    }
}
