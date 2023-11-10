package thaumicboots.main.utils.compat;

import static thaumicboots.main.utils.compat.TaintedHelper.TAINTED_MAGIC;

import net.minecraft.item.Item;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import thaumcraft.api.ThaumcraftApi;
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
            getItems();
        }
    }

    @Override
    public void init() {}

    @Override
    public void postInit() {}

    public void getItems() {
        bootsMeteoricComet = new ItemMeteoricCometBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
        GameRegistry.registerItem(bootsMeteoricComet, bootsMeteoricComet.getUnlocalizedName());

        bootsCometMeteor = new ItemCometMeteorBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
        GameRegistry.registerItem(bootsCometMeteor, bootsCometMeteor.getUnlocalizedName());

        if (Loader.isModLoaded(TAINTED_MAGIC) && Config.taintedMagicActive) {
            bootsCometVoid = new ItemCometVoidwalkerBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
            GameRegistry.registerItem(bootsCometVoid, bootsCometVoid.getUnlocalizedName());

            bootsMeteorVoid = new ItemMeteorVoidwalkerBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
            GameRegistry.registerItem(bootsMeteorVoid, bootsMeteorVoid.getUnlocalizedName());
        }
    }
}
