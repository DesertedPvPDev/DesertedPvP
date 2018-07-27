package codes.matthewp.desertedpvp.killstreak.impl;

import codes.matthewp.desertedpvp.entity.impl.EntityDoppelGanger;
import codes.matthewp.desertedpvp.entity.EntityTypes;
import codes.matthewp.desertedpvp.killstreak.IKillStreak;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class DoppleGanger extends IKillStreak {

    private String name;

    public DoppleGanger() {
        super("dopple_ganger");
    }

    @Override
    public void execute(Player p) {
        String playerName = name;
        playerName = playerName.replaceAll("%PLAYER%", p.getName());
        EntityTypes.spawnEntity(new EntityDoppelGanger(p.getWorld(), p.getUniqueId()), p.getLocation(), playerName);
    }

    @Override
    public void load(ConfigurationSection config) {
        loadIcon(config.getConfigurationSection("icon"));
        name = color(config.getString("entityName"));
        setCost(config.getInt("cost"));
    }
}
