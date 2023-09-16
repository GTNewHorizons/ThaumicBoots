package thaumicboots.item.boots.comet;

import emt.item.armor.boots.ItemElectricBootsTraveller;
import emt.util.EMTTextHelper;
import ic2.api.item.ElectricItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import thaumicboots.api.IComet;
import thaumicboots.api.ItemElectricBoots;
import thaumicboots.main.utils.TabThaumicBoots;

import java.util.List;

public class ItemElectricCometBoots extends ItemElectricBoots implements IComet {

    public float minimumHeight;
    public double minimumDistance;

    public ItemElectricCometBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
        setCreativeTab(TabThaumicBoots.tabThaumicBoots);
        setUnlocalizedName(unlocalisedName);
    }

    protected void setBootsData() {
        maxCharge = 100_000;
        energyPerDamage = 100; // 1k hits
        visDiscount = 2;
        transferLimit = 100;
        runBonus = 0.165F; // electric + comet
        jumpBonus = 0.275D; // 3 blocks
        longrunningbonus = 0.003F;
        minimumHeight = 4F;
        minimumDistance = 20d;
        tier = 2;
        damageAbsorptionRatio = 0.5D;
        iconResPath = "thaumicboots:electricComet_16x";
        armorResPath = "thaumicboots:model/electricbootsComet.png";
        unlocalisedName = "ItemElectricComet";
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

}

