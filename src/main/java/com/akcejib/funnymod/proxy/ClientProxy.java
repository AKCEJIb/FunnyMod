package com.akcejib.funnymod.proxy;

import com.akcejib.funnymod.tileentity.TileEntityEPC;
import com.akcejib.funnymod.tileentity.render.RendererEPC;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEPC.class, new RendererEPC());
	}

	@Override
	public void init() {

	}

	@Override
	public void postInit() {

	}

	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}

}
