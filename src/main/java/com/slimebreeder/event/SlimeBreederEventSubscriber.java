package com.slimebreeder.event;

import com.slimebreeder.entity.BaseSlimeEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class SlimeBreederEventSubscriber {

    public static void init() {
        final IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.addListener(SlimeBreederEventSubscriber::onMobInteract);
    }

    public static void onMobInteract(PlayerInteractEvent.EntityInteract event) {
        Entity entity = event.getTarget();
        Player player = event.getEntity();
        ItemStack itemStack = player.getItemInHand(event.getHand());
        if (entity instanceof BaseSlimeEntity && itemStack.isEdible()) {
            if (((BaseSlimeEntity) entity).getHunger() < 10) {
                player.playSound(SoundEvents.HORSE_EAT, 1.0F, 1.0F);
                entity.spawnAtLocation(Items.EGG);
            }
        }
    }
}
