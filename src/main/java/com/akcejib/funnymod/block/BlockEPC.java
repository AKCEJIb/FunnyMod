package com.akcejib.funnymod.block;

import java.util.Random;

import com.akcejib.funnymod.FunnyMod;
import com.akcejib.funnymod.GuiHandler;
import com.akcejib.funnymod.init.ModBlocks;
import com.akcejib.funnymod.reference.Reference;
import com.akcejib.funnymod.tileentity.TileEntityEPC;
import com.akcejib.funnymod.utility.Names;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockEPC extends FunnyModBlockTileEntity {

	protected Random random = new Random();
	@SideOnly(Side.CLIENT)
	private IIcon frontIcon;

	public BlockEPC() {
		// super(Material.glass);
		setBlockName(Names.Blocks.EPC);
		setBlockTextureName(Reference.MOD_ID + ":" + Names.Blocks.EPC);
		this.setCreativeTab(FunnyMod.tabFunnyMod);
		this.setHardness(3.0F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypePiston);
		this.setHarvestLevel("pickaxe", 3);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
		return Item.getItemFromBlock(ModBlocks.blockEPC);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
		return Item.getItemFromBlock(ModBlocks.blockEPC);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ) {
		if (!world.isRemote) {
			player.openGui(FunnyMod.instance, GuiHandler.GuiIDs.EPC.ordinal(), world, x, y, z);
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityEPC();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
		TileEntityEPC te = (TileEntityEPC) world.getTileEntity(x, y, z);

		if (te != null) {
			for (int i1 = 0; i1 < te.getSizeInventory(); ++i1) {
				ItemStack itemstack = te.getStackInSlot(i1);

				if (itemstack != null) {
					float f = this.random.nextFloat() * 0.8F + 0.1F;
					float f1 = this.random.nextFloat() * 0.8F + 0.1F;
					float f2 = this.random.nextFloat() * 0.8F + 0.1F;

					while (itemstack.stackSize > 0) {
						int j1 = this.random.nextInt(21) + 10;

						if (j1 > itemstack.stackSize) {
							j1 = itemstack.stackSize;
						}

						itemstack.stackSize -= j1;
						EntityItem entityitem = new EntityItem(world, x + f, y + f1, z + f2,
								new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem()
									.setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
						}

						float f3 = 0.05F;
						entityitem.motionX = (float) this.random.nextGaussian() * f3;
						entityitem.motionY = (float) this.random.nextGaussian() * f3 + 0.2F;
						entityitem.motionZ = (float) this.random.nextGaussian() * f3;
						world.spawnEntityInWorld(entityitem);
					}
				}
			}

			world.func_147453_f(x, y, z, block);
		}

		super.breakBlock(world, x, y, z, block, par6);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		this.setDirection(world, x, y, z);
	}

	private void setDirection(World world, int x, int y, int z) {
		if (!world.isRemote) {
			Block block = world.getBlock(x, y, z - 1);
			Block block1 = world.getBlock(x, y, z + 1);
			Block block2 = world.getBlock(x - 1, y, z);
			Block block3 = world.getBlock(x + 1, y, z);
			byte b0 = 3;

			if (block.isOpaqueCube() && !block1.isOpaqueCube()) {
				b0 = 3;
			}

			if (block1.isOpaqueCube() && !block.isOpaqueCube()) {
				b0 = 2;
			}

			if (block2.isOpaqueCube() && !block3.isOpaqueCube()) {
				b0 = 5;
			}

			if (block3.isOpaqueCube() && !block2.isOpaqueCube()) {
				b0 = 4;
			}

			world.setBlockMetadataWithNotify(x, y, z, b0, 2);
		}
	}

	/**
	 * Called when the block is placed in the world.
	 */
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
		int l = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

		if (l == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}

		if (l == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}

		if (l == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}

		if (l == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon) {
		this.blockIcon = icon.registerIcon(Reference.MOD_ID + ":blockEPC_side");
		this.frontIcon = icon.registerIcon(Reference.MOD_ID + ":blockEPC_front");

	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? this.blockIcon
				: (side == 0 ? this.blockIcon : (side != meta ? this.blockIcon : this.frontIcon));
	}

}
