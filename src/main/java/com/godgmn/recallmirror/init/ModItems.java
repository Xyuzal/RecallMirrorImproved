package com.godgmn.recallmirror.init;

import com.godgmn.recallmirror.RecallMirror;

import Items.ItemNetherRecallMirror;
import Items.ItemPortalScroll;
import Items.ItemRecallMirror;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber()
public class ModItems {
	static Item portalScroll;
	static Item recallMirror;
	static Item netherRecallMirror;
	static Item netherRecallMirrorCharged;

	public static void init() {
		portalScroll = new ItemPortalScroll("portal_scroll");
		recallMirror = new ItemRecallMirror("recall_mirror");
		netherRecallMirror = new ItemNetherRecallMirror("nether_recall_mirror");
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(portalScroll);
		event.getRegistry().registerAll(recallMirror);
		event.getRegistry().registerAll(netherRecallMirror);
	}

	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) {
		registerRender(portalScroll);
		registerRender(recallMirror);
		registerRender(netherRecallMirror);
		ModelLoader.setCustomModelResourceLocation(netherRecallMirror, 0, new ModelResourceLocation( netherRecallMirror.getRegistryName(), "charged"));
	}

	private static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation( item.getRegistryName(), "inventory"));
	}
}