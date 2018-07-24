package codes.matthewp.hypepvp.event.player;

import codes.matthewp.hypepvp.HypePvP;
import codes.matthewp.hypepvp.data.Messages;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveEvent implements Listener {

    private HypePvP pvp;

    public LeaveEvent(HypePvP pvp) {
        this.pvp = pvp;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        e.setQuitMessage("");
        Bukkit.broadcastMessage(Messages.getMessage("bye").replaceAll("%PLAYER%", e.getPlayer().getName()));

        pvp.getUserManager().removeUser(e.getPlayer().getUniqueId());
    }
}
