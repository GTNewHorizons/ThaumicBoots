package thaumicboots.events;

import java.util.HashMap;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import emt.EMT;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.common.ConfigTX;
import flaxbeard.thaumicexploration.integration.TTIntegration;
import ic2.api.item.ElectricItem;
import thaumicboots.item.boots.comet.ItemElectricCometBoots;
import thaumicboots.item.boots.comet.ItemNanoCometBoots;
import thaumicboots.item.boots.comet.ItemQuantumCometBoots;
import thaumicboots.item.boots.meteor.ItemElectricMeteorBoots;
import thaumicboots.item.boots.meteor.ItemNanoMeteorBoots;
import thaumicboots.item.boots.meteor.ItemQuantumMeteorBoots;
import thaumicboots.item.boots.unique.ItemCometMeteorBoots;
import thaumicboots.item.boots.unique.ItemMeteoricCometBoots;
import thaumicboots.item.boots.voidwalker.*;

public class BootsEventHandler {

    public static final PlayerCapabilities genericPlayerCapabilities = new PlayerCapabilities();

    @SubscribeEvent
    public void livingTick(LivingUpdateEvent event) {
        if (!(event.entity instanceof EntityPlayer)) {
            return;
        }

        EntityPlayer player = (EntityPlayer) event.entity;
        checkAir(player);

        if (!(event.entity.worldObj.isRemote)) {
            return;
        }

        ItemStack boots = player.inventory.armorItemInSlot(0);

        if ((this.prevStep.containsKey(event.entity.getEntityId()))
                && ((boots == null) || !((boots.getItem() instanceof ItemElectricMeteorBoots)
                        || (boots.getItem() instanceof ItemElectricCometBoots)))) {
            event.entity.stepHeight = (this.prevStep.get(event.entity.getEntityId()));
            this.prevStep.remove(event.entity.getEntityId());
        }
    }

    @SubscribeEvent
    public void joinWorld(EntityJoinWorldEvent event) {
        if ((event.entity instanceof EntityPlayer)) {
            EntityPlayer player = (EntityPlayer) event.entity;
        }
    }

    HashMap<Integer, Float> prevStep = new HashMap<>();

    @SubscribeEvent
    public void playerJumps(LivingEvent.LivingJumpEvent event) {

        // if not a player
        if (!(event.entity instanceof EntityPlayer)) {
            return;
        }

        EntityPlayer player = (EntityPlayer) event.entity;

        // if no boots equipped
        if (player.inventory.armorItemInSlot(0) == null) {
            return;
        }

        Item item = player.inventory.armorItemInSlot(0).getItem();

        if ((item instanceof ItemElectricMeteorBoots) || (item instanceof ItemCometMeteorBoots)
                || (item instanceof ItemMeteoricCometBoots)
                || (item instanceof ItemMeteorVoidwalkerBoots)) {

            if (player.isSneaking()) {
                Vec3 vector = event.entityLiving.getLook(0.5F);
                double total = Math.abs(vector.zCoord + vector.xCoord);
                double jump = 0;
                if (Loader.isModLoaded("ThaumicTinkerer")) {
                    jump = TTIntegration.getAscentLevel((EntityPlayer) event.entity);
                }
                if (jump >= 1) {
                    jump = (jump + 2D) / 4D;
                }

                if (vector.yCoord < total) vector.yCoord = total;

                event.entityLiving.motionY += ((jump + 1) * vector.yCoord) / 1.5F;
                event.entityLiving.motionZ += (jump + 1) * vector.zCoord * 4;
                event.entityLiving.motionX += (jump + 1) * vector.xCoord * 4;

            } else {
                // 0.275D is approx 3 blocks, 0.265D will get you to just 3 blocks,
                if (item instanceof ItemQuantumMeteorBoots) {
                    event.entityLiving.motionY += 0.275D * 4; // 12 blocks
                } else if (item instanceof ItemNanoMeteorBoots) {
                    event.entityLiving.motionY += 0.275D * 2.9; // 8 blocks
                } else if (item instanceof ItemMeteorVoidwalkerBoots) {
                    event.entityLiving.motionY += 0.275D * 3.2; // 3 blocks
                } else {
                    event.entityLiving.motionY += 0.275D * 1.9; // 5 blocks
                }
            }
        }

        else if ((item instanceof ItemElectricCometBoots) || (item instanceof ItemCometVoidwalkerBoots)) {
            // 0.55D is approx 5.5 blocks, so 0.275 is around 2.25 additional blocks
            if (item instanceof ItemNanoCometBoots) {
                event.entityLiving.motionY += 0.275D * 2.3; // 5.5 blocks
            } else if (item instanceof ItemQuantumCometBoots) {
                event.entityLiving.motionY += 0.275D * 3.3; // 12 blocks
            } else if (item instanceof ItemCometVoidwalkerBoots) {
                event.entityLiving.motionY += 0.450D; // 3.5 blocks
            } else {
                event.entityLiving.motionY += 0.275D; // 3 blocks
            }
        }

        else if ((item instanceof ItemElectricVoidwalkerBoots) || (item instanceof ItemNanoVoidwalkerBoots)
                || (item instanceof ItemQuantumVoidwalkerBoots)) {

                    if (item instanceof ItemElectricVoidwalkerBoots) {
                        event.entityLiving.motionY += 0.275D * 1.7;
                    } else if (item instanceof ItemNanoVoidwalkerBoots) {
                        event.entityLiving.motionY += 0.275D * 2.7;
                    } else { // ItemQuantumVoidwalkerBoots
                        event.entityLiving.motionY += 0.275D * 3.7;
                    }
                }
    }

    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event) {
        if (!EMT.instance.isSimulating()) {
            return;
        }

        // if entity isn't a player
        if (!(event.entity instanceof EntityPlayer)) {
            return;
        }

        EntityPlayer entity = (EntityPlayer) event.entity;

        // if no boots equiped
        if (entity.inventory.armorInventory[0] == null) {
            return;
        }
        ItemStack itemStack = entity.inventory.armorInventory[0];
        Item item = itemStack.getItem();

        // if the boots aren't Comet boots or its derivative
        if (!(item instanceof ItemElectricCometBoots)) {
            return;
        }

        ItemElectricCometBoots bootItem = (ItemElectricCometBoots) item;

        // nullifying the fall damages due to jump boost
        if (event.distance <= bootItem.getMinimumHeight()) {
            event.setCanceled(true);
            return;
        }

        double energyDemand = bootItem.getPowerConsumption(event.distance);

        // if boots can be discharged nullifying the fall damages
        if (energyDemand <= ElectricItem.manager.getCharge(itemStack)) {
            ElectricItem.manager.discharge(itemStack, energyDemand, Integer.MAX_VALUE, true, false, false);
            event.setCanceled(true);
        }
    }

    public void grief(EntityPlayer player) {
        // anti-griefing config
        if (!ConfigTX.allowBootsIce) {
            return;
        }
        for (int x = -5; x < 6; x++) {
            for (int z = -5; z < 6; z++) {
                int X = (int) (player.posX + x);
                int Y = (int) (player.posY - 1);
                int Z = (int) (player.posZ + z);

                // if the block isn't water
                if (player.worldObj.getBlock(X, Y, Z) != Blocks.water) {
                    continue;
                }

                // if the block hasn't some water properties
                if (player.worldObj.getBlock(X, Y, Z).getMaterial() != Material.water) {
                    continue;
                }

                // if the metadata of the block isn't 0
                if (player.worldObj.getBlockMetadata(X, Y, Z) != 0) {
                    continue;
                }

                // if the player is in water
                if (player.isInWater()) {
                    continue;
                }

                // ???
                if ((Math.abs(x) + Math.abs(z) >= 8)) {
                    continue;
                }

                player.worldObj.setBlock(X, Y, Z, ThaumicExploration.meltyIce);
                player.worldObj.spawnParticle("snowballpoof", X, Y + 1, Z, 0.0D, 0.025D, 0.0D);
            }
        }
    }

    // TODO boot effect
    public void checkAir(EntityPlayer player) {

        // no item in boots slot
        if (player.inventory.armorItemInSlot(0) == null) {
            return;
        }

        Item item = player.inventory.armorItemInSlot(0).getItem();

        // meteor boots
        if ((item instanceof ItemElectricMeteorBoots) || (item instanceof ItemMeteorVoidwalkerBoots)) {
            ItemStack itemStack = player.inventory.armorItemInSlot(0);
            if (!itemStack.hasTagCompound()) {
                NBTTagCompound par1NBTTagCompound = new NBTTagCompound();
                itemStack.setTagCompound(par1NBTTagCompound);
                itemStack.stackTagCompound.setBoolean("IsSmashing", false);
                itemStack.stackTagCompound.setInteger("smashTicks", 0);
                itemStack.stackTagCompound.setInteger("airTicks", 0);
            }
            boolean smashing = itemStack.stackTagCompound.getBoolean("IsSmashing");
            int ticks = itemStack.stackTagCompound.getInteger("smashTicks");
            int ticksAir = itemStack.stackTagCompound.getInteger("airTicks");

            if (player.onGround || player.isOnLadder()) {
                int size = 0;
                if (ticks > 5) size = 1;
                if (ticks > 10) size = 2;
                if (ticks > 15) size = 3;
                smashing = false;
                ticks = 0;
                ticksAir = 0;
                if (size > 0) {
                    player.worldObj.createExplosion(player, player.posX, player.posY, player.posZ, size, false);
                }
            }

            // COME ON AND SLAM
            if (!player.onGround && !player.isOnLadder() && !player.isInWater()) {
                if (!player.isSneaking()) {
                    ticksAir++;
                }

                if (player.isSneaking() && ticksAir > 5) {
                    smashing = true;
                }
            }

            if (player.capabilities.isFlying) {
                smashing = false;
                ticks = 0;
                ticksAir = 0;
            }
            if (smashing) {
                for (String particleName : new String[] { "flame", "smoke", "flame" }) {
                    player.worldObj.spawnParticle(
                            particleName,
                            player.posX + Math.random() - 0.5F,
                            player.posY + Math.random() - 0.5F,
                            player.posZ + Math.random() - 0.5F,
                            0.0D,
                            0.0D,
                            0.0D);
                }

                player.motionY -= 0.1F;
                ticks++;
            } else {
                double motion = Math.abs(player.motionX) + Math.abs(player.motionZ) + Math.abs(0.5 * player.motionY);
                if (!player.isWet() && motion > 0.1F) {
                    player.worldObj.spawnParticle(
                            "flame",
                            player.posX + Math.random() - 0.5F,
                            player.boundingBox.minY + 0.25F + ((Math.random() - 0.5) * 0.25F),
                            player.posZ + Math.random() - 0.5F,
                            0.0D,
                            0.025D,
                            0.0D);
                }
            }

            itemStack.stackTagCompound.setBoolean("IsSmashing", smashing);
            itemStack.stackTagCompound.setInteger("smashTicks", ticks);
            itemStack.stackTagCompound.setInteger("airTicks", ticksAir);
        }

        // comet boots
        else if ((item instanceof ItemElectricCometBoots) || (item instanceof ItemCometVoidwalkerBoots)) {
            ItemStack itemStack = player.inventory.armorItemInSlot(0);
            if (!itemStack.hasTagCompound()) {
                NBTTagCompound par1NBTTagCompound = new NBTTagCompound();
                itemStack.setTagCompound(par1NBTTagCompound);
                itemStack.stackTagCompound.setInteger("runTicks", 0);
            }

            grief(player);

            int ticks = itemStack.stackTagCompound.getInteger("runTicks");

            double motion = Math.abs(player.motionX) + Math.abs(player.motionZ) + Math.abs(player.motionY);
            if (motion > 0.1F || !player.onGround || player.isOnLadder()) {
                if (ticks < 100) ticks++;
            } else {
                ticks = 0;
            }

            if (!player.isWet() && motion > 0.1F) {
                player.worldObj.spawnParticle(
                        "fireworksSpark",
                        player.posX + Math.random() - 0.5F,
                        player.boundingBox.minY + 0.25F + ((Math.random() - 0.5) * 0.25F),
                        player.posZ + Math.random() - 0.5F,
                        0.0D,
                        0.025D,
                        0.0D);
            }

            itemStack.stackTagCompound.setInteger("runTicks", ticks);
        }

        else if ((item instanceof ItemMeteoricCometBoots || (item instanceof ItemCometMeteorBoots))) {
            ItemStack itemStack = player.inventory.armorItemInSlot(0);
            if (!itemStack.hasTagCompound()) {
                NBTTagCompound par1NBTTagCompound = new NBTTagCompound();
                itemStack.setTagCompound(par1NBTTagCompound);
                itemStack.stackTagCompound.setBoolean("IsSmashingMix", false);
                itemStack.stackTagCompound.setInteger("smashTicksMix", 0);
                itemStack.stackTagCompound.setInteger("airTicksMix", 0);
                itemStack.stackTagCompound.setInteger("runTicksMix", 0);
            }
            grief(player);

            boolean smashing = itemStack.stackTagCompound.getBoolean("IsSmashingMix");
            int ticksSmash = itemStack.stackTagCompound.getInteger("smashTicksMix");
            int ticksAir = itemStack.stackTagCompound.getInteger("airTicksMix");
            int ticksRun = itemStack.stackTagCompound.getInteger("runTicksMix");
            double motionRun = Math.abs(player.motionX) + Math.abs(player.motionZ) + Math.abs(player.motionY);
            if (motionRun > 0.1F || !player.onGround || player.isOnLadder()) {
                if (ticksRun < 100) ticksRun++;
            } else {
                ticksRun = 0;
            }
            if (player.onGround || player.isOnLadder()) {
                int size = 0;
                if (ticksSmash > 5) size = 1;
                if (ticksSmash > 10) size = 2;
                if (ticksSmash > 15) size = 3;
                smashing = false;
                ticksSmash = 0;
                ticksAir = 0;
                if (size > 0) {
                    player.worldObj.createExplosion(player, player.posX, player.posY, player.posZ, size, false);
                }
            }

            // COME ON AND SLAM
            if (!player.onGround && !player.isOnLadder() && !player.isInWater()) {
                if (!player.isSneaking()) {
                    ticksAir++;
                }

                if (player.isSneaking() && ticksAir > 5) {
                    smashing = true;
                }
            }

            if (player.capabilities.isFlying) {
                smashing = false;
                ticksSmash = 0;
                ticksAir = 0;
            }
            if (smashing) {

                for (String particleName : new String[] { "flame", "smoke", "flame" }) {
                    player.worldObj.spawnParticle(
                            particleName,
                            player.posX + Math.random() - 0.5F,
                            player.posY + Math.random() - 0.5F,
                            player.posZ + Math.random() - 0.5F,
                            0.0D,
                            0.0D,
                            0.0D);
                }

                player.motionY -= 0.1F;
                ticksSmash++;
            }

            if (!player.isWet() && motionRun > 0.1F) {
                for (String particleName : new String[] { "fireworksSpark", "flame", "flame" }) {
                    player.worldObj.spawnParticle(
                            particleName,
                            player.posX + Math.random() - 0.5F,
                            player.boundingBox.minY + 0.25F + ((Math.random() - 0.5) * 0.25F),
                            player.posZ + Math.random() - 0.5F,
                            0.0D,
                            0.025D,
                            0.0D);
                }
            }

            itemStack.stackTagCompound.setInteger("runTicksMix", ticksRun);
            itemStack.stackTagCompound.setBoolean("IsSmashingMix", smashing);
            itemStack.stackTagCompound.setInteger("smashTicksMix", ticksSmash);
            itemStack.stackTagCompound.setInteger("airTicksMix", ticksAir);
        }
    }
}
