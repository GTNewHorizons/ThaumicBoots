package thaumicboots.item.boots.voidwalker;

import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.Optional;
import gregtech.api.hazards.Hazard;
import gregtech.api.hazards.IHazardProtector;
import thaumicboots.api.IMeteor;
import thaumicboots.api.ItemVoidBoots;

@Optional.Interface(iface = "gregtech.api.hazards.IHazardProtector", modid = "gregtech_nh")
public class ItemMeteorVoidwalkerBoots extends ItemVoidBoots implements IMeteor, IHazardProtector {

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

    @Override
    @Optional.Method(modid = "gregtech_nh")
    public boolean protectsAgainst(ItemStack itemStack, Hazard hazard) {
        return true;
    }
}
