package thaumicboots.item.boots.unimplemented;

import thaumicboots.api.ItemVoidBoots;

public class ItemPurpleVoidwalkerBoots extends ItemVoidBoots {

    public ItemPurpleVoidwalkerBoots(final ArmorMaterial material, final int j, final int k) {
        super(material, j, k);
    }

    protected void setBootsData() {
        visDiscount = 5;
        jumpBonus = 0.450D;
        tier = 3;
        iconResPath = "thaumicboots:purpleHaze_16x";
        armorResPath = "thaumicboots:model/VoidwalkerBootsMeteor_-_Purple.png";
        unlocalisedName = "ItemPurpleVoidwalkerBoots";
    }
}
