package thaumicboots.main.utils.compat;

import cpw.mods.fml.common.Loader;
import thaumicboots.main.Config;

public class GTNHLibHelper implements IModHelper {

    private static boolean isGTNHLibActive = false;
    public static final String GTNHLIB = "gtnhlib";

    public static boolean isActive() {
        return isGTNHLibActive;
    }

    @Override
    public void preInit() {
        if (Loader.isModLoaded(GTNHLIB) && Config.gtnhLibActive) {
            isGTNHLibActive = true;
        }
    }

    @Override
    public void init() {}

    @Override
    public void postInit() {}
}
