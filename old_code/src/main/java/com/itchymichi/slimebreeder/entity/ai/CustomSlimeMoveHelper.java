package com.itchymichi.slimebreeder.entity.ai;

import com.itchymichi.slimebreeder.entity.monster.EntityColorSlimeBase;
import com.itchymichi.slimebreeder.entity.monster.EntityCustomSlimeBase;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.init.SoundEvents;

public class CustomSlimeMoveHelper extends EntityMoveHelper 
{
    private float yRot;
    private int jumpDelay;
    private final EntityCustomSlimeBase slime;
    private boolean isAggressive;

    public CustomSlimeMoveHelper(EntityCustomSlimeBase slimeIn)
    {
        super(slimeIn);
        this.slime = slimeIn;
        this.yRot = 180.0F * slimeIn.rotationYaw / (float)Math.PI;
    }

    public void setDirection(float p_179920_1_, boolean p_179920_2_)
    {
        this.yRot = p_179920_1_;
        this.isAggressive = p_179920_2_;
    }

    public void setSpeed(double speedIn)
    {
        this.speed = speedIn;
        this.action = EntityMoveHelper.Action.MOVE_TO;
    }

    public void onUpdateMoveHelper()
    {
        this.entity.rotationYaw = this.limitAngle(this.entity.rotationYaw, this.yRot, 90.0F);
        this.entity.rotationYawHead = this.entity.rotationYaw;
        this.entity.renderYawOffset = this.entity.rotationYaw;

        if (this.action != EntityMoveHelper.Action.MOVE_TO)
        {
            this.entity.setMoveForward(0.0F);
        }
        else
        {
            this.action = EntityMoveHelper.Action.WAIT;

            if (this.entity.onGround)
            {
                this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));

                if (this.jumpDelay-- <= 0)
                {
                    this.jumpDelay = ((EntityCustomSlimeBase) this.slime).getCustomJumpDelay();

                    if (this.isAggressive)
                    {
                        this.jumpDelay /= 3;
                    }

                    this.slime.getJumpHelper().setJumping();

                    if (((EntityCustomSlimeBase) this.slime).makesSoundOnJumpCustomSlime())
                    {
                    	float volume = 0.1F * 1.0F;
                    	
                        this.slime.playSound(SoundEvents.ENTITY_SLIME_JUMP, volume, ((this.slime.getRNG().nextFloat() - this.slime.getRNG().nextFloat()) * 0.2F + 1.0F) * 0.8F);
                    }
                }
                else
                {
                    this.slime.moveStrafing = 0.0F;
                    this.slime.moveForward = 0.0F;
                    this.entity.setAIMoveSpeed(0.0F);
                }
            }
            else
            {
                this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue()));
            }
        }
    }
}
