package thaumicboots.main;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import thaumicboots.api.serverfiles.PacketHandler;
import thaumicboots.main.utils.CraftingManager;
import thaumicboots.main.utils.compat.ModHelperManager;

@SuppressWarnings("unused")
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        Config.Init(event.getSuggestedConfigurationFile());

        Config.setupBlocks();
        Config.setupItems();

        ModHelperManager.preInit();

        PacketHandler.initPackets();
    }

    public void init(FMLInitializationEvent event) {
        ModHelperManager.init();
    }

    public void postInit(FMLPostInitializationEvent event) {
        ModHelperManager.postInit();

        CraftingManager.setupCrafting();
    }

}
