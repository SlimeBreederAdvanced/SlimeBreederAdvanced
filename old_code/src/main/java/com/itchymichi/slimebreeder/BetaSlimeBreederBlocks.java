package com.itchymichi.slimebreeder;

import java.util.ArrayList;
import java.util.List;

import com.itchymichi.slimebreeder.blocks.MoltenCrystalSlime;
import com.itchymichi.slimebreeder.blocks.SlimeBlock;
import com.itchymichi.slimebreeder.blocks.SlimeCoreBlock;
import com.itchymichi.slimebreeder.blocks.SlimeGrate;
import com.itchymichi.slimebreeder.blocks.SlimeTap;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import slimeknights.tconstruct.smeltery.block.BlockTinkerFluid;


public class BetaSlimeBreederBlocks 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	

	public static final Block SLIMECORE = new SlimeCoreBlock("slimecore", Material.IRON);
	public static final Block SLIMEBLOCK = new SlimeBlock("slimeblock", Material.IRON);
	public static final Block SLIMETAP = new SlimeTap("slimetap", Material.IRON);
	public static final Block SLIMEGRATE = new SlimeGrate("slimegrate", Material.IRON);
	

	
	
}
