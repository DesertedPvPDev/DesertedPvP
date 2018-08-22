package codes.matthewp.desertedpvp.data;

import org.bukkit.Bukkit;

import java.util.HashMap;

/**
 * Class to keep track of, start and cancel runnable tasks
 * Use this so that plugins don't have to remember task numbers,
 * only the task id.
 * NOTE: This class DOES NOT start the runnable, you must start the runnable and pass
 * the id to #registerTask
 */
public class TaskManager {

    /**
     * HashMap of ids to task numbers
     */
    private static HashMap<String, Integer> tasks;

    /**
     * Register a new task
     *
     * @param id     The id used to fetch the task number
     * @param taskID The id of the task that bukkit provides.
     */
    public static void registerTask(String id, int taskID) {
        checkNull();
        tasks.put(id, taskID);
    }

    /**
     * Get the bukkit id of a registered task.
     * @param id The id used to register the task. NOT the bukkit one.
     * @return The bukkit task ID
     */
    public static int getTaskId(String id) {
        checkNull();
        return tasks.get(id);
    }

    /**
     * Remove a task and cancel it running
     * @param id The id of the task used to register it.
     */
    public static void removeTask(String id) {
        checkNull();
        Bukkit.getScheduler().cancelTask(tasks.get(id));
        tasks.remove(id);
    }

    /**
     * Checks if the map of tasks is null.
     */
    private static void checkNull() {
        if (tasks == null) {
            tasks = new HashMap<>();
        }
    }

}
