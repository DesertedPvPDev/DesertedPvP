package codes.matthewp.desertedpvp.event.interact;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.data.Messages;
import codes.matthewp.desertedpvp.killstreak.IKillStreak;
import codes.matthewp.desertedpvp.killstreak.KillStreakManager;
import codes.matthewp.desertedpvp.killstreak.KillStreaks;
import codes.matthewp.desertedpvp.kit.IKit;
import codes.matthewp.desertedpvp.kit.KitManager;
import codes.matthewp.desertedpvp.kit.KitSelector;
import codes.matthewp.desertedpvp.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class InventoryClickEvent implements Listener {

    private DesertedPvP pvp;

    public InventoryClickEvent(DesertedPvP pvp) {
        this.pvp = pvp;
    }

    @EventHandler
    public void onClick(org.bukkit.event.inventory.InventoryClickEvent event) {
        if (strip(event.getInventory().getName()).equals(KitSelector.getGuiName())) {
            event.setCancelled(true);
            IKit kit = KitManager.getKitFromMat(event.getCurrentItem().getType());
            kit.giveKit(Bukkit.getPlayer(event.getWhoClicked().getUniqueId()));
            User user = pvp.getUserManager().getUser(Bukkit.getPlayer(event.getWhoClicked().getUniqueId()));
            user.setKit(kit);
            Bukkit.getPlayer(event.getWhoClicked().getUniqueId()).closeInventory();
        } else if (strip(event.getInventory().getName()).equals(KillStreaks.getGuiName())) {
            event.setCancelled(true);
            IKillStreak streak = KillStreakManager.getKSFromMat(event.getCurrentItem().getType());

            User user = pvp.getUserManager().getUser(event.getWhoClicked().getUniqueId());
            System.out.println("DEBUG: " + user.getCurrentKS());
            if (user.getCurrentKS() >= streak.getCost()) {
                user.subtractKS(streak.getCost());
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
