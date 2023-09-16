package thaumicboots.main;

import java.io.File;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import thaumicboots.item.tools.ItemThaumicInterfacer;
import thaumicboots.main.utils.VersionInfo;

/**
 * A class to hold some data related to mod state & functions.
 *
 * @author Alastors_Game
 */
public class Config {

    public static final String CATEGORY_MODULES = "modules";

    public static boolean thaumcraftActive;
    public static int blockStoneDeviceRI;
    public static int blockStoneDeviceTwoRI;
    public static int blockStoneDeviceThreeRI;

    public static Item arcaniumLens;

    // Tainted Magic Compat
    public static Item comaLasDrogas;

    // ----- Config State info ----------------------------------
    public static Configuration configuration;
    private static Config instance = null;

    public static boolean gtnhLibActive;
    public static boolean taintedMagicActive;
    public static boolean emtActive;
    public static boolean explorationsActive;

    public static void Init(File configFile) {
        if (instance != null) return;
        instance = new Config();
        FMLCommonHandler.instance().bus().register(instance);
        configuration = new Configuration(configFile);
        configuration.load();
        processConfigFile();

        configuration.save();
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(VersionInfo.ModName)) {
            if (configuration.hasChanged()) {
                configuration.save();
            }
        }
    }

    public static void saveConfigs() {
        configuration.save();
    }

    public static void setupBlocks() {}

    public static void setupItems() {
        arcaniumLens = new ItemThaumicInterfacer();
        GameRegistry.registerItem(arcaniumLens, arcaniumLens.getUnlocalizedName());
    }

    private static void processConfigFile() {
        syncConfigs();
    }

    private static void syncConfigs() {
        doModuleConfigs();
    }

    private static void doModuleConfigs() {
        Property p;
        // Requirements
        thaumcraftActive = configuration.get(CATEGORY_MODULES, "Thaumcraft", true).getBoolean();

        // Optional Modules
        p = configuration.get(CATEGORY_MODULES, "Electro-Magic-Tools", true);
        gtnhLibActive = p.getBoolean();

        p = configuration.get(CATEGORY_MODULES, "Tainted-Magic", true);
        taintedMagicActive = p.getBoolean();

        p = configuration.get(CATEGORY_MODULES, "Electro-Magic-Tools", true);
        emtActive = p.getBoolean();

        p = configuration.get(CATEGORY_MODULES, "Thaumic_Exploration", true);
        explorationsActive = p.getBoolean();
    }
}
