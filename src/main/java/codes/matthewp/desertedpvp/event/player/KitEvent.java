package codes.matthewp.desertedpvp.event.player;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.data.Messages;
import codes.matthewp.desertedpvp.kit.KitManager;
import codes.matthewp.desertedpvp.kit.kits.InfectedKit;
import codes.matthewp.desertedpvp.user.User;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by Mario on 9/30/2018.
 */
public class KitEvent implements Listener {
    @EventHandler
    public void onHitEvent(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        Entity damaged = e.getEntity();

        if(damager instanceof Player && damaged instanceof Player) {
            Player p = (Player) e.getDamager();

            User u = DesertedPvP.getInstace().getUserManager().getUser(p);
            if(u.getCurrentKit() instanceof InfectedKit) {
                if(!KitManager.isInCooldown(p)) {
                    ((Player) damaged).addPotionEffect(new PotionEffect(PotionEffectType.POISON, (int) 2.5 * 20, 1));
                    p.sendMessage(Messages.getMessage("poisonedEnemy"));

                    KitManager.addPlayerToCooldown(p, 10);
                }
            }
        }
    }
}
