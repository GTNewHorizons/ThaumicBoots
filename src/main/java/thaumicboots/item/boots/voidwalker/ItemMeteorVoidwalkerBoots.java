package thaumicboots.item.boots.voidwalker;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.MinecraftForge;

import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import taintedmagic.common.registry.ItemRegistry;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.IWarpingGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXWispEG;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.items.armor.Hover;
import thaumicboots.api.ItemVoidBoots;
import thaumicboots.main.utils.TabThaumicBoots;

public class ItemMeteorVoidwalkerBoots extends ItemVoidBoots
        implements IVisDiscountGear, IWarpingGear, IRunicArmor, IRepairable, ISpecialArmor {

    public ItemMeteorVoidwalkerBoots(final ArmorMaterial material, final int j, final int k) {
        super(material, j, k);
    }

    protected void setBootsData() {
        visDiscount = 5;
        damageAbsorptionRatio = 1.5D;
        jumpBonus = 0.275D * 3.2;
        tier = 3;
        iconResPath = "thaumicboots:purpleHaze_16x";
        armorResPath = "thaumicboots:model/VoidwalkerBootsMeteor_-_Purple.png";
        unlocalisedName = "ItemVoidMeteor";
    }
}
