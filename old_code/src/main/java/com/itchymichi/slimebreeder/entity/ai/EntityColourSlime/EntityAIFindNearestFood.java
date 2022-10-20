package com.itchymichi.slimebreeder.entity.ai.EntityColourSlime;

import com.google.common.base.Predicate;
import com.itchymichi.slimebreeder.SlimeBreeder;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.util.math.Vec2f;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityAIFindNearestFood extends EntityAIBase
{

	
    private static final Logger LOGGER = LogManager.getLogger();
    private final EntityLiving mob;
    private final Predicate<EntityItem> predicate;
    private final EntityAINearestAttackableTarget.Sorter sorter;
    private EntityItem target;
    //private final Class <? extends EntityItem > classToCheck;

    public EntityAIFindNearestFood(EntityLiving mobIn /*Class<? extends EntityItem> p_i45884_2_*/)
    {
        this.mob = mobIn;
        //this.classToCheck = p_i45884_2_;

        /*if (mobIn instanceof EntityCreature)
        {
            LOGGER.warn("Use NearestAttackableTargetGoal.class for PathfinerMob mobs!");
        }*/

        this.predicate = new Predicate<EntityItem>()
        {
            public boolean apply(@Nullable EntityItem p_apply_1_)
            {
                double d0 = EntityAIFindNearestFood.this.getFollowRange();

                if (p_apply_1_.isSneaking())
                {
                    d0 *= 0.800000011920929D;
                }

                return true;
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
        List<EntityItem> list = this.mob.world.<EntityItem>getEntitiesWithinAABB(EntityItem.class, this.mob.getEntityBoundingBox().expand(d0, 4.0D, d0), this.predicate);
        Collections.sort(list, this.sorter);

        if (list.isEmpty())
        {
            return false;
        }
        else
        {
        	if(this.mob.getActivePotionEffect(SlimeBreeder.proxy.slimeHunger) != null && list.get(0).getItem().getItem() == Items.CHICKEN)
        	{
        		//System.out.println("Should Execute");
            this.target = (EntityItem)list.get(0);
        	this.mob.getLookHelper().setLookPosition(target.posX, target.posY, target.posZ,(float)this.mob.getHorizontalFaceSpeed(), (float)this.mob.getVerticalFaceSpeed());
            
            return true;
        	}else
        	return false;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        EntityLivingBase entitylivingbase = this.mob.getAttackTarget();

       /* if (entitylivingbase == null)
        {
            return false;
        }
        else if (!entitylivingbase.isEntityAlive())
        {
            return false;
        }*/
        double d0 = this.getFollowRange();
        List<EntityItem> list = this.mob.world.<EntityItem>getEntitiesWithinAABB(EntityItem.class, this.mob.getEntityBoundingBox().expand(d0, 4.0D, d0), this.predicate);
        Collections.sort(list, this.sorter);

        if (list.isEmpty())
        {
        	//System.out.println("Stop Empty");
            return false;
        }
        
        if(this.mob.getActivePotionEffect(SlimeBreeder.proxy.slimeHunger) == null)
    	{
        	//System.out.println("Stop not hungry");
            return false;
        }
        
        else
        {
        	if(this.mob.getActivePotionEffect(SlimeBreeder.proxy.slimeHunger) != null && list.get(0).getItem().getItem() == Items.CHICKEN)
        	{
        		//System.out.println("Continue Execute");
            this.target = (EntityItem)list.get(0);
        	this.mob.getLookHelper().setLookPosition(target.posX, target.posY, target.posZ,(float)this.mob.getHorizontalFaceSpeed(), (float)this.mob.getVerticalFaceSpeed());
            return true;
        	}else
        		//System.out.println("Stop NA");
        	return false;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
    	//this.mob.setLastAttacker(this.target);
    	//this.mob.set
       // this.mob.setAttackTarget(this.target);

    	this.mob.getLookHelper().setLookPosition(target.posX, target.posY, target.posZ,(float)this.mob.getHorizontalFaceSpeed(), (float)this.mob.getVerticalFaceSpeed());

        super.startExecuting();
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.mob.setAttackTarget((EntityLivingBase)null);
        this.target = null;
        super.startExecuting();
    }

    protected double getFollowRange()
    {
        IAttributeInstance iattributeinstance = this.mob.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
        return iattributeinstance == null ? 16.0D : iattributeinstance.getAttributeValue();
    }
	
}
