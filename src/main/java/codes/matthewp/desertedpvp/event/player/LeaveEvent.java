package codes.matthewp.desertedpvp.event.player;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.data.CombatTag;
import codes.matthewp.desertedpvp.data.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveEvent implements Listener {

    private DesertedPvP pvp;

    public LeaveEvent(DesertedPvP pvp) {
        this.pvp = pvp;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        e.setQuitMessage("");

        if (CombatTag.isTagged(e.getPlayer())) {
            if (e.getPlayer().getLastDamageCause().getEntity() instanceof Player) {
                Player toReward = (Player) e.getPlayer().getLastDamageCause().getEntity();
                if (pvp.getUserManager().getUser(toReward).getCurrentKit() != null) {
                    pvp.getUserManager().getUser(toReward).getCurrentKit().gotKill(toReward, e.getPlayer());
                    pvp.getUserManager().getUser(toReward).addCoins(2);
                }
            }
        }

        Bukkit.broadcastMessage(Messages.getMessage("bye").replaceAll("%PLAYER%", e.getPlayer().getName()));
    }
}
