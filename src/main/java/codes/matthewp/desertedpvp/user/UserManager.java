package codes.matthewp.desertedpvp.user;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.teams.TeamMember;
import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
        user = DesertedPvP.getInstace().getCoinDataAccessor().fetchUserCoins(user);

        TeamMember tMember = null;
        if(DesertedPvP.getInstace().getTeamManager().getMemberFromUUID(uuid) == null) {
            tMember = DesertedPvP.getInstace().getTeamManager().createNewTeamMember(uuid);
        }
        else {
            tMember = DesertedPvP.getInstace().getTeamManager().getMemberFromUUID(uuid);
        }
        user.setTeamMember(tMember);
        return user;
    }

    /**
     * Save all users coins
     */
    public void saveUserCoins() {
        if (!playerUserMap.isEmpty()) {
            Validate.notNull(DesertedPvP.getInstace(), "PVP NULL");
            DesertedPvP.getInstace().getCoinDataAccessor().updateUsersCoins(playerUserMap.values());
        }
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
