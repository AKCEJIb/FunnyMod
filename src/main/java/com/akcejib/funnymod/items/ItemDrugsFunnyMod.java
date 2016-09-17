package com.akcejib.funnymod.items;

import com.akcejib.funnymod.FunnyMod;
import com.akcejib.funnymod.init.ModItems;
import com.akcejib.funnymod.reference.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class ItemDrugsFunnyMod extends Item {

	public ItemDrugsFunnyMod(String name) {
		this.setUnlocalizedName(name);
		this.setTextureName(Reference.MOD_ID + ":" + name);
		this.maxStackSize = 1;
		this.setCreativeTab(FunnyMod.tabFunnyMod);
	}

	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		--stack.stackSize;
		world.playSoundEffect(player.posX, player.posY, player.posZ, "fm:ah", 1.0F, 1.0F);
		player.inventory.addItemStackToInventory(new ItemStack(ModItems.itemInjector, 1));
		return stack;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

		player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		return stack;
	}

	/**
	 * How long it takes to use or consume an item
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 20;
	}

	/**
	 * returns the action that specifies what animation to play when the items
	 * is being used
	 */
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.bow;
	}
}
