package com.akcejib.funnymod.block.recepie;

import java.util.ArrayList;
import java.util.List;

import com.akcejib.funnymod.init.ModItems;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class RecipeEPC {

	private static final RecipeEPC recepieBase = new RecipeEPC();
	private List<Item> allowedList = new ArrayList<Item>();

	public static RecipeEPC recepieList() {
		return recepieBase;
	}

	private RecipeEPC() {
		allowedList.add(Items.diamond_hoe);
		allowedList.add(Items.diamond_axe);
		allowedList.add(Items.diamond_sword);
		allowedList.add(Items.diamond_pickaxe);
		allowedList.add(Items.diamond_shovel);
		allowedList.add(Items.diamond_boots);
		allowedList.add(Items.diamond_chestplate);
		allowedList.add(Items.diamond_helmet);
		allowedList.add(Items.diamond_leggings);

		allowedList.add(ModItems.itemEnderPort);

	}

	public List<Item> getList() {
		return allowedList;
	}
}
