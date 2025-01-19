package thaumicboots.item.boots.voidwalker;

import thaumicboots.api.IMeteor;
import thaumicboots.api.ItemVoidBoots;

public class ItemMeteorVoidwalkerBoots extends ItemVoidBoots implements IMeteor {

    public ItemMeteorVoidwalkerBoots(final ArmorMaterial material, final int j, final int k) {
        super(material, j, k);
    }

    protected void setBootsData() {
        super.setBootsData();
        visDiscount = 5;
        jumpBonus = 0.88D; // 9 blocks
        tier = 3;
        speedBonus = 0.300F;
        iconResPath = "thaumicboots:purpleHaze_16x";
        armorResPath = "thaumicboots:model/VoidwalkerBootsMeteor_-_Purple.png";
        unlocalisedName = "ItemVoidMeteor";
    }
}
