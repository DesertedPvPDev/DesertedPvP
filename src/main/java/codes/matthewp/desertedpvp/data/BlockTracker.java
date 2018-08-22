package codes.matthewp.desertedpvp.data;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockTracker {

    private static List<Location> blockLocs = new ArrayList<>();

    public static void addBlock(Location location) {
        blockLocs.add(location);
    }

    public static boolean isSetToBeRemoved(Location loc) {
        return blockLocs.contains(loc);
    }

    public static void removeBlock(Block block) {
        block.setType(Material.AIR);
        blockLocs.remove(block.getLocation());
    }

    public static void removeBlocks() {
        for (Location loc : blockLocs) {
            Block block = Bukkit.getWorld("world").getBlockAt(loc);
            removeBlock(block);
        }
    }
}
