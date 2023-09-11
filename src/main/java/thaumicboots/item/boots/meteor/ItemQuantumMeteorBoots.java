package thaumicboots.item.boots.meteor;

import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;

public class ItemQuantumMeteorBoots extends ItemElectricMeteorBoots implements IRepairable, IRunicArmor {

    public ItemQuantumMeteorBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
    }

    protected void setBootsData() {
        maxCharge = 10_000_000;
        energyPerDamage = 20000;
        visDiscount = 5;
        damageAbsorptionRatio = 2D;
        transferLimit = 12000;
        jumpBonus = 0.275D * 4;
        runBonus = getEMTQuantumSpeed();
        tier = 4;
        iconResPath = "thaumicboots:quantumMeteor_16x";
        armorResPath = "thaumicboots:model/quantumbootsMeteor.png";
        unlocalisedName = "ItemQuantumMeteor";
    }
}
