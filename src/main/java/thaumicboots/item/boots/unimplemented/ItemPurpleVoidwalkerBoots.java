package thaumicboots.item.boots.unimplemented;

import thaumicboots.api.ItemVoidBoots;

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

    // ignore this, it'll only ever matter if this is implemented, leave it be just in case it is
    /*
     * @SubscribeEvent public void playerJumps(final LivingEvent.LivingJumpEvent event) { if (event.entity instanceof
     * EntityPlayer) { final EntityPlayer player = (EntityPlayer) event.entity; final ItemStack boots =
     * player.inventory.armorItemInSlot(0); final ItemStack sash =
     * PlayerHandler.getPlayerBaubles(player).getStackInSlot(3); if (boots != null && boots.getItem() ==
     * ItemRegistry.ItemVoidwalkerBoots) { player.motionY *= 1.25D; } if (sash != null && sash.getItem() ==
     * ItemRegistry.ItemVoidwalkerSash) { player.motionY *= 1.05D; } } }
     */

}
