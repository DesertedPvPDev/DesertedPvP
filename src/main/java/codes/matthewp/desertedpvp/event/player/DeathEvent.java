package codes.matthewp.desertedpvp.event.player;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.user.User;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

public class DeathEvent implements Listener {

    private DesertedPvP pvp;

    public DeathEvent(DesertedPvP pvp) {
        this.pvp = pvp;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        e.getDrops().clear();
        e.setDroppedExp(0);

        e.setDeathMessage("");

        if (e.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e.getEntity().getLastDamageCause();
            if (event.getDamager().getType() == EntityType.PIG_ZOMBIE || event.getDamager().getType() == EntityType.IRON_GOLEM) {
                Entity entity = event.getDamager();
                if (entity.hasMetadata("owner")) {
                    String uuid = entity.getMetadata("owner").get(0).asString();
                    User user = DesertedPvP.getInstace().getUserManager().getUser(UUID.fromString(uuid));
                    user.addKS(1);
                }
            }
        }

        if (e.getEntity().getKiller() != null) {
            if (e.getEntity().getKiller() instanceof Player) {
                Player killer = e.getEntity().getKiller();
                pvp.getUserManager().getUser(killer).addKS();
                if (pvp.getUserManager().getUser(killer).getCurrentKit() != null) {
                    pvp.getUserManager().getUser(killer).getCurrentKit().gotKill(killer, e.getEntity());
                    pvp.getUserManager().getUser(killer).addCoins(2);
                }
            }
        }
    }

}
