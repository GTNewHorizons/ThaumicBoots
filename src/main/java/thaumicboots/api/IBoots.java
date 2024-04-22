package thaumicboots.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import thaumicboots.main.Config;

public interface IBoots {

    String TAG_MODE_JUMP = "jump";
    String TAG_MODE_SPEED = "speed";
    String TAG_MOD_OMNI = "omni";

    default void setSpeedModifier(ItemStack stack, double modifier) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.stackTagCompound.setDouble(TAG_MODE_SPEED, modifier);
    }

    default void setJumpModifier(ItemStack stack, double modifier) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.stackTagCompound.setDouble(TAG_MODE_JUMP, modifier);
    }

    default void setOmniEnabled(ItemStack stack, boolean enabled) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        stack.stackTagCompound.setBoolean(TAG_MOD_OMNI, enabled);
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

    default boolean toggleOmni(ItemStack stack) {
        if (stack.stackTagCompound == null) {
            stack.setTagCompound(new NBTTagCompound());
        }
        // Internally MC returns false by default if the tag is not present, we do not need a presence check.
        boolean omni = stack.stackTagCompound.getBoolean(TAG_MOD_OMNI);
        omni = !omni;
        stack.stackTagCompound.setBoolean(TAG_MOD_OMNI, omni);
        return omni;
    }

    default double getSpeedModifier(ItemStack stack) {
        if (stack.stackTagCompound != null) {
            return stack.stackTagCompound.getDouble(TAG_MODE_SPEED);
        }
        return 0;
    }

    default double getJumpModifier(ItemStack stack) {
        if (stack.stackTagCompound != null) {
            return stack.stackTagCompound.getDouble(TAG_MODE_JUMP);
        }
        return 0;
    }

    default boolean getOmniEnabled(ItemStack stack) {
        if (stack.stackTagCompound != null) {
            return stack.stackTagCompound.getBoolean(TAG_MOD_OMNI);
        }
        return false;
    }

    static ItemStack getBoots(EntityPlayer player) {
        return player.getCurrentArmor(0);
    }
}
