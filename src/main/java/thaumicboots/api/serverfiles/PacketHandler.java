package thaumicboots.api.serverfiles;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import thaumicboots.main.utils.VersionInfo;

public class PacketHandler {

    private PacketHandler() {}

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE
            .newSimpleChannel(VersionInfo.ModID.toLowerCase());

    public static void initPackets() {
        INSTANCE.registerMessage(PacketJumpToggle.class, PacketJumpToggle.class, 1, Side.SERVER);
        INSTANCE.registerMessage(PacketJumpToggleAck.class, PacketJumpToggleAck.class, 2, Side.CLIENT);
        INSTANCE.registerMessage(PacketSpeedToggle.class, PacketSpeedToggle.class, 3, Side.SERVER);
        INSTANCE.registerMessage(PacketSpeedToggleAck.class, PacketSpeedToggleAck.class, 4, Side.CLIENT);
        INSTANCE.registerMessage(PacketOmniToggle.class, PacketOmniToggle.class, 5, Side.SERVER);
        INSTANCE.registerMessage(PacketOmniToggleAck.class, PacketOmniToggleAck.class, 6, Side.CLIENT);
        INSTANCE.registerMessage(
                PacketInertiaCancellingToggle.class,
                PacketInertiaCancellingToggle.class,
                7,
                Side.SERVER);
        INSTANCE.registerMessage(
                PacketInertiaCancellingToggleAck.class,
                PacketInertiaCancellingToggleAck.class,
                8,
                Side.CLIENT);
    }
}
