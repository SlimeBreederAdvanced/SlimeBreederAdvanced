package com.itchymichi.slimebreeder.entity.ai;

import com.itchymichi.slimebreeder.entity.monster.EntityCustomSlimeBase;
import com.itchymichi.slimebreeder.entity.monster.EntityCustomSlimeBase;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNavigateGround;

public class AICustomSlimeFloat extends EntityAIBase
{
    private final EntityCustomSlimeBase slime;

    public AICustomSlimeFloat(EntityCustomSlimeBase slimeIn)
    {
        this.slime = slimeIn;
        this.setMutexBits(5);
        ((PathNavigateGround)slimeIn.getNavigator()).setCanSwim(true);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        return this.slime.isInWater() || this.slime.isInLava();
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        if (this.slime.getRNG().nextFloat() < 0.8F)
        {
            this.slime.getJumpHelper().setJumping();
        }

        ((CustomSlimeMoveHelper)this.slime.getMoveHelper()).setSpeed(1.2D);
    }
}