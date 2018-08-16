package codes.matthewp.desertedpvp.killstreak.impl;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.killstreak.IKillStreak;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class SupplyCrate extends IKillStreak {

    public SupplyCrate() {
        super("supply_crate");
    }

    @Override
    public void load(ConfigurationSection config) {
        loadIcon(config.getConfigurationSection("icon"));
        setCost(config.getInt("cost"));
        setPerm(config.getString("perm"));
    }

    @Override
    public void execute(Player p) {
        Location loc = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 64, p.getLocation().getZ());
        FallingBlock fb = p.getWorld().spawnFallingBlock(loc, Material.BEACON, (byte) 0);
        fb.setMetadata("isSupplyCrate", new FixedMetadataValue(DesertedPvP.getInstace(), p.getUniqueId()));
    }
}
