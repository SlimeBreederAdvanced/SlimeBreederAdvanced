package com.itchymichi.slimebreeder.models;

import com.itchymichi.slimebreeder.entity.monster.EntityArtificalSlimeBase;
import com.itchymichi.slimebreeder.entity.monster.EntityArtificalSlimeRainbowCrystal;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class ModelArtificalSlime extends ModelBase {
    public double[] modelScale = new double[] { 0.5D, 0.5D, 0.5D };
    public ModelRenderer slimeBodies;
    public ModelRenderer Mouth;
    public ModelRenderer rightEye;
    public ModelRenderer leftEye;

    public ModelArtificalSlime(int layer) {
    	if (layer > 0)
        {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.rightEye = new ModelRenderer(this, 32, 4);
        this.rightEye.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightEye.addBox(1.25F, 5.25F, -3.5F, 2, 2, 2, 0.0F);
        this.Mouth = new ModelRenderer(this, 32, 8);
        this.Mouth.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mouth.addBox(0.0F, 7.9F, -3.5F, 1, 1, 1, 0.0F);
        this.leftEye = new ModelRenderer(this, 32, 0);
        this.leftEye.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftEye.addBox(-3.25F, 5.25F, -3.5F, 2, 2, 2, 0.0F);
        this.slimeBodies = new ModelRenderer(this, 0, 16);
        this.slimeBodies.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.slimeBodies.addBox(-3.0F, 5.0F, -3.0F, 6, 6, 6, 0.0F);
        }else {
        this.slimeBodies = new ModelRenderer(this, 0, 0);
        this.slimeBodies.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.slimeBodies.addBox(-4.0F, 4.0F, -4.0F, 8, 8, 8, 0.0F);
        }
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
    	//Entity compressed = EntityList.createEntityFromNBT(((EntityArtificalSlimeRainbowCrystal) entityIn).getCompressedType(), entityIn.getEntityWorld());
		Entity compressed = EntityList.createEntityByIDFromName(new ResourceLocation((((EntityArtificalSlimeRainbowCrystal) entityIn).getCompressedType())), entityIn.getEntityWorld());

    	Entity slime = new EntitySlime(entityIn.getEntityWorld());
    	if (compressed.getClass() == slime.getClass())
        {
    	this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
    	GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, 0.01F, 0.0F);
        GlStateManager.scale(1D / modelScale[0], 1D / modelScale[1], 1D / modelScale[2]);
        this.slimeBodies.render(scale);
        GlStateManager.popMatrix();
        }
    	if (this.rightEye != null && compressed.getClass() == slime.getClass())
        {
        GlStateManager.pushMatrix();
        GlStateManager.scale(1D / modelScale[0], 1D / modelScale[1], 1D / modelScale[2]);
        this.rightEye.render(scale);
        this.Mouth.render(scale);
        this.leftEye.render(scale);
        GlStateManager.popMatrix();
        }
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
