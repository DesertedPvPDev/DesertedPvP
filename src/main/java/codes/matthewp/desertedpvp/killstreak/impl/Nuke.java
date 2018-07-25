package codes.matthewp.desertedpvp.killstreak.impl;

import codes.matthewp.desertedpvp.killstreak.IKillStreak;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class Nuke extends IKillStreak {

    private String message;
    private Sound sound;

    public Nuke() {
        super("nuke");
    }

    @Override
    public void load(ConfigurationSection config) {
        loadIcon(config.getConfigurationSection("icon"));
        message = config.getString("message");
        sound = Sound.valueOf(config.getString("sound"));
        setCost(config.getInt("cost"));
    }

    @Override
    public void execute(Player p) {
        p.sendMessage(message);
        p.playSound(p.getLocation(), sound, 1F, 1F);
        Location start = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 16, p.getLocation().getZ());
        // TODO
        /*
         * Needs to spawn a square like:
         * TTTTT
         * TTTTT
         * TTTTT
         * T = TNT and the middle block it the player
         */
    }
}
