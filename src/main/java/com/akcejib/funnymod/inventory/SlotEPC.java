package com.akcejib.funnymod.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotEPC extends Slot {

	public SlotEPC(IInventory inv, int invIndex, int x, int y) {
		super(inv, invIndex, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return this.inventory.isItemValidForSlot(getSlotIndex(), stack);
	}
}
