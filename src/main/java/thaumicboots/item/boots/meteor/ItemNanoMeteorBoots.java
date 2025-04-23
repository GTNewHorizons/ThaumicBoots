package thaumicboots.item.boots.meteor;

import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.Optional;
import gregtech.api.hazards.Hazard;
import gregtech.api.hazards.IHazardProtector;

@Optional.Interface(iface = "gregtech.api.hazards.IHazardProtector", modid = "gregtech_nh")
public class ItemNanoMeteorBoots extends ItemElectricMeteorBoots implements IHazardProtector {

    public ItemNanoMeteorBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
    }

    protected void setBootsData() {
        super.setBootsData();
        maxCharge = 1_000_000;
        energyPerDamage = 500; // 2k hits, 2x prev
        visDiscount = 4;
        damageAbsorptionRatio = 1.5D;
        transferLimit = 1600;
        jumpBonus = 0.7975D; // 8 Blocks
        speedBonus = getEMTNanoSpeed();
        tier = 3;
        iconResPath = "thaumicboots:nanoMeteor_16x";
        armorResPath = "thaumicboots:model/nanobootsMeteor.png";
        unlocalisedName = "ItemNanoMeteor";
    }

    @Override
    @Optional.Method(modid = "gregtech_nh")
    public boolean protectsAgainst(ItemStack itemStack, Hazard hazard) {
        return true;
    }
}
