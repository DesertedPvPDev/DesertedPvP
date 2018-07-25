package codes.matthewp.desertedpvp.event.interact;

import codes.matthewp.desertedpvp.data.Messages;
import codes.matthewp.desertedpvp.killstreak.KillStreaks;
import codes.matthewp.desertedpvp.kit.KitSelector;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractEvent implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (KitSelector.isKitSelector(e.getPlayer().getItemInHand())) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                KitSelector.openGUI(e.getPlayer());
            }
        } else if (KillStreaks.isKillStreaks(e.getPlayer().getItemInHand())) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                KillStreaks.openGUI(e.getPlayer());
            }
        } else if(e.getAction() != Action.RIGHT_CLICK_BLOCK || e.getClickedBlock().getType() != Material.CHEST) {
            if(e.getClickedBlock().hasMetadata("isSupplyCrate")) {
                String ownerUUID = e.getClickedBlock().getMetadata("isSupplyCrate").get(0).asString();
                if(!e.getPlayer().getUniqueId().toString().equals(ownerUUID)) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(Messages.getMessage("notYourDrop"));
                }
            }
        }
    }
}
