package com.slimebreeder.entity;

import com.slimebreeder.SlimeBreeder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SBEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SlimeBreeder.MODID);

    public static RegistryObject<EntityType<LunarSlimeEntity>> LUNAR_SLIME_ENEITY = ENTITY_TYPES.register("lunar_slime", () -> EntityType.Builder.of(LunarSlimeEntity::new, MobCategory.CREATURE)
            .sized(2.04F, 2.04F)
            .clientTrackingRange(10)
            .build("lunar_slime"));

}
