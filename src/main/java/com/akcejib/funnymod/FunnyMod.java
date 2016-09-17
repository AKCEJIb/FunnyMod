package com.akcejib.funnymod;

import com.akcejib.funnymod.init.ModBlocks;
import com.akcejib.funnymod.init.ModItems;
import com.akcejib.funnymod.init.ModRecipes;
import com.akcejib.funnymod.init.ModTileEntities;
import com.akcejib.funnymod.network.DescriptionHandler;
import com.akcejib.funnymod.network.NetworkHandler;
import com.akcejib.funnymod.proxy.CommonProxy;
import com.akcejib.funnymod.reference.Reference;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class FunnyMod {
	@Mod.Instance(Reference.MOD_ID)
	public static FunnyMod instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModBlocks.init();
		ModItems.init();
		ModRecipes.init();
		ModTileEntities.init();
		DescriptionHandler.init();
		NetworkHandler.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		proxy.preInit();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}

	@Mod.EventHandler
	public void preInit(FMLPostInitializationEvent event) {
		proxy.postInit();
	}

	public static CreativeTabs tabFunnyMod = new CreativeTabs("tabFunnyMod") {
		@Override
		public Item getTabIconItem() {
			return ModItems.itemInjector;
		}
	};
}
