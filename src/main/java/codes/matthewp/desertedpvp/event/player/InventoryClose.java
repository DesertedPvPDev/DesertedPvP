package codes.matthewp.desertedpvp.event.player;

import codes.matthewp.desertedpvp.DesertedPvP;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryClose implements Listener {

    private DesertedPvP pvp;

    public InventoryClose(DesertedPvP pvp) {
        this.pvp = pvp;
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getInventory().getType() == InventoryType.CHEST) {
            if (e.getPlayer() instanceof Player) {
                Player p = (Player) e.getPlayer();
                Chest chest = (Chest) e.getInventory().getHolder();
                p.sendMessage("X: " + chest.getX() + " Y: " + chest.getY() + " Z: " + chest.getZ());
            }
        }
    }
}
