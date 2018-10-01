package codes.matthewp.desertedpvp.kit;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedpvp.kit.kits.*;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KitManager {

    private static HashMap<Material, IKit> matToKit;
    private static List<IKit> kits;
    private static HashMap<Player, Integer> cooldown;

    //Cooldown related methods
    public static int getRemainingTime(Player p) {
        if(!isInCooldown(p)) {
            return -1;
        }
        return cooldown.get(p);
    }
    public static boolean isInCooldown(Player p) {
        return cooldown.containsKey(p);
    }
    public static boolean addPlayerToCooldown(Player p, int time) {
        if(!isInCooldown(p)) {
            cooldown.put(p, time);
            return true;
        }
        return false;
    }
    public static boolean removePlayerFromCooldown(Player p){
        if(!isInCooldown(p)) {
            return false;
        }
        cooldown.remove(p);
        return true;
    }
    //Need to be Completed with Messages
    public static void runCooldown() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if(!cooldown.isEmpty()) {
                    for(Player p: cooldown.keySet()) {
                        int time = getRemainingTime(p) - 1;
                        cooldown.put(p, time);

                        if(time == 0) {
                            removePlayerFromCooldown(p);
                        }
                    }
                }
            }
        }.runTaskTimerAsynchronously(DesertedPvP.getInstace(), 0, 20);
    }

    public static void addKit(IKit kit) {
        checkNull();
        kits.add(kit);
    }

    public static IKit getKit(String intelID) {
        for (IKit kit : kits) {
            if (kit.intelID().equals(intelID)) {
                return kit;
            }
        }
        return null;
    }

    public static IKit getKitFromMat(Material mat) {
        return matToKit.get(mat);
    }

    public static void lazyLoad() {
        addKit(new KnightKit());
        addKit(new KnightKit2());
        addKit(new TosserKit());
        addKit(new ArcherKit());
        addKit(new ArcherKit2());
        addKit(new ManiacKit());
        addKit(new JuggernautKit());
        addKit(new SniperKit());
        addKit(new BattleBaccaKit());
        addKit(new RichKit());
        addKit(new BeastMasterKit());
        addKit(new PorcupineKit());
        addKit(new GolemKit());
        addKit(new NinjaKIt());
        addKit(new PyroNinja());
        addKit(new ReaperKit());
        addKit(new ChefKit());
    }

    public static void loadKit(String key, ConfigurationSection section) {
        for (IKit kit : kits) {
            if (kit.intelID().equals(key)) {
                kit.load(section);
                matToKit.put(kit.iconMat, kit);
            }
        }
    }

    public static List<IKit> getPlayerKits(Player p) {
        checkNull();
        return kits;
    }

    private static void checkNull() {
        if (matToKit == null) {
            matToKit = new HashMap<>();
        }
        if (kits == null) {
            kits = new ArrayList<>();
        }
    }
    private static void checkNullCooldown() {
        if(cooldown == null) {
            cooldown = new HashMap<>();
        }
    }
 }
