package thaumicboots.main.utils;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumicboots.api.serverfiles.PacketHandler;
import thaumicboots.api.serverfiles.PacketInertiaToggle;
import thaumicboots.api.serverfiles.PacketJumpInc;
import thaumicboots.api.serverfiles.PacketJumpIncMod;
import thaumicboots.api.serverfiles.PacketOmniToggle;
import thaumicboots.api.serverfiles.PacketSpeedInc;
import thaumicboots.api.serverfiles.PacketSpeedIncMod;
import thaumicboots.api.serverfiles.PacketStepToggle;

public class BootKeys {

    private static final KeyBinding keyJumpToggle = new KeyBinding(
            "keybinding.jumptoggle",
            Keyboard.KEY_NONE,
            "Thaumic Boots");
    private final KeyBinding keySpeedToggle = new KeyBinding(
            "keybinding.speedtoggle",
            Keyboard.KEY_NONE,
            "Thaumic Boots");
    private final KeyBinding keyOmniToggle = new KeyBinding(
            "keybinding.omnitoggle",
            Keyboard.KEY_NONE,
            "Thaumic Boots");
    private final KeyBinding keyInertiaToggle = new KeyBinding(
            "keybinding.inertiatoggle",
            Keyboard.KEY_NONE,
            "Thaumic Boots");
    private final KeyBinding keyIncrementMod = new KeyBinding(
            "keybinding.incrementmodifier",
            Keyboard.KEY_NONE,
            "Thaumic Boots");

    private final KeyBinding keyStepAssist = new KeyBinding(
            "keybinding.stepassist",
            Keyboard.KEY_NONE,
            "Thaumic Boots");

    public BootKeys() {
        FMLCommonHandler.instance().bus().register(this);
        ClientRegistry.registerKeyBinding(keyJumpToggle);
        ClientRegistry.registerKeyBinding(keySpeedToggle);
        ClientRegistry.registerKeyBinding(keyOmniToggle);
        ClientRegistry.registerKeyBinding(keyInertiaToggle);
        ClientRegistry.registerKeyBinding(keyIncrementMod);
        ClientRegistry.registerKeyBinding(keyStepAssist);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void keyPressed(TickEvent.ClientTickEvent event) {
        checkKeys();
    }

    private void checkKeys() {
        if (keyJumpToggle.isPressed()) {
            toggleJump(keyIncrementMod.getIsKeyPressed());
        } else if (keySpeedToggle.isPressed()) {
            toggleSpeed(keyIncrementMod.getIsKeyPressed());
        } else if (keyOmniToggle.isPressed()) {
            toggleOmni();
        } else if (keyInertiaToggle.isPressed()) {
            toggleInertia();
        } else if (keyStepAssist.isPressed()) {
            toggleStep();
        }
    }

    private static void toggleJump(boolean incMod) {
        if (incMod) {
            PacketHandler.INSTANCE.sendToServer(new PacketJumpIncMod());
        } else {
            PacketHandler.INSTANCE.sendToServer(new PacketJumpInc());
        }
    }

    private static void toggleSpeed(boolean incMod) {
        if (incMod) {
            PacketHandler.INSTANCE.sendToServer(new PacketSpeedIncMod());
        } else {
            PacketHandler.INSTANCE.sendToServer(new PacketSpeedInc());
        }
    }

    private static void toggleOmni() {
        PacketHandler.INSTANCE.sendToServer(new PacketOmniToggle());
    }

    private static void toggleStep() {
        PacketHandler.INSTANCE.sendToServer(new PacketStepToggle());
    }

    private static void toggleInertia() {
        PacketHandler.INSTANCE.sendToServer(new PacketInertiaToggle());
    }
}
