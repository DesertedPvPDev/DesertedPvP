package codes.matthewp.desertedpvp.event.player;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.data.Messages;
import codes.matthewp.desertedpvp.data.SpawnData;
import codes.matthewp.desertedpvp.killstreak.KillStreaks;
import codes.matthewp.desertedpvp.kit.KitSelector;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    private DesertedPvP pvp;

    public JoinEvent(DesertedPvP pvp) {
        this.pvp = pvp;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        p.getActivePotionEffects().clear();
        p.setFireTicks(0);
        p.setGameMode(GameMode.SURVIVAL);
        p.setHealth(20);
        p.setFoodLevel(20);
        p.teleport(new Location(Bukkit.getWorld("world"),
                SpawnData.getX(), SpawnData.getY(), SpawnData.getZ(), SpawnData.getYaw(), SpawnData.getPitch()));
        p.setExp(0F);
        KitSelector.giveKitSelector(p);
        KillStreaks.giveKillStreaksItem(p);
        e.setJoinMessage("");
        Bukkit.broadcastMessage(Messages.getMessage("welcome").replaceAll("%PLAYER%", p.getName()));

        pvp.getUserManager().addUser(p.getUniqueId(), pvp.getUserManager().genUser(p.getUniqueId()));
    }
}
