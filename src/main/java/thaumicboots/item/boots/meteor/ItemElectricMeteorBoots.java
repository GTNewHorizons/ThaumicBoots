package thaumicboots.item.boots.meteor;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import emt.util.EMTTextHelper;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.items.armor.Hover;
import thaumicboots.api.ItemElectricBoots;
import thaumicboots.main.utils.TabThaumicBoots;

public class ItemElectricMeteorBoots extends ItemElectricBoots {

    public ItemElectricMeteorBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
    }

    protected void setBootsData() {
        maxCharge = 100_000;
        energyPerDamage = 1_000;
        visDiscount = 2;
        damageAbsorptionRatio = 1.25D;
        transferLimit = 100;
        jumpBonus = 0.275D * 1.9;
        baseBonus = 0.055F;
        tier = 2;
        negateFall = true;
        iconResPath = "thaumicboots:electricMeteor_16x";
        armorResPath = "thaumicboots:model/electricbootsMeteor.png";
        unlocalisedName = "ItemElectricMeteor";
    }





}
