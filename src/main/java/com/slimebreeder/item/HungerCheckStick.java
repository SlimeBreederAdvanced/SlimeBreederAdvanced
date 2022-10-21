package com.slimebreeder.item;

import com.slimebreeder.SlimeBreeder;
import com.slimebreeder.SlimeBreederTab;
import com.slimebreeder.entity.BaseSlimeEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class HungerCheckStick extends Item {

    public HungerCheckStick() {
        super(new Properties().tab(SlimeBreederTab.TAB));
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        if (pInteractionTarget instanceof BaseSlimeEntity) {
            pPlayer.sendSystemMessage(Component.translatable(SlimeBreeder.MODID + ".check.info"));
            pPlayer.sendSystemMessage(Component.translatable(Float.toString(((BaseSlimeEntity) pInteractionTarget).getHunger())));
        }
        return super.interactLivingEntity(pStack, pPlayer, pInteractionTarget, pUsedHand);
    }

}
