package thaumicboots.item.boots.comet;

import emt.util.EMTConfigHandler;
import net.minecraft.item.EnumRarity;

public class ItemQuantumCometBoots extends ItemElectricCometBoots {

    public ItemQuantumCometBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
    }

    @Override
    protected void setBootsData(){
        maxCharge=10_000_000;
        energyPerDamage = 20_000;
        visDiscount = 5;
        transferLimit = 12_000;
        unlocalisedName = "ItemQuantumComet";
        iconResPath = "thaumicboots:quantumComet_16x";
        armorResPath = "thaumicboots:model/quantumbootsComet.png";
        rarity = EnumRarity.rare;
        baseBonus = (float) EMTConfigHandler.quantumBootsSpeed + 0.220F;
        minimumDistance = EMTConfigHandler.quantumBootsMaxDrop;
        minimumHeight = 10F;
        runicCharge = 0;
        damageAbsorptionRatio = 2D;
        baseAbsorptionRatio = 0.15D;
        provideEnergy = false;
        tier = 4;
    }
}
