package codes.matthewp.desertedpvp.placeholder;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.user.User;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class CoinsPlaceholder extends PlaceholderExpansion {

    public String getIdentifier() {
        return "pvpcoins";
    }

    public String getPlugin() {
        return null;
    }

    public String getAuthor() {
        return "Iterable";
    }

    public String getVersion() {
        return "0.0.1";
    }

    public String onPlaceholderRequest(Player player, String identifier) {
        System.out.println("PLACEHOLDER ACTION");

        if (player == null) {
            return "";
        }

        System.out.println("IDENTIFIER: " + identifier);
        if (identifier.equalsIgnoreCase("pcoins")) {
            System.out.println("FOUND PCOINS ID");
            User user = DesertedPvP.getInstace().getUserManager().getUser(player);
            return String.valueOf(user.getCoins());
        }

        return null;
    }
}
