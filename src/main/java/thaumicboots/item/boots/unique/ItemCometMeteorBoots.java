package thaumicboots.item.boots.unique;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import thaumcraft.api.IRepairable;
import thaumcraft.api.IRunicArmor;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.items.armor.Hover;
import thaumicboots.api.ItemBoots;
import thaumicboots.main.utils.TabThaumicBoots;

public class ItemCometMeteorBoots extends ItemBoots implements IRepairable, IRunicArmor {

    public IIcon icon;

    public ItemCometMeteorBoots(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
        setCreativeTab(TabThaumicBoots.tabThaumicBoots);
        setUnlocalizedName(unlocalisedName);
    }

    protected void setBootsData() {
        damageAbsorptionRatio = 1.5D;
        jumpBonus = 0.35D;
        tier = 2;
        baseBonus = 0.165F;
        iconResPath = "thaumicboots:bootsCometMeteor";
        armorResPath = "thaumicboots:model/VoidwalkerBootsComet.png";
        unlocalisedName = "ItemCometMeteor";
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

            float bonus = 0.165F;
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
            if (!player.inventory.armorItemInSlot(0).hasTagCompound()) {
                NBTTagCompound par1NBTTagCompound = new NBTTagCompound();
                player.inventory.armorItemInSlot(0).setTagCompound(par1NBTTagCompound);
                player.inventory.armorItemInSlot(0).stackTagCompound.setInteger("runTicks", 0);
            }
            int ticks = player.inventory.armorItemInSlot(0).stackTagCompound.getInteger("runTicks");

        }
    }

}
