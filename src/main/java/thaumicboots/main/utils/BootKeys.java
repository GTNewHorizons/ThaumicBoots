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
import thaumicboots.api.serverfiles.PacketInertiaCancellingToggle;
import thaumicboots.api.serverfiles.PacketJumpToggle;
import thaumicboots.api.serverfiles.PacketOmniToggle;
import thaumicboots.api.serverfiles.PacketSpeedToggle;

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
    private final KeyBinding keyInertiaCancellingToggle = new KeyBinding(
            "keybinding.inertiacancellingtoggle",
            Keyboard.KEY_NONE,
            "Thaumic Boots");

    public BootKeys() {
        FMLCommonHandler.instance().bus().register(this);
        ClientRegistry.registerKeyBinding(keyJumpToggle);
        ClientRegistry.registerKeyBinding(keySpeedToggle);
        ClientRegistry.registerKeyBinding(keyOmniToggle);
        ClientRegistry.registerKeyBinding(keyInertiaCancellingToggle);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void keyPressed(TickEvent.ClientTickEvent event) {
        checkKeys();
    }

    private void checkKeys() {
        if (keyJumpToggle.isPressed()) {
            toggleJump();
        } else if (keySpeedToggle.isPressed()) {
            toggleSpeed();
        } else if (keyOmniToggle.isPressed()) {
            toggleOmni();
        } else if (keyInertiaCancellingToggle.isPressed()) {
            toggleInertiaCancelling();
        }
    }

    private static void toggleJump() {
        PacketHandler.INSTANCE.sendToServer(new PacketJumpToggle());
    }

    private static void toggleSpeed() {
        PacketHandler.INSTANCE.sendToServer(new PacketSpeedToggle());
    }

    private static void toggleOmni() {
        PacketHandler.INSTANCE.sendToServer(new PacketOmniToggle());
    }

    private static void toggleInertiaCancelling() {
        PacketHandler.INSTANCE.sendToServer(new PacketInertiaCancellingToggle());
    }
}
