package codes.matthewp.desertedpvp.event.player;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.data.CombatTag;
import codes.matthewp.desertedpvp.data.Messages;
import codes.matthewp.desertedpvp.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class HurtEvent implements Listener {

    private DesertedPvP pvp;

    public HurtEvent(DesertedPvP pvp) {
        this.pvp = pvp;
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;

        Player hit = (Player) event.getEntity();

        if (event.getDamager() instanceof Player) {
            Player damager = (Player) event.getDamager();

            if (!CombatTag.isTagged(hit)) {
                hit.sendMessage(Messages.getMessage("nowCombatTagged"));
            }
            if (!CombatTag.isTagged(damager)) {
                damager.sendMessage(Messages.getMessage("nowCombatTagged"));
            }

            CombatTag.tagPlayer(hit);
            CombatTag.tagPlayer(damager);
        }

        if (event.getDamager() instanceof Arrow) {

            Arrow arrow = (Arrow) event.getDamager();

            if (!(arrow.getShooter() instanceof Player))
                return;

            Player shooter = (Player) arrow.getShooter();

            User user = pvp.getUserManager().getUser(shooter);

            if (!user.getCurrentKit().intelID().equals("sniper"))
                return;

            double distance = hit.getLocation().distance(shooter.getLocation());

            if (distance < 50)
                return;

            hit.damage(9001, shooter);

            user.addKS();

            String msg = Messages.getMessage("sniperKill")
                    .replace("%SNIPER%", shooter.getDisplayName())
                    .replace("%KILLED%", hit.getDisplayName());

            Bukkit.broadcastMessage(msg);
        }
    }


}
