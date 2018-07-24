package codes.matthewp.hypepvp.event.world;

import codes.matthewp.hypepvp.HypePvP;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class TransEntityEvent implements Listener {

    @EventHandler
    public void onTrans(EntityChangeBlockEvent e) {
        if (e.getEntity() instanceof FallingBlock) {
            FallingBlock fb = (FallingBlock) e.getEntity();
            if (fb.getMaterial() == Material.CHEST) {
                // This is the identifier for the create so we know
                // if it is ours or somehow a chest is falling
                if (fb.hasMetadata("isSupplyCrate")) {
                    e.setCancelled(true);
                    e.getBlock().setType(Material.CHEST);
                    List<MetadataValue> blockMeta = fb.getMetadata("isSupplyCrate");
                    e.getBlock().setMetadata("isSupplyCrate", new FixedMetadataValue(HypePvP.getInstace(), blockMeta.get(0).asString()));
                    Chest chest = (Chest) e.getBlock().getState();
                    Inventory inv = chest.getBlockInventory();
                    inv.addItem(new ItemStack(Material.WOOD));
                }
            }
        }
    }
}
