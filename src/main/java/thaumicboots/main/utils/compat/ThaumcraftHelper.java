package thaumicboots.main.utils.compat;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import emt.init.EMTItems;
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

    public static final String Name = "Thaumcraft";

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
    }

    public static void getBlocks() {
        plant = BlockInterface.getBlock(Name, "blockCustomPlant");
        candle = BlockInterface.getBlock(Name, "blockCandle");
        crystal = BlockInterface.getBlock(Name, "blockCrystal");
        marker = BlockInterface.getBlock(Name, "blockMarker");
        jar = BlockInterface.getBlock(Name, "blockJar");
        log = BlockInterface.getBlock(Name, "blockMagicalLog");
        leaf = BlockInterface.getBlock(Name, "blockMagicalLeaves");
        warded = BlockInterface.getBlock(Name, "blockWarded");
        wooden = BlockInterface.getBlock(Name, "blockWoodenDevice");
        metal = BlockInterface.getBlock(Name, "blockMetalDevice");
        airy = BlockInterface.getBlock(Name, "blockAiry");
    }

    public static void getItems() {
        filledJar = ItemInterface.getItem(Name, "BlockJarFilledItem");
        miscResource = ItemInterface.getItem(Name, "ItemResource");
        shard = ItemInterface.getItem(Name, "ItemShard");
        golem = ItemInterface.getItem(Name, "ItemGolemPlacer");
        nuggetMetal = ItemInterface.getItem(Name, "ItemNugget");
        shard = ItemInterface.getItem(Name, "ItemShard");
        nuggetChicken = ItemInterface.getItem(Name, "ItemNuggetChicken");
        nuggetBeef = ItemInterface.getItem(Name, "ItemNuggetBeef");
        nuggetPork = ItemInterface.getItem(Name, "ItemNuggetPork");
        zombieBrain = ItemInterface.getItem(Name, "ItemZombieBrain");
    }

    // Crucible Recipes
    public static CrucibleRecipe thaumaturgicCombinator;

    // Infusion Recipes
    public static InfusionRecipe electricVoid;
    public static InfusionRecipe nanoVoid;
    public static InfusionRecipe quantumVoid;

    public static InfusionRecipe electricComet;
    public static InfusionRecipe nanoComet;
    public static InfusionRecipe quantumComet;

    public static InfusionRecipe voidMeteor;
    public static InfusionRecipe voidComet;

    public static InfusionRecipe cometMeteor;
    public static InfusionRecipe meteorComet;

    public static void setupCrafting() {
        thaumaturgicCombinator = ThaumcraftApi.addCrucibleRecipe(
                "TB_Core_Research",
                new ItemStack(Config.arcaniumLens),
                new ItemStack(miscResource, 1, MiscResource.THAUMIUM.ordinal()),
                new AspectList().add(TB_Aspect.BOOTS, 25).add(Aspect.EXCHANGE, 25).add(TB_Aspect.SPACE, 25));

        electricComet = ThaumcraftApi.addInfusionCraftingRecipe(
                "TB_Explorations_EMT_Compat",
                new ItemStack(Config.bootsElectricComet),
                0,
                new AspectList().add(Aspect.EXCHANGE, 75).add(Aspect.MAGIC, 50).add(Aspect.CRAFT, 50)
                        .add(TB_Aspect.SPACE, 25).add(TB_Aspect.BOOTS, 25),
                new ItemStack(Config.arcaniumLens),
                new ItemStack[] { new ItemStack(ThaumicExploration.bootsComet),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(shard, 1, ShardType.BALANCED.ordinal()),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(EMTItems.electricBootsTraveller),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(shard, 1, ShardType.BALANCED.ordinal()),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()) });

        nanoComet = ThaumcraftApi.addInfusionCraftingRecipe(
                "TB_Explorations_EMT_Compat",
                new ItemStack(Config.bootsNanoComet),
                0,
                new AspectList().add(Aspect.EXCHANGE, 75).add(Aspect.MAGIC, 50).add(Aspect.CRAFT, 50)
                        .add(TB_Aspect.SPACE, 25).add(TB_Aspect.BOOTS, 25),
                new ItemStack(Config.arcaniumLens),
                new ItemStack[] { new ItemStack(ThaumicExploration.bootsComet),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(shard, 1, ShardType.BALANCED.ordinal()),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(EMTItems.nanoBootsTraveller),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(shard, 1, ShardType.BALANCED.ordinal()),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()) });

        quantumComet = ThaumcraftApi.addInfusionCraftingRecipe(
                "TB_Explorations_EMT_Compat",
                new ItemStack(Config.bootsQuantumComet),
                0,
                new AspectList().add(Aspect.EXCHANGE, 75).add(Aspect.MAGIC, 50).add(Aspect.CRAFT, 50)
                        .add(TB_Aspect.SPACE, 25).add(TB_Aspect.BOOTS, 25),
                new ItemStack(Config.arcaniumLens),
                new ItemStack[] { new ItemStack(ThaumicExploration.bootsComet),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(shard, 1, ShardType.BALANCED.ordinal()),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(EMTItems.quantumBootsTraveller),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(shard, 1, ShardType.BALANCED.ordinal()),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()) });

        electricVoid = ThaumcraftApi.addInfusionCraftingRecipe(
                "TB_EMT_Tainted_Compat",
                new ItemStack(Config.bootsElectricVoid),
                0,
                new AspectList().add(Aspect.EXCHANGE, 75).add(Aspect.MAGIC, 50).add(Aspect.CRAFT, 50)
                        .add(TB_Aspect.SPACE, 25).add(TB_Aspect.BOOTS, 25),
                new ItemStack(Config.arcaniumLens),
                new ItemStack[] { new ItemStack(ItemRegistry.ItemVoidwalkerBoots),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(shard, 1, ShardType.BALANCED.ordinal()),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(EMTItems.electricBootsTraveller),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(shard, 1, ShardType.BALANCED.ordinal()),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()) });

        nanoVoid = ThaumcraftApi.addInfusionCraftingRecipe(
                "TB_EMT_Tainted_Compat",
                new ItemStack(Config.bootsNanoVoid),
                0,
                new AspectList().add(Aspect.EXCHANGE, 75).add(Aspect.MAGIC, 50).add(Aspect.CRAFT, 50)
                        .add(TB_Aspect.SPACE, 25).add(TB_Aspect.BOOTS, 25),
                new ItemStack(Config.arcaniumLens),
                new ItemStack[] { new ItemStack(ItemRegistry.ItemVoidwalkerBoots),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(shard, 1, ShardType.BALANCED.ordinal()),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(EMTItems.nanoBootsTraveller),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(shard, 1, ShardType.BALANCED.ordinal()),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()) });

        quantumVoid = ThaumcraftApi.addInfusionCraftingRecipe(
                "TB_EMT_Tainted_Compat",
                new ItemStack(Config.bootsQuantumVoid),
                0,
                new AspectList().add(Aspect.EXCHANGE, 75).add(Aspect.MAGIC, 50).add(Aspect.CRAFT, 50)
                        .add(TB_Aspect.SPACE, 25).add(TB_Aspect.BOOTS, 25),
                new ItemStack(Config.arcaniumLens),
                new ItemStack[] { new ItemStack(ItemRegistry.ItemVoidwalkerBoots),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(shard, 1, ShardType.BALANCED.ordinal()),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(EMTItems.quantumBootsTraveller),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(shard, 1, ShardType.BALANCED.ordinal()),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()) });

        voidMeteor = ThaumcraftApi.addInfusionCraftingRecipe(
                "TB_Explorations_Tainted_Compat",
                new ItemStack(Config.bootsMeteorVoid),
                0,
                new AspectList().add(Aspect.EXCHANGE, 75).add(Aspect.MAGIC, 50).add(Aspect.CRAFT, 50)
                        .add(TB_Aspect.SPACE, 25).add(TB_Aspect.BOOTS, 25),
                new ItemStack(Config.arcaniumLens),
                new ItemStack[] { new ItemStack(ItemRegistry.ItemVoidwalkerBoots),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(shard, 1, ShardType.BALANCED.ordinal()),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(ThaumicExploration.bootsMeteor),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(shard, 1, ShardType.BALANCED.ordinal()),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()) });

        voidComet = ThaumcraftApi.addInfusionCraftingRecipe(
                "TB_Explorations_Tainted_Compat",
                new ItemStack(Config.bootsCometVoid),
                0,
                new AspectList().add(Aspect.EXCHANGE, 75).add(Aspect.MAGIC, 50).add(Aspect.CRAFT, 50)
                        .add(TB_Aspect.SPACE, 25).add(TB_Aspect.BOOTS, 25),
                new ItemStack(Config.arcaniumLens),
                new ItemStack[] { new ItemStack(ItemRegistry.ItemVoidwalkerBoots),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(shard, 1, ShardType.BALANCED.ordinal()),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(ThaumicExploration.bootsComet),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(shard, 1, ShardType.BALANCED.ordinal()),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()) });

        cometMeteor = ThaumcraftApi.addInfusionCraftingRecipe(
                "TB_Explorations_Tainted_Compat",
                new ItemStack(Config.bootsCometMeteor),
                0,
                new AspectList().add(Aspect.EXCHANGE, 75).add(Aspect.MAGIC, 50).add(Aspect.CRAFT, 50)
                        .add(TB_Aspect.SPACE, 25).add(TB_Aspect.BOOTS, 25),
                new ItemStack(Config.arcaniumLens),
                new ItemStack[] { new ItemStack(ThaumicExploration.bootsComet),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(shard, 1, ShardType.BALANCED.ordinal()),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(ThaumicExploration.bootsMeteor),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(shard, 1, ShardType.BALANCED.ordinal()),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()) });

        meteorComet = ThaumcraftApi.addInfusionCraftingRecipe(
                "TB_Explorations_Tainted_Compat",
                new ItemStack(Config.bootsMeteoricComet),
                0,
                new AspectList().add(Aspect.EXCHANGE, 75).add(Aspect.MAGIC, 50).add(Aspect.CRAFT, 50)
                        .add(TB_Aspect.SPACE, 25).add(TB_Aspect.BOOTS, 25),
                new ItemStack(Config.arcaniumLens),
                new ItemStack[] { new ItemStack(ThaumicExploration.bootsMeteor),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(shard, 1, ShardType.BALANCED.ordinal()),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(ThaumicExploration.bootsComet),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()),
                        new ItemStack(shard, 1, ShardType.BALANCED.ordinal()),
                        new ItemStack(miscResource, 1, MiscResource.SALIS.ordinal()) });

    }

    public static void setupResearch() {
        String category = "THAUMICBOOTS";
        ResearchCategories.registerCategory(
                category,
                new ResourceLocation(VersionInfo.ModID, "textures/items/bootsCometMeteor.png"),
                new ResourceLocation(VersionInfo.ModID, "textures/gui/research_bg1_b.png"));
        ResearchItem coreResearch;
        ResearchItem explorationsCore, emtCore, taintedCore, uniqueCore;
        ResearchPage core1, core2, explorationsCore1, explorationsCore2, emtCore1, emtCore2, taintedCore1, taintedCore2,
                uniqueCore1, uniqueCore2;
        ResearchPage explorationsEMT1, explorationsEMT2, explorationsEMT3, explorationsEMT4, emtTainted1, emtTainted2,
                emtTainted3, emtTainted4, explorationsTainted1, explorationsTainted2, explorationsTainted3,
                explorationsCompat1, explorationsCompat2, explorationsCompat3;

        ResearchItem explorationsEmtCompat, emtTaintedCompat, explorationsTaintedCompat, explorationsCompat;
        coreResearch = new ResearchItem(
                "TB_Core_Research",
                category,
                new AspectList(),
                0,
                0,
                0,
                new ItemStack(Config.arcaniumLens)).setParents("THAUMIUM").setAutoUnlock();

        explorationsCore = new ResearchItem(
                "TB_Explorations_Core",
                category,
                new AspectList().add(Aspect.CRAFT, 1).add(Aspect.EXCHANGE, 1).add(TB_Aspect.SPACE, 1)
                        .add(Aspect.MAGIC, 1),
                0,
                -3,
                0,
                new ItemStack(ThaumicExploration.bootsMeteor)).setParents("METEORBOOTS", "COMETBOOTS");

        emtCore = new ResearchItem(
                "TB_EMT_Core",
                category,
                new AspectList(),
                -3,
                2,
                0,
                new ItemStack(EMTItems.electricBootsTraveller)).setParents("Electric Boots of the Traveller")
                        .setAutoUnlock();

        taintedCore = new ResearchItem(
                "TB_Tainted_Core",
                category,
                new AspectList(),
                3,
                2,
                0,
                new ItemStack(ItemRegistry.ItemVoidwalkerBoots)).setParents("VOIDWALKERBOOTS").setAutoUnlock();

        emtTaintedCompat = new ResearchItem(
                "TB_EMT_Tainted_Compat",
                category,
                new AspectList(),
                0,
                3,
                0,
                new ItemStack(Config.bootsElectricVoid))
                        .setParents("TB_Tainted_Core", "TB_EMT_Core", "TB_Core_Research");

        explorationsTaintedCompat = new ResearchItem(
                "TB_Explorations_Tainted_Compat",
                category,
                new AspectList(),
                3,
                -2,
                0,
                new ItemStack(Config.bootsMeteorVoid))
                        .setParents("TB_Explorations_Core", "TB_Tainted_Core", "TB_Core_Research");

        explorationsEmtCompat = new ResearchItem(
                "TB_Explorations_EMT_Compat",
                category,
                new AspectList(),
                -3,
                -2,
                0,
                new ItemStack(Config.bootsElectricMeteor))
                        .setParents("TB_Core_Research", "TB_Explorations_Core", "TB_EMT_Core");

        explorationsCompat = new ResearchItem(
                "TB_Explorations_Compat",
                category,
                new AspectList(),
                0,
                -2,
                0,
                new ItemStack(Config.bootsCometMeteor)).setParents("TB_Core_Research", "TB_Explorations_Core");

        core1 = new ResearchPage("Core.1");
        core2 = new ResearchPage(thaumaturgicCombinator);
        coreResearch.setPages(core1, core2);

        explorationsCore1 = new ResearchPage("ExplorationsCore.1");
        explorationsCore.setPages(explorationsCore1);

        taintedCore1 = new ResearchPage("TaintedCore.1");
        taintedCore.setPages(taintedCore1);

        emtCore1 = new ResearchPage("EMTCore.1");
        emtCore2 = new ResearchPage("EMTCore.2");
        emtCore.setPages(emtCore1, emtCore2);

        explorationsEMT1 = new ResearchPage("ExplorationsEMT.1");
        explorationsEMT2 = new ResearchPage(electricComet);
        explorationsEMT3 = new ResearchPage(nanoComet);
        explorationsEMT4 = new ResearchPage(quantumComet);
        explorationsEmtCompat.setPages(explorationsEMT1, explorationsEMT2, explorationsEMT3, explorationsEMT4);

        explorationsTainted1 = new ResearchPage("ExplorationsTainted.1");
        explorationsTainted2 = new ResearchPage(voidMeteor);
        explorationsTainted3 = new ResearchPage(voidComet);
        explorationsTaintedCompat.setPages(explorationsTainted1, explorationsTainted2, explorationsTainted3);

        emtTainted1 = new ResearchPage("EMTTainted.1");
        emtTainted2 = new ResearchPage(electricVoid);
        emtTainted3 = new ResearchPage(nanoVoid);
        emtTainted4 = new ResearchPage(quantumVoid);
        emtTaintedCompat.setPages(emtTainted1, emtTainted2, emtTainted3, emtTainted4);

        explorationsCompat1 = new ResearchPage("ExplorationsCompat.1");
        explorationsCompat2 = new ResearchPage(cometMeteor);
        explorationsCompat3 = new ResearchPage(meteorComet);
        explorationsCompat.setPages(explorationsCompat1, explorationsCompat2, explorationsCompat3);

        /*
         * uniqueCore = new ResearchItem( "TB_Unique_Core", category, new AspectList(), 0, 3, 0, new
         * ItemStack(Config.bootsCometMeteor)).setAutoUnlock();
         */

        ResearchCategories.addResearch(coreResearch);
        ResearchCategories.addResearch(explorationsCore);
        ResearchCategories.addResearch(emtCore);
        ResearchCategories.addResearch(taintedCore);
        // ResearchCategories.addResearch(uniqueCore);
        ResearchCategories.addResearch(emtTaintedCompat);
        ResearchCategories.addResearch(explorationsTaintedCompat);
        ResearchCategories.addResearch(explorationsEmtCompat);
        ResearchCategories.addResearch(explorationsCompat);
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

        list = new AspectList(new ItemStack(ThaumicExploration.bootsComet)).add(TB_Aspect.BOOTS, 12)
                .add(TB_Aspect.SPACE, 8);
        item = new ItemStack(ThaumicExploration.bootsComet);
        ThaumcraftApi.registerObjectTag(item, list);

        list = new AspectList(new ItemStack(ThaumicExploration.bootsMeteor)).add(TB_Aspect.BOOTS, 12)
                .add(TB_Aspect.SPACE, 8);
        item = new ItemStack(ThaumicExploration.bootsMeteor);
        ThaumcraftApi.registerObjectTag(item, list);

        list = new AspectList(new ItemStack(EMTItems.electricBootsTraveller)).add(TB_Aspect.BOOTS, 12);
        item = new ItemStack(EMTItems.electricBootsTraveller);
        ThaumcraftApi.registerObjectTag(item, list);

        list = new AspectList(new ItemStack(ItemRegistry.ItemVoidwalkerBoots)).add(TB_Aspect.BOOTS, 12);
        item = new ItemStack(ItemRegistry.ItemVoidwalkerBoots);
        ThaumcraftApi.registerObjectTag(item, list);
    }
}
