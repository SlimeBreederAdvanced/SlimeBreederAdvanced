package com.itchymichi.slimebreeder.entity.ai.EntityColourSlime;

import com.google.common.base.Predicate;
import com.itchymichi.slimebreeder.entity.monster.EntityColorSlimeBase;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.scoreboard.Team;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityAIFindNearestOwner extends EntityAIBase 
{
    private static final Logger LOGGER = LogManager.getLogger();
    /** The entity that use this AI */
    private final EntityLiving entityLiving;
    /** Use to determine if an entity correspond to specification */
    private final Predicate<Entity> predicate;
    /** Used to compare two entities */
    private final EntityAINearestAttackableTarget.Sorter sorter;
    /** The current target */
    private EntityLivingBase entityTarget;
    
    private final EntityColorSlimeBase slime;
    
    private int Domesticated;

    public EntityAIFindNearestOwner(EntityLiving entityLivingIn, int domesticated)
    {
        this.entityLiving = entityLivingIn;
        this.slime = (EntityColorSlimeBase) entityLivingIn;
        this.Domesticated = domesticated;
        //System.out.println("Domesticated =" + Domesticated);
        //System.out.println("Domesticated ?" + domesticated);
        
        //this.Domesticated = domesticated;
        
        /*if (entityLivingIn instanceof EntityCreature)
        {
            LOGGER.warn("Use NearestAttackableTargetGoal.class for PathfinerMob mobs!");
        }*/

        this.predicate = new Predicate<Entity>()
        {
            public boolean apply(@Nullable Entity p_apply_1_)
            {
                if (!(p_apply_1_ instanceof EntityPlayer))
                {
                    return false;
                }
                else if (((EntityPlayer)p_apply_1_).capabilities.disableDamage)
                {
                    return false;
                }

                else
                {
                    double d0 = EntityAIFindNearestOwner.this.maxTargetRange();

                    if (p_apply_1_.isSneaking())
                    {
                        d0 *= 0.800000011920929D;
                    }

                    if (p_apply_1_.isInvisible())
                    {
                        float f = ((EntityPlayer)p_apply_1_).getArmorVisibility();

                        if (f < 0.1F)
                        {
                            f = 0.1F;
                        }

                        d0 *= (double)(0.7F * f);
                    }

                    return (double)p_apply_1_.getDistance(EntityAIFindNearestOwner.this.entityLiving) > d0 ? false : EntityAITarget.isSuitableTarget(EntityAIFindNearestOwner.this.entityLiving, (EntityLivingBase)p_apply_1_, false, true);
                }
            }
        };
        this.sorter = new EntityAINearestAttackableTarget.Sorter(entityLivingIn);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        double d0 = this.maxTargetRange();
        List<EntityPlayer> list = this.entityLiving.world.<EntityPlayer>getEntitiesWithinAABB(EntityPlayer.class, this.entityLiving.getEntityBoundingBox().expand(d0, 4.0D, d0), this.predicate);
        Collections.sort(list, this.sorter);

        if (list.isEmpty())
        {
            return false;
        }
        
        if(Domesticated >=5)
        {
        	return true;
        }
        
        else
        {
            this.entityTarget = (EntityLivingBase)list.get(0);
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        EntityLivingBase entitylivingbase = this.entityLiving.getAttackTarget();

        if (entitylivingbase == null)
        {
            return false;
        }
        
        if(this.entityLiving.getDistanceSq(entitylivingbase) <= 1.0D )
        {
        	this.entityLiving.setJumping(true);
        	return true;
        }
        
        else if (!entitylivingbase.isEntityAlive())
        {
            return false;
        }
        else if (entitylivingbase instanceof EntityPlayer && ((EntityPlayer)entitylivingbase).capabilities.disableDamage)
        {
            return false;
        }
        else
        {
            Team team = this.entityLiving.getTeam();
            Team team1 = entitylivingbase.getTeam();

            if (team != null && team1 == team)
            {
                return false;
            }
            else
            {
                double d0 = this.maxTargetRange();
                return this.entityLiving.getDistanceSq(entitylivingbase) > d0 * d0 ? false : !(entitylivingbase instanceof EntityPlayerMP) || !((EntityPlayerMP)entitylivingbase).interactionManager.isCreative();
            }
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.entityLiving.setAttackTarget(this.entityTarget);
        super.startExecuting();
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.entityLiving.setAttackTarget((EntityLivingBase)null);
        super.startExecuting();
    }

    /**
     * Return the max target range of the entiity (16 by default)
     */
    protected double maxTargetRange()
    {
        IAttributeInstance iattributeinstance = this.entityLiving.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
        return iattributeinstance == null ? 16.0D : iattributeinstance.getAttributeValue();
    }
    
    public void setDomesticated(int dom)
    {
    	this.Domesticated = dom;
    }
    
}
