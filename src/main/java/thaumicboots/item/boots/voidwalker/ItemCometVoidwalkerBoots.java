package thaumicboots.item.boots.voidwalker;

import thaumicboots.api.IComet;
import thaumicboots.api.ItemVoidBoots;

public class ItemCometVoidwalkerBoots extends ItemVoidBoots implements IComet {

    public ItemCometVoidwalkerBoots(final ArmorMaterial material, final int j, final int k) {
        super(material, j, k);
    }

    protected void setBootsData() {
        super.setBootsData();
        visDiscount = 5;
        jumpBonus = 0.450D; // 4.5 blocks
        tier = 3;
        speedBonus = 0.215F * 3;
        iconResPath = "thaumicboots:voidComet_16x";
        armorResPath = "thaumicboots:model/VoidwalkerBootsComet_-_Purple.png";
        unlocalisedName = "ItemVoidComet";
    }
}
