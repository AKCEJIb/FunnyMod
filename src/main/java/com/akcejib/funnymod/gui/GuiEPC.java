package com.akcejib.funnymod.gui;

import com.akcejib.funnymod.inventory.ContainerEPC;
import com.akcejib.funnymod.tileentity.TileEntityEPC;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;

@SideOnly(Side.CLIENT)
public class GuiEPC extends GuiFunnyMod {

	private final TileEntityEPC te;

	public GuiEPC(InventoryPlayer playerInv, TileEntityEPC te) {
		super(new ContainerEPC(playerInv, te), "blockEPC", te);
		this.te = te;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTick, mouseX, mouseY);
		drawTexturedModalRect(guiLeft + 108, guiTop + 38, 176, 0, 33 * te.getEnergy() / te.getMaxEnergy(), 13);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);

		if (te.getEnergy() >= 0)
			fontRendererObj.drawString(I18n.format("gui.funnymod.EPC.energyStored", te.getEnergy()), 104, 25,
					0xFF444444);// 0xAARRGGBB
	}
}
