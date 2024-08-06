package ungoisy.firstmod.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ungoisy.firstmod.items.ItemBridgeEgg;
import ungoisy.firstmod.items.ItemModFireball;

public class BridgeEggEvent {

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onEvent(PlayerInteractEvent event) {

        if(event.entity instanceof EntityPlayer) {
            final EntityPlayer player = (EntityPlayer) event.entity;
            if (player.getEntityWorld().isRemote) {
                return;
            }
            if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK || event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
                return;
            }
            if (player.getCurrentEquippedItem() == null) {
                return;
            }

            if (player.getCurrentEquippedItem().getItem() instanceof ItemBridgeEgg) {
                int colourCode = 5; // lime
                if (player.getCurrentEquippedItem().getUnlocalizedName().equals("item.bridge_egg_red")) {
                    colourCode = 14; // red
                }
                final int meta = colourCode;

                EntityEgg egg = new EntityEgg(event.world, player) {
                    @Override
                    protected void onImpact(MovingObjectPosition p_70184_1_) {
                        if ((this.getEntityWorld().getBlockState((this.getPosition())) == Blocks.wool.getStateFromMeta(meta) || this.getEntityWorld().getBlockState((this.getPosition())) == Blocks.air.getDefaultState()) && this.ticksExisted < 20) {
                            return;
                        } else {
                            super.onImpact(p_70184_1_);
                        }
                    }

                    @Override
                    public void onUpdate() {

                        this.getEntityWorld().setBlockState(new BlockPos(this.posX, this.posY-1.5, this.posZ), Blocks.wool.getStateFromMeta(meta));
                        this.getEntityWorld().setBlockState(new BlockPos(this.posX+0.3, this.posY-1.5, this.posZ+0.3), Blocks.wool.getStateFromMeta(meta));
                        this.getEntityWorld().setBlockState(new BlockPos(this.posX-0.3, this.posY-1.5, this.posZ-0.3), Blocks.wool.getStateFromMeta(meta));
                        this.getEntityWorld().setBlockState(new BlockPos(this.posX-0.3, this.posY-1.5, this.posZ+0.3), Blocks.wool.getStateFromMeta(meta));
                        this.getEntityWorld().setBlockState(new BlockPos(this.posX+0.3, this.posY-1.5, this.posZ-0.3), Blocks.wool.getStateFromMeta(meta));

                        if (player.getEntityWorld().getBlockState(player.getPosition()) == Blocks.wool.getStateFromMeta(meta)) {
                            player.getEntityWorld().setBlockState(player.getPosition(), Blocks.air.getDefaultState());
                        }
                        super.onUpdate();
                        if (this.ticksExisted > 20) {
                            this.onImpact(new MovingObjectPosition(this));
                        }


                    }


                };
                player.getEntityWorld().spawnEntityInWorld(egg);

                if (!player.capabilities.isCreativeMode) {
                    player.getCurrentEquippedItem().stackSize -= 1;
                }
            }
        }
    }
}
