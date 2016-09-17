package com.akcejib.funnymod.inventory;

import java.util.List;

import com.akcejib.funnymod.tileentity.TileEntityEPC;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerEPC extends ContainerFunnyMod {

	private TileEntityEPC te;

	private int lastEnergy = -1;

	public ContainerEPC(InventoryPlayer playerInv, TileEntityEPC te) {
		this.addSlotToContainer(new SlotEPC(te, 0, 56, 17));
		this.addSlotToContainer(new SlotEPC(te, 1, 56, 53));
		addPlayerSlots(playerInv, 8, 84);
		this.te = te;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return te.isUseableByPlayer(player);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		if (lastEnergy != te.getEnergy()) {
			for (ICrafting crafter : (List<ICrafting>) crafters) {
				crafter.sendProgressBarUpdate(this, 0, te.getEnergy());
			}
			lastEnergy = te.getEnergy();
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int value) {
		super.updateProgressBar(id, value);
		if (id == 0) {
			te.setEnergy(value);
		}

	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(slotIndex);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			// From here change accordingly...
			if (slotIndex < 2) {
				if (!mergeItemStack(itemstack1, 2, 38, true)) {
					return null;
				}
			} else {
				// Shift click single items only.

				for (int i = 0; i < 2; i++) {
					Slot shiftedInSlot = (Slot) inventorySlots.get(i);
					if (!shiftedInSlot.getHasStack() && shiftedInSlot.isItemValid(itemstack1))
						mergeItemStack(itemstack1, i, i + 1, false);

				}
			}
			// ...till here.

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(player, itemstack1);
		}

		return itemstack;
	}

}
