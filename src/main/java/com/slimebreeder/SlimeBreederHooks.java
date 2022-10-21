package com.slimebreeder;

import com.slimebreeder.api.SlimeType;
import com.slimebreeder.entity.BaseSlimeEntity;
import com.slimebreeder.item.SBItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class SlimeBreederHooks {

    public static void handleSlimeInteract(BaseSlimeEntity entity, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.isEdible() && entity.getHunger() < 20.0D || entity.getHunger() <= 0) {
            entity.regenHunger(BaseSlimeEntity.REGEN_AMOUNT);
            entity.getLevel().addParticle(ParticleTypes.HEART, entity.getX(), entity.getY(), entity.getZ(), 0.0D, 0.0D, 0.0D);
            if (!player.getAbilities().invulnerable) {
                itemStack.shrink(1);
            }
        }
    }

    public static void handleDropDeath(BaseSlimeEntity entity) {
        if(entity.getLevel().isClientSide || !entity.getLevel().getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            return;
        }
        ItemStack stack = entity.getAbsorbedItem();
        if(!stack.isEmpty()) {
            entity.spawnAtLocation(stack);
        }
    }

    public static void handleHunger(BaseSlimeEntity entity) {
        if (entity.getHunger() > 0 && SlimeBreederConfig.CONFIG.enableHungerReduction.get()) {
            if (!entity.getLevel().isClientSide() && entity.isAlive() && --entity.hungerChangeTime <= 0) {
                entity.playSound(SoundEvents.TURTLE_LAY_EGG, 1.0F, (entity.getRandom().nextFloat() - entity.getRandom().nextFloat()) * 0.2F + 1.0F);
                SlimeBreederHooks.handleSlimeTypes(entity);
                entity.gameEvent(GameEvent.ENTITY_PLACE);
                entity.hungerChangeTime = entity.getRandom().nextInt(3000) + 3000;
                entity.reduceHunger(3.0F);
            }
        }

        if (entity.getHunger() <= 4) {
            entity.getLevel().addParticle(ParticleTypes.ANGRY_VILLAGER, entity.getX(), entity.getY(), entity.getZ(), 0.0D, 0.0D, 0.0D);
            entity.sendSystemMessage(Component.translatable(SlimeBreeder.MODID + "slime.nohungervalue"));
        }
    }

    protected static void handleSlimeTypes(BaseSlimeEntity entity) {
        if (entity.getSlimeType() == SlimeType.LUNAR_SLIME) {
            entity.spawnAtLocation(SBItems.LUNAR_SLIME_BALL.get());
        }else {
            entity.spawnAtLocation(Items.SLIME_BALL);
        }
    }

    public static void handleAbsorber(BaseSlimeEntity entity) {
        if(!entity.getAbsorbedItem().isEmpty()) {
            return;
        }

        AABB boundingBox = entity.getBoundingBox();
        List<ItemEntity> entities = entity.level.getEntities(EntityType.ITEM, boundingBox, item -> item.isAlive() && !item.isPickable() && !item.getItem()
                .isEmpty());
        entities.stream().findFirst().ifPresent(item -> {
            ItemStack stack = item.getItem();
            ItemStack absorbedStack = stack.split(1);
            entity.setAbsorbedItem(absorbedStack);
            if(stack.isEmpty()) {
                item.discard();
            }
        });
    }
}
