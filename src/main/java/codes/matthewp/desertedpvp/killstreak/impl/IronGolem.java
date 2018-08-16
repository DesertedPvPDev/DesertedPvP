package codes.matthewp.desertedpvp.killstreak.impl;

import codes.matthewp.desertedpvp.entity.EntityTypes;
import codes.matthewp.desertedpvp.entity.impl.EntityIronBlocks;
import codes.matthewp.desertedpvp.killstreak.IKillStreak;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class IronGolem extends IKillStreak {

    private String name;

    public IronGolem() {
        super("iron_boi");
    }

    @Override
    public void execute(Player p) {
        String playerName = name;
        playerName = playerName.replaceAll("%PLAYER%", p.getName());
        EntityTypes.spawnEntity(new EntityIronBlocks(p.getWorld(), p.getUniqueId()), p.getLocation(), playerName);
    }

    @Override
    public void load(ConfigurationSection config) {
        loadIcon(config.getConfigurationSection("icon"));
        name = color(config.getString("entityName"));
        setCost(config.getInt("cost"));
        setPerm(config.getString("perm"));
    }
}
