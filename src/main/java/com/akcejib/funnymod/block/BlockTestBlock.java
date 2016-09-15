package com.akcejib.funnymod.block;

import com.akcejib.funnymod.reference.Reference;

import net.minecraft.block.material.Material;

public class BlockTestBlock extends FunnyModBlock {

	public BlockTestBlock(Material material, String name) {
		super(material, name);
		setBlockTextureName(Reference.MOD_ID + ":" + name);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean isOpaqueCube()
    {
        return false;
    }

}
