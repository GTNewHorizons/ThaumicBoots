package thaumicboots.item.boots.voidwalker;

public class ItemQuantumVoidwalkerBoots extends ItemElectricVoidwalkerBoots {

    public ItemQuantumVoidwalkerBoots(final ArmorMaterial material, final int j, final int k) {
        super(material, j, k);
    }

    protected void setBootsData() {
        maxCharge = 100_000_000;
        energyPerDamage = 12_500; // allows for 8k hits 2x more than base quantum, 2x more than prev (for this mod)
        visDiscount = 5 + 5; // voidwalker + quantum discount
        damageAbsorptionRatio = 3.0D;
        transferLimit = 24_000;
        jumpBonus = 1.0175D; // 11 blocks
        runBonus = 1.250F;
        iconResPath = "thaumicboots:quantumVoid_16x";
        armorResPath = "thaumicboots:model/quantumbootsVoidwalker.png";
        unlocalisedName = "ItemQuantumVoid";
    }
}
