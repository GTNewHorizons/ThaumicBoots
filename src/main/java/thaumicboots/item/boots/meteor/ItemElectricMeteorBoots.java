package thaumicboots.item.boots.meteor;

import thaumicboots.api.IMeteor;
import thaumicboots.api.ItemElectricBoots;

public class ItemElectricMeteorBoots extends ItemElectricBoots implements IMeteor {

    public ItemElectricMeteorBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
    }

    protected void setBootsData() {
        super.setBootsData();
        maxCharge = 100_000;
        energyPerDamage = 100; // 1k hits
        visDiscount = 2;
        damageAbsorptionRatio = 1.25D;
        transferLimit = 100;
        jumpBonus = 0.5225D; // 5 blocks
        speedBonus = 0.055F; // base electric
        tier = 2;
        negateFall = true;
        iconResPath = "thaumicboots:electricMeteor_16x";
        armorResPath = "thaumicboots:model/electricbootsMeteor.png";
        unlocalisedName = "ItemElectricMeteor";
    }

}
