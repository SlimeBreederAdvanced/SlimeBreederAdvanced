package com.slimebreeder.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.slimebreeder.SlimeBreeder;
import com.slimebreeder.entity.slime.LunarSlimeEntity;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SlimeOuterLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LunarSlimeRenderer extends MobRenderer<LunarSlimeEntity, SlimeModel<LunarSlimeEntity>> {

    public LunarSlimeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SlimeModel<>(pContext.bakeLayer(ModelLayers.SLIME)), 0.25F);
        this.addLayer(new SlimeOuterLayer<>(this, pContext.getModelSet()));
        this.addLayer(new SlimeItemLayer<>(this));
    }

    @Override
    public void render(LunarSlimeEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.shadowRadius = 0.25F * (float)pEntity.getSize();
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    protected void scale(LunarSlimeEntity pLivingEntity, PoseStack pMatrixStack, float pPartialTickTime) {
        float f = 0.999F;
        pMatrixStack.scale(f, f, f);
        pMatrixStack.translate(0.0D, (double)0.001F, 0.0D);
        float f1 = (float)pLivingEntity.getSize();
        float f2 = Mth.lerp(pPartialTickTime, pLivingEntity.squish, pLivingEntity.oSquish) / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        pMatrixStack.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    @Override
    public ResourceLocation getTextureLocation(LunarSlimeEntity pEntity) {
        return new ResourceLocation(SlimeBreeder.MODID, "textures/entity/slime/lunar_slime.png");
    }
}
