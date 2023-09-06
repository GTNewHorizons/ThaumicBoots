package thaumicboots.item.boots.unique;

import net.minecraft.util.IIcon;

import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumicboots.api.ItemBoots;
import thaumicboots.main.utils.TabThaumicBoots;

public class ItemCometMeteorBoots extends ItemBoots implements IRepairable, IRunicArmor {

    public IIcon icon;

    public ItemCometMeteorBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
        setCreativeTab(TabThaumicBoots.tabThaumicBoots);
        setUnlocalizedName(unlocalisedName);
    }

    protected void setBootsData() {
        damageAbsorptionRatio = 1.5D;
        jumpBonus = 0.35D;
        tier = 2;
        baseBonus = 0.165F;
        runningbonus = 0.003F;
        steadyBonus = true;
        negateFall = true;
        waterEffects = true;
        iconResPath = "thaumicboots:bootsCometMeteor";
        armorResPath = "thaumicboots:model/VoidwalkerBootsComet.png";
        unlocalisedName = "ItemCometMeteor";
    }
}
