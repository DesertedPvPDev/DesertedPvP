package codes.matthewp.desertedpvp.task;

import codes.matthewp.desertedpvp.data.CombatTag;
import codes.matthewp.desertedpvp.data.Messages;
import org.bukkit.entity.Player;

/**
 * Task to have a combat cooldown
 */
public class CombatCountdown implements Runnable {

    /**
     * Time in seconds
     */
    private int i = 20;

    /**
     * Player ins
     */
    private Player player;

    /**
     * Init a new countdown
     *
     * @param player Player to use
     */
    public CombatCountdown(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        this.i--;
        if (this.i <= 0) {
            player.sendMessage(Messages.getMessage("noLongerTagged"));
            CombatTag.removeTag(player);
        }
    }
}
