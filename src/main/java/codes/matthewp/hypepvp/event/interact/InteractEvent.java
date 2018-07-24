package codes.matthewp.hypepvp.event.interact;

import codes.matthewp.hypepvp.killstreak.KillStreaks;
import codes.matthewp.hypepvp.kit.IKit;
import codes.matthewp.hypepvp.kit.KitManager;
import codes.matthewp.hypepvp.kit.KitSelector;
import codes.matthewp.hypepvp.kit.kits.AssassinKit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractEvent implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if(KitSelector.isKitSelector(e.getPlayer().getItemInHand())) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                KitSelector.openGUI(e.getPlayer());
            }
        } else if(KillStreaks.isKillStreaks(e.getPlayer().getItemInHand())) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                KillStreaks.openGUI(e.getPlayer());
            }
        }
    }
}
