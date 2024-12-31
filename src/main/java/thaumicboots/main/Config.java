package thaumicboots.main;

import java.io.File;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import thaumcraft.api.ThaumcraftApi;
import thaumicboots.item.boots.unique.ItemChristmasBoots;
import thaumicboots.item.boots.unique.ItemSeasonBoots;
import thaumicboots.item.boots.unique.ItemSlowBoots;
import thaumicboots.item.tools.ItemThaumicInterfacer;
import thaumicboots.main.utils.VersionInfo;

/**
 * A class to hold some data related to mod state & functions.
 *
 * @author Alastors_Game
 */
public class Config {

    public static final String CATEGORY_MODULES = "modules";
    public static final String BOOTS = "boots";

    public static boolean thaumcraftActive;

    public static Item arcaniumLens;
    public static Item seasonBoots;
    public static Item christmasBoots;
    public static Item slowBoots;
    // ----- Config State info ----------------------------------
    public static Configuration configuration;
    private static Config instance = null;

    public static boolean gtnhLibActive;
    public static boolean taintedMagicActive;
    public static boolean emtActive;
    public static boolean explorationsActive;
    public static double bootsChangeRate;
    public static double changeRateModifier;
    public static boolean allowInertiaCancelingFeature;

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

        seasonBoots = new ItemSeasonBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
        GameRegistry.registerItem(seasonBoots, seasonBoots.getUnlocalizedName());

        christmasBoots = new ItemChristmasBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
        GameRegistry.registerItem(christmasBoots, christmasBoots.getUnlocalizedName());

        slowBoots = new ItemSlowBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
        GameRegistry.registerItem(slowBoots, slowBoots.getUnlocalizedName());
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

        p = configuration.get(
                BOOTS,
                "Default-Change-Rate",
                0.25,
                "The rate that boot jump and speed modifiers change when the Jump/Speed Modulation key is pressed.",
                0.01,
                1.0);
        bootsChangeRate = p.getDouble();

        p = configuration.get(
                BOOTS,
                "Change-Rate-Modifier",
                .4,
                "The value that modifies the boot change rate when the Modulation Modifier key is pressed.",
                0.01,
                1.0);
        changeRateModifier = p.getDouble();

        p = configuration.get(BOOTS, "Allow-Inertia-Canceling-Feature", true);
        allowInertiaCancelingFeature = p.getBoolean();
    }
}
