package com.slimebreeder;

import com.slimebreeder.api.SlimeType;
import com.slimebreeder.entity.BaseSlimeEntity;
import com.slimebreeder.item.SBItems;
import net.minecraft.world.entity.Entity;

public class SlimeBreederHooks {

    public static void handleSlimeTypes(Entity entity) {
        if (entity instanceof BaseSlimeEntity) {
            if (((BaseSlimeEntity) entity).getSlimeType() == SlimeType.LUNAR_SLIME) {
                entity.spawnAtLocation(SBItems.LUNAR_SLIME_BALL.get());
            }
        }
    }
}
