package thaumicboots.main;

import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent.MissingMapping;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import thaumicboots.events.BootsEventHandler;
import thaumicboots.main.utils.CalendarHelper;
import thaumicboots.main.utils.LogHelper;
import thaumicboots.main.utils.VersionInfo;

@SuppressWarnings("unused")
@Mod(
        modid = VersionInfo.ModID,
        name = VersionInfo.ModName,
        version = VersionInfo.Version,
        dependencies = VersionInfo.Depends)
public class ThaumicBoots {

    @Mod.Instance(VersionInfo.ModID)
    public static ThaumicBoots instance;

    @SidedProxy(serverSide = "thaumicboots.main.CommonProxy", clientSide = "thaumicboots.main.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        CalendarHelper.calendar();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        BootsEventHandler entityEventHandler = new BootsEventHandler();
        MinecraftForge.EVENT_BUS.register(entityEventHandler);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void handleMissingMapping(FMLMissingMappingsEvent event) {
        for (MissingMapping mapping : event.get()) {
            // TODO: ... maybe not this.
            LogHelper.info(String.format("Missing mapping: %s - ignoring.", mapping.name));
            mapping.ignore();
        }
    }
}
