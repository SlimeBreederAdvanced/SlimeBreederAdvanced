package com.itchymichi.slimebreeder.entity.ai;

import com.itchymichi.slimebreeder.entity.monster.EntityCustomSlimeBase;
import com.itchymichi.slimebreeder.entity.monster.EntityCustomSlimeBase;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;

public class AICustomSlimeAttack extends EntityAIBase
{
    private final EntityCustomSlimeBase slime;
    private int growTieredTimer;

    public AICustomSlimeAttack(EntityCustomSlimeBase slimeIn)
    {
        this.slime = slimeIn;
        this.setMutexBits(0);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        EntityLivingBase entitylivingbase = this.slime.getAttackTarget();
        return entitylivingbase == null ? false : (!entitylivingbase.isEntityAlive() ? false : !(entitylivingbase instanceof EntityPlayer) || !((EntityPlayer)entitylivingbase).capabilities.disableDamage) || (entitylivingbase instanceof EntityCustomSlimeBase && ((EntityCustomSlimeBase)entitylivingbase).getGrowingAge() == 0 && this.slime.getGrowingAge() == 0) ;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.growTieredTimer = 300;
        super.startExecuting();
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        EntityLivingBase entitylivingbase = this.slime.getAttackTarget();
        return entitylivingbase == null ? false : (!entitylivingbase.isEntityAlive() ? false : (entitylivingbase instanceof EntityPlayer && ((EntityPlayer)entitylivingbase).capabilities.disableDamage ? false : --this.growTieredTimer > 0));
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        this.slime.faceEntity(this.slime.getAttackTarget(), 10.0F, 10.0F);
        ((CustomSlimeMoveHelper)this.slime.getMoveHelper()).setDirection(this.slime.rotationYaw, ((EntityCustomSlimeBase) this.slime).canDamagePlayer());
    }
}
