package codes.matthewp.hypepvp.event.interact;

import codes.matthewp.hypepvp.HypePvP;
import codes.matthewp.hypepvp.data.Messages;
import codes.matthewp.hypepvp.killstreak.IKillStreak;
import codes.matthewp.hypepvp.killstreak.KillStreakManager;
import codes.matthewp.hypepvp.killstreak.KillStreaks;
import codes.matthewp.hypepvp.kit.IKit;
import codes.matthewp.hypepvp.kit.KitManager;
import codes.matthewp.hypepvp.kit.KitSelector;
import codes.matthewp.hypepvp.user.User;
import codes.matthewp.hypepvp.user.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class InventoryClickEvent implements Listener {

    private HypePvP pvp;

    public InventoryClickEvent(HypePvP pvp) {
        this.pvp = pvp;
    }

    @EventHandler
    public void onClick(org.bukkit.event.inventory.InventoryClickEvent event) {
        if(strip(event.getInventory().getName()).equals(KitSelector.getGuiName())) {
            event.setCancelled(true);
            IKit kit = KitManager.getKitFromMat(event.getCurrentItem().getType());
            kit.giveKit(Bukkit.getPlayer(event.getWhoClicked().getUniqueId()));
            Bukkit.getPlayer(event.getWhoClicked().getUniqueId()).closeInventory();
        } else if(strip(event.getInventory().getName()).equals(KillStreaks.getGuiName())) {
            event.setCancelled(true);
            IKillStreak streak = KillStreakManager.getKSFromMat(event.getCurrentItem().getType());

            User user = pvp.getUserManager().getUser(event.getWhoClicked().getUniqueId());

            if(user.getCurrentKS() >= streak.getCost()) {
                streak.execute(Bukkit.getPlayer(event.getWhoClicked().getName()));
                Bukkit.getPlayer(event.getWhoClicked().getUniqueId()).closeInventory();
            } else {
                int neeeded;
                neeeded = streak.getCost() - user.getCurrentKS();

                String msg = Messages.getMessage("notEnoughKS");
                msg = msg.replaceAll("%NEEDED%", String.valueOf(neeeded));
                Bukkit.getPlayer(event.getWhoClicked().getUniqueId()).sendMessage(msg);

                Bukkit.getPlayer(event.getWhoClicked().getUniqueId()).closeInventory();
            }
        }
    }

    private String strip(String s) {
        return ChatColor.stripColor(s);
    }
}
