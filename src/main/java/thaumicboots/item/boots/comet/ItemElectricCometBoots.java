package thaumicboots.item.boots.comet;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
import thaumcraft.common.items.armor.Hover;
import thaumicboots.main.utils.TabThaumicBoots;

public class ItemElectricCometBoots extends ItemArmor
        implements IRepairable, IRunicArmor, IElectricItem, IVisDiscountGear, ISpecialArmor {

    public IIcon icon;
    public float baseBonus;
    public float minimumHeight;
    public int maxCharge;
    public int energyPerDamage;
    public int visDiscount;
    public int runicCharge;
    public int tier;
    public double minimumDistance;
    public double damageAbsorptionRatio;
    public double baseAbsorptionRatio;
    public double transferLimit;
    public boolean provideEnergy;
    public String iconResPath;
    public String armorResPath;
    public String unlocalisedName;
    public EnumRarity rarity;

    public ItemElectricCometBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
        setBootsData();
        setCreativeTab(TabThaumicBoots.tabThaumicBoots);
        setUnlocalizedName(unlocalisedName);

    }

    protected void setBootsData() {
        maxCharge = 100_000;
        energyPerDamage = 1_000;
        runicCharge = 0;
        visDiscount = 2;
        provideEnergy = false;
        baseAbsorptionRatio = 0.15D;
        damageAbsorptionRatio = 1.5D;
        transferLimit = 100;
        baseBonus = 0.165F;
        minimumHeight = 4F;
        minimumDistance = 20d;
        tier = 2;
        iconResPath = "thaumicboots:electricComet_16x";
        armorResPath = "thaumicboots:model/electricbootsComet.png";
        unlocalisedName = "ItemElectricComet";
        rarity = EnumRarity.rare;
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List itemList) {
        ItemStack itemStack = new ItemStack(this, 1);
        if (getChargedItem(itemStack) == this) {
            ItemStack charged = new ItemStack(this, 1);
            ElectricItem.manager.charge(charged, 2147483647, 2147483647, true, false);
            itemList.add(charged);
        }
        if (getEmptyItem(itemStack) == this) {
            itemList.add(new ItemStack(this, 1, getMaxDamage()));
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        this.icon = ir.registerIcon(iconResPath);
    }

    @SideOnly(Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer) {
        return armorResPath;
    }

    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack) {
        return EnumRarity.rare;
    }

    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.isItemEqual(new ItemStack(Items.leather))
                || super.getIsRepairable(par1ItemStack, par2ItemStack);
    }

    public float getPowerConsumptionMultiplier(float distance) {
        return (distance > minimumDistance) ? distance * 3 : distance;
    }

    public float getPowerConsumption(float distance) {
        return energyPerDamage * (getPowerConsumptionMultiplier(distance) - getMinimumHeight());
    }

    public float getMinimumHeight() {
        return minimumHeight;
    }

    protected float computeBonus(ItemStack itemStack, EntityPlayer player) {
        int ticks = player.inventory.armorItemInSlot(0).stackTagCompound.getInteger("runTicks");

        float bonus = baseBonus + ((ticks / 5) * 0.003F);
        if (ElectricItem.manager.getCharge(itemStack) == 0) {
            bonus = 0;
        } else if (player.isInWater()) {
            bonus /= 4.0F;
        }

        return bonus;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {

        if (player.capabilities.isFlying || player.moveForward <= 0F) {
            return;
        }

        if (player.worldObj.isRemote) {
            if (!Thaumcraft.instance.entityEventHandler.prevStep.containsKey(Integer.valueOf(player.getEntityId()))) {
                Thaumcraft.instance.entityEventHandler.prevStep
                        .put(Integer.valueOf(player.getEntityId()), Float.valueOf(player.stepHeight));
            }
            player.stepHeight = 1.0F;
        }
        if (!player.inventory.armorItemInSlot(0).hasTagCompound()) {
            NBTTagCompound par1NBTTagCompound = new NBTTagCompound();
            player.inventory.armorItemInSlot(0).setTagCompound(par1NBTTagCompound);
            player.inventory.armorItemInSlot(0).stackTagCompound.setInteger("runTicks", 0);
        }

        float bonus = computeBonus(itemStack, player);

        if (player.onGround || player.isOnLadder()) {
            player.moveFlying(0.0F, 1.0F, bonus);
        } else if (Hover.getHover(player.getEntityId())) {
            player.jumpMovementFactor = 0.03F;
        } else {
            player.jumpMovementFactor = 0.05F;
        }
    }

    public int getRunicCharge(ItemStack arg0) {
        return runicCharge;
    }

    @Override
    public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source,
            double damage, int slot) {
        if (source.isUnblockable()) {
            return new net.minecraftforge.common.ISpecialArmor.ArmorProperties(0, 0.0D, 0);
        } else {
            double absorptionRatio = getBaseAbsorptionRatio() * getDamageAbsorptionRatio();
            int energyPerDamage = getEnergyPerDamage();
            double damageLimit = energyPerDamage <= 0 ? 0
                    : (25 * ElectricItem.manager.getCharge(armor)) / energyPerDamage;
            return new net.minecraftforge.common.ISpecialArmor.ArmorProperties(0, absorptionRatio, (int) damageLimit);
        }
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        if (ElectricItem.manager.getCharge(armor) >= getEnergyPerDamage()) {
            return (int) Math.round(20D * getBaseAbsorptionRatio() * getDamageAbsorptionRatio());
        } else {
            return 0;
        }
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
        ElectricItem.manager.discharge(stack, damage * getEnergyPerDamage(), 0x7fffffff, true, false, false);
    }

    public double getDamageAbsorptionRatio() {
        return damageAbsorptionRatio;
    }

    protected double getBaseAbsorptionRatio() {
        return baseAbsorptionRatio;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        list.add(EMTTextHelper.PURPLE + EMTTextHelper.localize("tooltip.EMT.visDiscount") + ": " + visDiscount + "%");
    }

    public int getEnergyPerDamage() {
        return energyPerDamage;
    }

    @Override
    public boolean canProvideEnergy(ItemStack itemStack) {
        return provideEnergy;
    }

    @Override
    public double getMaxCharge(ItemStack itemStack) {
        return maxCharge;
    }

    @Override
    public int getTier(ItemStack itemStack) {
        return 2;
    }

    @Override
    public double getTransferLimit(ItemStack itemStack) {
        return transferLimit;
    }

    @Override
    public Item getChargedItem(ItemStack itemStack) {
        return this;
    }

    @Override
    public Item getEmptyItem(ItemStack itemStack) {
        return this;
    }

    @Override
    public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
        return visDiscount;
    }
}
