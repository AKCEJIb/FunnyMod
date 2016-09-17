package com.akcejib.funnymod.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModRecipes {
	public static void init() {

		// Default Recipe

		// -- items
		// ITEM: EnderTeleport
		GameRegistry.addRecipe(new ItemStack(ModItems.itemEnderPort, 1, 249), new Object[] { "RDR", "CDC", "PCP", 'R',
				Items.redstone, 'D', Blocks.diamond_block, 'C', Blocks.cactus, 'P', Items.ender_pearl });
		// ITEM: Doritos Pack
		GameRegistry.addRecipe(new ItemStack(ModItems.itemDoritosPack), new Object[] { "RRR", "BOB", "CBC", 'R',
				Items.blaze_powder, 'B', Items.cooked_beef, 'O', Items.carrot, 'C', Blocks.cactus });
		// ITEM: Doritos (MLG Edition)
		GameRegistry.addRecipe(new ItemStack(ModItems.itemDoritosMLG),
				new Object[] { "#E#", "EDE", "#E#", 'E', Items.emerald, 'D', ModItems.itemDoritos });
		// ITEM: Injector
		GameRegistry.addRecipe(new ItemStack(ModItems.itemInjector),
				new Object[] { "#GS", "G#G", "IG#", 'G', Blocks.glass, 'S', Items.stick, 'I', Items.iron_ingot });

		// -- blocks
		// BLOCK: EnderPort Charger
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockEPC), new Object[] { "RRR", "DCD", "RRR", 'R',
				Blocks.redstone_block, 'D', Blocks.diamond_block, 'C', Blocks.chest });

		// Shapeless Recipe

		// -- items
		// ITEM: Doritos
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.itemDoritos, 3), ModItems.itemDoritosPack);
		// ITEM: Amphetamine
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.itemAmphetamine), ModItems.itemInjector, Items.sugar,
				Items.redstone, Items.fermented_spider_eye);
		// ITEM: Antibiotics
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.itemAntibiotics), ModItems.itemInjector,
				Items.golden_apple, Items.golden_carrot);
	}
}
