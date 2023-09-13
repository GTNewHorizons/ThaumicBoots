package thaumicboots.main.utils.compat;

import cpw.mods.fml.common.Loader;
import thaumicboots.main.Config;

public class TaintedHelper implements IModHelper {

    private static boolean isTaintedMagicActive = false;
    public static final String TAINTED_MAGIC = "TaintedMagic";

    public static boolean isActive() {
        return isTaintedMagicActive;
    }

    @Override
    public void preInit() {
        if (Loader.isModLoaded(TAINTED_MAGIC) && Config.taintedMagicActive) {
            isTaintedMagicActive = true;
        }
    }

    @Override
    public void init() {}

    @Override
    public void postInit() {}
}
