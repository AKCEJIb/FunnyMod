package com.akcejib.funnymod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemAntibiotic extends ItemDrugsFunnyMod {

	public ItemAntibiotic(String name) {
		super(name);
	}

	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onEaten(stack, world, player);
		player.addPotionEffect(new PotionEffect(Potion.heal.id, 10 * 20, 1));
		return stack;
	}

}
