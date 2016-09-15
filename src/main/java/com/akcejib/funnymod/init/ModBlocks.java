package com.akcejib.funnymod.init;

import com.akcejib.funnymod.block.BlockTestBlock;
import com.akcejib.funnymod.block.FunnyModBlock;
import com.akcejib.funnymod.utility.Names;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;

public class ModBlocks {

	public static final FunnyModBlock testBlock = new BlockTestBlock(Material.rock, Names.Blocks.TEST_BLOCK);

	public static void init() {
		GameRegistry.registerBlock(testBlock, Names.Blocks.TEST_BLOCK);
	}
}
