package codes.matthewp.desertedpvp.data;

import org.bukkit.ChatColor;

import java.util.HashMap;

/**
 * Simple util to quickly access message configuration
 */
public class Messages {

    /**
     * List of keys to a message
     */
    private static HashMap<String, String> messages;

    /**
     * Add a message to the list
     *
     * @param key   Key to access message
     * @param value Message based on key
     */
    public static void addMesssage(String key, String value) {
        if (messages == null) {
            messages = new HashMap<>();
        }
        messages.put(key, ChatColor.translateAlternateColorCodes('&', value));
    }

    /**
     * Get a specific message from the list
     * @param key Key to access the message
     * @return String message
     */
    public static String getMessage(String key) {
        return messages.get(key);
    }
}
