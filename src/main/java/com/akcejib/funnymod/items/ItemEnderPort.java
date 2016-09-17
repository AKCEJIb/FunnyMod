package com.akcejib.funnymod.items;

import com.akcejib.funnymod.FunnyMod;
import com.akcejib.funnymod.reference.Reference;
import com.akcejib.funnymod.utility.Names;

import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEnderPort extends Item {

	public ItemEnderPort() {
		this.maxStackSize = 1;
		this.setMaxDamage(250);
		this.setNoRepair();
		this.setContainerItem(this);
		this.setUnlocalizedName(Names.Items.ENDERPORT);
		this.setTextureName(Reference.MOD_ID + ":" + Names.Items.ENDERPORT);
		this.setCreativeTab(FunnyMod.tabFunnyMod);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		if (player.capabilities.isCreativeMode) {
			return itemStack;
		} else {
			if (itemStack.getItemDamage() < itemStack.getMaxDamage() - 1) {
				itemStack.damageItem(1, player);
				world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

				if (!world.isRemote) {
					world.spawnEntityInWorld(new EntityEnderPearl(world, player));
				}
			}
			return itemStack;
		}
	}
}