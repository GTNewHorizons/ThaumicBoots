package thaumicboots.item.boots.voidwalker;

import net.minecraftforge.common.ISpecialArmor;

import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.IWarpingGear;
import thaumicboots.api.ItemVoidBoots;

public class ItemMeteorVoidwalkerBoots extends ItemVoidBoots
        implements IVisDiscountGear, IWarpingGear, IRunicArmor, IRepairable, ISpecialArmor {

    public ItemMeteorVoidwalkerBoots(final ArmorMaterial material, final int j, final int k) {
        super(material, j, k);
    }

    protected void setBootsData() {
        visDiscount = 5;
        damageAbsorptionRatio = 1.5D;
        jumpBonus = 0.275D * 3.2;
        tier = 3;
        iconResPath = "thaumicboots:purpleHaze_16x";
        armorResPath = "thaumicboots:model/VoidwalkerBootsMeteor_-_Purple.png";
        unlocalisedName = "ItemVoidMeteor";
    }
}
