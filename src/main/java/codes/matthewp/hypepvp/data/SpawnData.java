package codes.matthewp.hypepvp.data;

import java.util.HashMap;

public class SpawnData {

    private static HashMap<String, Integer> spawnDat;

    public static double getX() {
        return spawnDat.get("x");
    }

    public static double getY() {
        return spawnDat.get("y");
    }

    public static double getZ() {
        return spawnDat.get("z");
    }

    public static float getPitch() {
        return spawnDat.get("pitch");
    }

    public static float getYaw() {
        return spawnDat.get("yaw");
    }

    public static void addSpawnDat(String key, int value) {
        checkForNull();
        spawnDat.put(key, value);
    }

    private static void checkForNull() {
        if(spawnDat == null) {
            spawnDat = new HashMap<>();
        }
    }
}
