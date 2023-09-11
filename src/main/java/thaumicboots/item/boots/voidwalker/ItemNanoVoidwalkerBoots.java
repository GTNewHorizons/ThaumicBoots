package thaumicboots.item.boots.voidwalker;

public class ItemNanoVoidwalkerBoots extends ItemElectricVoidwalkerBoots {

    public ItemNanoVoidwalkerBoots(final ArmorMaterial material, final int j, final int k) {
        super(material, j, k);
    }

    protected void setBootsData() {
        maxCharge = 10_000_000;
        energyPerDamage = 2_500;
        visDiscount = 5 + 4; // voidwalker + nano discount
        damageAbsorptionRatio = 2.75D;
        transferLimit = 2_400;
        jumpBonus = 0.275D * 2.7;
        runBonus = 0.550F;
        iconResPath = "thaumicboots:nanoVoid_16x";
        armorResPath = "thaumicboots:model/nanobootsVoidwalker.png";
        unlocalisedName = "ItemNanoVoid";
    }
}
