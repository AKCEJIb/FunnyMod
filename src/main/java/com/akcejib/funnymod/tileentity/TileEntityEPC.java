package com.akcejib.funnymod.tileentity;

import com.akcejib.funnymod.block.recepie.RecipeEPC;
import com.akcejib.funnymod.init.ModBlocks;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityEPC extends TileEntityFunnyMod implements IInventory {
	private ItemStack[] itemStacks = new ItemStack[2];
	private int side;
	private int energy = 0;
	private final int maxEnergy = 1500;

	@Override
	public void updateEntity() {
		if (!this.worldObj.isRemote) {
			if (itemStacks[0] != null) {
				if (energy > 0) {
					if (itemStacks[0].getItemDamage() > 0) {
						itemStacks[0].setItemDamage(itemStacks[0].getItemDamage() - 1);
						--energy;
					}
				}
			}
			if (itemStacks[1] != null) {

				if (isEnergyItem(itemStacks[1]) && energy + getItemEnergy(itemStacks[1]) <= maxEnergy) {
					--this.itemStacks[1].stackSize;

					if (this.itemStacks[1].stackSize == 0) {
						energy += getItemEnergy(itemStacks[1]);
						this.itemStacks[1] = null;
					}
					if (itemStacks[1] != null) {
						energy += getItemEnergy(itemStacks[1]);
					}
				}
			}
		}
	}

	public ItemStack[] getItemStacks() {
		return itemStacks;
	}

	public void setSide(int value) {
		side = value;
	}

	public int getSide() {
		return side;
	}

	public static int getItemEnergy(ItemStack stack) {
		if (stack != null) {
			if (stack.getItem() == Items.redstone)
				return 10;
			if (stack.getItem() == Item.getItemFromBlock(Blocks.redstone_block))
				return 90;
		}
		return 0;
	}

	public static boolean isEnergyItem(ItemStack stack) {
		return getItemEnergy(stack) > 0;
	}

	public void setEnergy(int value) {
		energy = value;
	}

	public int getEnergy() {
		return energy;
	}

	public int getMaxEnergy() {
		return maxEnergy;
	}

	@Override
	public void writeToPacket(ByteBuf buf) {
		for (ItemStack stack : itemStacks)
			ByteBufUtils.writeItemStack(buf, stack);
	}

	@Override
	public void readFromPacket(ByteBuf buf) {
		for (int i = 0; i < itemStacks.length; i++)
			itemStacks[i] = ByteBufUtils.readItemStack(buf);
		worldObj.markBlockRangeForRenderUpdate(xCoord, yCoord, zCoord, xCoord, yCoord, zCoord);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		energy = tag.getInteger("energyStored");
		itemStacks = new ItemStack[2];
		NBTTagList camoStackTag = tag.getTagList("itemStacks", 10);

		for (int i = 0; i < camoStackTag.tagCount(); i++) {
			NBTTagCompound t = camoStackTag.getCompoundTagAt(i);
			int index = t.getByte("index");
			if (index >= 0 && index < itemStacks.length) {
				itemStacks[index] = ItemStack.loadItemStackFromNBT(t);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setInteger("energyStored", energy);
		NBTTagList itemStacksTag = new NBTTagList();
		for (int i = 0; i < itemStacks.length; i++) {
			ItemStack stack = itemStacks[i];
			if (stack != null) {
				NBTTagCompound t = new NBTTagCompound();
				stack.writeToNBT(t);
				t.setByte("index", (byte) i);
				itemStacksTag.appendTag(t);
			}
		}
		tag.setTag("itemStacks", itemStacksTag);
	}

	@Override
	public int getSizeInventory() {
		return itemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.itemStacks[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int count) {
		if (this.itemStacks[slot] != null) {
			ItemStack itemstack;

			if (this.itemStacks[slot].stackSize <= count) {
				itemstack = this.itemStacks[slot];
				this.itemStacks[slot] = null;
				this.markDirty();
				return itemstack;
			} else {
				itemstack = this.itemStacks[slot].splitStack(count);

				if (this.itemStacks[slot].stackSize == 0) {
					this.itemStacks[slot] = null;
				}

				this.markDirty();
				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (this.itemStacks[slot] != null) {
			ItemStack itemstack = this.itemStacks[slot];
			this.itemStacks[slot] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		this.itemStacks[slot] = stack;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}

		this.markDirty();
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	@Override
	public String getInventoryName() {
		return ModBlocks.blockEPC.getUnlocalizedName() + ".name";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false
				: player.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;

	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {

		boolean state = false;
		if (slot == 1) {
			if (isEnergyItem(stack)) {
				state = true;
			}
		} else if (slot == 0 && !isEnergyItem(stack) && RecipeEPC.recepieList().getList().contains(stack.getItem())) {
			state = true;
		} else {
			state = false;
		}
		return state;
	}

}
