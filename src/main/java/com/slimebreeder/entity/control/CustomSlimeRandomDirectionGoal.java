package com.slimebreeder.entity.control;

import com.slimebreeder.entity.BaseSlimeEntity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class CustomSlimeRandomDirectionGoal extends Goal {

    private final BaseSlimeEntity slime;
    private float chosenDegrees;
    private int nextRandomizeTime;

    public CustomSlimeRandomDirectionGoal(BaseSlimeEntity pSlime) {
        this.slime = pSlime;
        this.setFlags(EnumSet.of(Goal.Flag.LOOK));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        return this.slime.getTarget() == null && (this.slime.isOnGround() || this.slime.isInWater() || this.slime.isInLava() || this.slime.hasEffect(MobEffects.LEVITATION)) && this.slime.getMoveControl() instanceof CustomSlimeMoveControl;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        if (--this.nextRandomizeTime <= 0) {
            this.nextRandomizeTime = this.adjustedTickDelay(40 + this.slime.getRandom().nextInt(60));
            this.chosenDegrees = (float)this.slime.getRandom().nextInt(360);
        }

        ((CustomSlimeMoveControl)this.slime.getMoveControl()).setDirection(this.chosenDegrees, false);
    }
}
