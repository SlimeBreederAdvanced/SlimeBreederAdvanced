package com.itchymichi.slimebreeder.entity.ai;

import com.itchymichi.slimebreeder.entity.monster.EntityCustomSlimeBase;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.MobEffects;

public class AICustomSlimeFaceRandom extends EntityAIBase
{
    private final EntityCustomSlimeBase slime;
    private float chosenDegrees;
    private int nextRandomizeTime;

    public AICustomSlimeFaceRandom(EntityCustomSlimeBase slimeIn)
    {
        this.slime = slimeIn;
        this.setMutexBits(2);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        return this.slime.getAttackTarget() == null && (this.slime.onGround || this.slime.isInWater() || this.slime.isInLava() || this.slime.isPotionActive(MobEffects.LEVITATION));
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        if (--this.nextRandomizeTime <= 0)
        {
            this.nextRandomizeTime = 40 + this.slime.getRNG().nextInt(60);
            this.chosenDegrees = (float)this.slime.getRNG().nextInt(360);
        }

        ((CustomSlimeMoveHelper)this.slime.getMoveHelper()).setDirection(this.chosenDegrees, false);
    }
}
