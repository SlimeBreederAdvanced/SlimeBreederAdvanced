package com.slimebreeder.block;

import com.slimebreeder.SlimeBreeder;
import com.slimebreeder.registry.SBItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

public class SBBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SlimeBreeder.MODID);

    public static final RegistryObject<Block> JAR_BLOCK = register("jar_block", JarBlock::new, CreativeModeTab.TAB_MISC);

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier)
    {
        return register(name, blockSupplier, (Function<T, ? extends BlockItem>) null);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, CreativeModeTab group)
    {
        return register(name, blockSupplier, block -> new BlockItem(block, new Item.Properties().tab(group)));
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, Item.Properties blockItemProperties)
    {
        return register(name, blockSupplier, block -> new BlockItem(block, blockItemProperties));
    }

    public static <T extends Block> RegistryObject<T> registerBlock(DeferredRegister<Block> blocks, DeferredRegister<Item> items, String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory)
    {
        final String actualName = name.toLowerCase(Locale.ROOT);
        final RegistryObject<T> block = blocks.register(actualName, blockSupplier);
        if (blockItemFactory != null)
        {
            items.register(actualName, () -> blockItemFactory.apply(block.get()));
        }
        return block;
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory)
    {
        return registerBlock(SBBlocks.BLOCKS, SBItems.ITEMS, name, blockSupplier, blockItemFactory);
    }
}
