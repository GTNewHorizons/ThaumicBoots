package thaumicboots.item.boots.comet;

public class ItemNanoCometBoots extends ItemElectricCometBoots {

    public ItemNanoCometBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
    }

    @Override
    protected void setBootsData() {
        maxCharge = 1_000_000;
        energyPerDamage = 5_000;
        visDiscount = 4;
        transferLimit = 1_600;
        runBonus = getEMTNanoSpeed() + 0.110F;
        jumpBonus = 0.275D * 2.3;
        minimumHeight = 6F;
        minimumDistance = 35.0F;
        damageAbsorptionRatio = 1.5D;
        tier = 3;
        iconResPath = "thaumicboots:nanoComet_16x";
        armorResPath = "thaumicboots:model/nanobootsComet.png";
        unlocalisedName = "ItemNanoComet";
    }
}
