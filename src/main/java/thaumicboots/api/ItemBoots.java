package thaumicboots.api;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;

public class ItemBoots extends ItemArmor implements ITBootJumpable, IVisDiscountGear, IRunicArmor, IRepairable {

    public IIcon icon;

    public int maxCharge;
    public int energyPerDamage;
    public double transferLimit;
    public boolean provideEnergy;

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
        maxCharge = 0;
        energyPerDamage = 0;
        provideEnergy = false;
        transferLimit = 0;

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
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        if (iconResPath == null) {
            iconResPath = "thaumicboots:VoidwalkerBootsComet_-_Purple.png";
        }
        this.icon = ir.registerIcon(iconResPath);
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
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

    public double getDamageAbsorptionRatio() {
        return damageAbsorptionRatio;
    }

    protected double getBaseAbsorptionRatio() {
        return baseAbsorptionRatio;
    }

    public int getEnergyPerDamage() {
        return energyPerDamage;
    }

    public boolean canProvideEnergy(ItemStack itemStack) {
        return provideEnergy;
    }

    public double getMaxCharge(ItemStack itemStack) {
        return maxCharge;
    }

    public int getTier(ItemStack itemStack) {
        return tier;
    }

    public double getTransferLimit(ItemStack itemStack) {
        return transferLimit;
    }

    public Item getChargedItem(ItemStack itemStack) {
        return this;
    }

    public Item getEmptyItem(ItemStack itemStack) {
        return this;
    }

    public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
        return visDiscount;
    }
}
