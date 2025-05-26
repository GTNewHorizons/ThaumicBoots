package thaumicboots.item.boots.comet;

import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.Optional;
import gregtech.api.hazards.Hazard;
import gregtech.api.hazards.IHazardProtector;

@Optional.Interface(iface = "gregtech.api.hazards.IHazardProtector", modid = "gregtech_nh")
public class ItemNanoCometBoots extends ItemElectricCometBoots implements IHazardProtector {

    public ItemNanoCometBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
    }

    @Override
    protected void setBootsData() {
        super.setBootsData();
        maxCharge = 1_000_000;
        energyPerDamage = 500; // 2k hits, 2x prev
        visDiscount = 4;
        transferLimit = 1_600;
        speedBonus = getEMTNanoSpeed() + 0.110F; // nano + comet * 2
        jumpBonus = 0.6325D; // 6 blocks
        minimumHeight = 6F;
        minimumDistance = 35.0F;
        damageAbsorptionRatio = 1.5D;
        tier = 3;
        iconResPath = "thaumicboots:nanoComet_16x";
        armorResPath = "thaumicboots:model/nanobootsComet.png";
        unlocalisedName = "ItemNanoComet";
    }

    @Override
    @Optional.Method(modid = "gregtech_nh")
    public boolean protectsAgainst(ItemStack itemStack, Hazard hazard) {
        return true;
    }
}
