package com.slimebreeder.entity.goal;

import com.slimebreeder.entity.AquaSlimeEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.Goal;

public class AquaSlimeSwimGoal extends Goal {
    private final AquaSlimeEntity slime;

    public AquaSlimeSwimGoal(AquaSlimeEntity slime) {
        this.slime = slime;
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        return true;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        int i = this.slime.getNoActionTime();
        if (i > 100) {
            this.slime.setMovementVector(0.0F, 0.0F, 0.0F);
        } else if (this.slime.getRandom().nextInt(reducedTickDelay(50)) == 0 || !this.slime.isInWater() || !this.slime.hasMovementVector()) {
            float f = this.slime.getRandom().nextFloat() * ((float)Math.PI * 2F);
            float f1 = Mth.cos(f) * 0.2F;
            float f2 = -0.1F + this.slime.getRandom().nextFloat() * 0.2F;
            float f3 = Mth.sin(f) * 0.2F;
            this.slime.setMovementVector(f1, f2, f3);
        }

    }
}
