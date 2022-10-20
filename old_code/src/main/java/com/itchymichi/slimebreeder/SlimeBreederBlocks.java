package com.itchymichi.slimebreeder;

import java.util.ArrayList;
import java.util.List;

import com.itchymichi.slimebreeder.blocks.RainbowArtificalSlimeBlock;
import com.itchymichi.slimebreeder.blocks.RainbowSlimeCasing;
import com.itchymichi.slimebreeder.blocks.RedSlimeCasing;
import com.itchymichi.slimebreeder.blocks.SlimeBackpack;
import com.itchymichi.slimebreeder.blocks.SlimeBrewingTank;
import com.itchymichi.slimebreeder.blocks.SlimeCoreBlock;
import com.itchymichi.slimebreeder.blocks.SlimeFurnaceOff;
import com.itchymichi.slimebreeder.blocks.SlimeFurnaceOn;
import com.itchymichi.slimebreeder.blocks.SlimeGrate;
import com.itchymichi.slimebreeder.blocks.SlimeMonocle;
import com.itchymichi.slimebreeder.blocks.SlimeTap;
import com.itchymichi.slimebreeder.blocks.SlimeTopHat;
import com.itchymichi.slimebreeder.blocks.BlueSlimeCasing;
import com.itchymichi.slimebreeder.blocks.ClearSlimeCasing;
import com.itchymichi.slimebreeder.blocks.CompressedSlime;
import com.itchymichi.slimebreeder.blocks.CrystalMonolithBase;
import com.itchymichi.slimebreeder.blocks.GreenSlimeCasing;
import com.itchymichi.slimebreeder.blocks.SolidSlime;
import com.itchymichi.slimebreeder.items.SlimeCore;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;


public class SlimeBreederBlocks 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	

	public static final Block SOLIDSLIME = new SolidSlime("solid_slime", Material.IRON);
	public static final Block COMPRESSEDSLIME = new CompressedSlime("compressed_slimeblock", Material.IRON);
	public static final Block CLEARSLIMECASING = new ClearSlimeCasing("clearslimecasing", Material.IRON);
	public static final Block REDSLIMECASING = new RedSlimeCasing("redslimecasing", Material.IRON);
	public static final Block BLUESLIMECASING = new BlueSlimeCasing("blueslimecasing", Material.IRON);
	public static final Block GREENSLIMECASING = new GreenSlimeCasing("greenslimecasing", Material.IRON);
	public static final Block RAINBOWSLIMECASING = new RainbowSlimeCasing("rainbowslimecasing", Material.IRON);
	public static final Block RAINBOWARTIFICALSLIMEBLOCK = new RainbowArtificalSlimeBlock("rainbowartificalslimeblock", Material.IRON);
	public static final Block CRYSTALMONOLITHBASE = new CrystalMonolithBase("crystalmonolithbase", Material.IRON);
	public static final Block SLIMECORE = new SlimeCoreBlock("slimecore", Material.IRON);
	public static final Block SLIMETAP = new SlimeTap("slimetap", Material.IRON);
	public static final Block SLIMETOPHAT = new SlimeTopHat("slimetophat", Material.IRON);
	public static final Block SLIMEMONOCLE = new SlimeMonocle("slimemonocle", Material.IRON);
	public static final Block SLIMEGRATE = new SlimeGrate("slimegrate", Material.IRON);
	public static final Block SLIMEBACKPACK = new SlimeBackpack("slimebackpack", Material.IRON);
	public static final Block SLIMEFURNACEOFF = new SlimeFurnaceOff("slimefurnaceoff", Material.IRON);
	public static final Block SLIMEFURNACEON = new SlimeFurnaceOn("slimefurnaceon", Material.IRON);	
	public static final Block SLIMEBREWINGTANK = new SlimeBrewingTank("slimebrewingtank", Material.IRON);
	
}
