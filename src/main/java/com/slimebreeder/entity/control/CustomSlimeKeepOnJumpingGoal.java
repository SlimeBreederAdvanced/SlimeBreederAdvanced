package com.slimebreeder.entity.control;

import com.slimebreeder.entity.BaseSlimeEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class CustomSlimeKeepOnJumpingGoal extends Goal {

    private final BaseSlimeEntity slime;

    public CustomSlimeKeepOnJumpingGoal(BaseSlimeEntity pSlime) {
        this.slime = pSlime;
        this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        return !this.slime.isPassenger();
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        ((CustomSlimeMoveControl)this.slime.getMoveControl()).setWantedMovement(1.0D);
    }
}
