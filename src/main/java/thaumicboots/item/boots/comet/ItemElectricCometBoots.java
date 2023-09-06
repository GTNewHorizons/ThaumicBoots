package thaumicboots.item.boots.comet;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import emt.util.EMTTextHelper;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.items.armor.Hover;
import thaumicboots.api.ItemElectricBoots;
import thaumicboots.main.utils.TabThaumicBoots;

public class ItemElectricCometBoots extends ItemElectricBoots implements IElectricItem, ISpecialArmor {

    public float minimumHeight;
    public double minimumDistance;

    public ItemElectricCometBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
        setCreativeTab(TabThaumicBoots.tabThaumicBoots);
        setUnlocalizedName(unlocalisedName);
    }

    protected void setBootsData() {
        maxCharge = 100_000;
        energyPerDamage = 1_000;
        runicCharge = 0;
        visDiscount = 2;
        provideEnergy = false;
        damageAbsorptionRatio = 1.5D;
        transferLimit = 100;
        jumpBonus = 0.275D;
        minimumHeight = 4F;
        minimumDistance = 20d;
        tier = 2;
        iconResPath = "thaumicboots:electricComet_16x";
        armorResPath = "thaumicboots:model/electricbootsComet.png";
        unlocalisedName = "ItemElectricComet";
        rarity = EnumRarity.rare;
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
}
