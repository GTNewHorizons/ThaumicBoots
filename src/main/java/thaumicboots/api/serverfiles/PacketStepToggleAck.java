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

public class PacketStepToggleAck implements IMessage, IMessageHandler<PacketStepToggleAck, IMessage> {

    public boolean state;

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        state = byteBuf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeBoolean(state);
    }

    @SideOnly(Side.CLIENT)
    public IMessage onMessage(PacketStepToggleAck message, MessageContext ctx) {
        Minecraft mc = Minecraft.getMinecraft();
        final ItemStack boots = IBoots.getBoots(mc.thePlayer);
        if (boots.getItem() instanceof IBoots item) {
            item.setModeStep(boots, message.state);
            if (GTNHLibHelper.isActive()) {
                IBoots.renderHUDStepNotification();
            }
        }

        return null;
    }
}
