package codes.matthewp.desertedpvp.event.player;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.data.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
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
        if (e.getEntity() instanceof Player) {
            if (e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
                if (e.getDamager() instanceof Arrow) {
                    Player hit = (Player) e.getEntity();
                    Arrow arrow = (Arrow) e.getDamager();
                    Player damager = (Player) arrow.getShooter();
                    // STOPS HERE
                    if (pvp.getUserManager().getUser(damager).getCurrentKit().intelID().equals("sniper")) {
                        System.out.println("Person who hit is a sniper");
                        double dist = hit.getLocation().distance(damager.getLocation());
                        System.out.println(dist);
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
}
