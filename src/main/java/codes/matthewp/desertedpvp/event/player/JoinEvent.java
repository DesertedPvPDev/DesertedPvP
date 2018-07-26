package codes.matthewp.desertedpvp.event.player;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.data.Messages;
import codes.matthewp.desertedpvp.data.SpawnData;
import codes.matthewp.desertedpvp.user.User;
import org.bukkit.Bukkit;
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
        p.teleport(new Location(Bukkit.getWorld("world"),
                SpawnData.getX(), SpawnData.getY(), SpawnData.getZ(), SpawnData.getYaw(), SpawnData.getPitch()));

        User user = pvp.getUserManager().genUser(p.getUniqueId());
        user.restPlayer();
        pvp.getUserManager().addUser(p.getUniqueId(), user);

        e.setJoinMessage("");
        Bukkit.broadcastMessage(Messages.getMessage("welcome").replaceAll("%PLAYER%", p.getName()));
    }
}
