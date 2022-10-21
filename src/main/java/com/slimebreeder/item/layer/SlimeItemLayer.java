package com.slimebreeder.item.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.slimebreeder.api.AbsorberAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public class SlimeItemLayer<T extends Entity> extends RenderLayer<T, SlimeModel<T>> {

    public SlimeItemLayer(RenderLayerParent<T, SlimeModel<T>> pRenderer) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, T entity, float f, float g, float h, float j, float k, float l) {

        if(entity.isAlive() && !entity.isInvisible()) {

            if(entity instanceof AbsorberAPI abs) {
                ItemStack stack = abs.getAbsorbedItem();
                if(stack.isEmpty()) {
                    return;
                }
                if(!stack.isEmpty()) {
                    poseStack.pushPose();
                    poseStack.mulPose(Vector3f.XP.rotationDegrees(180));
                    poseStack.translate(0, -1, 0);
                    poseStack.mulPose(Vector3f.XP.rotationDegrees(90));
                    poseStack.translate(0, -(4 * 0.0626), 0);
                    poseStack.translate(0, 0, -0.0626 / 4);
                    poseStack.mulPose(Vector3f.YP.rotationDegrees(90));
                    Minecraft.getInstance()
                            .getItemRenderer()
                            .renderStatic(stack, ItemTransforms.TransformType.GROUND, i, OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, (int) entity.blockPosition()
                                    .asLong());
                    poseStack.popPose();
                }

            }
        }
    }
}
