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

public class PacketSpeedIncModAck implements IMessage, IMessageHandler<PacketSpeedIncModAck, IMessage> {

    public double state;

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        state = byteBuf.readDouble();
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeDouble(state);
    }

    @SideOnly(Side.CLIENT)
    public IMessage onMessage(PacketSpeedIncModAck message, MessageContext ctx) {
        Minecraft mc = Minecraft.getMinecraft();
        final ItemStack boots = IBoots.getBoots(mc.thePlayer);
        if (boots.getItem() instanceof IBoots item) {
            item.setModeSpeed(boots, message.state);
            if (GTNHLibHelper.isActive()) {
                IBoots.renderHUDSpeedNotification();
            }
        }

        return null;
    }
}
