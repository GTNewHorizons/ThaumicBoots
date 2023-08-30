package thaumicboots.item.boots.voidwalker;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;

import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import taintedmagic.common.registry.ItemRegistry;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.IWarpingGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXWispEG;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.items.armor.Hover;
import thaumicboots.main.utils.TabThaumicBoots;

public class ItemQuantumVoidwalkerBoots extends ItemArmor
        implements IVisDiscountGear, IWarpingGear, IRunicArmor, IRepairable, ISpecialArmor, IElectricItem {

    public int maxCharge = 100000000;
    public int energyPerDamage = 10000;
    public int visDiscount = 5 + 5;

    public double transferLimit = 24000;

    public ItemQuantumVoidwalkerBoots(final ArmorMaterial material, final int j, final int k) {
        super(material, j, k);
        setCreativeTab(TabThaumicBoots.tabThaumicBoots);
        setUnlocalizedName("ItemQuantumVoid");
        setTextureName("thaumicboots:quantumVoid_16x");

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List itemList) {
        ItemStack itemStack = new ItemStack(this, 1);
        if (getChargedItem(itemStack) == this) {
            ItemStack charged = new ItemStack(this, 1);
            ElectricItem.manager.charge(charged, 2147483647, 2147483647, true, false);
            itemList.add(charged);
        }
        if (getEmptyItem(itemStack) == this) {
            itemList.add(new ItemStack(this, 1, getMaxDamage()));
        }
    }

    @Override
    public String getArmorTexture(final ItemStack stack, final Entity entity, final int slot, final String type) {
        return "thaumicboots:model/quantumbootsVoidwalker.png";
    }

    @Override
    public EnumRarity getRarity(final ItemStack stack) {
        return EnumRarity.epic;
    }

    @Override
    public int getRunicCharge(final ItemStack stack) {
        return 0;
    }

    @Override
    public int getWarp(final ItemStack stack, final EntityPlayer player) {
        return 5;
    }

    @Override
    public int getVisDiscount(final ItemStack stack, final EntityPlayer player, final Aspect aspect) {
        return visDiscount;
    }

    @Override
    public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean b) {
        list.add(
                EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount")
                        + ": "
                        + getVisDiscount(stack, player, null)
                        + "%");
    }

    @Override
    public void onArmorTick(final World world, final EntityPlayer player, final ItemStack stack) {
        super.onArmorTick(world, player, stack);
        // repair
        if (!world.isRemote && stack.isItemDamaged() && player.ticksExisted % 20 == 0) {
            // stack.damageItem(-1, player);
        }

        // particles
        final double motion = Math.abs(player.motionX) + Math.abs(player.motionZ) + Math.abs(0.5 * player.motionY);
        if (world.isRemote && (motion > 0.1D || !player.onGround) && world.rand.nextInt(3) == 0) {
            particles(world, player);
        }

        if (player.moveForward > 0.0F) {
            // increased step height
            if (player.worldObj.isRemote && !player.isSneaking()) {
                if (!Thaumcraft.instance.entityEventHandler.prevStep.containsKey(player.getEntityId())) {
                    Thaumcraft.instance.entityEventHandler.prevStep.put(player.getEntityId(), player.stepHeight);
                }
                player.stepHeight = 1.0F;
            }

            // speed boost
            if (player.onGround || player.capabilities.isFlying) {
                float bonus = 1.250F;
                final ItemStack sash = PlayerHandler.getPlayerBaubles(player).getStackInSlot(3);
                if (sash != null && sash.getItem() == ItemRegistry.ItemVoidwalkerSash) {
                    bonus *= 3.0F;
                }
                if (ElectricItem.manager.getCharge(stack) == 0) {
                    bonus *= 0;
                }

                player.moveFlying(0.0F, 1.0F, player.capabilities.isFlying ? bonus * 0.75F : bonus);
            } else if (Hover.getHover(player.getEntityId())) {
                player.jumpMovementFactor = 0.03F;
            } else {
                player.jumpMovementFactor = player.isSprinting() ? 0.045F : 0.04F;
            }
        }
        // negate fall damage
        if (player.fallDistance > 3.0F) {
            player.fallDistance = 1.0F;
        }
    }

    @SubscribeEvent
    public void playerJumps(final LivingEvent.LivingJumpEvent event) {
        if (event.entity instanceof EntityPlayer) {
            final EntityPlayer player = (EntityPlayer) event.entity;
            final ItemStack boots = player.inventory.armorItemInSlot(0);
            final ItemStack sash = PlayerHandler.getPlayerBaubles(player).getStackInSlot(3);

            if (boots != null && boots.getItem() == ItemRegistry.ItemVoidwalkerBoots) {
                player.motionY *= 1.25D;
            }
            if (sash != null && sash.getItem() == ItemRegistry.ItemVoidwalkerSash) {
                player.motionY *= 1.05D;
            }
        }
    }

    @SideOnly(Side.CLIENT)
    private void particles(final World world, final EntityPlayer player) {
        final FXWispEG fx = new FXWispEG(
                world,
                player.posX + (Math.random() - Math.random()) * 0.5D,
                player.boundingBox.minY + 0.05D + (Math.random() - Math.random()) * 0.1D,
                player.posZ + (Math.random() - Math.random()) * 0.5D,
                player);
        ParticleEngine.instance.addEffect(world, fx);
    }

    @Override
    public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source,
            double damage, int slot) {
        if (source.isUnblockable()) {
            return new net.minecraftforge.common.ISpecialArmor.ArmorProperties(0, 0.0D, 0);
        } else {
            double absorptionRatio = getBaseAbsorptionRatio() * getDamageAbsorptionRatio();
            int energyPerDamage = getEnergyPerDamage();
            double damageLimit = energyPerDamage <= 0 ? 0
                    : (25 * ElectricItem.manager.getCharge(armor)) / energyPerDamage;
            return new net.minecraftforge.common.ISpecialArmor.ArmorProperties(0, absorptionRatio, (int) damageLimit);
        }
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        if (ElectricItem.manager.getCharge(armor) >= getEnergyPerDamage()) {
            return (int) Math.round(20D * getBaseAbsorptionRatio() * getDamageAbsorptionRatio());
        } else {
            return 0;
        }
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
        ElectricItem.manager.discharge(stack, damage * getEnergyPerDamage(), 0x7fffffff, true, false, false);
    }

    public double getDamageAbsorptionRatio() {
        return 3D;
    }

    private double getBaseAbsorptionRatio() {
        return 0.15D;
    }

    public int getEnergyPerDamage() {
        return energyPerDamage;
    }

    @Override
    public boolean canProvideEnergy(ItemStack itemStack) {
        return false;
    }

    @Override
    public double getMaxCharge(ItemStack itemStack) {
        return maxCharge;
    }

    @Override
    public int getTier(ItemStack itemStack) {
        return 4;
    }

    @Override
    public double getTransferLimit(ItemStack itemStack) {
        return transferLimit;
    }

    @Override
    public Item getChargedItem(ItemStack itemStack) {
        return this;
    }

    @Override
    public Item getEmptyItem(ItemStack itemStack) {
        return this;
    }
}
