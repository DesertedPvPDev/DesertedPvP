package codes.matthewp.desertedpvp.event.player;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.data.BlockTracker;
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
                Chest chest = (Chest) e.getInventory().getHolder();
                if (chest.hasMetadata("isSupplyCrate")) {
                    if (BlockTracker.isSetToBeRemoved(chest.getLocation())) {
                        BlockTracker.removeBlock(chest.getBlock());
                    }
                }
            }
        }
    }
}
