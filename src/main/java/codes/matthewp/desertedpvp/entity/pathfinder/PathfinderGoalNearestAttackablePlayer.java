package codes.matthewp.desertedpvp.entity.pathfinder;

import com.google.common.base.Predicate;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.entity.HumanEntity;

import javax.annotation.Nullable;
import java.util.UUID;

public class PathfinderGoalNearestAttackablePlayer extends PathfinderGoalNearestAttackableTarget {

    public PathfinderGoalNearestAttackablePlayer(EntityCreature creature, UUID owner) {
        super(creature, EntityHuman.class, true);
        this.c = new Predicate() {

            private boolean canAttack(EntityLiving entity) {

                // May only attack humans
                if(!(entity instanceof EntityPlayer)) {
                    System.out.println("DEBUG: Stopped from targeting a non-human");
                    return false;
                }

                if(((EntityPlayer) entity).getUniqueID().equals(owner)) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean apply(@Nullable Object o) {
                return canAttack((EntityLiving)o);
            }
        };
    }


}
