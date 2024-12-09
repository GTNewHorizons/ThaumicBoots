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

public interface IBoots {

    String TAG_MODE_JUMP = "jump";
    String TAG_MODE_SPEED = "speed";
    String TAG_MODE_OMNI = "omni";
    /** Cancels out creative flight inertia if true, leaves flight inertia as-is if false */
    String TAG_MODE_INERTIA = "inertiacanceling";

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

    default void setIsInertiaCanceling(ItemStack stack, boolean enabled) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.stackTagCompound.setBoolean(TAG_MODE_INERTIA, enabled);
    }

    default double changeSpeed(ItemStack stack, double modifier) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        // Internally MC returns 0 by default if the tag is not present, we do not need a presence check.
        double oldSpeed = stack.stackTagCompound.getDouble(TAG_MODE_SPEED);
        double newSpeed = ((Math.round(20D * (oldSpeed + modifier)))) / 20D;
        if (newSpeed > 1.05D) {
            newSpeed = 0;
        } else if (newSpeed > 1) {
            newSpeed = 1;
        }
        stack.stackTagCompound.setDouble(TAG_MODE_SPEED, newSpeed);
        return newSpeed;
    }

    default double changeJump(ItemStack stack, double modifier) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        // Internally MC returns 0 by default if the tag is not present, we do not need a presence check.
        double oldJump = stack.stackTagCompound.getDouble(TAG_MODE_JUMP);
        double newJump = ((Math.round(20D * (oldJump + modifier)))) / 20D;
        if (newJump > 1.05D) {
            newJump = 0;
        } else if (newJump > 1) {
            newJump = 1;
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

    default boolean changeIsInertiaCanceled(ItemStack stack) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        // Internally MC returns false by default if the tag is not present, we do not need a presence check.
        boolean inertiaCanceling = !(stack.stackTagCompound.getBoolean(TAG_MODE_INERTIA));
        stack.stackTagCompound.setBoolean(TAG_MODE_INERTIA, inertiaCanceling);
        return inertiaCanceling;
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

    default boolean isInertiaCanceled(ItemStack stack) {
        if (stack.stackTagCompound != null) {
            return stack.stackTagCompound.getBoolean(TAG_MODE_INERTIA);
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
        String result = "thaumicboots.activeState" + getBoots(mc.thePlayer).stackTagCompound.getBoolean(TAG_MODE_OMNI);
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
    public static void renderHUDInertiaNotification(boolean serverConfigValue) {
        String finalResult;
        if (serverConfigValue) {
            Minecraft mc = Minecraft.getMinecraft();
            String result = "thaumicboots.activeState"
                    + getBoots(mc.thePlayer).stackTagCompound.getBoolean(TAG_MODE_INERTIA);
            String midResult;
            if (getBoots(mc.thePlayer).stackTagCompound.getBoolean(TAG_MODE_INERTIA)) {
                midResult = EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocal(result);
            } else {
                midResult = EnumChatFormatting.DARK_RED + StatCollector.translateToLocal(result);
            }
            finalResult = EnumChatFormatting.GOLD
                    + StatCollector.translateToLocal("thaumicboots.inertiaCancelingEffect")
                    + " "
                    + midResult;
        } else {
            finalResult = EnumChatFormatting.DARK_RED
                    + StatCollector.translateToLocal("thaumicboots.inertiaCancelingConfigDisabledMessage");
        }
        GTNHLib.proxy.printMessageAboveHotbar(finalResult, 60, true, true);
    }

    @Optional.Method(modid = "gtnhlib")
    public static String getModeText(String effect, double val) {
        String endResult = (int) val + "%";
        String result = "";
        String result = "";
        switch ((int) Math.floor(val / 25.0D)) {
            case 0:
                result = EnumChatFormatting.DARK_RED + StatCollector.translateToLocal(endResult);
                break;
            case 1:
                result = EnumChatFormatting.RED + StatCollector.translateToLocal(endResult);
                break;
            case 2:
                result = EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocal(endResult);
                break;
            case 3:
                result = EnumChatFormatting.GREEN + StatCollector.translateToLocal(endResult);
                break;
            case 4:
                result = EnumChatFormatting.AQUA + StatCollector.translateToLocal(endResult);
                break;
            default:
                result = EnumChatFormatting.DARK_GRAY + StatCollector.translateToLocal(endResult);
                break;
        }
        return EnumChatFormatting.GOLD + StatCollector.translateToLocal(effect) + " " + result;
    }
}
