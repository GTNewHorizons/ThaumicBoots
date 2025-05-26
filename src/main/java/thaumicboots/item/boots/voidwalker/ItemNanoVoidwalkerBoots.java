package thaumicboots.item.boots.voidwalker;

public class ItemNanoVoidwalkerBoots extends ItemElectricVoidwalkerBoots {

    public ItemNanoVoidwalkerBoots(final ArmorMaterial material, final int j, final int k) {
        super(material, j, k);
    }

    protected void setBootsData() {
        super.setBootsData();
        maxCharge = 1_000_000;
        energyPerDamage = 500; // allows for 4k hits 2x more than base nano, 2x more than prev (for this mod)
        visDiscount = 5 + 4; // voidwalker + nano discount
        damageAbsorptionRatio = 2.75D;
        transferLimit = 2_400;
        jumpBonus = 0.7425D; // 7.5 blocks
        speedBonus = 0.550F;
        iconResPath = "thaumicboots:nanoVoid_16x";
        armorResPath = "thaumicboots:model/nanobootsVoidwalker.png";
        unlocalisedName = "ItemNanoVoid";
    }
}
