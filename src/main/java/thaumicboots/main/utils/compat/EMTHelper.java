package thaumicboots.main.utils.compat;

import static thaumicboots.main.utils.compat.ExplorationsHelper.EXPLORATIONS;
import static thaumicboots.main.utils.compat.TaintedHelper.TAINTED_MAGIC;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.registry.GameRegistry;
import emt.init.EMTItems;
import flaxbeard.thaumicexploration.ThaumicExploration;
import taintedmagic.common.registry.ItemRegistry;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;
import thaumicboots.api.TB_Aspect;
import thaumicboots.item.boots.comet.ItemElectricCometBoots;
import thaumicboots.item.boots.comet.ItemNanoCometBoots;
import thaumicboots.item.boots.comet.ItemQuantumCometBoots;
import thaumicboots.item.boots.meteor.ItemElectricMeteorBoots;
import thaumicboots.item.boots.meteor.ItemNanoMeteorBoots;
import thaumicboots.item.boots.meteor.ItemQuantumMeteorBoots;
import thaumicboots.item.boots.voidwalker.ItemElectricVoidwalkerBoots;
import thaumicboots.item.boots.voidwalker.ItemNanoVoidwalkerBoots;
import thaumicboots.item.boots.voidwalker.ItemQuantumVoidwalkerBoots;
import thaumicboots.main.Config;

public class EMTHelper implements IModHelper {

    private static boolean isEMTActive = false;
    public static final String EMT = "EMT";

    // Tainted Magic + EMT compat
    public static Item bootsElectricVoid;
    public static Item bootsNanoVoid;
    public static Item bootsQuantumVoid;

    // Thaumic Explorations + EMT Compat
    public static Item bootsElectricMeteor;
    public static Item bootsNanoMeteor;
    public static Item bootsQuantumMeteor;
    public static Item bootsElectricComet;

    public static Item bootsNanoComet;
    public static Item bootsQuantumComet;

    public static boolean isActive() {
        return isEMTActive;
    }

    @Override
    public void preInit() {
        if (Loader.isModLoaded(EMT) && Config.emtActive) {
            isEMTActive = true;
            getItems();
        }
    }

    @Override
    public void init() {}

    @Override
    public void postInit() {}

    @Optional.Method(modid = "EMT")
    public void getItems() {
        if (Loader.isModLoaded(TAINTED_MAGIC) && Config.taintedMagicActive) {
            bootsElectricVoid = new ItemElectricVoidwalkerBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
            GameRegistry.registerItem(bootsElectricVoid, bootsElectricVoid.getUnlocalizedName());

            bootsNanoVoid = new ItemNanoVoidwalkerBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
            GameRegistry.registerItem(bootsNanoVoid, bootsNanoVoid.getUnlocalizedName());

            bootsQuantumVoid = new ItemQuantumVoidwalkerBoots(ThaumcraftApi.armorMatSpecial, 4, 3);
            GameRegistry.registerItem(bootsQuantumVoid, bootsQuantumVoid.getUnlocalizedName());
        }

        if (Loader.isModLoaded(EXPLORATIONS) && Config.explorationsActive) {
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
    }

    public static InfusionRecipe electricVoid;
    public static InfusionRecipe nanoVoid;
    public static InfusionRecipe quantumVoid;

    public static InfusionRecipe electricComet;
    public static InfusionRecipe nanoComet;
    public static InfusionRecipe quantumComet;

    public static InfusionRecipe electricMeteor;
    public static InfusionRecipe nanoMeteor;
    public static InfusionRecipe quantumMeteor;

    public static void setupCrafting() {
        if (ExplorationsHelper.isActive()) {
            electricComet = ThaumcraftApi.addInfusionCraftingRecipe(
                    "TB_Explorations_EMT_Compat",
                    new ItemStack(EMTHelper.bootsElectricComet, 1, 0),
                    0,
                    new AspectList().add(Aspect.EXCHANGE, 75).add(Aspect.MAGIC, 50).add(Aspect.CRAFT, 50)
                            .add(TB_Aspect.SPACE, 25).add(TB_Aspect.BOOTS, 25),
                    new ItemStack(Config.arcaniumLens),
                    new ItemStack[] { new ItemStack(ThaumicExploration.bootsComet),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 6),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(EMTItems.electricBootsTraveller, 1, OreDictionary.WILDCARD_VALUE),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 6),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()) });

            nanoComet = ThaumcraftApi.addInfusionCraftingRecipe(
                    "TB_Explorations_EMT_Compat",
                    new ItemStack(EMTHelper.bootsNanoComet, 1, 0),
                    0,
                    new AspectList().add(Aspect.EXCHANGE, 75).add(Aspect.MAGIC, 50).add(Aspect.CRAFT, 50)
                            .add(TB_Aspect.SPACE, 25).add(TB_Aspect.BOOTS, 25),
                    new ItemStack(Config.arcaniumLens),
                    new ItemStack[] { new ItemStack(ThaumicExploration.bootsComet),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 6),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(EMTItems.nanoBootsTraveller, 1, OreDictionary.WILDCARD_VALUE),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 6),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()) });

            quantumComet = ThaumcraftApi.addInfusionCraftingRecipe(
                    "TB_Explorations_EMT_Compat",
                    new ItemStack(EMTHelper.bootsQuantumComet, 1, 0),
                    0,
                    new AspectList().add(Aspect.EXCHANGE, 75).add(Aspect.MAGIC, 50).add(Aspect.CRAFT, 50)
                            .add(TB_Aspect.SPACE, 25).add(TB_Aspect.BOOTS, 25),
                    new ItemStack(Config.arcaniumLens),
                    new ItemStack[] { new ItemStack(ThaumicExploration.bootsComet),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 6),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(EMTItems.quantumBootsTraveller, 1, OreDictionary.WILDCARD_VALUE),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 6),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()) });

            electricMeteor = ThaumcraftApi.addInfusionCraftingRecipe(
                    "TB_Explorations_EMT_Compat",
                    new ItemStack(EMTHelper.bootsElectricMeteor, 1, 0),
                    0,
                    new AspectList().add(Aspect.EXCHANGE, 75).add(Aspect.MAGIC, 50).add(Aspect.CRAFT, 50)
                            .add(TB_Aspect.SPACE, 25).add(TB_Aspect.BOOTS, 25),
                    new ItemStack(Config.arcaniumLens),
                    new ItemStack[] { new ItemStack(ThaumicExploration.bootsMeteor),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 6),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(EMTItems.electricBootsTraveller, 1, OreDictionary.WILDCARD_VALUE),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 6),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()) });

            nanoMeteor = ThaumcraftApi.addInfusionCraftingRecipe(
                    "TB_Explorations_EMT_Compat",
                    new ItemStack(EMTHelper.bootsNanoMeteor, 1, 0),
                    0,
                    new AspectList().add(Aspect.EXCHANGE, 75).add(Aspect.MAGIC, 50).add(Aspect.CRAFT, 50)
                            .add(TB_Aspect.SPACE, 25).add(TB_Aspect.BOOTS, 25),
                    new ItemStack(Config.arcaniumLens),
                    new ItemStack[] { new ItemStack(ThaumicExploration.bootsMeteor),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 6),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(EMTItems.nanoBootsTraveller, 1, OreDictionary.WILDCARD_VALUE),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 6),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()) });

            quantumMeteor = ThaumcraftApi.addInfusionCraftingRecipe(
                    "TB_Explorations_EMT_Compat",
                    new ItemStack(EMTHelper.bootsQuantumMeteor, 1, 0),
                    0,
                    new AspectList().add(Aspect.EXCHANGE, 75).add(Aspect.MAGIC, 50).add(Aspect.CRAFT, 50)
                            .add(TB_Aspect.SPACE, 25).add(TB_Aspect.BOOTS, 25),
                    new ItemStack(Config.arcaniumLens),
                    new ItemStack[] { new ItemStack(ThaumicExploration.bootsMeteor),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 6),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(EMTItems.quantumBootsTraveller, 1, OreDictionary.WILDCARD_VALUE),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 6),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()) });
        }

        if (TaintedHelper.isActive()) {
            electricVoid = ThaumcraftApi.addInfusionCraftingRecipe(
                    "TB_EMT_Tainted_Compat",
                    new ItemStack(EMTHelper.bootsElectricVoid, 1, 0),
                    0,
                    new AspectList().add(Aspect.EXCHANGE, 75).add(Aspect.MAGIC, 50).add(Aspect.CRAFT, 50)
                            .add(TB_Aspect.SPACE, 25).add(TB_Aspect.BOOTS, 25),
                    new ItemStack(Config.arcaniumLens),
                    new ItemStack[] { new ItemStack(ItemRegistry.ItemVoidwalkerBoots),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 6),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(EMTItems.electricBootsTraveller, 1, OreDictionary.WILDCARD_VALUE),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 6),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()) });

            nanoVoid = ThaumcraftApi.addInfusionCraftingRecipe(
                    "TB_EMT_Tainted_Compat",
                    new ItemStack(EMTHelper.bootsNanoVoid, 1, 0),
                    0,
                    new AspectList().add(Aspect.EXCHANGE, 75).add(Aspect.MAGIC, 50).add(Aspect.CRAFT, 50)
                            .add(TB_Aspect.SPACE, 25).add(TB_Aspect.BOOTS, 25),
                    new ItemStack(Config.arcaniumLens),
                    new ItemStack[] { new ItemStack(ItemRegistry.ItemVoidwalkerBoots),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 6),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(EMTItems.nanoBootsTraveller, 1, OreDictionary.WILDCARD_VALUE),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 6),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()) });

            quantumVoid = ThaumcraftApi.addInfusionCraftingRecipe(
                    "TB_EMT_Tainted_Compat",
                    new ItemStack(EMTHelper.bootsQuantumVoid, 1, 0),
                    0,
                    new AspectList().add(Aspect.EXCHANGE, 75).add(Aspect.MAGIC, 50).add(Aspect.CRAFT, 50)
                            .add(TB_Aspect.SPACE, 25).add(TB_Aspect.BOOTS, 25),
                    new ItemStack(Config.arcaniumLens),
                    new ItemStack[] { new ItemStack(ItemRegistry.ItemVoidwalkerBoots),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 6),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(EMTItems.quantumBootsTraveller, 1, OreDictionary.WILDCARD_VALUE),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 6),
                            new ItemStack(
                                    ThaumcraftHelper.miscResource,
                                    1,
                                    ThaumcraftHelper.MiscResource.SALIS.ordinal()) });
        }
    }

    public static void setupResearch() {
        String category = "THAUMICBOOTS";
        ResearchItem emtCore;
        ResearchPage emtCore1, emtCore2;
        ResearchPage explorationsEMT1, explorationsEMT2, explorationsEMT3, explorationsEMT4, explorationsEMT5,
                explorationsEMT6, explorationsEMT7, emtTainted1, emtTainted2, emtTainted3, emtTainted4;
        ResearchItem explorationsEmtCompat, emtTaintedCompat;

        emtCore = new ResearchItem(
                "TB_EMT_Core",
                category,
                new AspectList().add(TB_Aspect.BOOTS, 25).add(Aspect.ENERGY, 25).add(Aspect.EXCHANGE, 15),
                -3,
                2,
                0,
                new ItemStack(EMTItems.electricBootsTraveller));
        emtCore1 = new ResearchPage("EMTCore.1");
        emtCore2 = new ResearchPage("EMTCore.2");
        emtCore.setPages(emtCore1, emtCore2);
        emtCore.setConcealed().setParents("Electric Boots of the Traveller");
        ResearchCategories.addResearch(emtCore);

        if (TaintedHelper.isActive()) {
            emtTaintedCompat = new ResearchItem(
                    "TB_EMT_Tainted_Compat",
                    category,
                    new AspectList().add(TB_Aspect.BOOTS, 25).add(Aspect.ELDRITCH, 25).add(Aspect.ENERGY, 25)
                            .add(Aspect.EXCHANGE, 15),
                    0,
                    3,
                    0,
                    new ItemStack(EMTHelper.bootsElectricVoid));
            emtTainted1 = new ResearchPage("EMTTainted.1");
            emtTainted2 = new ResearchPage(electricVoid);
            emtTainted3 = new ResearchPage(nanoVoid);
            emtTainted4 = new ResearchPage(quantumVoid);
            emtTaintedCompat.setPages(emtTainted1, emtTainted2, emtTainted3, emtTainted4);
            emtTaintedCompat.setConcealed().setParents("TB_Tainted_Core", "TB_EMT_Core", "TB_Core_Research");
            ResearchCategories.addResearch(emtTaintedCompat);
        }
        if (ExplorationsHelper.isActive()) {
            explorationsEmtCompat = new ResearchItem(
                    "TB_Explorations_EMT_Compat",
                    category,
                    new AspectList().add(TB_Aspect.BOOTS, 25).add(TB_Aspect.SPACE, 25).add(Aspect.ENERGY, 15)
                            .add(Aspect.EXCHANGE, 15),
                    -3,
                    -2,
                    0,
                    new ItemStack(EMTHelper.bootsElectricMeteor));

            explorationsEMT1 = new ResearchPage("ExplorationsEMT.1");
            explorationsEMT2 = new ResearchPage(electricComet);
            explorationsEMT3 = new ResearchPage(nanoComet);
            explorationsEMT4 = new ResearchPage(quantumComet);
            explorationsEMT5 = new ResearchPage(electricComet);
            explorationsEMT6 = new ResearchPage(nanoComet);
            explorationsEMT7 = new ResearchPage(quantumComet);
            explorationsEmtCompat.setPages(
                    explorationsEMT1,
                    explorationsEMT2,
                    explorationsEMT3,
                    explorationsEMT4,
                    explorationsEMT5,
                    explorationsEMT6,
                    explorationsEMT7);
            explorationsEmtCompat.setConcealed().setParents("TB_Core_Research", "TB_Explorations_Core", "TB_EMT_Core");
            ResearchCategories.addResearch(explorationsEmtCompat);
        }
    }

    public static void setupItemAspects() {
        ItemStack item;
        AspectList list;
        list = new AspectList(new ItemStack(EMTItems.electricBootsTraveller)).add(TB_Aspect.BOOTS, 12);
        item = new ItemStack(EMTItems.electricBootsTraveller);
        ThaumcraftApi.registerObjectTag(item, list);
    }
}
