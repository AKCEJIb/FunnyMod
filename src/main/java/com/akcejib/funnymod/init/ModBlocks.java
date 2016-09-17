package com.akcejib.funnymod.init;

import com.akcejib.funnymod.block.BlockEPC;
import com.akcejib.funnymod.utility.Names;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class ModBlocks {

	public static final Block blockEPC = new BlockEPC();

	public static void init() {
		GameRegistry.registerBlock(blockEPC, Names.Blocks.EPC);
	}
}
