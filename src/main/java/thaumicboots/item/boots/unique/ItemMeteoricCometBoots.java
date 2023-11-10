package thaumicboots.item.boots.unique;

import net.minecraft.util.IIcon;

public class ItemMeteoricCometBoots extends ItemCometMeteorBoots {

    public IIcon icon;

    public ItemMeteoricCometBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
    }

    protected void setBootsData() {
        super.setBootsData();
        iconResPath = "thaumicboots:bootsMeteorComet";
        armorResPath = "thaumicboots:model/VoidwalkerBootsMeteor.png";
        unlocalisedName = "ItemMeteoricComet";
    }

}
