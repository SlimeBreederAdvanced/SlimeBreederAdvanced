package com.itchymichi.slimebreeder.models;

import com.itchymichi.slimebreeder.entity.monster.EntityArtificalSlimeGel;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;

public class ModelArtificalSlime2Backup extends ModelBase

	{
	    /** The slime's bodies, both the inside box and the outside box */
	    ModelRenderer slimeBodies;
	    /** The slime's right eye */
	    ModelRenderer slimeRightEye;
	    /** The slime's left eye */
	    ModelRenderer slimeLeftEye;
	    /** The slime's mouth */
	    ModelRenderer slimeMouth;
	    
	    ModelRenderer slimeHat;

	    public ModelArtificalSlime2Backup(int p_i1157_1_)
	    {
	        this.slimeBodies = new ModelRenderer(this, 0, p_i1157_1_);
	        this.slimeBodies.addBox(-4.0F, 16.0F, -4.0F, 8, 8, 8);

	        if (p_i1157_1_ > 0)
	        {

	        	this.slimeBodies = new ModelRenderer(this, 0, p_i1157_1_);
	            this.slimeBodies.addBox(-3.0F, 17.0F, -3.0F, 6, 6, 6);
	            this.slimeRightEye = new ModelRenderer(this, 32, 0);
	            this.slimeRightEye.addBox(-3.25F, 18.0F, -3.5F, 2, 2, 2);
	            this.slimeLeftEye = new ModelRenderer(this, 32, 4);
	            this.slimeLeftEye.addBox(1.25F, 18.0F, -3.5F, 2, 2, 2);
	            this.slimeMouth = new ModelRenderer(this, 32, 8);
	            this.slimeMouth.addBox(0.0F, 21.0F, -3.5F, 1, 1, 1);
	        	
	        	/*this.slimeBodies = new ModelRenderer(this, 0, p_i1157_1_);
	            this.slimeBodies.addBox(-3.0F, 17.0F, -3.0F, 6, 6, 6);
	            this.slimeRightEye = new ModelRenderer(this, 32, 0);
	            this.slimeRightEye.addBox(-3.25F, 18.0F, -3.5F, 2, 2, 2);
	            this.slimeLeftEye = new ModelRenderer(this, 32, 4);
	            this.slimeLeftEye.addBox(1.25F, 18.0F, -3.5F, 2, 2, 2);
	            this.slimeMouth = new ModelRenderer(this, 32, 8);
	            this.slimeMouth.addBox(0.0F, 21.0F, -3.5F, 1, 1, 1);*/
	            //this.slimeHat = new ModelRenderer(this, 32, 8);
	            //this.slimeHat.addBox(0.0F, 11.0F, -3.5F, 1, 1, 1);
	        	
	        }
	    }

	    /**
	     * Sets the models various rotation angles then renders the model.
	     */
	    public void render(Entity entityIn, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float scale)
	    {
	        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale, entityIn);
	        this.slimeBodies.render(scale);

	        if (this.slimeRightEye != null)
	        {
	            this.slimeRightEye.render(scale);
	            this.slimeLeftEye.render(scale);
	            this.slimeMouth.render(scale);
	            //this.slimeHat.render(scale);
	           
	        }
	    }
	    

	    

	    
	}