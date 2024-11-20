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

public class PacketJumpIncAck implements IMessage, IMessageHandler<PacketJumpIncAck, IMessage> {

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
    public IMessage onMessage(PacketJumpIncAck message, MessageContext ctx) {
        Minecraft mc = Minecraft.getMinecraft();
        final ItemStack boots = IBoots.getBoots(mc.thePlayer);
        if (boots.getItem() instanceof IBoots item) {
            item.setModeJump(boots, message.state);
            if (GTNHLibHelper.isActive()) {
                IBoots.renderHUDJumpNotification();
            }
        }

        return null;
    }
}
