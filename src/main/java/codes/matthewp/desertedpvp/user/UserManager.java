package codes.matthewp.desertedpvp.user;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.data.Database;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * User DAO
 * Used to access all temp online data.
 */
public class UserManager {

    /**
     * Map of UUID to user object
     */
    private Map<UUID, User> playerUserMap;

    /**
     * Default constructor
     */
    public UserManager() {
        playerUserMap = new HashMap<>();
    }

    /**
     * Scans all players online and puts
     * them in the user cash if they aren't
     * already.
     */
    public void scanForUsers() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(!playerUserMap.containsKey(p.getUniqueId())) {
                genUser(p.getUniqueId());
            }
        }
    }

    /**
     * Get a user by their player object
     * @param p Player to fetch from
     * @return User
     */
    public User getUser(Player p) {
        return playerUserMap.get(p.getUniqueId());
    }

    /**
     * Get a user by their UUID
     * @param id UUID to fetch from
     * @return User
     */
    public User getUser(UUID id) {
        return playerUserMap.get(id);
    }

    /**
     * Add a user to the user cache
     * @param uuid UUID to radd
     * @param user User to match the UUID
     */
    public void addUser(UUID uuid, User user) {
        playerUserMap.put(uuid, user);
    }

    /**
     * Generate a user instance
     * @param uuid UUID to use
     * @return User
     */
    public User genUser(UUID uuid) {
        User user = new User(uuid);
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(DesertedPvP.getInstace(), new Runnable() {
            @Override
            public void run() {
               getUser(uuid).setCoins(Database.getInstance().getUsersCoins(user));
            }
        }, 20L);

        return user;
    }

    /**
     * Save all users coins
     */
    public void saveUserCoins() {
        Database.getInstance().updateUsersCoins(playerUserMap.values());
    }

    /**
     * Check if a user is already in local cache
     * @param p Player object
     * @return boolean True if we have the player locally
     */
    public boolean hasUser(Player p) {
        return playerUserMap.containsKey(p.getUniqueId());
    }
}
