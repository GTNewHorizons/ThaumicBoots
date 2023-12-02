package thaumicboots.item.boots.unique;

import net.minecraft.util.IIcon;
import thaumicboots.api.ICometMeteorMix;
import thaumicboots.api.ItemBoots;
import thaumicboots.main.utils.TabThaumicBoots;

public class ItemSeasonBoots extends ItemBoots {

    public IIcon icon;

    public ItemSeasonBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
        setCreativeTab(TabThaumicBoots.tabThaumicBoots);
        setUnlocalizedName(unlocalisedName);
    }

    protected void setBootsData() {
        super.setBootsData();
        jumpBonus = 0.35D; // 3.5 blocks
        tier = 2;
        runBonus = 0.165F;
        longrunningbonus = 0.003F;
        steadyBonus = true;
        negateFall = true;
        waterEffects = true;
        iconResPath = "thaumicboots:bootsChristmas";
        armorResPath = "thaumicboots:model/boots_arcanium.png";
        unlocalisedName = "ItemSeasonBoots";
    }
}
