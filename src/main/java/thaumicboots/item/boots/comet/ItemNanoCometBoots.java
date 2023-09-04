package thaumicboots.item.boots.comet;

import emt.util.EMTConfigHandler;
import net.minecraft.item.EnumRarity;

public class ItemNanoCometBoots extends ItemElectricCometBoots {

    public ItemNanoCometBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
    }

    @Override
    protected void setBootsData(){
        maxCharge = 1_000_000;
        energyPerDamage = 5_000;
        visDiscount = 4;
        transferLimit = 1_600;
        iconResPath = "thaumicboots:nanoComet_16x";
        armorResPath = "thaumicboots:model/nanobootsComet.png";
        unlocalisedName = "ItemNanoComet";
        rarity = EnumRarity.rare;
        baseBonus = (float) EMTConfigHandler.nanoBootsSpeed + 0.110F;
        minimumHeight = 6F;
        minimumDistance = EMTConfigHandler.nanoBootsMaxDrop;
        runicCharge = 0;
        damageAbsorptionRatio = 1.5D;
        baseAbsorptionRatio = 0.15D;
        provideEnergy = false;
        tier = 3;
    }
}
