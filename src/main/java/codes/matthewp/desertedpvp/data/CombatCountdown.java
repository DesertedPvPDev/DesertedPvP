package codes.matthewp.desertedpvp.data;

import org.bukkit.entity.Player;

public class CombatCountdown implements Runnable {

    private int i = 20;

    private Player player;

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
