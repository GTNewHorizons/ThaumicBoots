package thaumicboots.item.boots.unique;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.items.armor.Hover;
import thaumicboots.main.utils.TabThaumicBoots;

public class ItemMeteoricCometBoots extends ItemCometMeteorBoots {

    public IIcon icon;

    public ItemMeteoricCometBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
    }

    protected void setBootsData() {
        damageAbsorptionRatio = 1.5D;
        jumpBonus = 0.35D; // needs to be executed, or it'll stay at 0
        tier = 2;
        iconResPath = "thaumicboots:bootsMeteorComet";
        armorResPath = "thaumicboots:model/VoidwalkerBootsMeteor.png";
        unlocalisedName = "ItemMeteoricComet";
    }

}
