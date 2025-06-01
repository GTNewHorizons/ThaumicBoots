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
        INSTANCE.registerMessage(PacketJumpInc.class, PacketJumpInc.class, 1, Side.SERVER);
        INSTANCE.registerMessage(PacketJumpIncAck.class, PacketJumpIncAck.class, 2, Side.CLIENT);
        INSTANCE.registerMessage(PacketSpeedInc.class, PacketSpeedInc.class, 3, Side.SERVER);
        INSTANCE.registerMessage(PacketSpeedIncAck.class, PacketSpeedIncAck.class, 4, Side.CLIENT);
        INSTANCE.registerMessage(PacketOmniToggle.class, PacketOmniToggle.class, 5, Side.SERVER);
        INSTANCE.registerMessage(PacketOmniToggleAck.class, PacketOmniToggleAck.class, 6, Side.CLIENT);
        INSTANCE.registerMessage(PacketInertiaToggle.class, PacketInertiaToggle.class, 7, Side.SERVER);
        INSTANCE.registerMessage(PacketInertiaToggleAck.class, PacketInertiaToggleAck.class, 8, Side.CLIENT);
        INSTANCE.registerMessage(PacketSpeedIncMod.class, PacketSpeedIncMod.class, 9, Side.SERVER);
        INSTANCE.registerMessage(PacketSpeedIncModAck.class, PacketSpeedIncModAck.class, 10, Side.CLIENT);
        INSTANCE.registerMessage(PacketJumpIncMod.class, PacketJumpIncMod.class, 11, Side.SERVER);
        INSTANCE.registerMessage(PacketJumpIncModAck.class, PacketJumpIncModAck.class, 12, Side.CLIENT);
        INSTANCE.registerMessage(PacketStepToggle.class, PacketStepToggle.class, 13, Side.SERVER);
        INSTANCE.registerMessage(PacketStepToggleAck.class, PacketStepToggleAck.class, 14, Side.CLIENT);
    }
}
