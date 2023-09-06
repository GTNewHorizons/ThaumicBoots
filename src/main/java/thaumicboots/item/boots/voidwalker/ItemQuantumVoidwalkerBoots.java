package thaumicboots.item.boots.voidwalker;

public class ItemQuantumVoidwalkerBoots extends ItemElectricVoidwalkerBoots {

    public ItemQuantumVoidwalkerBoots(final ArmorMaterial material, final int j, final int k) {
        super(material, j, k);
    }

    protected void setBootsData() {
        maxCharge = 100_000_000;
        energyPerDamage = 10_000;
        visDiscount = 5 + 5; // voidwalker + quantum discount
        damageAbsorptionRatio = 3.0D;
        transferLimit = 24_000;
        jumpBonus = 0.275D * 3.7;
        baseBonus = 1.250F;
        iconResPath = "thaumicboots:quantumVoid_16x";
        armorResPath = "thaumicboots:model/quantumbootsVoidwalker.png";
        unlocalisedName = "ItemQuantumVoid";
    }
}
