package com.itchymichi.slimebreeder.entity.ai.EntityArtificalSlimeRainbowCrystal;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Predicate;
import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.entity.ai.EntityColourSlime.EntityAIFindNearestSlime;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayerMP;

public class EntityAIFindNearestCompressed extends EntityAIBase
{

    private static final Logger LOGGER = LogManager.getLogger();
    private final EntityLiving mob;
    private final Predicate<EntityLivingBase> predicate;
    private final EntityAINearestAttackableTarget.Sorter sorter;
    private EntityLivingBase target;
    private final Class <? extends EntityLivingBase > classToCheck;

    public EntityAIFindNearestCompressed(EntityLiving mobIn, Class <? extends EntityLivingBase > p_i45884_2_)
    {
        this.mob = mobIn;
        this.classToCheck = p_i45884_2_;

        /*if (mobIn instanceof EntityCreature)
        {
            LOGGER.warn("Use NearestAttackableTargetGoal.class for PathfinerMob mobs!");
        }*/

        this.predicate = new Predicate<EntityLivingBase>()
        {
            public boolean apply(@Nullable EntityLivingBase p_apply_1_)
            {
                double d0 = EntityAIFindNearestCompressed.this.getFollowRange();

                if (p_apply_1_.isSneaking())
                {
                    d0 *= 0.800000011920929D;
                }

                return p_apply_1_.isInvisible() ? false : ((double)p_apply_1_.getDistance(EntityAIFindNearestCompressed.this.mob) > d0 ? false : EntityAITarget.isSuitableTarget(EntityAIFindNearestCompressed.this.mob, p_apply_1_, false, true));
            }
        };
        this.sorter = new EntityAINearestAttackableTarget.Sorter(mobIn);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        double d0 = this.getFollowRange();
        List<EntityLivingBase> list = this.mob.world.<EntityLivingBase>getEntitiesWithinAABB(this.classToCheck, this.mob.getEntityBoundingBox().grow(d0, 4.0D, d0), this.predicate);
        Collections.sort(list, this.sorter);
        
        ;

        if (list.isEmpty())
        {
            return false;
        }
        else
        {
        	if(list.get(0).getClass() == this.classToCheck && list.get(0).getClass() != EntitySlime.class)
        	{
        		//System.out.println("Executing ... " + classToCheck);
            this.target = (EntityLivingBase)list.get(0);
            return true;
        	}else
        	return false;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        EntityLivingBase entitylivingbase = this.mob.getAttackTarget();
        
        if (entitylivingbase == null)
        {
            return false;
        }
        
        
        else if (!entitylivingbase.isEntityAlive())
        {
            return false;
        }
        
        else if (entitylivingbase.getClass() == this.classToCheck)
        {
        	//System.out.println("Dont attack");
            return false;
        }
        
        
        else
        {
        	//System.out.println("Continue executing");
            double d0 = this.getFollowRange();
            return this.mob.getDistanceSq(entitylivingbase) > d0 * d0 ? false : !(entitylivingbase instanceof EntityPlayerMP) || !((EntityPlayerMP)entitylivingbase).interactionManager.isCreative();
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
    	if(!(this.target.hasCustomName())) {
        this.mob.setAttackTarget(this.target);
       // System.out.println("target name = " + target.getCustomNameTag() );
       // System.out.println("target = " + target);
        super.startExecuting();
    	}
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.mob.setAttackTarget((EntityLivingBase)null);
        //System.out.println("reset");
        super.startExecuting();
    }

    protected double getFollowRange()
    {
        IAttributeInstance iattributeinstance = this.mob.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
        return iattributeinstance == null ? 16.0D : iattributeinstance.getAttributeValue();
    }
	
}
