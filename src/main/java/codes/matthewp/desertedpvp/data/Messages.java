package codes.matthewp.desertedpvp.data;

import org.bukkit.ChatColor;

import java.util.HashMap;

public class Messages {

    private static HashMap<String, String> messages;

    public static void addMesssage(String key, String value) {
        if (messages == null) {
            messages = new HashMap<String, String>();
        }
        messages.put(key, ChatColor.translateAlternateColorCodes('&', value));
    }

    public static String getMessage(String key) {
        return messages.get(key);
    }
}
