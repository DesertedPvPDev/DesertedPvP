package codes.matthewp.hypepvp.data;

import java.util.HashMap;

public class TaskManager {

    private static HashMap<String, Integer> tasks;

    public static void registerTask(String id, int taskID) {
        checkNull();
        tasks.put(id, taskID);
    }

    public static int getTaskId(String id) {
        return tasks.get(id);
    }

    public static void removeTask(String id) {
        tasks.remove(id);
    }

    private static void checkNull() {
        if(tasks == null) {
            tasks = new HashMap<>();
        }
    }

}
