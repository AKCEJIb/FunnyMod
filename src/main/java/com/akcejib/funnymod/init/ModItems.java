package com.akcejib.funnymod.init;

import com.akcejib.funnymod.FunnyMod;
import com.akcejib.funnymod.items.ItemAmphetamine;
import com.akcejib.funnymod.items.ItemAntibiotic;
import com.akcejib.funnymod.items.ItemDoritos;
import com.akcejib.funnymod.items.ItemDrugsFunnyMod;
import com.akcejib.funnymod.items.ItemEnderPort;
import com.akcejib.funnymod.reference.Reference;
import com.akcejib.funnymod.utility.Names;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ModItems {

	// Drugs
	public static final ItemDrugsFunnyMod itemAntibiotics = new ItemAntibiotic(Names.Items.ANTIBIOTICS);
	public static final ItemDrugsFunnyMod itemAmphetamine = new ItemAmphetamine(Names.Items.AMPHETAMINE);
	public static final Item itemInjector = new Item().setUnlocalizedName(Names.Items.INJECTOR)
			.setTextureName(Reference.MOD_ID + ":" + Names.Items.INJECTOR).setCreativeTab(FunnyMod.tabFunnyMod);

	// EnderPort
	public static final Item itemEnderPort = new ItemEnderPort();

	// MLG
	public static final Item itemDoritos = new ItemDoritos(10, 10F, false, 10, Names.Items.DORITOS);
	public static final Item itemDoritosMLG = new ItemDoritos(20, 20F, true, 60, Names.Items.DORITOS_MLG);
	public static final Item itemDoritosPack = new Item().setUnlocalizedName(Names.Items.DORITOS_PACK)
			.setTextureName(Reference.MOD_ID + ":" + Names.Items.DORITOS_PACK).setCreativeTab(FunnyMod.tabFunnyMod);;

	public static void init() {
		// Drugs
		GameRegistry.registerItem(itemInjector, Names.Items.INJECTOR);
		GameRegistry.registerItem(itemAntibiotics, Names.Items.ANTIBIOTICS);
		GameRegistry.registerItem(itemAmphetamine, Names.Items.AMPHETAMINE);

		// EnderPort
		GameRegistry.registerItem(itemEnderPort, Names.Items.ENDERPORT);

		// MLG
		GameRegistry.registerItem(itemDoritos, Names.Items.DORITOS);
		GameRegistry.registerItem(itemDoritosMLG, Names.Items.DORITOS_MLG);
		GameRegistry.registerItem(itemDoritosPack, Names.Items.DORITOS_PACK);
	}

}
