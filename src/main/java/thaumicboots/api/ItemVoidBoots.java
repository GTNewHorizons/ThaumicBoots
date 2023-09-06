package thaumicboots.api;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import taintedmagic.common.registry.ItemRegistry;
import thaumcraft.api.IWarpingGear;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXWispEG;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.items.armor.Hover;
import thaumicboots.main.utils.TabThaumicBoots;

public class ItemVoidBoots extends ItemBoots implements IWarpingGear, ISpecialArmor {

    public ItemVoidBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
        setCreativeTab(TabThaumicBoots.tabThaumicBoots);
        setUnlocalizedName(unlocalisedName);
    }

    protected void setBootsData() {
        visDiscount = 5;
        damageAbsorptionRatio = 1.5D;
        jumpBonus = 0.450D;
        tier = 3;
        iconResPath = "thaumicboots:voidComet_16x";
        armorResPath = "thaumicboots:model/VoidwalkerBootsComet_-_Purple.png";
        unlocalisedName = "ItemVoidComet";
        rarity = EnumRarity.epic;
    }

    @Override
    public EnumRarity getRarity(final ItemStack stack) {
        return EnumRarity.epic;
    }

    @Override
    public int getWarp(final ItemStack stack, final EntityPlayer player) {
        return 5;
    }

    @Override
    public ArmorProperties getProperties(final EntityLivingBase entity, final ItemStack stack,
                                         final DamageSource source, final double dmg, final int slot) {
        int priority = 0;
        double ratio = damageReduceAmount / 90.0D;

        if (source.isMagicDamage() || source.isFireDamage() || source.isExplosion()) {
            priority = 1;
            ratio = damageReduceAmount / 80.0D;
        } else if (source.isUnblockable()) {
            priority = 0;
            ratio = 0.0D;
        }
        return new ArmorProperties(priority, ratio, stack.getMaxDamage() + 1 - stack.getItemDamage());
    }

    @Override
    public int getArmorDisplay(final EntityPlayer player, final ItemStack stack, final int slot) {
        return damageReduceAmount;
    }

    @Override
    public void damageArmor(final EntityLivingBase entity, final ItemStack stack, final DamageSource source,
                            final int dmg, final int slot) {
        if (source != DamageSource.fall) {
            stack.damageItem(dmg, entity);
        }
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
    public void onUpdate(final ItemStack stack, final World world, final Entity entity, final int j, final boolean k) {
        super.onUpdate(stack, world, entity, j, k);
        if (!world.isRemote && stack.isItemDamaged()
                && entity.ticksExisted % 20 == 0
                && entity instanceof EntityLivingBase) {
            stack.damageItem(-1, (EntityLivingBase) entity);
        }
    }

    @Override
    public void onArmorTick(final World world, final EntityPlayer player, final ItemStack stack) {
        super.onArmorTick(world, player, stack);
        // repair
        if (!world.isRemote && stack.isItemDamaged() && player.ticksExisted % 20 == 0) {
            stack.damageItem(-1, player);
        }

        // particles
        final double motion = Math.abs(player.motionX) + Math.abs(player.motionZ) + Math.abs(0.5 * player.motionY);
        if (world.isRemote && (motion > 0.1D || !player.onGround) && world.rand.nextInt(3) == 0) {
            particles(world, player);
        }

        if (player.moveForward <= 0F) {
            return;
        }


            if (!player.isSneaking()){
                stepHeight(player);
            }

            // speed boost
            if (player.onGround || player.capabilities.isFlying || player.isOnLadder()) {
                float bonus = 0.215F * 3;
                player.moveFlying(0.0F, 1.0F, player.capabilities.isFlying ? bonus * 0.75F : bonus);
            } else if (Hover.getHover(player.getEntityId())) {
                player.jumpMovementFactor = 0.03F;
            } else {
                player.jumpMovementFactor = player.isSprinting() ? 0.045F : 0.04F;
            }

        // negate fall damage
        if (player.fallDistance > 3.0F) {
            player.fallDistance = 1.0F;
        }
    }

    // TODO: Extract this into it's own method
    public float sashEquiped(final EntityPlayer player) {
        final ItemStack sash = PlayerHandler.getPlayerBaubles(player).getStackInSlot(3);
        if(sash !=null&&sash.getItem()==ItemRegistry.ItemVoidwalkerSash) {
            return 3.0F;
        }
        return 1.0F;
    }

    @SideOnly(Side.CLIENT)
    protected void particles(final World world, final EntityPlayer player) {
        final FXWispEG fx = new FXWispEG(
                world,
                player.posX + (Math.random() - Math.random()) * 0.5D,
                player.boundingBox.minY + 0.05D + (Math.random() - Math.random()) * 0.1D,
                player.posZ + (Math.random() - Math.random()) * 0.5D,
                player);
        ParticleEngine.instance.addEffect(world, fx);
    }
}
