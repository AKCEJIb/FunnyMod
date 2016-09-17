package com.akcejib.funnymod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemAmphetamine extends ItemDrugsFunnyMod {

	public ItemAmphetamine(String name) {
		super(name);
	}

	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onEaten(stack, world, player);
		player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 10 * 20, 1));
		player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 10 * 20, 1));
		player.addPotionEffect(new PotionEffect(Potion.confusion.id, 10 * 20, 1));
		return stack;
	}
}
