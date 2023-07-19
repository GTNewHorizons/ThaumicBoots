package thaumicboots.main;

import java.io.File;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import thaumcraft.api.ThaumcraftApi;
import thaumicboots.item.boots.comet.ItemElectricCometBoots;
import thaumicboots.item.boots.comet.ItemNanoCometBoots;
import thaumicboots.item.boots.comet.ItemQuantumCometBoots;
import thaumicboots.item.boots.meteor.ItemElectricMeteorBoots;
import thaumicboots.item.boots.meteor.ItemNanoMeteorBoots;
import thaumicboots.item.boots.meteor.ItemQuantumMeteorBoots;
import thaumicboots.item.boots.unique.ItemCometMeteorBoots;
import thaumicboots.item.boots.unique.ItemMeteoricCometBoots;
import thaumicboots.item.boots.voidwalker.*;
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

    // Thaumic Explorations Compat
    public static Item bootsMeteoricComet;
    public static Item bootsCometMeteor;

    // Thaumic Explorations + EMT Compat
    public static Item bootsElectricMeteor;
    public static Item bootsNanoMeteor;
    public static Item bootsQuantumMeteor;
    public static Item bootsElectricComet;

    public static Item bootsNanoComet;
    public static Item bootsQuantumComet;

    // Tainted Magic + EMT compat
    public static Item bootsElectricVoid;
    public static Item bootsNanoVoid;
    public static Item bootsQuantumVoid;

    // Tainted Magic + Thaumic Exploration Compat
    public static Item bootsCometVoid;
    public static Item bootsMeteorVoid;

    // Tainted Magic Compat
    public static Item comaLasDrogas;

    // ----- Config State info ----------------------------------
    public static Configuration configuration;
    private static Config instance = null;

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

    public static void setupBlocks() {

        setupInfusionFucker();
    }

    public static void setupItems() {
        arcaniumLens = new ItemThaumicInterfacer();
        GameRegistry.registerItem(arcaniumLens, arcaniumLens.getUnlocalizedName());

        bootsElectricMeteor = new ItemElectricMeteorBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
        GameRegistry.registerItem(bootsElectricMeteor, bootsElectricMeteor.getUnlocalizedName());

        bootsNanoMeteor = new ItemNanoMeteorBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
        GameRegistry.registerItem(bootsNanoMeteor, bootsNanoMeteor.getUnlocalizedName());

        bootsQuantumMeteor = new ItemQuantumMeteorBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
        GameRegistry.registerItem(bootsQuantumMeteor, bootsQuantumMeteor.getUnlocalizedName());

        bootsElectricComet = new ItemElectricCometBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
        GameRegistry.registerItem(bootsElectricComet, bootsElectricComet.getUnlocalizedName());

        bootsNanoComet = new ItemNanoCometBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
        GameRegistry.registerItem(bootsNanoComet, bootsNanoComet.getUnlocalizedName());

        bootsQuantumComet = new ItemQuantumCometBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
        GameRegistry.registerItem(bootsQuantumComet, bootsQuantumComet.getUnlocalizedName());

        bootsMeteoricComet = new ItemMeteoricCometBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
        GameRegistry.registerItem(bootsMeteoricComet, bootsMeteoricComet.getUnlocalizedName());

        bootsCometMeteor = new ItemCometMeteorBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
        GameRegistry.registerItem(bootsCometMeteor, bootsCometMeteor.getUnlocalizedName());

        bootsElectricVoid = new ItemElectricVoidwalkerBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
        GameRegistry.registerItem(bootsElectricVoid, bootsElectricVoid.getUnlocalizedName());

        bootsNanoVoid = new ItemNanoVoidwalkerBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
        GameRegistry.registerItem(bootsNanoVoid, bootsNanoVoid.getUnlocalizedName());

        bootsQuantumVoid = new ItemQuantumVoidwalkerBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
        GameRegistry.registerItem(bootsQuantumVoid, bootsQuantumVoid.getUnlocalizedName());

        bootsCometVoid = new ItemCometVoidwalkerBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
        GameRegistry.registerItem(bootsCometVoid, bootsCometVoid.getUnlocalizedName());

        bootsMeteorVoid = new ItemMeteorVoidwalkerBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
        GameRegistry.registerItem(bootsMeteorVoid, bootsMeteorVoid.getUnlocalizedName());
    }

    private static void processConfigFile() {
        syncConfigs();
    }

    private static void syncConfigs() {
        doModuleConfigs();
    }

    private static void doModuleConfigs() {
        thaumcraftActive = configuration.get(CATEGORY_MODULES, "Thaumcraft", true).getBoolean();
    }

    public static void setupInfusionFucker() {}

    static {
        blockStoneDeviceRI = -1;
        blockStoneDeviceTwoRI = -2;
        blockStoneDeviceThreeRI = -3;
    }
}
