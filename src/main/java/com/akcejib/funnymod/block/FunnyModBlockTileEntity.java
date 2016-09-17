package com.akcejib.funnymod.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class FunnyModBlockTileEntity extends BlockContainer {

	public FunnyModBlockTileEntity(Material material) {
		super(material);
	}
	
	public FunnyModBlockTileEntity() {
		this(Material.rock);
	}
	

}
