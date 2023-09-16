package thaumicboots.item.boots.unique;

import net.minecraft.util.IIcon;

public class ItemMeteoricCometBoots extends ItemCometMeteorBoots {

    public IIcon icon;

    public ItemMeteoricCometBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
    }

    protected void setBootsData() {
        jumpBonus = 0.35D; // needs to be executed, or it'll stay at 0 // 3.5 blocks
        tier = 2;
        iconResPath = "thaumicboots:bootsMeteorComet";
        armorResPath = "thaumicboots:model/VoidwalkerBootsMeteor.png";
        unlocalisedName = "ItemMeteoricComet";
    }

}
