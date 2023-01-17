package net.pndevonian;

import net.lepidodendron.enchantments.Enchantments;
import net.lepidodendron.item.ItemBoneWand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.pndevonian.world.dimension.devonian.WorldDevonian;

public class PNWandHandler {

    @SubscribeEvent
    public void useWand(PlayerInteractEvent.RightClickBlock event) {

        EntityPlayer entity = event.getEntityPlayer();
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        EnumHand hand = event.getHand();
        EnumFacing facing = event.getFace();

        if (world.isRemote) {
            return;
        }

        BlockPos pos1 = pos.offset(facing);
        ItemStack itemstack = entity.getHeldItem(hand);

        if (itemstack.getItem() != ItemBoneWand.block) {
            return;
        }

        if (!(itemstack.getItem().getDamage(itemstack) < (itemstack.getItem().getMaxDamage() - 1))) {
            event.setCancellationResult(EnumActionResult.FAIL);
            //event.setCanceled(true);
            return;
        }

        int levelEnchantment = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(Enchantments.TIME_REVERSAL, itemstack);
        if (levelEnchantment > 0) {
            if (!entity.canPlayerEdit(pos1, facing, itemstack)) {
                event.setCancellationResult(EnumActionResult.FAIL);
                //event.setCanceled(true);
                return;
            }
            if (world.isAirBlock(pos1)) {

                boolean portalSpawnDevonian = WorldDevonian.portal.portalSpawn(world, pos1);

                if (portalSpawnDevonian) {
                    if (!entity.capabilities.isCreativeMode && itemstack.getItemDamage() < (itemstack.getItem().getMaxDamage() - 1)) {
                        itemstack.damageItem(1, entity);
                    }
                    event.setCancellationResult(EnumActionResult.SUCCESS);
                    //event.setCanceled(true);
                    return;
                }
            }
        }
    }
}
