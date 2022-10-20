package com.slimebreeder.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.slimebreeder.SlimeBreeder;
import com.slimebreeder.entity.BaseSlimeEntity;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SlimeOuterLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LunarSlimeRenderer extends MobRenderer<BaseSlimeEntity, SlimeModel<BaseSlimeEntity>> {

    public LunarSlimeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SlimeModel<>(pContext.bakeLayer(ModelLayers.SLIME)), 0.25F);
        this.addLayer(new SlimeOuterLayer<>(this, pContext.getModelSet()));
    }

    public void render(BaseSlimeEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.shadowRadius = 0.25F;
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(BaseSlimeEntity pEntity) {
        return new ResourceLocation(SlimeBreeder.MODID, "textures/entity/slime/lunar_slime.png");
    }
}
