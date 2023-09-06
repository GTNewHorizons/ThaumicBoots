package thaumicboots.item.boots.voidwalker;

import thaumicboots.api.ItemVoidBoots;

public class ItemCometVoidwalkerBoots extends ItemVoidBoots {

    public ItemCometVoidwalkerBoots(final ArmorMaterial material, final int j, final int k) {
        super(material, j, k);
    }

    protected void setBootsData() {
        visDiscount = 5;
        damageAbsorptionRatio = 1.5D;
        jumpBonus = 0.450D;
        tier = 3;
        iconResPath = "thaumicboots:voidComet_16x";
        armorResPath = "thaumicboots:model/VoidwalkerBootsComet_-_Purple.png";
        unlocalisedName = "ItemVoidComet";
    }
}
