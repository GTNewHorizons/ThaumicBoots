package thaumicboots.api;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.gtnewhorizon.gtnhlib.GTNHLib;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.items.armor.Hover;

public class ItemBoots extends ItemArmor
        implements ITBootJumpable, ITBootSpeed, IVisDiscountGear, IRunicArmor, IRepairable {

    public IIcon icon;

    public float runBonus;
    public float longrunningbonus;
    public int visDiscount;
    public int runicCharge;
    public int tier;
    public double damageAbsorptionRatio;
    public double baseAbsorptionRatio;
    public boolean steadyBonus;
    public boolean negateFall;
    public boolean waterEffects;
    public String iconResPath;
    public String armorResPath;
    public String unlocalisedName;
    public EnumRarity rarity;

    public double jumpBonus;
    public double jumpToggle = 0.0;

    public double speedToggle;

    public static final String TAG_MODE_JUMP = "jump";
    public static final String TAG_MODE_SPEED = "speed";

    public ItemBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
        setBootsData();
    }

    protected void setBootsData() {
        runicCharge = 0;
        visDiscount = 0;
        baseAbsorptionRatio = 0.15D;
        damageAbsorptionRatio = 0.0D;
        runBonus = 0.165F;
        jumpBonus = 0.0D;
        tier = 0;
        steadyBonus = false;
        negateFall = false;
        waterEffects = false;
        longrunningbonus = 0.0F;
        iconResPath = "thaumicboots:electricVoid_16x";
        armorResPath = "thaumicboots:model/electricbootsVoidwalker.png";
        unlocalisedName = "ItemElectricVoid";
        rarity = EnumRarity.rare;
        jumpToggle = 0;
        speedToggle = 0;
    }

    public double getJumpModifier() {
        return jumpBonus;
    }

    @SideOnly(Side.CLIENT)
    public static double changeJump(double prevJump) {
        double newJump = prevJump + 0.25;
        if (newJump > 1.1) {
            newJump = 0;
        }
        return newJump;
    }

    public static double isJumpEnabled(final ItemStack stack) {
        return stack.stackTagCompound.getDouble(TAG_MODE_JUMP);
    }

    public static void setModeJump(ItemStack stack, double state) {
        stack.stackTagCompound.setDouble(TAG_MODE_JUMP, state);
    }

    public float getSpeedModifier() {
        return runBonus;
    }

    @SideOnly(Side.CLIENT)
    public static double changeSpeed(double prevSpeed) {
        double newSpeed = prevSpeed + 0.25;
        if (newSpeed > 1.1) {
            newSpeed = 0;
        }
        return newSpeed;
    }

    public static double isSpeedEnabled(final ItemStack stack) {
        return stack.stackTagCompound.getDouble(TAG_MODE_SPEED);
    }

    public static void setModeSpeed(ItemStack stack, double state) {
        stack.stackTagCompound.setDouble(TAG_MODE_SPEED, state);
    }

    // TODO: the part not from interfaces

    @SideOnly(Side.CLIENT) // this method is important, it's what initializes the texture
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    @SideOnly(Side.CLIENT)

    public void registerIcons(IIconRegister ir) {
        this.icon = ir.registerIcon(iconResPath);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        if (visDiscount > 0) {
            list.add(
                    EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount")
                            + ": "
                            + visDiscount
                            + "%");
        }
    }

    @Override
    public String getArmorTexture(final ItemStack stack, final Entity entity, final int slot, final String type) {
        return armorResPath;
    }

    @Override
    public EnumRarity getRarity(final ItemStack stack) {
        return rarity = EnumRarity.rare;
    }

    public int getRunicCharge(ItemStack arg0) {
        return runicCharge;
    }

    public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
        return visDiscount;
    }

    public double getDamageAbsorptionRatio() {
        return damageAbsorptionRatio;
    }

    protected double getBaseAbsorptionRatio() {
        return baseAbsorptionRatio;
    }

    public int getTier(ItemStack itemStack) {
        return tier;
    }

    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.isItemEqual(new ItemStack(Items.leather))
                || super.getIsRepairable(par1ItemStack, par2ItemStack);
    }

    protected float computeBonus(ItemStack itemStack, EntityPlayer player) {
        int ticks = player.inventory.armorItemInSlot(0).stackTagCompound.getInteger("runTicks");
        float bonus = runBonus + ((ticks / 5) * longrunningbonus);
        return bonus;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (player.moveForward <= 0F) {
            return;
        }

        float bonus = getSpeedModifier();
        stepHeight(player);
        if (steadyBonus) {
            runningTicks(player);
            bonus = computeBonus(itemStack, player);
        }

        bonus *= itemStack.stackTagCompound.getDouble(TAG_MODE_SPEED);
        applyBonus(player, bonus);

        if (negateFall) {
            if (player.fallDistance > 0.0F) {
                player.fallDistance = 0.0F;
            }
        }
    }

    public void stepHeight(EntityPlayer player) {
        if (player.worldObj.isRemote) {
            if (!Thaumcraft.instance.entityEventHandler.prevStep.containsKey(Integer.valueOf(player.getEntityId()))) {
                Thaumcraft.instance.entityEventHandler.prevStep
                        .put(Integer.valueOf(player.getEntityId()), Float.valueOf(player.stepHeight));
            }
            player.stepHeight = 1.0F;
        }
    }

    public void runningTicks(EntityPlayer player) {
        if (!player.inventory.armorItemInSlot(0).hasTagCompound()) {
            NBTTagCompound par1NBTTagCompound = new NBTTagCompound();
            player.inventory.armorItemInSlot(0).setTagCompound(par1NBTTagCompound);
            player.inventory.armorItemInSlot(0).stackTagCompound.setInteger("runTicks", 0);
        }
    }

    public void applyBonus(EntityPlayer player, float bonus) {
        if (waterEffects) {
            if (player.isInWater()) {
                bonus /= 4.0F;
            }
        }
        if (player.onGround || player.isOnLadder() || player.capabilities.isFlying) {
            player.moveFlying(0.0F, 1.0F, bonus);
        } else if (Hover.getHover(player.getEntityId())) {
            player.jumpMovementFactor = 0.03F;
        } else {
            player.jumpMovementFactor = 0.05F;
        }
    }

    // taken from Vazkii
    public static ItemStack getBoots(EntityPlayer player) {
        ItemStack stack1 = player.getCurrentArmor(0);
        return isBoot(stack1) ? stack1 : null;
    }

    private static boolean isBoot(ItemStack stack) {
        return stack != null && (stack.getItem() instanceof ItemBoots);
    }

    @Optional.Method(modid = "gtnhlib")
    public static String getJumpModeText(ItemStack stack) {
        double val = stack.stackTagCompound.getDouble(TAG_MODE_JUMP) * 100;
        String endResult = String.valueOf((int) val) + "%";
        return EnumChatFormatting.GOLD + StatCollector.translateToLocal("thaumicboots.jumpEffect")
                + " "
                + (isJumpEnabled(stack) > 0 ? EnumChatFormatting.GREEN + StatCollector.translateToLocal(endResult)
                        : EnumChatFormatting.RED + StatCollector.translateToLocal(endResult));
    }

    @Optional.Method(modid = "gtnhlib")
    @SideOnly(Side.CLIENT)
    public static void renderHUDJumpNotification() {
        Minecraft mc = Minecraft.getMinecraft();
        String text = getJumpModeText(getBoots(mc.thePlayer));
        GTNHLib.proxy.printMessageAboveHotbar(text, 60, true, true);
    }

    @Optional.Method(modid = "gtnhlib")
    public static String getSpeedModeText(ItemStack stack) {
        double val = stack.stackTagCompound.getDouble(TAG_MODE_SPEED) * 100;
        String endResult = String.valueOf((int) val) + "%";
        return EnumChatFormatting.GOLD + StatCollector.translateToLocal("thaumicboots.speedEffect")
                + " "
                + (isJumpEnabled(stack) > 0 ? EnumChatFormatting.GREEN + StatCollector.translateToLocal(endResult)
                        : EnumChatFormatting.RED + StatCollector.translateToLocal(endResult));
    }

    @Optional.Method(modid = "gtnhlib")
    @SideOnly(Side.CLIENT)
    public static void renderHUDSpeedNotification() {
        Minecraft mc = Minecraft.getMinecraft();
        String text = getSpeedModeText(getBoots(mc.thePlayer));
        GTNHLib.proxy.printMessageAboveHotbar(text, 60, true, true);
    }

}
