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
    public static double bootsJumpChangeRate;
    public static double bootsSpeedChangeRate;

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
                "Jump-Change-Rate",
                0.25,
                "The rate that boot jump modifier changes when the Jump Modulation key is pressed. 'I will not provide support to those who change this -Alastor'",
                0.01,
                1.0);
        bootsJumpChangeRate = p.getDouble();

        p = configuration.get(
                BOOTS,
                "Speed-Change-Rate",
                0.25,
                "The rate that boot speed modifier changes when the Speed Modulation key is pressed. 'I will not provide support to those who change this -Alastor'",
                0.01,
                1.0);
        bootsSpeedChangeRate = p.getDouble();

    }
}
