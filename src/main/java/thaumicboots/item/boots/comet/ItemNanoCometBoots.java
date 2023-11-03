package thaumicboots.item.boots.comet;

public class ItemNanoCometBoots extends ItemElectricCometBoots {

    public ItemNanoCometBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
    }

    @Override
    protected void setBootsData() {
        super.setBootsData();
        maxCharge = 1_000_000;
        energyPerDamage = 500; // 2k hits, 2x prev
        visDiscount = 4;
        transferLimit = 1_600;
        runBonus = getEMTNanoSpeed() + 0.110F; // nano + comet * 2
        jumpBonus = 0.6325D; // 6 blocks
        minimumHeight = 6F;
        minimumDistance = 35.0F;
        damageAbsorptionRatio = 1.5D;
        tier = 3;
        iconResPath = "thaumicboots:nanoComet_16x";
        armorResPath = "thaumicboots:model/nanobootsComet.png";
        unlocalisedName = "ItemNanoComet";
    }
}
