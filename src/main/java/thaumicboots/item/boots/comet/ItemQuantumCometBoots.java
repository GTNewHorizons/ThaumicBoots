package thaumicboots.item.boots.comet;

import net.minecraft.item.EnumRarity;

import emt.util.EMTConfigHandler;

public class ItemQuantumCometBoots extends ItemElectricCometBoots {

    public ItemQuantumCometBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
    }

    @Override
    protected void setBootsData() {
        maxCharge = 10_000_000;
        energyPerDamage = 20_000;
        visDiscount = 5;
        transferLimit = 12_000;
        unlocalisedName = "ItemQuantumComet";
        iconResPath = "thaumicboots:quantumComet_16x";
        armorResPath = "thaumicboots:model/quantumbootsComet.png";
        rarity = EnumRarity.rare;
        runBonus = getEMTQuantumSpeed() + 0.220F;
        jumpBonus = 0.275D * 3.3;
        minimumDistance = EMTConfigHandler.quantumBootsMaxDrop;
        minimumHeight = 10F;
        damageAbsorptionRatio = 2D;
        tier = 4;
    }
}
