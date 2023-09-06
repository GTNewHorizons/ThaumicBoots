package thaumicboots.item.boots.unimplemented;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
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
import thaumicboots.api.ItemVoidBoots;
import thaumicboots.main.utils.TabThaumicBoots;

public class ItemPurpleVoidwalkerBoots extends ItemVoidBoots {

    public ItemPurpleVoidwalkerBoots(final ArmorMaterial material, final int j, final int k) {
        super(material, j, k);
    }

    protected void setBootsData() {
        visDiscount = 5;
        damageAbsorptionRatio = 1.5D;
        jumpBonus = 0.450D;
        tier = 3;
        iconResPath = "thaumicboots:purpleHaze_16x";
        armorResPath = "thaumicboots:model/VoidwalkerBootsMeteor_-_Purple.png";
        unlocalisedName = "ItemPurpleVoidwalkerBoots";
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

}
