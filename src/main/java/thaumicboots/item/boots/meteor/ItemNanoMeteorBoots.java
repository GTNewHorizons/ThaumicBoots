package thaumicboots.item.boots.meteor;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import emt.util.EMTConfigHandler;
import emt.util.EMTTextHelper;
import ic2.api.item.ElectricItem;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.items.armor.Hover;
import thaumicboots.main.utils.TabThaumicBoots;

public class ItemNanoMeteorBoots extends ItemElectricMeteorBoots {

    public ItemNanoMeteorBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
    }

    protected void setBootsData() {
        maxCharge = 1_000_000;
        energyPerDamage = 5_000;
        visDiscount = 4;
        damageAbsorptionRatio = 1.5D;
        transferLimit = 1600;
        jumpBonus = 0.275D * 2.9;
        tier = 3;
        iconResPath = "thaumicboots:nanoMeteor_16x";
        armorResPath = "thaumicboots:model/nanobootsMeteor.png";
        unlocalisedName = "ItemNanoMeteor";
    }
}
