package thaumicboots.api;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import com.gtnewhorizon.gtnhlib.GTNHLib;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumicboots.main.Config;

public interface IBoots {

    String TAG_MODE_JUMP = "jump";
    String TAG_MODE_SPEED = "speed";
    String TAG_MODE_OMNI = "omni";
    String TAG_MODE_INERTIA_CANCELLING = "inertiacancelling";

    default void setModeSpeed(ItemStack stack, double modifier) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.stackTagCompound.setDouble(TAG_MODE_SPEED, modifier);
    }

    default void setModeJump(ItemStack stack, double modifier) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.stackTagCompound.setDouble(TAG_MODE_JUMP, modifier);
    }

    default void setModeOmni(ItemStack stack, boolean enabled) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.stackTagCompound.setBoolean(TAG_MODE_OMNI, enabled);
    }

    default void setModeInertiaCancelling(ItemStack stack, boolean enabled) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.stackTagCompound.setBoolean(TAG_MODE_INERTIA_CANCELLING, enabled);
    }

    default double changeSpeed(ItemStack stack) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        // Internally MC returns 0 by default if the tag is not present, we do not need a presence check.
        double oldSpeed = stack.stackTagCompound.getDouble(TAG_MODE_SPEED);
        double newSpeed = oldSpeed + Config.bootsSpeedChangeRate;
        if (newSpeed > 1) {
            newSpeed = 0;
        }
        stack.stackTagCompound.setDouble(TAG_MODE_SPEED, newSpeed);
        return newSpeed;
    }

    default double changeJump(ItemStack stack) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        // Internally MC returns 0 by default if the tag is not present, we do not need a presence check.
        double oldJump = stack.stackTagCompound.getDouble(TAG_MODE_JUMP);
        double newJump = oldJump + Config.bootsSpeedChangeRate;
        if (newJump > 1) {
            newJump = 0;
        }
        stack.stackTagCompound.setDouble(TAG_MODE_JUMP, newJump);
        return newJump;
    }

    default boolean changeOmniState(ItemStack stack) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        // Internally MC returns false by default if the tag is not present, we do not need a presence check.
        boolean omni = stack.stackTagCompound.getBoolean(TAG_MODE_OMNI);
        omni = !omni;
        stack.stackTagCompound.setBoolean(TAG_MODE_OMNI, omni);
        return omni;
    }

    default boolean changeInertiaCancellingState(ItemStack stack) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        // Internally MC returns false by default if the tag is not present, we do not need a presence check.
        boolean inertiaCancelling = stack.stackTagCompound.getBoolean(TAG_MODE_INERTIA_CANCELLING);
        inertiaCancelling = !inertiaCancelling;
        stack.stackTagCompound.setBoolean(TAG_MODE_INERTIA_CANCELLING, inertiaCancelling);
        return inertiaCancelling;
    }

    default double isSpeedEnabled(ItemStack stack) {
        if (stack.stackTagCompound != null) {
            return stack.stackTagCompound.getDouble(TAG_MODE_SPEED);
        }
        return 0;
    }

    static double isJumpEnabled(ItemStack stack) {
        if (stack.stackTagCompound != null) {
            return stack.stackTagCompound.getDouble(TAG_MODE_JUMP);
        }
        return 0;
    }

    default boolean isOmniEnabled(ItemStack stack) {
        if (stack.stackTagCompound != null) {
            return stack.stackTagCompound.getBoolean(TAG_MODE_OMNI);
        }
        return false;
    }

    default boolean isInertiaCancellingEnabled(ItemStack stack) {
        if (stack.stackTagCompound != null) {
            return stack.stackTagCompound.getBoolean(TAG_MODE_INERTIA_CANCELLING);
        }
        return false;
    }

    static ItemStack getBoots(EntityPlayer player) {
        ItemStack stack1 = player.getCurrentArmor(0);
        return isBoot(stack1) ? stack1 : null;
    }

    static boolean isBoot(ItemStack stack) {
        return stack != null && (stack.getItem() instanceof IBoots);
    }

    @Optional.Method(modid = "gtnhlib")
    @SideOnly(Side.CLIENT)
    public static void renderHUDJumpNotification() {
        Minecraft mc = Minecraft.getMinecraft();
        String text = getModeText(
                "thaumicboots.jumpEffect",
                getBoots(mc.thePlayer).stackTagCompound.getDouble(TAG_MODE_JUMP) * 100);
        GTNHLib.proxy.printMessageAboveHotbar(text, 60, true, true);
    }

    @Optional.Method(modid = "gtnhlib")
    @SideOnly(Side.CLIENT)
    public static void renderHUDSpeedNotification() {
        Minecraft mc = Minecraft.getMinecraft();
        String text = getModeText(
                "thaumicboots.speedEffect",
                getBoots(mc.thePlayer).stackTagCompound.getDouble(TAG_MODE_SPEED) * 100);
        GTNHLib.proxy.printMessageAboveHotbar(text, 60, true, true);
    }

    @Optional.Method(modid = "gtnhlib")
    @SideOnly(Side.CLIENT)
    public static void renderHUDOmniNotification() {
        Minecraft mc = Minecraft.getMinecraft();
        String result = "thaumicboots.omniState" + getBoots(mc.thePlayer).stackTagCompound.getBoolean(TAG_MODE_OMNI);
        String midResult, finalResult;
        if (getBoots(mc.thePlayer).stackTagCompound.getBoolean(TAG_MODE_OMNI)) {
            midResult = EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocal(result);
        } else {
            midResult = EnumChatFormatting.DARK_RED + StatCollector.translateToLocal(result);
        }
        finalResult = EnumChatFormatting.GOLD + StatCollector.translateToLocal("thaumicboots.omniEffect")
                + " "
                + midResult;
        GTNHLib.proxy.printMessageAboveHotbar(finalResult, 60, true, true);
    }

    @Optional.Method(modid = "gtnhlib")
    @SideOnly(Side.CLIENT)
    public static void renderHUDInertiaCancellingNotification(boolean serverConfigValue) {
        String finalResult;
        if (serverConfigValue) {
            Minecraft mc = Minecraft.getMinecraft();
            String result = "thaumicboots.inertiaCancellingState"
                    + getBoots(mc.thePlayer).stackTagCompound.getBoolean(TAG_MODE_INERTIA_CANCELLING);
            String midResult;
            if (getBoots(mc.thePlayer).stackTagCompound.getBoolean(TAG_MODE_INERTIA_CANCELLING)) {
                midResult = EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocal(result);
            } else {
                midResult = EnumChatFormatting.DARK_RED + StatCollector.translateToLocal(result);
            }
            finalResult = EnumChatFormatting.GOLD
                    + StatCollector.translateToLocal("thaumicboots.inertiaCancellingEffect")
                    + " "
                    + midResult;
        } else {
            finalResult = EnumChatFormatting.DARK_RED
                    + StatCollector.translateToLocal("thaumicboots.inertiaCancellingConfigDisabledMessage");
        }
        GTNHLib.proxy.printMessageAboveHotbar(finalResult, 60, true, true);
    }

    @Optional.Method(modid = "gtnhlib")
    public static String getModeText(String effect, double val) {
        String endResult = (int) val + "%";
        String result = switch ((int) val) {
            case 0 -> EnumChatFormatting.DARK_RED + StatCollector.translateToLocal(endResult);
            case 25 -> EnumChatFormatting.RED + StatCollector.translateToLocal(endResult);
            case 50 -> EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocal(endResult);
            case 75 -> EnumChatFormatting.GREEN + StatCollector.translateToLocal(endResult);
            case 100 -> EnumChatFormatting.AQUA + StatCollector.translateToLocal(endResult);
            default -> EnumChatFormatting.DARK_GRAY + StatCollector.translateToLocal(endResult);
        };
        return EnumChatFormatting.GOLD + StatCollector.translateToLocal(effect) + " " + result;
    }
}
