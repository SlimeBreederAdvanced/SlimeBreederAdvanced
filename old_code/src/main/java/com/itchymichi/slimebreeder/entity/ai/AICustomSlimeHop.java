package com.itchymichi.slimebreeder.entity.ai;

import com.itchymichi.slimebreeder.entity.monster.EntityCustomSlimeBase;

import net.minecraft.entity.ai.EntityAIBase;

public class AICustomSlimeHop extends EntityAIBase
{
    private final EntityCustomSlimeBase slime;

    public AICustomSlimeHop(EntityCustomSlimeBase slimeIn)
    {
        this.slime = slimeIn;
        this.setMutexBits(5);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        return true;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        ((CustomSlimeMoveHelper)this.slime.getMoveHelper()).setSpeed(1.0D);
    }
}
