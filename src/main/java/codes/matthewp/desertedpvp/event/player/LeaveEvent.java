package codes.matthewp.desertedpvp.event.player;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.data.Messages;
import org.bukkit.Bukkit;
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
        Bukkit.broadcastMessage(Messages.getMessage("bye").replaceAll("%PLAYER%", e.getPlayer().getName()));
    }
}
