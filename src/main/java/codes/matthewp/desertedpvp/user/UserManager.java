package codes.matthewp.desertedpvp.user;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * User DAO
 */
public class UserManager {

    private Map<UUID, User> playerUserMap;

    public UserManager() {
        playerUserMap = new HashMap<>();
    }

    public User getUser(Player p) {
        return playerUserMap.get(p.getUniqueId());
    }

    public User getUser(UUID id) {
        return playerUserMap.get(id);
    }

    public void addUser(UUID uuid, User user) {
        playerUserMap.put(uuid, user);
    }

    public User genUser(UUID uuid) {
        return new User(uuid);
    }

    public void removeUser(UUID id) {
        playerUserMap.remove(id);
    }
}
