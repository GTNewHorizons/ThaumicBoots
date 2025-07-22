package thaumicboots.item.boots.voidwalker;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.hazards.Hazard;
import gregtech.api.hazards.IHazardProtector;
import taintedmagic.api.IVoidwalker;
import thaumcraft.api.IWarpingGear;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXWispEG;
import thaumicboots.api.ItemElectricBoots;
import thaumicboots.main.utils.TabThaumicBoots;

@Optional.InterfaceList({ @Optional.Interface(iface = "taintedmagic.api.IVoidwalker", modid = "TaintedMagic"),
        @Optional.Interface(iface = "gregtech.api.hazards.IHazardProtector", modid = "gregtech_nh") })
public class ItemElectricVoidwalkerBoots extends ItemElectricBoots
        implements IVoidwalker, IWarpingGear, ISpecialArmor, IHazardProtector {

    public ItemElectricVoidwalkerBoots(final ArmorMaterial material, final int j, final int k) {
        super(material, j, k);
        setCreativeTab(TabThaumicBoots.tabThaumicBoots);
        setUnlocalizedName(unlocalisedName);
        setBootsData();
    }

    protected void setBootsData() {
        super.setBootsData();
        maxCharge = 100_000;
        energyPerDamage = 100; // allows for 2k hits 2x more than base electric (for this mod)
        runicCharge = 0;
        visDiscount = 5 + 2; // voidwalker + electric discount
        provideEnergy = false;
        damageAbsorptionRatio = 2.25D;
        transferLimit = 400;
        jumpBonus = 0.4675D; // 4.5 blocks
        speedBonus = 0.200F;
        negateFall = true;

        tier = 3;
        iconResPath = "thaumicboots:electricVoid_16x";
        armorResPath = "thaumicboots:model/electricbootsVoidwalker.png";
        unlocalisedName = "ItemElectricVoid";
    }

    @Override
    public int getWarp(final ItemStack stack, final EntityPlayer player) {
        return 5;
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

    // necessary for void + electric function
    @Override
    public void onArmorTick(final World world, final EntityPlayer player, final ItemStack stack) {
        super.onArmorTick(world, player, stack);

        // particles
        final double motion = Math.abs(player.motionX) + Math.abs(player.motionZ) + Math.abs(0.5 * player.motionY);
        if (world.isRemote && (motion > 0.1D || !player.onGround) && world.rand.nextInt(3) == 0) {
            particles(world, player);
        }
    }

    @Override
    public EnumRarity getRarity(final ItemStack stack) {
        return EnumRarity.epic;
    }

    @Override
    @Optional.Method(modid = "gregtech_nh")
    public boolean protectsAgainst(ItemStack itemStack, Hazard hazard) {
        return true;
    }
}
