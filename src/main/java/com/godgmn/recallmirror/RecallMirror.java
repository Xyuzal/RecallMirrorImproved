package com.godgmn.recallmirror;

import org.apache.logging.log4j.Logger;

import com.godgmn.recallmirror.init.ModItems;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = RecallMirror.MODID, name = RecallMirror.NAME, version = RecallMirror.VERSION)
public class RecallMirror {
    public static final String MODID = "recallmirror";
    public static final String NAME = "Recall Mirror";
    public static final String VERSION = "1.0";
    public static final String ACCEPTED_MINECRAFT_VERSIONS = "[1.12]";

	public static final String PORTAL_SCROLL = "portal_scroll";
	public static final String RECALL_MIRROR = "recall_mirror";
	public static final String NETHER_RECALL_MIRROR = "nether_recall_mirror";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        ModItems.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) { }

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		System.out.println(RecallMirror.MODID + ":postInit");
	}

	@Instance
	public static RecallMirror instance;
}