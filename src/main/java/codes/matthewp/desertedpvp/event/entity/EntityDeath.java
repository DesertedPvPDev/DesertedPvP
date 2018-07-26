package codes.matthewp.desertedpvp.event.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeath implements Listener {

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if(e.getEntityType() == EntityType.PLAYER)
            return;
        e.setDroppedExp(0);
        e.getDrops().clear();
    }
}
