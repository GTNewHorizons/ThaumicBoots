package thaumicboots.api;

import emt.util.EMTTextHelper;
import ic2.api.item.ElectricItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.StatCollector;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;

import java.util.List;

public class ItemBoots extends ItemArmor implements ITBootJumpable, IVisDiscountGear, IRunicArmor, IRepairable {

    public IIcon icon;

    public float baseBonus;
    public int visDiscount;
    public int runicCharge;
    public int tier;
    public double damageAbsorptionRatio;
    public double baseAbsorptionRatio;
    public String iconResPath;
    public String armorResPath;
    public String unlocalisedName;
    public EnumRarity rarity;

    public double jumpBonus;

    public ItemBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
        setBootsData();
    }

    protected void setBootsData() {
        runicCharge = 0;
        visDiscount = 0;
        baseAbsorptionRatio = 0.15D;
        damageAbsorptionRatio = 0.0D;
        baseBonus = 0.165F;
        tier = 0;
        iconResPath = "thaumicboots:electricVoid_16x";
        armorResPath = "thaumicboots:model/electricbootsVoidwalker.png";
        unlocalisedName = "ItemElectricVoid";
        rarity = EnumRarity.rare;
        jumpBonus = 0.0D;
    }

    public double getJumpModifier() {
        return jumpBonus;
    }

    public void toggle() {

    }

    public boolean getToggle() {
        return false;
    }

    // TODO: the part not from interfaces

    @SideOnly(Side.CLIENT) // this method is important, it's what initializes the texture
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }
    @SideOnly(Side.CLIENT)

    public void registerIcons(IIconRegister ir) {
        this.icon = ir.registerIcon(iconResPath);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        if (visDiscount > 0) {
            list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") + ": " + visDiscount + "%");
        }
    }

    @Override
    public String getArmorTexture(final ItemStack stack, final Entity entity, final int slot, final String type) {
        return armorResPath;
    }

    @Override
    public EnumRarity getRarity(final ItemStack stack) {
        return rarity = EnumRarity.rare;
    }

    public int getRunicCharge(ItemStack arg0) {
        return runicCharge;
    }

    public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
        return visDiscount;
    }

    public double getDamageAbsorptionRatio() {
        return damageAbsorptionRatio;
    }

    protected double getBaseAbsorptionRatio() {
        return baseAbsorptionRatio;
    }

    public int getTier(ItemStack itemStack) {
        return tier;
    }

    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.isItemEqual(new ItemStack(Items.leather))
                || super.getIsRepairable(par1ItemStack, par2ItemStack);
    }


}
