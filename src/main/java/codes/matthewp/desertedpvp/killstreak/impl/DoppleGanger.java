package codes.matthewp.desertedpvp.killstreak.impl;

import codes.matthewp.desertedpvp.entity.EntityDoppleGanger;
import codes.matthewp.desertedpvp.entity.EntityTypes;
import codes.matthewp.desertedpvp.killstreak.IKillStreak;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class DoppleGanger extends IKillStreak {

    private String name;

    public DoppleGanger() {
        super("dopple_ganger");
    }

    @Override
    public void execute(Player p) {
        name = name.replaceAll("%PLAYER%", p.getName());
        EntityTypes.spawnEntity(new EntityDoppleGanger(p.getWorld(), p.getUniqueId()), p.getLocation(), name);
    }

    @Override
    public void load(ConfigurationSection config) {
        loadIcon(config.getConfigurationSection("icon"));
        name = color(config.getString("entityName"));
        setCost(config.getInt("cost"));
    }
}
