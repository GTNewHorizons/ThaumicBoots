package thaumicboots.main.utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import thaumicboots.main.Config;
import thaumicboots.main.utils.compat.ExplorationsHelper;

public class TabThaumicBoots extends CreativeTabs {

    public static TabThaumicBoots tabThaumicBoots = new TabThaumicBoots();

    public TabThaumicBoots() {
        super(getNextID(), VersionInfo.ModName);
    }

    public Item getTabIconItem() {
        if (ExplorationsHelper.isActive()) {
            return ExplorationsHelper.bootsCometMeteor;
        }
        return Config.arcaniumLens;
    }
}
