package codes.matthewp.desertedpvp.placeholder;

import codes.matthewp.desertedpvp.DesertedPvP;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class PvPPlaceholders extends PlaceholderExpansion {
    @Override
    public String getIdentifier() {
        return "pvp";
    }

    @Override
    public String getPlugin() {
        return null;
    }

    @Override
    public String getAuthor() {
        return "Iterable";
    }

    @Override
    public String getVersion() {
        return "0.0.1";
    }

    @Override
    public String onPlaceholderRequest(Player player, String s) {

        if (player == null) {
            return "";
        }

        if (s.equalsIgnoreCase("coins")) {
            return String.valueOf(DesertedPvP.getInstace().getUserManager().getUser(player).getCoins());
        }
        return null;
    }
}
