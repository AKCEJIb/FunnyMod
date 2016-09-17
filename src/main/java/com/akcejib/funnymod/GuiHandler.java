package com.akcejib.funnymod;

import com.akcejib.funnymod.gui.GuiEPC;
import com.akcejib.funnymod.inventory.ContainerEPC;
import com.akcejib.funnymod.tileentity.TileEntityEPC;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

	public enum GuiIDs {
		EPC;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (GuiIDs.values()[ID]) {
		case EPC:
			return new ContainerEPC(player.inventory, (TileEntityEPC) world.getTileEntity(x, y, z));
		}
		throw new IllegalArgumentException("No gui with id " + ID);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (GuiIDs.values()[ID]) {
		case EPC:
			return new GuiEPC(player.inventory, (TileEntityEPC) world.getTileEntity(x, y, z));
		}
		throw new IllegalArgumentException("No gui with id " + ID);
	}

}
