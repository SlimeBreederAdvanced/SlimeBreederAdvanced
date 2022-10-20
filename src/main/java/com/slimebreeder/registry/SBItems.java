package com.slimebreeder.registry;

import com.slimebreeder.SlimeBreeder;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SBItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SlimeBreeder.MODID);

    public static final RegistryObject<Item> LUNAR_SLIME_EGG = ITEMS.register("lunar_slime_egg", () -> new ForgeSpawnEggItem(SBEntityTypes.LUNAR_SLIME_ENEITY, 0xff0000, 0x00ff00, new Item.Properties().tab(CreativeModeTab.TAB_FOOD)));

}
