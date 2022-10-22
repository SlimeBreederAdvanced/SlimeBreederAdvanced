package com.slimebreeder.entity.goal;

import com.slimebreeder.entity.control.CustomSlimeMoveControl;
import com.slimebreeder.entity.slime.BaseSlimeEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class CustomSlimeFloatGoal extends Goal {
    private final BaseSlimeEntity slime;

    public CustomSlimeFloatGoal(BaseSlimeEntity pSlime) {
        this.slime = pSlime;
        this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
        pSlime.getNavigation().setCanFloat(true);
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        return (this.slime.isInWater() || this.slime.isInLava()) && this.slime.getMoveControl() instanceof CustomSlimeMoveControl;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        if (this.slime.getRandom().nextFloat() < 0.8F) {
            this.slime.getJumpControl().jump();
        }

        ((CustomSlimeMoveControl)this.slime.getMoveControl()).setWantedMovement(1.2D);
    }
}