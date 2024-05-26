package thaumicboots.api.serverfiles;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import thaumicboots.api.IBoots;

public class PacketInertiaCancellingToggle
        implements IMessage, IMessageHandler<PacketInertiaCancellingToggle, IMessage> {

    public void fromBytes(ByteBuf byteBuf) {
        // not needed
    }

    public void toBytes(ByteBuf byteBuf) {
        // not needed
    }

    @Override
    public IMessage onMessage(PacketInertiaCancellingToggle message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().playerEntity;
        final ItemStack boots = IBoots.getBoots(player);
        if (boots != null && boots.getItem() instanceof IBoots item) {
            boolean inertiaCancellingState = item.changeInertiaCancellingState(boots);
            PacketInertiaCancellingToggleAck ackMessage = new PacketInertiaCancellingToggleAck();
            ackMessage.state = inertiaCancellingState;
            PacketHandler.INSTANCE.sendTo(ackMessage, player);
        }
        return null;
    }
}
