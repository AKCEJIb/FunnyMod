package com.akcejib.funnymod.tileentity.render;

import org.lwjgl.opengl.GL11;

import com.akcejib.funnymod.reference.Reference;
import com.akcejib.funnymod.tileentity.TileEntityEPC;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RendererEPC extends TileEntitySpecialRenderer {

	private final ResourceLocation textures;
	private RenderItem customRenderItem;

	public RendererEPC() {
		customRenderItem = new RenderItem() {
			@Override
			public boolean shouldBob() {
				return false;
			}

			@Override
			public boolean shouldSpreadItems() {
				return false;
			}
		};
		customRenderItem.setRenderManager(RenderManager.instance);
		textures = new ResourceLocation(Reference.MOD_ID + ":textures/models/wooden_plate.png");
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
		if (te != null) {
			renderItems((TileEntityEPC) te, x, y, z, scale);
		}

	}

	private void renderItems(TileEntityEPC te, double x, double y, double z, float scale) {
		if (te.getItemStacks()[0] != null && te.getItemStacks()[0].getItem() != null) {
			doRenderItem(te.getItemStacks()[0], x, y, z);
		}
	}

	public void doRenderItem(ItemStack itemstack, double x, double y, double z) {

		EntityItem item = new EntityItem(null);
		item.age = 0;
		item.hoverStart = (float) (Math.PI * 2.0F);
		item.setEntityItemStack(itemstack);

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glTranslatef(0.5F, 1.02F, 0.415F);
		GL11.glScalef(0.7F, 0.7F, 0.7F);
		GL11.glRotatef(90.0F, 90.0F, 0.0F, 0.0F);
		customRenderItem.doRender(item, 0, 0, 0, 0, 0);
		GL11.glPopMatrix();
	}

}
