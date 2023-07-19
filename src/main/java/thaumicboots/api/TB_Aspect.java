package thaumicboots.api;

import net.minecraft.util.ResourceLocation;

import thaumcraft.api.aspects.Aspect;

public class TB_Aspect {

    public static Aspect SPACE = null;
    public static Aspect BOOTS = null;

    public static void addTB_Aspects() {

        // 3c04b5 - original blue
        SPACE = new Aspect(
                "caelum",
                0x5E74CF,
                new Aspect[] { Aspect.CRYSTAL, Aspect.METAL },
                new ResourceLocation("thaumicboots", "textures/aspects/space.png"),
                1);
        ThaumicBootsAPI.thaumcraftAspectSpace = SPACE;

        // 005252
        // 4a412a - brown
        BOOTS = new Aspect(
                "tabernus",
                0x4C8569,
                new Aspect[] { Aspect.ARMOR, Aspect.TRAVEL },
                new ResourceLocation("thaumicboots", "textures/aspects/boots.png"),
                1);
        ThaumicBootsAPI.thaumcraftAspectSpace = BOOTS;
    }
}
