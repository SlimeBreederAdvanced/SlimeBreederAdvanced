package com.slimebreeder.block;

import com.slimebreeder.SlimeBreeder;
import com.slimebreeder.SlimeBreederTab;
import com.slimebreeder.item.SBItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlimeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.function.Function;
import java.util.function.Supplier;

public class SBBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SlimeBreeder.MODID);

    public static final RegistryObject<Block> JAR_BLOCK = register("jar", JarBlock::new);
    public static final RegistryObject<Block> COW_JAR_BLOCK = register("cow_jar", CowJarBlock::new);
    public static final RegistryObject<Block> AQUA_SLIME_BLOCK = register("aqua_slime_block", SBBlocks::ofSlimeBlock);
    public static final RegistryObject<Block> FLAME_SLIME_BLOCK = register("flame_slime_block", SBBlocks::ofSlimeBlock);
    public static final RegistryObject<Block> LUNAR_SLIME_BLOCK = register("lunar_slime_block", SBBlocks::ofSlimeBlock);
    public static final RegistryObject<Block> JUNGLE_SLIME_BLOCK = register("jungle_slime_block", SBBlocks::ofSlimeBlock);
    public static final RegistryObject<Block> BLACK_SLIME_BLOCK = register("black_slime_block", SBBlocks::ofSlimeBlock);
    public static final RegistryObject<Block> PURPLE_SLIME_BLOCK = register("purple_slime_block", SBBlocks::ofSlimeBlock);
    public static final RegistryObject<Block> CORRUPT_SLIME_BLOCK = register("corrupt_slime_block", SBBlocks::ofSlimeBlock);

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier) {
        return register(name, blockSupplier, block -> new BlockItem(block, new Item.Properties().tab(SlimeBreederTab.TAB)));
    }

    public static <T extends Block> RegistryObject<T> registerBlock(DeferredRegister<Block> blocks, DeferredRegister<Item> items, String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory) {
        final String actualName = name.toLowerCase(Locale.ROOT);
        final RegistryObject<T> block = blocks.register(actualName, blockSupplier);
        if (blockItemFactory != null)
        {
            items.register(actualName, () -> blockItemFactory.apply(block.get()));
        }
        return block;
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory) {
        return registerBlock(SBBlocks.BLOCKS, SBItems.ITEMS, name, blockSupplier, blockItemFactory);
    }

    protected static Block ofSlimeBlock() {
        return new SlimeBlock(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK));
    }
}
