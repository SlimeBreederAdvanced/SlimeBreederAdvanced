package com.itchymichi.slimebreeder.entity.BreederPlayer;

import com.mojang.authlib.GameProfile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BreederPlayer extends EntityPlayer
{

	public BreederPlayer(World worldIn, GameProfile gameProfileIn) {
		super(worldIn, gameProfileIn);
		
	}

	@Override
	public boolean isSpectator() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCreative() {
		// TODO Auto-generated method stub
		return false;
	}

}
