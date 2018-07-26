package codes.matthewp.desertedpvp.entity;

import net.minecraft.server.v1_8_R3.Entity;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.Map;

public enum EntityTypes {

    DOPPEL_GANGER("PigZombie", 57, EntityDoppelGanger.class);

    private EntityTypes(String name, int id, Class<? extends Entity> custom) {
        addToMaps(custom, name, id);
    }

    public static void spawnEntity(Entity entity, Location loc, String name) {
        entity.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());

        net.minecraft.server.v1_8_R3.ItemStack sword = CraftItemStack.asNMSCopy(new ItemStack(Material.STONE_SWORD));
        net.minecraft.server.v1_8_R3.ItemStack helm = CraftItemStack.asNMSCopy(new ItemStack(Material.IRON_HELMET));
        net.minecraft.server.v1_8_R3.ItemStack chest = CraftItemStack.asNMSCopy(new ItemStack(Material.IRON_CHESTPLATE));
        net.minecraft.server.v1_8_R3.ItemStack legs = CraftItemStack.asNMSCopy(new ItemStack(Material.IRON_LEGGINGS));
        net.minecraft.server.v1_8_R3.ItemStack boots = CraftItemStack.asNMSCopy(new ItemStack(Material.IRON_BOOTS));

        entity.setCustomName(name);
        entity.setCustomNameVisible(true);
        entity.setEquipment(0, sword);
        entity.setEquipment(4, helm);
        entity.setEquipment(3, chest);
        entity.setEquipment(2, legs);
        entity.setEquipment(1, boots);
        ((CraftWorld) loc.getWorld()).getHandle().addEntity(entity);
    }

    @SuppressWarnings("unchecked")
    private static void addToMaps(Class clazz, String name, int id) {
        ((Map) getPrivateField("c", net.minecraft.server.v1_8_R3.EntityTypes.class, null)).put(name, clazz);
        ((Map) getPrivateField("d", net.minecraft.server.v1_8_R3.EntityTypes.class, null)).put(clazz, name);
        ((Map) getPrivateField("f", net.minecraft.server.v1_8_R3.EntityTypes.class, null)).put(clazz, id);
    }

    public static Object getPrivateField(String fieldName, Class clazz, Object object) {
        Field field;
        Object o = null;

        try {
            field = clazz.getDeclaredField(fieldName);

            field.setAccessible(true);

            o = field.get(object);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return o;
    }
}
