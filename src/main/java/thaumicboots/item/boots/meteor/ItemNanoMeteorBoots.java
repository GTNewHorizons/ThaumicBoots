package thaumicboots.item.boots.meteor;

public class ItemNanoMeteorBoots extends ItemElectricMeteorBoots {

    public ItemNanoMeteorBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
    }

    protected void setBootsData() {
        super.setBootsData();
        maxCharge = 1_000_000;
        energyPerDamage = 500; // 2k hits, 2x prev
        visDiscount = 4;
        damageAbsorptionRatio = 1.5D;
        transferLimit = 1600;
        jumpBonus = 0.7975D; // 8 Blocks
        speedBonus = getEMTNanoSpeed();
        tier = 3;
        iconResPath = "thaumicboots:nanoMeteor_16x";
        armorResPath = "thaumicboots:model/nanobootsMeteor.png";
        unlocalisedName = "ItemNanoMeteor";
    }
}
