package thaumicboots.api.serverfiles;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import thaumicboots.api.IBoots;
import thaumicboots.main.Config;

public class PacketInertiaToggle implements IMessage, IMessageHandler<PacketInertiaToggle, IMessage> {

    public void fromBytes(ByteBuf byteBuf) {
        // not needed
    }

    public void toBytes(ByteBuf byteBuf) {
        // not needed
    }

    @Override
    public IMessage onMessage(PacketInertiaToggle message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().playerEntity;
        final ItemStack boots = IBoots.getBoots(player);
        if (boots != null && boots.getItem() instanceof IBoots item) {
            boolean newInertiaCancelingState;
            if (Config.allowInertiaCancelingFeature) {
                newInertiaCancelingState = item.changeIsInertiaCanceled(boots);
            } else {
                item.setIsInertiaCanceling(boots, false);
                newInertiaCancelingState = false;
            }
            PacketInertiaToggleAck ackMessage = new PacketInertiaToggleAck();
            ackMessage.state = newInertiaCancelingState;
            ackMessage.serverConfigValue = Config.allowInertiaCancelingFeature;
            PacketHandler.INSTANCE.sendTo(ackMessage, player);
        }
        return null;
    }
}
