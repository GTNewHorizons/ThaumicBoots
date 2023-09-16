package thaumicboots.main.utils.compat;

import java.util.ArrayList;
import java.util.List;

public class ModHelperManager {

    private static List<IModHelper> helpers;

    public static void preInit() {
        setupHelpers();

        for (IModHelper helper : helpers) {
            helper.preInit();
        }
    }

    public static void init() {
        for (IModHelper helper : helpers) {
            helper.init();
        }
    }

    public static void postInit() {
        for (IModHelper helper : helpers) {
            helper.postInit();
        }
    }

    private static void setupHelpers() {
        helpers = new ArrayList<>();
        helpers.add(new EMTHelper());
        helpers.add(new TaintedHelper());
        helpers.add(new ExplorationsHelper());
        helpers.add(new GTNHLibHelper());
        helpers.add(new ThaumcraftHelper());
    }
}
