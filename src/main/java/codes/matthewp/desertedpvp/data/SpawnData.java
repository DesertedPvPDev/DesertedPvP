package codes.matthewp.desertedpvp.data;

import java.util.HashMap;

/**
 * Simple utility to get the spawn data.
 * Used because spawn is access from many points.
 */
public class SpawnData {

    /**
     * Used to keep track of x,y,z
     */
    private static HashMap<String, Integer> spawnDat;

    /**
     * Get the x of spawn
     *
     * @return double x
     */
    public static double getX() {
        return spawnDat.get("x");
    }

    /**
     * Get the y of spawn
     * @return double y
     */
    public static double getY() {
        return spawnDat.get("y");
    }

    /**
     * Get the z of spawn
     * @return double z
     */
    public static double getZ() {
        return spawnDat.get("z");
    }

    /**
     * Get the pitch of spawn
     * @return float pitch
     */
    public static float getPitch() {
        return spawnDat.get("pitch");
    }

    /**
     * Get the yaw of spawn
     * @return float yaw
     */
    public static float getYaw() {
        return spawnDat.get("yaw");
    }

    /**
     * Add a piece to spawn data
     * @param key Key of value, such as X
     * @param value Integer value of the key
     */
    public static void addSpawnDat(String key, int value) {
        checkForNull();
        spawnDat.put(key, value);
    }

    /**
     * Private method to check if spawn data is null
     */
    private static void checkForNull() {
        if (spawnDat == null) {
            spawnDat = new HashMap<>();
        }
    }
}
