package thaumicboots.events;

import java.util.HashMap;

import emt.EMT;
import ic2.api.item.ElectricItem;
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

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.common.ConfigTX;
import flaxbeard.thaumicexploration.integration.TTIntegration;
import net.minecraftforge.event.entity.living.LivingFallEvent;
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
        if ((event.entity instanceof EntityPlayer)) {
            EntityPlayer player = (EntityPlayer) event.entity;
            checkAir(player);
        }

        if ((event.entity.worldObj.isRemote) && (this.prevStep.containsKey(Integer.valueOf(event.entity.getEntityId())))
                && ((((EntityPlayer) event.entity).inventory.armorItemInSlot(0) == null)
                        || (!(((EntityPlayer) event.entity).inventory.armorItemInSlot(0)
                                .getItem() instanceof ItemElectricMeteorBoots)
                                && !(((EntityPlayer) event.entity).inventory.armorItemInSlot(0)
                                        .getItem() instanceof ItemElectricCometBoots)))) {
            event.entity.stepHeight = ((Float) this.prevStep.get(Integer.valueOf(event.entity.getEntityId())))
                    .floatValue();
            this.prevStep.remove(Integer.valueOf(event.entity.getEntityId()));
        }
    }

    @SubscribeEvent
    public void joinWorld(EntityJoinWorldEvent event) {
        if ((event.entity instanceof EntityPlayer)) {
            EntityPlayer player = (EntityPlayer) event.entity;
        }
    }

    HashMap<Integer, Float> prevStep = new HashMap();

    @SubscribeEvent
    public void playerJumps(LivingEvent.LivingJumpEvent event) {
        if (((event.entity instanceof EntityPlayer))
                && (((EntityPlayer) event.entity).inventory.armorItemInSlot(0) != null)
                && ((((EntityPlayer) event.entity).inventory.armorItemInSlot(0)
                        .getItem() instanceof ItemElectricMeteorBoots)
                        || ((((EntityPlayer) event.entity).inventory.armorItemInSlot(0)
                                .getItem() instanceof ItemCometMeteorBoots)
                                || (((EntityPlayer) event.entity).inventory.armorItemInSlot(0)
                                        .getItem() instanceof ItemMeteoricCometBoots)
                                || ((((EntityPlayer) event.entity).inventory.armorItemInSlot(0)
                                        .getItem() instanceof ItemMeteorVoidwalkerBoots))))) {
            if (((EntityPlayer) event.entity).isSneaking()) {
                Vec3 vector = event.entityLiving.getLook(0.5F);
                double total = Math.abs(vector.zCoord + vector.xCoord);
                EntityPlayer player = (EntityPlayer) event.entity;
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

                if ((((EntityPlayer) event.entity).inventory.armorItemInSlot(0)
                        .getItem() instanceof ItemNanoMeteorBoots)
                        && !(((EntityPlayer) event.entity).inventory.armorItemInSlot(0)
                                .getItem() instanceof ItemQuantumMeteorBoots)) {
                    event.entityLiving.motionY += 0.275D * 2.9; // 8 blocks
                } else if (((EntityPlayer) event.entity).inventory.armorItemInSlot(0)
                        .getItem() instanceof ItemQuantumMeteorBoots)
                    event.entityLiving.motionY += 0.275D * 4; // 12 blocks
                else if (((EntityPlayer) event.entity).inventory.armorItemInSlot(0)
                        .getItem() instanceof ItemMeteorVoidwalkerBoots) {
                            event.entityLiving.motionY += 0.275D * 3.2; // 3 blocks
                        } else
                    event.entityLiving.motionY += 0.275D * 1.9; // 5 blocks
            }
        } else if (((event.entity instanceof EntityPlayer))
                && (((EntityPlayer) event.entity).inventory.armorItemInSlot(0) != null)
                && ((((EntityPlayer) event.entity).inventory.armorItemInSlot(0)
                        .getItem() instanceof ItemElectricCometBoots)
                        || (((EntityPlayer) event.entity).inventory.armorItemInSlot(0)
                                .getItem() instanceof ItemCometVoidwalkerBoots))) {
                                    // 0.55D is approx 5.5 blocks, so 0.275 is around 2.25 additional blocks
                                    if ((((EntityPlayer) event.entity).inventory.armorItemInSlot(0)
                                            .getItem() instanceof ItemNanoCometBoots)
                                            && !(((EntityPlayer) event.entity).inventory.armorItemInSlot(0)
                                                    .getItem() instanceof ItemQuantumCometBoots)) {
                                        event.entityLiving.motionY += 0.275D * 2.3; // 5.5 blocks
                                    } else if (((EntityPlayer) event.entity).inventory.armorItemInSlot(0)
                                            .getItem() instanceof ItemQuantumCometBoots)
                                        event.entityLiving.motionY += 0.275D * 3.3; // 12 blocks
                                    else if (((EntityPlayer) event.entity).inventory.armorItemInSlot(0)
                                            .getItem() instanceof ItemCometVoidwalkerBoots) {
                                                event.entityLiving.motionY += 0.450D; // 3.5 blocks
                                            } else
                                        event.entityLiving.motionY += 0.275D; // 3 blocks
                                } else
            if (((event.entity instanceof EntityPlayer))
                    && (((EntityPlayer) event.entity).inventory.armorItemInSlot(0) != null)
                    && ((((EntityPlayer) event.entity).inventory.armorItemInSlot(0)
                            .getItem() instanceof ItemElectricVoidwalkerBoots)
                            || (((EntityPlayer) event.entity).inventory.armorItemInSlot(0)
                                    .getItem() instanceof ItemNanoVoidwalkerBoots)
                            || (((EntityPlayer) event.entity).inventory.armorItemInSlot(0)
                                    .getItem() instanceof ItemQuantumVoidwalkerBoots))) {
                                        if (((EntityPlayer) event.entity).inventory.armorItemInSlot(0)
                                                .getItem() instanceof ItemElectricVoidwalkerBoots) {
                                            event.entityLiving.motionY += 0.275D * 1.7; // 3 blocks
                                        } else if (((EntityPlayer) event.entity).inventory.armorItemInSlot(0)
                                                .getItem() instanceof ItemNanoVoidwalkerBoots) {
                                                    event.entityLiving.motionY += 0.275D * 2.7; // 3
                                                                                                // blocks
                                                } else
                                            if (((EntityPlayer) event.entity).inventory.armorItemInSlot(0)
                                                    .getItem() instanceof ItemQuantumVoidwalkerBoots) {
                                                        event.entityLiving.motionY += 0.275D * 3.7; // 3
                                                                                                    // blocks
                                                    }
                                    }
    }

    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event) {
        if (!EMT.instance.isSimulating()){
            return;
        }

        // if entity isn't a player
        if (!(event.entity instanceof EntityPlayer)){
            return;
        }

        EntityPlayer entity = (EntityPlayer) event.entity;

        // if no boots equiped
        if (entity.inventory.armorInventory[0] == null){
            return;
        }
        ItemStack itemStack = entity.inventory.armorInventory[0];
        Item item = itemStack.getItem();

        // if the boots aren't Comet boots or its derivative
        if (!(item instanceof ItemElectricCometBoots)){
            return;
        }

        ItemElectricCometBoots bootItem = (ItemElectricCometBoots) item;

        // nullifying the fall damages due to jump boost
        if (event.distance <= bootItem.getMinimumHeight()){
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


    // TODO boot effect
    public void checkAir(EntityPlayer player) {
        // meteor boots
        if ((player.inventory.armorItemInSlot(0) != null)
                && ((player.inventory.armorItemInSlot(0).getItem() instanceof ItemElectricMeteorBoots)
                        || (player.inventory.armorItemInSlot(0).getItem() instanceof ItemMeteorVoidwalkerBoots))) {
            ItemStack item = player.inventory.armorItemInSlot(0);
            if (!item.hasTagCompound()) {
                NBTTagCompound par1NBTTagCompound = new NBTTagCompound();
                item.setTagCompound(par1NBTTagCompound);
                item.stackTagCompound.setBoolean("IsSmashing", false);
                item.stackTagCompound.setInteger("smashTicks", 0);
                item.stackTagCompound.setInteger("airTicks", 0);
            }
            boolean smashing = item.stackTagCompound.getBoolean("IsSmashing");
            int ticks = item.stackTagCompound.getInteger("smashTicks");
            int ticksAir = item.stackTagCompound.getInteger("airTicks");

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

                player.worldObj.spawnParticle(
                        "flame",
                        (double) (player.posX + Math.random() - 0.5F),
                        (double) (player.posY + Math.random() - 0.5F),
                        (double) (player.posZ + Math.random() - 0.5F),
                        0.0D,
                        0.0D,
                        0.0D);
                player.worldObj.spawnParticle(
                        "smoke",
                        (double) (player.posX + Math.random() - 0.5F),
                        (double) (player.posY + Math.random() - 0.5F),
                        (double) (player.posZ + Math.random() - 0.5F),
                        0.0D,
                        0.0D,
                        0.0D);
                player.worldObj.spawnParticle(
                        "flame",
                        (double) (player.posX + Math.random() - 0.5F),
                        (double) (player.posY + Math.random() - 0.5F),
                        (double) (player.posZ + Math.random() - 0.5F),
                        0.0D,
                        0.0D,
                        0.0D);
                player.motionY -= 0.1F;
                ticks++;
            } else {
                double motion = Math.abs(player.motionX) + Math.abs(player.motionZ) + Math.abs(0.5 * player.motionY);
                if (!player.isWet() && motion > 0.1F) {
                    player.worldObj.spawnParticle(
                            "flame",
                            (double) (player.posX + Math.random() - 0.5F),
                            (double) (player.boundingBox.minY + 0.25F + ((Math.random() - 0.5) * 0.25F)),
                            (double) (player.posZ + Math.random() - 0.5F),
                            0.0D,
                            0.025D,
                            0.0D);
                    // player.worldObj.spawnParticle("flame", (double)(player.posX + Math.random()-0.5F),
                    // (double)(player.boundingBox.minY + 0.25F + ((Math.random()-0.5)*0.25F)), (double)(player.posZ +
                    // Math.random()-0.5F), 0.0D, 0.025D, 0.0D);
                }
            }

            item.stackTagCompound.setBoolean("IsSmashing", smashing);
            item.stackTagCompound.setInteger("smashTicks", ticks);
            item.stackTagCompound.setInteger("airTicks", ticksAir);
        }
        // comet boots
        else if ((player.inventory.armorItemInSlot(0) != null)
                && (((player.inventory.armorItemInSlot(0).getItem() instanceof ItemElectricCometBoots))
                        || (player.inventory.armorItemInSlot(0).getItem() instanceof ItemCometVoidwalkerBoots))) {

                            Vec3 vector = player.getLook(1.0F);
                            ItemStack item = player.inventory.armorItemInSlot(0);
                            if (!item.hasTagCompound()) {
                                NBTTagCompound par1NBTTagCompound = new NBTTagCompound();
                                item.setTagCompound(par1NBTTagCompound);
                                item.stackTagCompound.setInteger("runTicks", 0);
                            }

                            // anti-griefing config
                            if (ConfigTX.allowBootsIce) {
                                for (int x = -5; x < 6; x++) {
                                    for (int z = -5; z < 6; z++) {
                                        if ((player.worldObj.getBlock(
                                                (int) (player.posX + x),
                                                (int) (player.posY - 1),
                                                (int) (player.posZ + z)) == Blocks.water
                                                || player.worldObj.getBlock(
                                                        (int) (player.posX + x),
                                                        (int) (player.posY - 1),
                                                        (int) (player.posZ + z)) == Blocks.water)
                                                && player.worldObj.getBlock(
                                                        (int) (player.posX + x),
                                                        (int) player.posY - 1,
                                                        (int) (player.posZ + z)).getMaterial() == Material.water
                                                && player.worldObj.getBlockMetadata(
                                                        (int) (player.posX + x),
                                                        (int) player.posY - 1,
                                                        (int) (player.posZ + z)) == 0
                                                && !player.isInWater()
                                                && (Math.abs(x) + Math.abs(z) < 8)) {
                                            player.worldObj.setBlock(
                                                    (int) (player.posX + x),
                                                    (int) player.posY - 1,
                                                    (int) (player.posZ + z),
                                                    ThaumicExploration.meltyIce);
                                            player.worldObj.spawnParticle(
                                                    "snowballpoof",
                                                    (int) (player.posX + x),
                                                    (int) player.posY,
                                                    (int) (player.posZ + z),
                                                    0.0D,
                                                    0.025D,
                                                    0.0D);
                                        }
                                    }
                                }
                            }

                            int ticks = item.stackTagCompound.getInteger("runTicks");

                            double motion = Math.abs(player.motionX) + Math.abs(player.motionZ)
                                    + Math.abs(player.motionY);
                            if (motion > 0.1F || !player.onGround || player.isOnLadder()) {
                                if (ticks < 100) ticks++;
                            } else {
                                ticks = 0;
                            }

                            if (!player.isWet() && motion > 0.1F) {
                                player.worldObj.spawnParticle(
                                        "fireworksSpark",
                                        (double) (player.posX + Math.random() - 0.5F),
                                        (double) (player.boundingBox.minY + 0.25F + ((Math.random() - 0.5) * 0.25F)),
                                        (double) (player.posZ + Math.random() - 0.5F),
                                        0.0D,
                                        0.025D,
                                        0.0D);

                                // player.worldObj.spawnParticle("snowballpoof", (double)(player.posX +
                                // Math.random()-0.5F),
                                // (double)(player.boundingBox.minY + 0.25F + ((Math.random()-0.5)*0.25F)),
                                // (double)(player.posZ
                                // +
                                // Math.random()-0.5F), 0.0D, 0.025D, 0.0D);
                            }

                            item.stackTagCompound.setInteger("runTicks", ticks);
                        } else
            if ((player.inventory.armorItemInSlot(0) != null)
                    && (((player.inventory.armorItemInSlot(0).getItem() instanceof ItemMeteoricCometBoots))
                            || (player.inventory.armorItemInSlot(0).getItem() instanceof ItemCometMeteorBoots))) {
                                ItemStack item = player.inventory.armorItemInSlot(0);
                                if (!item.hasTagCompound()) {
                                    NBTTagCompound par1NBTTagCompound = new NBTTagCompound();
                                    item.setTagCompound(par1NBTTagCompound);
                                    item.stackTagCompound.setBoolean("IsSmashingMix", false);
                                    item.stackTagCompound.setInteger("smashTicksMix", 0);
                                    item.stackTagCompound.setInteger("airTicksMix", 0);
                                    item.stackTagCompound.setInteger("runTicksMix", 0);
                                }
                                // anti-griefing config
                                if (ConfigTX.allowBootsIce) {
                                    for (int x = -5; x < 6; x++) {
                                        for (int z = -5; z < 6; z++) {
                                            if ((player.worldObj.getBlock(
                                                    (int) (player.posX + x),
                                                    (int) (player.posY - 1),
                                                    (int) (player.posZ + z)) == Blocks.water
                                                    || player.worldObj.getBlock(
                                                            (int) (player.posX + x),
                                                            (int) (player.posY - 1),
                                                            (int) (player.posZ + z)) == Blocks.water)
                                                    && player.worldObj.getBlock(
                                                            (int) (player.posX + x),
                                                            (int) player.posY - 1,
                                                            (int) (player.posZ + z)).getMaterial() == Material.water
                                                    && player.worldObj.getBlockMetadata(
                                                            (int) (player.posX + x),
                                                            (int) player.posY - 1,
                                                            (int) (player.posZ + z)) == 0
                                                    && !player.isInWater()
                                                    && (Math.abs(x) + Math.abs(z) < 8)) {
                                                player.worldObj.setBlock(
                                                        (int) (player.posX + x),
                                                        (int) player.posY - 1,
                                                        (int) (player.posZ + z),
                                                        ThaumicExploration.meltyIce);
                                                player.worldObj.spawnParticle(
                                                        "snowballpoof",
                                                        (int) (player.posX + x),
                                                        (int) player.posY,
                                                        (int) (player.posZ + z),
                                                        0.0D,
                                                        0.025D,
                                                        0.0D);
                                            }
                                        }
                                    }
                                }

                                boolean smashing = item.stackTagCompound.getBoolean("IsSmashingMix");
                                int ticksSmash = item.stackTagCompound.getInteger("smashTicksMix");
                                int ticksAir = item.stackTagCompound.getInteger("airTicksMix");
                                int ticksRun = item.stackTagCompound.getInteger("runTicksMix");
                                double motionRun = Math.abs(player.motionX) + Math.abs(player.motionZ)
                                        + Math.abs(player.motionY);
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
                                        player.worldObj.createExplosion(
                                                player,
                                                player.posX,
                                                player.posY,
                                                player.posZ,
                                                size,
                                                false);
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

                                    player.worldObj.spawnParticle(
                                            "flame",
                                            (double) (player.posX + Math.random() - 0.5F),
                                            (double) (player.posY + Math.random() - 0.5F),
                                            (double) (player.posZ + Math.random() - 0.5F),
                                            0.0D,
                                            0.0D,
                                            0.0D);
                                    player.worldObj.spawnParticle(
                                            "smoke",
                                            (double) (player.posX + Math.random() - 0.5F),
                                            (double) (player.posY + Math.random() - 0.5F),
                                            (double) (player.posZ + Math.random() - 0.5F),
                                            0.0D,
                                            0.0D,
                                            0.0D);
                                    player.worldObj.spawnParticle(
                                            "flame",
                                            (double) (player.posX + Math.random() - 0.5F),
                                            (double) (player.posY + Math.random() - 0.5F),
                                            (double) (player.posZ + Math.random() - 0.5F),
                                            0.0D,
                                            0.0D,
                                            0.0D);
                                    player.motionY -= 0.1F;
                                    ticksSmash++;
                                }

                                if (!player.isWet() && motionRun > 0.1F) {
                                    player.worldObj.spawnParticle(
                                            "fireworksSpark",
                                            (double) (player.posX + Math.random() - 0.5F),
                                            (double) (player.boundingBox.minY + 0.25F
                                                    + ((Math.random() - 0.5) * 0.25F)),
                                            (double) (player.posZ + Math.random() - 0.5F),
                                            0.0D,
                                            0.025D,
                                            0.0D);
                                    player.worldObj.spawnParticle(
                                            "flame",
                                            (double) (player.posX + Math.random() - 0.5F),
                                            (double) (player.boundingBox.minY + 0.25F
                                                    + ((Math.random() - 0.5) * 0.25F)),
                                            (double) (player.posZ + Math.random() - 0.5F),
                                            0.0D,
                                            0.025D,
                                            0.0D);
                                    player.worldObj.spawnParticle(
                                            "flame",
                                            (double) (player.posX + Math.random() - 0.5F),
                                            (double) (player.boundingBox.minY + 0.25F
                                                    + ((Math.random() - 0.5) * 0.25F)),
                                            (double) (player.posZ + Math.random() - 0.5F),
                                            0.0D,
                                            0.025D,
                                            0.0D);
                                    // player.worldObj.spawnParticle("snowballpoof", (double)(player.posX +
                                    // Math.random()-0.5F),
                                    // (double)(player.boundingBox.minY + 0.25F + ((Math.random()-0.5)*0.25F)),
                                    // (double)(player.posZ
                                    // +
                                    // Math.random()-0.5F), 0.0D, 0.025D, 0.0D);
                                }

                                item.stackTagCompound.setInteger("runTicksMix", ticksRun);
                                item.stackTagCompound.setBoolean("IsSmashingMix", smashing);
                                item.stackTagCompound.setInteger("smashTicksMix", ticksSmash);
                                item.stackTagCompound.setInteger("airTicksMix", ticksAir);
                            }
    }
}
