package thaumicboots.api;

import java.util.List;

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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.items.armor.Hover;

public class ItemBoots extends ItemArmor
        implements ITBootJumpable, ITBootSpeed, IVisDiscountGear, IRunicArmor, IRepairable, IBoots {

    public IIcon icon;

    public float runBonus;
    public float longrunningbonus;
    public int visDiscount;
    public int runicCharge;
    public int tier;
    public boolean steadyBonus;
    public boolean negateFall;
    public boolean waterEffects;
    public String iconResPath;
    public String armorResPath;
    public String unlocalisedName;

    public double jumpBonus;
    public boolean omniMovement;

    public ItemBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
        setBootsData();
    }

    protected void setBootsData() {
        runicCharge = 0;
        visDiscount = 0;
        runBonus = 0.165F;
        jumpBonus = 0.0D;
        omniMovement = false;
        tier = 0;
        steadyBonus = false; // this is the toggle for the longrunningbonus.
        negateFall = true; // certain boots don't have fall damage in base.
        waterEffects = false; // certain boots aren't hindered by being in the water.
        longrunningbonus = 0.0F; // this is only for the comet boots, though it could be used for other boots.
        iconResPath = "thaumicboots:electricVoid_16x";
        armorResPath = "thaumicboots:model/electricbootsVoidwalker.png";
        unlocalisedName = "ItemElectricVoid";
    }

    public double getJumpModifier() {
        return jumpBonus;

    }

    public float getSpeedModifier() {
        return runBonus;
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
        return EnumRarity.rare;
    }

    public int getRunicCharge(ItemStack arg0) {
        return runicCharge;
    }

    public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect) {
        return visDiscount;
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
        return runBonus + ((ticks * 0.2F) * longrunningbonus);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (negateFall && player.fallDistance > 0.0F) {
            player.fallDistance = 0.0F;
        }
        boolean omniMode = isOmniEnabled(itemStack);
        if ((player.moveForward == 0F && player.moveStrafing == 0F && omniMode)
                || (player.moveForward <= 0F && !omniMode)) {
            return;
        }

        float bonus = getSpeedModifier();
        stepHeight(player);
        if (steadyBonus) {
            runningTicks(player);
            bonus = computeBonus(itemStack, player);
        }

        applyFinalBonus(bonus, player, itemStack);
    }

    public void applyFinalBonus(float bonus, EntityPlayer player, ItemStack itemStack) {
        bonus *= isSpeedEnabled(itemStack);
        applyBonus(player, bonus, itemStack);
    }

    public void stepHeight(EntityPlayer player) {
        if (!player.worldObj.isRemote) {
            return;
        }
        if (!Thaumcraft.instance.entityEventHandler.prevStep.containsKey(Integer.valueOf(player.getEntityId()))) {
            Thaumcraft.instance.entityEventHandler.prevStep
                    .put(Integer.valueOf(player.getEntityId()), Float.valueOf(player.stepHeight));
        }
        player.stepHeight = 1.0F;
    }

    public void runningTicks(EntityPlayer player) {
        if (!player.inventory.armorItemInSlot(0).hasTagCompound()) {
            NBTTagCompound par1NBTTagCompound = new NBTTagCompound();
            player.inventory.armorItemInSlot(0).setTagCompound(par1NBTTagCompound);
            player.inventory.armorItemInSlot(0).stackTagCompound.setInteger("runTicks", 0);
        }
    }

    public void applyBonus(EntityPlayer player, float bonus, ItemStack itemStack) {
        if (waterEffects && player.isInWater()) {
            bonus *= 0.25F;
        }
        if (player.onGround || player.isOnLadder() || player.capabilities.isFlying) {
            if (player.moveForward != 0.0) {
                player.moveFlying(0.0F, player.moveForward, bonus);
            }
            if (player.moveStrafing != 0.0 && itemStack.stackTagCompound.getBoolean(TAG_MODE_OMNI)) {
                player.moveFlying(player.moveStrafing, 0.0F, bonus);
            }
        } else if (Hover.getHover(player.getEntityId())) {
            player.jumpMovementFactor = 0.03F;
        } else {
            player.jumpMovementFactor = 0.05F;
        }
    }
}
