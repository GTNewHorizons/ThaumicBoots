package thaumicboots.item.boots.unique;

import thaumicboots.api.IComet;
import thaumicboots.api.ItemBoots;
import thaumicboots.main.utils.CalendarHelper;
import thaumicboots.main.utils.TabThaumicBoots;

public class ItemChristmasBoots extends ItemBoots implements IComet {

    int modifier = 1;

    public ItemChristmasBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
        setCreativeTab(TabThaumicBoots.tabThaumicBoots);
        setUnlocalizedName(unlocalisedName);
    }

    protected void setBootsData() {
        super.setBootsData();
        if (CalendarHelper.isChristmas()) {
            visDiscount = 25;
            modifier = 12;
        } else {
            visDiscount = 2;
        }
        jumpBonus = 0.35D; // 3.5 blocks
        tier = 2;
        speedBonus = 0.165F;
        longrunningbonus = 0.012F * modifier;
        steadyBonus = true;
        negateFall = true;
        waterEffects = true;
        unlocalisedName = "ItemChristmasBoots";
        iconResPath = "thaumicboots:bootsChristmas";
        armorResPath = "thaumicboots:model/boots_christmas.png";
    }
}
