package thaumicboots.api;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import thaumicboots.main.utils.TabThaumicBoots;

import java.util.List;

public class ItemElectricBoots extends ItemBoots implements IElectricItem {

    public int maxCharge;
    public int energyPerDamage;
    public double transferLimit;
    public boolean provideEnergy;

    public ItemElectricBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
        setCreativeTab(TabThaumicBoots.tabThaumicBoots);
        setUnlocalizedName(unlocalisedName);
    }

    protected void setBootsData() {
        maxCharge = 0;
        energyPerDamage = 0;
        provideEnergy = false;
        transferLimit = 0;
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

    public double getTransferLimit(ItemStack itemStack) {
        return transferLimit;
    }

    // TODO: non-variable related methods

    @Override
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

    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        if (ElectricItem.manager.getCharge(armor) >= getEnergyPerDamage()) {
            return (int) Math.round(20D * getBaseAbsorptionRatio() * getDamageAbsorptionRatio());
        } else {
            return 0;
        }
    }

    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
        ElectricItem.manager.discharge(stack, damage * getEnergyPerDamage(), 0x7fffffff, true, false, false);
    }

    public Item getChargedItem(ItemStack itemStack) {
        return this;
    }

    public Item getEmptyItem(ItemStack itemStack) {
        return this;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack){
        if (player.moveForward <= 0F) {
            return;
        }

        float bonus = baseBonus;
        stepHeight(player);
        if (steadyBonus) {
            runningTicks(player);
            bonus = computeBonus(itemStack, player);
        }
        if (ElectricItem.manager.getCharge(itemStack) == 0) {
            bonus *= 0;
        } 
        applyBonus(player, bonus);

        if (negateFall){
            if (player.fallDistance > 0.0F) {
                player.fallDistance = 0.0F;
            }
        }
    }
}
