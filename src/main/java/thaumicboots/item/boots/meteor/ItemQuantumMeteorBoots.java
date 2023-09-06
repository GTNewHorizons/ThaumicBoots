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

public class ItemQuantumMeteorBoots extends ItemElectricMeteorBoots implements IRepairable, IRunicArmor {

    public ItemQuantumMeteorBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
    }

    protected void setBootsData() {
        maxCharge = 10_000_000;
        energyPerDamage = 20000;
        visDiscount = 5;
        damageAbsorptionRatio = 2D;
        transferLimit = 12000;
        jumpBonus = 0.275D * 4;
        tier = 4;
        iconResPath = "thaumicboots:quantumMeteor_16x";
        armorResPath = "thaumicboots:model/quantumbootsMeteor.png";
        unlocalisedName = "ItemQuantumMeteor";
    }
}
