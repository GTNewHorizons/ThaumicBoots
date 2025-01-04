package thaumicboots.item.boots.unique;

import thaumicboots.api.ItemBoots;
import thaumicboots.main.utils.TabThaumicBoots;

public class ItemSlowBoots extends ItemBoots {

    public ItemSlowBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
        setCreativeTab(TabThaumicBoots.tabThaumicBoots);
        setUnlocalizedName(unlocalisedName);
    }

    protected void setBootsData() {
        super.setBootsData();
        tier = 2;
        speedBonus = -0.035F;
        negateFall = true;
        unlocalisedName = "ItemSlowBoots";
        iconResPath = "thaumicboots:bootsSlow_16x";
        armorResPath = "thaumicboots:model/slowboots.png";
    }
}
