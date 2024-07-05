package thaumicboots.main.utils.compat;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

import flaxbeard.thaumicexploration.ThaumicExploration;
import taintedmagic.common.registry.ItemRegistry;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;
import thaumicboots.api.TB_Aspect;
import thaumicboots.main.Config;
import thaumicboots.main.utils.BlockInterface;
import thaumicboots.main.utils.CalendarHelper;
import thaumicboots.main.utils.ItemInterface;
import thaumicboots.main.utils.LocalizationManager;
import thaumicboots.main.utils.VersionInfo;

public class ThaumcraftHelper implements IModHelper {

    @SuppressWarnings("unused")
    public enum MiscResource {
        ALUMENTUM,
        NITOR,
        THAUMIUM,
        QUICKSILVER,
        MAGIC_TALLOW,
        BRAIN_DEPRECATED,
        AMBER,
        ENCHANTED_FABRIC,
        VIS_FILTER,
        KNOWLEDGE_FRAGMENT,
        MIRRORED_GLASS,
        TAINTED_GOO,
        TAINTED_TENDRIL,
        JAR_LABEL,
        SALIS,
        CHARM,
        VOID_INGOT,
        VOID_SEED,
        COIN,;
    }

    @SuppressWarnings("unused")
    public enum NuggetType {
        IRON,
        COPPER,
        TIN,
        SILVER,
        LEAD,
        QUICKSILVER,
        THAUMIUM,
        VOID_METAL,
        _8,
        _9,
        _10,
        _11,
        _12,
        _13,
        _14,
        _15,
        NATIVE_IRON,
        NATIVE_COPPER,
        NATIVE_TIN,
        NATIVE_SILVER,
        NATIVE_LEAD,
        NATIVE_CINNABAR,
        _22,
        _23,
        _24,
        _25,
        _26,
        _27,
        _28,
        _29,
        _30,
        NATIVE_GOLD,;
    }

    @SuppressWarnings("unused")
    public enum ShardType {
        AIR,
        FIRE,
        WATER,
        EARTH,
        ORDER,
        CHAOS,
        BALANCED,;
    }

    @SuppressWarnings("unused")
    public enum MetalDeviceType {
        CRUCIBLE,
        ALEMBIC,
        VIS_CHARGE_RELAY,
        ADVANCED_ALCHEMICAL_CONSTRUCT,
        _4,
        ITEM_GRATE,
        _6,
        ARCANE_LAMP,
        LAMP_OF_GROWTH,
        ALCHEMICAL_CONSTRUCT,
        THAUMATORIUM,
        _11,
        MNEMONIC_MATRIX,
        LAMP_OF_FERTILITY,
        VIS_RELAY,;
    }

    @SuppressWarnings("unused")
    public enum WoodenDeviceType {
        BELLOWS,
        EAR,
        PRESSURE_PLATE,
        PRESSURE_PLATE_B,
        BORE_BASE,
        BORE,
        PLANKS_GREATWOOD,
        PLANKS_SILVERWOOD,
        BANNER,;
    }

    @SuppressWarnings("unused")
    public enum AiryBlockType {
        NODE,
        NITOR,
        _2,
        _3,
        WARDING_STONE_FENCE,
        ENERGIZED_NODE,;
    }

    @SuppressWarnings("unused")
    public enum Entity {

        BRAINY_ZOMBIE("entBrainyZombie", "EntityBrainyZombie"),
        GIANT_BRAINY_ZOMBIE("entGiantBrainyZombie", "EntityGiantBrainyZombie"),
        WISP("entWisp", "EntityWisp"),
        FIREBAT("entFirebat", "EntityFireBat"),;

        private static final String packageName = "thaumcraft.common.entities.monster.";

        public final String entityID;
        private final String className;

        private Entity(String id, String clazz) {
            this.entityID = id;
            this.className = clazz;
        }

        public String getClassName() {
            return packageName + this.className;
        }
    }

    @SuppressWarnings("unused")
    public enum BlockPlant {
        GREATWOOD_SAPLING,
        SILVERWOOD_SAPLING,
        SHIMMERLEAF,
        CINDERPEARL,
        PURIFYING_PLANT,
        VISHROOM,;
    }

    @SuppressWarnings("unused")
    public enum TreeType {
        GREATWOOD,
        SILVERWOOD,;
    }

    public static Block plant;
    public static Block candle;
    public static Block crystal;
    public static Block marker;
    public static Block jar;
    public static Block log;
    public static Block leaf;
    public static Block warded;
    public static Block wooden;
    public static Block metal;
    public static Block airy;

    public static Item filledJar;
    public static Item miscResource;
    public static Item shard;
    public static Item golem;
    public static Item nuggetMetal;
    public static Item nuggetChicken;
    public static Item nuggetBeef;
    public static Item nuggetPork;
    public static Item zombieBrain;

    public static final String THAUMCRAFT = "Thaumcraft";

    public void preInit() {
        TB_Aspect.addTB_Aspects();
    }

    public void init() {
        getBlocks();
        getItems();
    }

    public void postInit() {
        setupItemAspects();
        setupCrafting();
        setupResearch();
        if (EMTHelper.isActive()) {
            EMTHelper.setupItemAspects();
            EMTHelper.setupCrafting();
            EMTHelper.setupResearch();
        }
    }

    public static void getBlocks() {
        plant = BlockInterface.getBlock(THAUMCRAFT, "blockCustomPlant");
        candle = BlockInterface.getBlock(THAUMCRAFT, "blockCandle");
        crystal = BlockInterface.getBlock(THAUMCRAFT, "blockCrystal");
        marker = BlockInterface.getBlock(THAUMCRAFT, "blockMarker");
        jar = BlockInterface.getBlock(THAUMCRAFT, "blockJar");
        log = BlockInterface.getBlock(THAUMCRAFT, "blockMagicalLog");
        leaf = BlockInterface.getBlock(THAUMCRAFT, "blockMagicalLeaves");
        warded = BlockInterface.getBlock(THAUMCRAFT, "blockWarded");
        wooden = BlockInterface.getBlock(THAUMCRAFT, "blockWoodenDevice");
        metal = BlockInterface.getBlock(THAUMCRAFT, "blockMetalDevice");
        airy = BlockInterface.getBlock(THAUMCRAFT, "blockAiry");
    }

    public static void getItems() {
        filledJar = ItemInterface.getItem(THAUMCRAFT, "BlockJarFilledItem");
        miscResource = ItemInterface.getItem(THAUMCRAFT, "ItemResource");
        shard = ItemInterface.getItem(THAUMCRAFT, "ItemShard");
        golem = ItemInterface.getItem(THAUMCRAFT, "ItemGolemPlacer");
        nuggetMetal = ItemInterface.getItem(THAUMCRAFT, "ItemNugget");
        shard = ItemInterface.getItem(THAUMCRAFT, "ItemShard");
        nuggetChicken = ItemInterface.getItem(THAUMCRAFT, "ItemNuggetChicken");
        nuggetBeef = ItemInterface.getItem(THAUMCRAFT, "ItemNuggetBeef");
        nuggetPork = ItemInterface.getItem(THAUMCRAFT, "ItemNuggetPork");
        zombieBrain = ItemInterface.getItem(THAUMCRAFT, "ItemZombieBrain");
    }

    // Crucible Recipes
    public static CrucibleRecipe thaumaturgicCombinator;

    // Infusion Recipes
    public static InfusionRecipe voidMeteor;
    public static InfusionRecipe voidComet;

    public static InfusionRecipe cometMeteor;
    public static InfusionRecipe meteorComet;

    public static InfusionRecipe seasonalBoot;
    public static CrucibleRecipe seasonalToChristmas;
    public static CrucibleRecipe slowBoot;

    public static void setupCrafting() {
        thaumaturgicCombinator = ThaumcraftApi.addCrucibleRecipe(
                "TB_Core_Research",
                new ItemStack(Config.arcaniumLens),
                new ItemStack(miscResource, 1, MiscResource.THAUMIUM.ordinal()),
                new AspectList().add(TB_Aspect.BOOTS, 25).add(Aspect.EXCHANGE, 25).add(TB_Aspect.SPACE, 25));

        seasonalBoot = ThaumcraftApi.addInfusionCraftingRecipe(
                "TB_Seasonal_Boots",
                new ItemStack(Config.seasonBoots),
                10,
                new AspectList().add(TB_Aspect.BOOTS, 75).add(Aspect.LIGHT, 50).add(Aspect.MAGIC, 50)
                        .add(Aspect.MOTION, 50).add(Aspect.AURA, 25),
                new ItemStack(ConfigItems.itemBootsTraveller),
                new ItemStack[] { new ItemStack(Items.fireworks, 1, OreDictionary.WILDCARD_VALUE),
                        new ItemStack(Items.dye, 1, 10), new ItemStack(Items.book),
                        new ItemStack(Items.fireworks, 1, OreDictionary.WILDCARD_VALUE),
                        new ItemStack(Items.iron_sword), new ItemStack(Blocks.lit_pumpkin),
                        new ItemStack(ConfigItems.itemFocusFrost), new ItemStack(Blocks.sapling, 1, 1) });

        slowBoot = ThaumcraftApi.addCrucibleRecipe(
                "TB_Unique_Boots",
                new ItemStack(Config.slowBoots),
                new ItemStack(ConfigItems.itemBootsTraveller),
                new AspectList().add(Aspect.TRAP, 25).add(TB_Aspect.BOOTS, 25).add(Aspect.EXCHANGE, 10));

        if (CalendarHelper.isChristmas()) {
            seasonalToChristmas = ThaumcraftApi.addCrucibleRecipe(
                    "TB_Seasonal_Boots",
                    new ItemStack(Config.christmasBoots),
                    new ItemStack(Config.seasonBoots),
                    new AspectList().add(Aspect.TRAP, 50).add(Aspect.EXCHANGE, 25).add(Aspect.COLD, 25));
        }

        if (!EMTHelper.isActive() && !ExplorationsHelper.isActive() && !TaintedHelper.isActive()) {
            return;
        }

        if (ExplorationsHelper.isActive() && TaintedHelper.isActive()) {
            voidMeteor = ThaumcraftApi.addInfusionCraftingRecipe(
                    "TB_Explorations_Tainted_Compat",
                    new ItemStack(ExplorationsHelper.bootsMeteorVoid),
                    0,
                    new AspectList().add(Aspect.EXCHANGE, 75).add(Aspect.MAGIC, 50).add(Aspect.CRAFT, 50)
                            .add(TB_Aspect.SPACE, 25).add(TB_Aspect.BOOTS, 25),
                    new ItemStack(Config.arcaniumLens),
                    new ItemStack[] { new ItemStack(ItemRegistry.ItemVoidwalkerBoots),
                            new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 6),
                            new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                            new ItemStack(ThaumicExploration.bootsMeteor),
                            new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 6),
                            new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()) });

            voidComet = ThaumcraftApi.addInfusionCraftingRecipe(
                    "TB_Explorations_Tainted_Compat",
                    new ItemStack(ExplorationsHelper.bootsCometVoid),
                    0,
                    new AspectList().add(Aspect.EXCHANGE, 75).add(Aspect.MAGIC, 50).add(Aspect.CRAFT, 50)
                            .add(TB_Aspect.SPACE, 25).add(TB_Aspect.BOOTS, 25),
                    new ItemStack(Config.arcaniumLens),
                    new ItemStack[] { new ItemStack(ItemRegistry.ItemVoidwalkerBoots),
                            new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 6),
                            new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                            new ItemStack(ThaumicExploration.bootsComet),
                            new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 6),
                            new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()) });
        }

        if (ExplorationsHelper.isActive()) {
            cometMeteor = ThaumcraftApi.addInfusionCraftingRecipe(
                    "TB_Explorations_Compat",
                    new ItemStack(ExplorationsHelper.bootsCometMeteor),
                    0,
                    new AspectList().add(Aspect.EXCHANGE, 75).add(Aspect.MAGIC, 50).add(Aspect.CRAFT, 50)
                            .add(TB_Aspect.SPACE, 25).add(TB_Aspect.BOOTS, 25),
                    new ItemStack(Config.arcaniumLens),
                    new ItemStack[] { new ItemStack(ThaumicExploration.bootsComet),
                            new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 2),
                            new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                            new ItemStack(ThaumicExploration.bootsMeteor),
                            new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 6),
                            new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()) });

            meteorComet = ThaumcraftApi.addInfusionCraftingRecipe(
                    "TB_Explorations_Compat",
                    new ItemStack(ExplorationsHelper.bootsMeteoricComet),
                    0,
                    new AspectList().add(Aspect.EXCHANGE, 75).add(Aspect.MAGIC, 50).add(Aspect.CRAFT, 50)
                            .add(TB_Aspect.SPACE, 25).add(TB_Aspect.BOOTS, 25),
                    new ItemStack(Config.arcaniumLens),
                    new ItemStack[] { new ItemStack(ThaumicExploration.bootsMeteor),
                            new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 6),
                            new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                            new ItemStack(ThaumicExploration.bootsComet),
                            new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                            new ItemStack(ConfigItems.itemShard, 1, 1),
                            new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()) });
        }

    }

    public static void setupResearch() {
        String category = "THAUMICBOOTS";
        ResearchCategories.registerCategory(
                category,
                new ResourceLocation(VersionInfo.ModID, "textures/items/bootsCometMeteor.png"),
                new ResourceLocation(VersionInfo.ModID, "textures/gui/research_bg1_b.png"));

        ResearchItem coreResearch;
        ResearchItem explorationsCore, taintedCore, seasonalCore, seasonalStabilized, uniqueCore;
        ResearchPage core1, core2, explorationsCore1, explorationsCore2, taintedCore1, taintedCore2, seasonalCore1,
                seasonalCore2, seasonalStabilized1, seasonalStabilized2, uniqueCore1, uniqueCore2;
        ResearchPage explorationsTainted1, explorationsTainted2, explorationsTainted3, explorationsCompat1,
                explorationsCompat2, explorationsCompat3;

        ResearchItem explorationsTaintedCompat, explorationsCompat;
        coreResearch = new ResearchItem(
                "TB_Core_Research",
                category,
                new AspectList().add(TB_Aspect.BOOTS, 10).add(Aspect.EXCHANGE, 10),
                0,
                0,
                0,
                new ItemStack(Config.arcaniumLens));

        core1 = new ResearchPage("Core.1");
        core2 = new ResearchPage(thaumaturgicCombinator);
        coreResearch.setPages(core1, core2);
        coreResearch.setParents("THAUMIUM");
        ResearchCategories.addResearch(coreResearch);

        seasonalCore = new ResearchItem(
                "TB_Seasonal_Boots",
                category,
                new AspectList().add(TB_Aspect.BOOTS, 25).add(Aspect.EXCHANGE, 25).add(Aspect.COLD, 25)
                        .add(Aspect.MAGIC, 25).add(Aspect.ENERGY, 25),
                2,
                0,
                0,
                new ItemStack(Config.seasonBoots));
        seasonalCore1 = new ResearchPage("SeasonalCore.1");
        seasonalCore2 = new ResearchPage(seasonalBoot);
        seasonalCore.setPages(seasonalCore1, seasonalCore2);
        seasonalCore.setParents("TB_Core_Research");
        ResearchCategories.addResearch(seasonalCore);

        seasonalStabilized = new ResearchItem(
                "TB_Seasonal_Stabilized",
                category,
                new AspectList().add(Aspect.TRAP, 50).add(Aspect.CRYSTAL, 25).add(Aspect.EXCHANGE, 25)
                        .add(Aspect.COLD, 25).add(Aspect.LIGHT, 25),
                4,
                0,
                0,
                new ItemStack(Config.christmasBoots));
        seasonalStabilized1 = new ResearchPage("SeasonalStabilized.1");
        if (CalendarHelper.isChristmas()) {
            seasonalStabilized2 = new ResearchPage(seasonalToChristmas);
        } else {
            seasonalStabilized2 = new ResearchPage("SeasonalStabilized.2");
        }
        seasonalStabilized.setPages(seasonalStabilized1, seasonalStabilized2);
        seasonalStabilized.setParents("TB_Seasonal_Boots");
        ResearchCategories.addResearch(seasonalStabilized);

        uniqueCore = new ResearchItem(
                "TB_Unique_Boots",
                category,
                new AspectList().add(TB_Aspect.BOOTS, 25).add(Aspect.EXCHANGE, 25).add(Aspect.TOOL, 25)
                        .add(Aspect.MAGIC, 25).add(Aspect.ENERGY, 25),
                -2,
                0,
                0,
                new ItemStack(Config.slowBoots));
        uniqueCore1 = new ResearchPage("UniqueCore.1");
        uniqueCore2 = new ResearchPage(slowBoot);
        uniqueCore.setPages(uniqueCore1, uniqueCore2);
        uniqueCore.setParents("TB_Core_Research");
        ResearchCategories.addResearch(uniqueCore);

        if (!EMTHelper.isActive() && !ExplorationsHelper.isActive() && !TaintedHelper.isActive()) {
            return;
        }

        if (ExplorationsHelper.isActive()) {
            explorationsCore = new ResearchItem(
                    "TB_Explorations_Core",
                    category,
                    new AspectList().add(TB_Aspect.BOOTS, 25).add(TB_Aspect.SPACE, 25).add(Aspect.EXCHANGE, 15),
                    0,
                    -3,
                    0,
                    new ItemStack(ThaumicExploration.bootsMeteor));
            explorationsCore1 = new ResearchPage("ExplorationsCore.1");
            explorationsCore.setPages(explorationsCore1);
            explorationsCore.setConcealed().setParents("METEORBOOTS", "COMETBOOTS");
            ResearchCategories.addResearch(explorationsCore);

            explorationsCompat = new ResearchItem(
                    "TB_Explorations_Compat",
                    category,
                    new AspectList().add(TB_Aspect.BOOTS, 25).add(TB_Aspect.SPACE, 25).add(Aspect.EXCHANGE, 15),
                    0,
                    -2,
                    0,
                    new ItemStack(ExplorationsHelper.bootsCometMeteor));
            explorationsCompat1 = new ResearchPage("ExplorationsCompat.1");
            explorationsCompat2 = new ResearchPage(cometMeteor);
            explorationsCompat3 = new ResearchPage(meteorComet);
            explorationsCompat.setPages(explorationsCompat1, explorationsCompat2, explorationsCompat3);
            explorationsCompat.setConcealed().setParents("TB_Core_Research", "TB_Explorations_Core");
            ResearchCategories.addResearch(explorationsCompat);
        }

        if (TaintedHelper.isActive()) {
            taintedCore = new ResearchItem(
                    "TB_Tainted_Core",
                    category,
                    new AspectList().add(TB_Aspect.BOOTS, 25).add(Aspect.ELDRITCH, 25).add(Aspect.EXCHANGE, 15),
                    3,
                    2,
                    0,
                    new ItemStack(ItemRegistry.ItemVoidwalkerBoots));
            taintedCore1 = new ResearchPage("TaintedCore.1");
            taintedCore.setPages(taintedCore1);
            taintedCore.setConcealed().setParents("VOIDWALKERBOOTS");
            ResearchCategories.addResearch(taintedCore);
        }

        if (ExplorationsHelper.isActive() && TaintedHelper.isActive()) {
            explorationsTaintedCompat = new ResearchItem(
                    "TB_Explorations_Tainted_Compat",
                    category,
                    new AspectList().add(TB_Aspect.BOOTS, 25).add(TB_Aspect.SPACE, 25).add(Aspect.ELDRITCH, 25)
                            .add(Aspect.EXCHANGE, 15),
                    3,
                    -2,
                    0,
                    new ItemStack(ExplorationsHelper.bootsMeteorVoid));
            explorationsTainted1 = new ResearchPage("ExplorationsTainted.1");
            explorationsTainted2 = new ResearchPage(voidMeteor);
            explorationsTainted3 = new ResearchPage(voidComet);
            explorationsTaintedCompat.setPages(explorationsTainted1, explorationsTainted2, explorationsTainted3);
            explorationsTaintedCompat.setConcealed()
                    .setParents("TB_Explorations_Core", "TB_Tainted_Core", "TB_Core_Research");
            ResearchCategories.addResearch(explorationsTaintedCompat);
        }

    }

    public static ResearchPage getResearchPage(String ident) {
        return new ResearchPage(LocalizationManager.getLocalizedString("tc.research_page." + ident));
    }

    public static void setupItemAspects() {
        ItemStack item;
        AspectList list;

        list = new AspectList(new ItemStack(Items.leather_boots)).add(TB_Aspect.BOOTS, 2);
        item = new ItemStack(Items.leather_boots);
        ThaumcraftApi.registerObjectTag(item, list);

        list = new AspectList(new ItemStack(Items.chainmail_boots)).add(TB_Aspect.BOOTS, 4);
        item = new ItemStack(Items.chainmail_boots);
        ThaumcraftApi.registerObjectTag(item, list);

        list = new AspectList(new ItemStack(Items.iron_boots)).add(TB_Aspect.BOOTS, 4);
        item = new ItemStack(Items.iron_boots);
        ThaumcraftApi.registerObjectTag(item, list);

        list = new AspectList(new ItemStack(Items.golden_boots)).add(TB_Aspect.BOOTS, 8);
        item = new ItemStack(Items.golden_boots);
        ThaumcraftApi.registerObjectTag(item, list);

        list = new AspectList(new ItemStack(Items.diamond_boots)).add(TB_Aspect.BOOTS, 16);
        item = new ItemStack(Items.diamond_boots);
        ThaumcraftApi.registerObjectTag(item, list);

        list = new AspectList(new ItemStack(ConfigItems.itemBootsCultist)).add(TB_Aspect.BOOTS, 6);
        item = new ItemStack(ConfigItems.itemBootsCultist);
        ThaumcraftApi.registerObjectTag(item, list);

        list = new AspectList(new ItemStack(ConfigItems.itemBootsTraveller)).add(TB_Aspect.BOOTS, 12);
        item = new ItemStack(ConfigItems.itemBootsTraveller);
        ThaumcraftApi.registerObjectTag(item, list);

        list = new AspectList(new ItemStack(Items.ender_pearl)).add(TB_Aspect.SPACE, 4);
        item = new ItemStack(Items.ender_pearl);
        ThaumcraftApi.registerObjectTag(item, list);

        list = new AspectList(new ItemStack(Items.ender_eye)).add(TB_Aspect.SPACE, 8);
        item = new ItemStack(Items.ender_eye);
        ThaumcraftApi.registerObjectTag(item, list);

        list = new AspectList(new ItemStack(Items.blaze_powder)).add(TB_Aspect.SPACE, 1);
        item = new ItemStack(Items.blaze_powder);
        ThaumcraftApi.registerObjectTag(item, list);

        list = new AspectList(new ItemStack(Items.blaze_rod)).add(TB_Aspect.SPACE, 4);
        item = new ItemStack(Items.blaze_rod);
        ThaumcraftApi.registerObjectTag(item, list);

        if (ExplorationsHelper.isActive()) {
            list = new AspectList(new ItemStack(ThaumicExploration.bootsComet)).add(TB_Aspect.BOOTS, 12)
                    .add(TB_Aspect.SPACE, 8);
            item = new ItemStack(ThaumicExploration.bootsComet);
            ThaumcraftApi.registerObjectTag(item, list);

            list = new AspectList(new ItemStack(ThaumicExploration.bootsMeteor)).add(TB_Aspect.BOOTS, 12)
                    .add(TB_Aspect.SPACE, 8);
            item = new ItemStack(ThaumicExploration.bootsMeteor);
            ThaumcraftApi.registerObjectTag(item, list);
        }

        if (TaintedHelper.isActive()) {
            list = new AspectList(new ItemStack(ItemRegistry.ItemVoidwalkerBoots)).add(TB_Aspect.BOOTS, 12);
            item = new ItemStack(ItemRegistry.ItemVoidwalkerBoots);
            ThaumcraftApi.registerObjectTag(item, list);
        }
    }
}
