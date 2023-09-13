package thaumicboots.main.utils.compat;

import net.minecraft.item.Item;

import cpw.mods.fml.common.Loader;
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
import thaumicboots.item.boots.voidwalker.ItemCometVoidwalkerBoots;
import thaumicboots.item.boots.voidwalker.ItemMeteorVoidwalkerBoots;
import thaumicboots.main.Config;

public class ExplorationsHelper implements IModHelper {

    private static boolean isExplorationsActive = false;
    public static final String EXPLORATIONS = "ThaumicExploration";

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

    // Tainted Magic + Thaumic Exploration Compat
    public static Item bootsCometVoid;
    public static Item bootsMeteorVoid;

    public static boolean isActive() {
        return isExplorationsActive;
    }

    @Override
    public void preInit() {
        if (Loader.isModLoaded(EXPLORATIONS) && Config.explorationsActive) {
            isExplorationsActive = true;
        }
    }

    @Override
    public void init() {
        if (isActive()) {
            getItems();
        }
    }

    @Override
    public void postInit() {}

    public void getItems() {
        bootsMeteoricComet = new ItemMeteoricCometBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
        GameRegistry.registerItem(bootsMeteoricComet, bootsMeteoricComet.getUnlocalizedName());

        bootsCometMeteor = new ItemCometMeteorBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
        GameRegistry.registerItem(bootsCometMeteor, bootsCometMeteor.getUnlocalizedName());

        if (EMTHelper.isActive()) {
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
        }

        if (TaintedHelper.isActive()) {
            bootsCometVoid = new ItemCometVoidwalkerBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
            GameRegistry.registerItem(bootsCometVoid, bootsCometVoid.getUnlocalizedName());

            bootsMeteorVoid = new ItemMeteorVoidwalkerBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
            GameRegistry.registerItem(bootsMeteorVoid, bootsMeteorVoid.getUnlocalizedName());
        }
    }
}
