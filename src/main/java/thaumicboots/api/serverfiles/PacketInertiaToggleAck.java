package thaumicboots.api.serverfiles;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import thaumicboots.api.IBoots;
import thaumicboots.main.utils.compat.GTNHLibHelper;

public class PacketInertiaToggleAck implements IMessage, IMessageHandler<PacketInertiaToggleAck, IMessage> {

    public boolean state;
    public boolean serverConfigValue;

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        state = byteBuf.readBoolean();
        serverConfigValue = byteBuf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeBoolean(state);
        byteBuf.writeBoolean(serverConfigValue);
    }

    @SideOnly(Side.CLIENT)
    public IMessage onMessage(PacketInertiaToggleAck message, MessageContext ctx) {
        Minecraft mc = Minecraft.getMinecraft();
        final ItemStack boots = IBoots.getBoots(mc.thePlayer);
        if (boots.getItem() instanceof IBoots item) {
            item.setIsInertiaCanceling(boots, message.state);
            if (GTNHLibHelper.isActive()) {
                IBoots.renderHUDInertiaNotification(message.serverConfigValue);
            }
        }

        return null;
    }
}
