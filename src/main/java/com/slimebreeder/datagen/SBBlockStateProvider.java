package com.slimebreeder.datagen;

import com.slimebreeder.SlimeBreeder;
import com.slimebreeder.block.SBBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SBBlockStateProvider extends BlockStateProvider {

    private final DeferredRegister<? extends Block> deferredRegister;
    private final Set<Block> skipBlocks = new HashSet<>();

    public SBBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper, DeferredRegister<? extends Block> deferredRegister) {
        super(gen, SlimeBreeder.MODID, exFileHelper);
        this.deferredRegister = deferredRegister;
    }

    @Override
    protected void registerStatesAndModels() {
        Set<Block> blocks = getBlocks();
        setSkipBlocks();
        blocks.removeAll(skipBlocks);
        registerBlock(blocks);
    }

    protected void skipBlock(Block... blocks) {
        Collections.addAll(skipBlocks, blocks);
    }

    protected Set<Block> getBlocks() {
        return deferredRegister.getEntries().stream().map(RegistryObject::get).collect(Collectors.toSet());
    }

    protected void registerBlock(Set<Block> blocks) {
        blocks.forEach(this::simpleBlock);
    }

    protected void setSkipBlocks() {
        skipBlock(SBBlocks.JAR_BLOCK.get(), SBBlocks.COW_JAR_BLOCK.get());
    }
}