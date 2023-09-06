package thaumicboots.item.boots.meteor;

import thaumicboots.api.ItemElectricBoots;

public class ItemElectricMeteorBoots extends ItemElectricBoots {

    public ItemElectricMeteorBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
    }

    protected void setBootsData() {
        maxCharge = 100_000;
        energyPerDamage = 1_000;
        visDiscount = 2;
        damageAbsorptionRatio = 1.25D;
        transferLimit = 100;
        jumpBonus = 0.275D * 1.9;
        baseBonus = 0.055F;
        tier = 2;
        negateFall = true;
        iconResPath = "thaumicboots:electricMeteor_16x";
        armorResPath = "thaumicboots:model/electricbootsMeteor.png";
        unlocalisedName = "ItemElectricMeteor";
    }

}
