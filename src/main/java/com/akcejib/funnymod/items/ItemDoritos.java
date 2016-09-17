package com.akcejib.funnymod.items;

import com.akcejib.funnymod.FunnyMod;
import com.akcejib.funnymod.reference.Reference;

import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;

public class ItemDoritos extends ItemFood {

	public ItemDoritos(int p_i45339_1_, float p_i45339_2_, boolean p_i45339_3_, int duration, String name) {
		super(p_i45339_1_, p_i45339_2_, p_i45339_3_);
		this.setUnlocalizedName(name);
		this.setTextureName(Reference.MOD_ID + ":" + name);
		this.setAlwaysEdible();
		this.setPotionEffect(Potion.fireResistance.id, duration, 0, 1.0F);
		this.setCreativeTab(FunnyMod.tabFunnyMod);
	}

}
