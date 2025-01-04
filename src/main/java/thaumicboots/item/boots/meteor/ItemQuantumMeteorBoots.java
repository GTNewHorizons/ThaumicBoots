package thaumicboots.item.boots.meteor;

import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;

public class ItemQuantumMeteorBoots extends ItemElectricMeteorBoots implements IRepairable, IRunicArmor {

    public ItemQuantumMeteorBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
    }

    protected void setBootsData() {
        super.setBootsData();
        maxCharge = 10_000_000;
        energyPerDamage = 2_500; // 4k hits, 2x prev
        visDiscount = 5;
        damageAbsorptionRatio = 2D;
        transferLimit = 12000;
        jumpBonus = 1.1D; // 12 Blocks
        speedBonus = getEMTQuantumSpeed();
        tier = 4;
        iconResPath = "thaumicboots:quantumMeteor_16x";
        armorResPath = "thaumicboots:model/quantumbootsMeteor.png";
        unlocalisedName = "ItemQuantumMeteor";
    }
}
