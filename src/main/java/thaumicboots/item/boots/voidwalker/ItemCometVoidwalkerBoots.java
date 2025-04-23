package thaumicboots.item.boots.voidwalker;

import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.Optional;
import gregtech.api.hazards.Hazard;
import gregtech.api.hazards.IHazardProtector;
import thaumicboots.api.IComet;
import thaumicboots.api.ItemVoidBoots;

@Optional.Interface(iface = "gregtech.api.hazards.IHazardProtector", modid = "gregtech_nh")
public class ItemCometVoidwalkerBoots extends ItemVoidBoots implements IComet, IHazardProtector {

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

    @Override
    @Optional.Method(modid = "gregtech_nh")
    public boolean protectsAgainst(ItemStack itemStack, Hazard hazard) {
        return true;
    }
}
