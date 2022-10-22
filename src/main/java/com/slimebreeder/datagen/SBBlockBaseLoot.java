package com.slimebreeder.datagen;

import net.minecraft.core.Registry;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("deprecation")
public class SBBlockBaseLoot extends BlockLoot {

    private final String modID;
    private final Set<Block> skipBlocks = new HashSet<>();

    public SBBlockBaseLoot(String modid) {
        this.modID = modid;
        setSkipBlocks();
    }

    @Override
    protected void add(Block pBlock, LootTable.Builder pLootTableBuilder) {
        super.add(pBlock, pLootTableBuilder);
        skipBlocks.add(pBlock);
    }

    protected void skip(Block... blocks) {
        Collections.addAll(skipBlocks, blocks);
    }

    protected void skip(Collection<Block> blocks) {
        skipBlocks.addAll(blocks);
    }

    protected void dropSelfWithContents(Set<Block> blocks) {
        for (Block block : blocks) {
            if (skipBlocks.contains(block)) {
                continue;
            }
            add(block, createSingleItemTable(block));
        }
    }

    @Override
    protected void addTables() {
        dropSelfWithContents(Registry.BLOCK.stream()
                .filter(block -> modID.equals(Registry.BLOCK.getKey(block).getNamespace()))
                .collect(Collectors.toSet()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return skipBlocks;
    }

    protected void skipBlocks(Block... blocks) {
        Collections.addAll(skipBlocks, blocks);
    }

    protected void setSkipBlocks() {

    }
}
