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
    }

    public double getJumpModifier() {
        return jumpBonus;
    }

    public void toggleJump() {}

    public boolean getJumpToggle() {
        return false;
    }

    public float getSpeedModifier() {
        return runBonus;
    }

    public void toggleSpeed() {}

    public boolean getSpeedToggle() {
        return false;
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

        float bonus = runBonus;
        stepHeight(player);
        if (steadyBonus) {
            runningTicks(player);
            bonus = computeBonus(itemStack, player);
        }

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

}
