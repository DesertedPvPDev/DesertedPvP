package codes.matthewp.desertedpvp.data;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * Util class used to keep track of location
 * of blocks that need to be reset
 */
public class BlockTracker {

    /**
     * List of locations of blocks that need to be removed.
     */
    private static List<Location> blockLocs = new ArrayList<>();

    /**
     * Add a block to be tracked.
     *
     * @param location The location of the block. b#getLocation()
     */
    public static void addBlock(Location location) {
        blockLocs.add(location);
    }

    /**
     * Check if a block is already set to be removed.
     * @param loc Location of the block
     * @return true if the block is set to be removed.
     */
    public static boolean isSetToBeRemoved(Location loc) {
        return blockLocs.contains(loc);
    }

    /**
     * Remove block from the world, and tracking list.
     * @param block Block to be removed.
     */
    public static void removeBlock(Block block) {
        block.setType(Material.AIR);
        blockLocs.remove(block.getLocation());
    }

    /**
     * Remove ALL blocks set to be removed.
     */
    public static void removeBlocks() {
        for (Location loc : blockLocs) {
            Block block = Bukkit.getWorld("world").getBlockAt(loc);
            removeBlock(block);
        }
    }
}
