package com.itchymichi.slimebreeder.render.entity.layers.EntityCustomSlimeBase;

import com.itchymichi.slimebreeder.SlimeBreeder;
import com.itchymichi.slimebreeder.entity.monster.EntityColorSlimeBase;
import com.itchymichi.slimebreeder.entity.monster.EntityCustomSlimeBase;
import com.itchymichi.slimebreeder.models.ModelCustomSlimeBase;
import com.itchymichi.slimebreeder.models.ModelSlime2;
import com.itchymichi.slimebreeder.render.entity.RenderEntityCustomSlimeBase;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerCustomSlimeGelBase implements LayerRenderer<EntityCustomSlimeBase>
{
	
	private final RenderEntityCustomSlimeBase customslimebaseRenderer;
	private final ModelBase customslimebaseModel = new ModelCustomSlimeBase(0);
	private static final ResourceLocation customslimebaseTexture = new ResourceLocation(SlimeBreeder.MODID + ":textures/models/customslimebase.png");
	
	
	public LayerCustomSlimeGelBase(RenderEntityCustomSlimeBase slimeRendererIn)
    {
        this.customslimebaseRenderer = slimeRendererIn;
    }

	
	public void doRenderLayer(EntityCustomSlimeBase entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) 
	{
		this.customslimebaseRenderer.bindTexture(customslimebaseTexture);
		
        if (!entitylivingbaseIn.isInvisible())
        {
        	GlStateManager.pushMatrix();
        	//System.out.println(customslimebaseTexture);
            GlStateManager.color(0.5F, 0.5F, 1.0F, 1.0F);
            GlStateManager.enableNormalize();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            this.customslimebaseModel.setModelAttributes(this.customslimebaseRenderer.getMainModel());
            this.customslimebaseModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GlStateManager.disableBlend();
            GlStateManager.disableNormalize();
            GlStateManager.popMatrix();
        }
    }

    public boolean shouldCombineTextures()
    {
        return true;
    }

}
