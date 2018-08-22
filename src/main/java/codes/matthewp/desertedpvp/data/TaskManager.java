package codes.matthewp.desertedpvp.data;

import org.bukkit.Bukkit;

import java.util.HashMap;

public class TaskManager {

    private static HashMap<String, Integer> tasks;

    public static void registerTask(String id, int taskID) {
        checkNull();
        tasks.put(id, taskID);
    }

    public static int getTaskId(String id) {
        checkNull();
        return tasks.get(id);
    }

    public static void removeTask(String id) {
        checkNull();
        tasks.remove(id);
        Bukkit.getScheduler().cancelTask(tasks.get(id));
    }

    private static void checkNull() {
        if (tasks == null) {
            tasks = new HashMap<>();
        }
    }

}
