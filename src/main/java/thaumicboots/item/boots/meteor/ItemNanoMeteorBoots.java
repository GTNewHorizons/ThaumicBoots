package thaumicboots.item.boots.meteor;

import emt.util.EMTConfigHandler;

public class ItemNanoMeteorBoots extends ItemElectricMeteorBoots {

    public ItemNanoMeteorBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
    }

    protected void setBootsData() {
        maxCharge = 1_000_000;
        energyPerDamage = 5_000;
        visDiscount = 4;
        damageAbsorptionRatio = 1.5D;
        transferLimit = 1600;
        jumpBonus = 0.275D * 2.9;
        baseBonus = (float) EMTConfigHandler.nanoBootsSpeed;
        tier = 3;
        iconResPath = "thaumicboots:nanoMeteor_16x";
        armorResPath = "thaumicboots:model/nanobootsMeteor.png";
        unlocalisedName = "ItemNanoMeteor";
    }
}
