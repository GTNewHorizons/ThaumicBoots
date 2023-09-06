package thaumicboots.item.boots.meteor;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import emt.util.EMTTextHelper;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.items.armor.Hover;
import thaumicboots.api.ItemElectricBoots;
import thaumicboots.main.utils.TabThaumicBoots;

public class ItemElectricMeteorBoots extends ItemElectricBoots {

    public ItemElectricMeteorBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
    }

    protected void setBootsData() {
        maxCharge = 100_000;
        energyPerDamage = 1_000;
        visDiscount = 2;
        damageAbsorptionRatio = 1.25D;
        transferLimit = 100;
        jumpBonus = 0.275D * 1.9;
        tier = 2;
        iconResPath = "thaumicboots:electricMeteor_16x";
        armorResPath = "thaumicboots:model/electricbootsMeteor.png";
        unlocalisedName = "ItemElectricMeteor";
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (player.fallDistance > 0.0F) {
            player.fallDistance = 0.0F;
        }

        if ((!player.capabilities.isFlying) && (player.moveForward > 0.0F)) {
            int haste = EnchantmentHelper
                    .getEnchantmentLevel(Config.enchHaste.effectId, player.inventory.armorItemInSlot(0));
            if (player.worldObj.isRemote) {
                if (!Thaumcraft.instance.entityEventHandler.prevStep
                        .containsKey(Integer.valueOf(player.getEntityId()))) {
                    Thaumcraft.instance.entityEventHandler.prevStep
                            .put(Integer.valueOf(player.getEntityId()), Float.valueOf(player.stepHeight));
                }
                player.stepHeight = 1.0F;
            }
            float bonus = 0.055F;
            if (ElectricItem.manager.getCharge(itemStack) == 0) {
                bonus *= 0;
            }
            if (player.isInWater()) {
                bonus /= 4.0F;
            }
            if (player.onGround) {
                player.moveFlying(0.0F, 1.0F, bonus);
            } else if (Hover.getHover(player.getEntityId())) {
                player.jumpMovementFactor = 0.03F;
            } else {
                player.jumpMovementFactor = 0.05F;
            }
            if (player.fallDistance > 0.0F) {
                player.fallDistance = 0.0F;
            }
        }
    }





}
