package com.akcejib.funnymod.init;

import com.akcejib.funnymod.tileentity.TileEntityEPC;
import com.akcejib.funnymod.utility.Names;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileEntities {

	public static void init() {
		GameRegistry.registerTileEntity(TileEntityEPC.class, Names.TileEntities.EPC);
	}
}
