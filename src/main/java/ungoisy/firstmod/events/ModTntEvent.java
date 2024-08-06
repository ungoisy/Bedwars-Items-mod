package ungoisy.firstmod.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ungoisy.firstmod.items.ItemBridgeEgg;
import ungoisy.firstmod.items.ItemModTnt;

public class ModTntEvent {


    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onEvent(PlayerInteractEvent event) {

        if(event.entity instanceof EntityPlayer) {
            final EntityPlayer player = (EntityPlayer) event.entity;
            if (player.getEntityWorld().isRemote) {
                return;
            }
            if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK || event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) {
                return;
            }
            if (player.getCurrentEquippedItem() == null) {
                return;
            }

            if (player.getCurrentEquippedItem().getItem() instanceof ItemModTnt) {
                double positionX = event.pos.getX()+0.5D + event.face.getDirectionVec().getX();
                double positionY = event.pos.getY() + event.face.getDirectionVec().getY();
                double positionZ = event.pos.getZ()+0.5D + event.face.getDirectionVec().getZ();


                EntityTNTPrimed tnt = new EntityTNTPrimed(event.world, positionX, positionY, positionZ, player) {
                    Vec3 movePlayer = new Vec3(0, 0, 0);
                    double jumpStrength = 1.3;

                    @Override
                    public void onUpdate() {
                        if (this.fuse <= 1) {
                            // ULTRA SCUFFED
                            player.addPotionEffect(new PotionEffect(Potion.resistance.id, 1, 3));
                            movePlayer = new Vec3((player.posX - this.posX), (player.posY + 1 - this.posY), (player.posZ - this.posZ)).normalize();
                        }
                        super.onUpdate();

                        if (this.fuse <= 0 && getDistanceBetweenEntities(this, player) < 4) {
                            player.motionX = movePlayer.xCoord * jumpStrength;
                            player.motionY = movePlayer.yCoord * jumpStrength;
                            player.motionZ = movePlayer.zCoord * jumpStrength;
                            player.velocityChanged = true;
                        }
                    }
                };

                tnt.fuse = 49;
                player.getEntityWorld().spawnEntityInWorld(tnt);
                if (!player.capabilities.isCreativeMode) {
                    player.getCurrentEquippedItem().stackSize -= 1;
                }
            }
        }
    }

    public static double getDistanceBetweenEntities(Entity entity, Entity otherEntity) {
        double deltaX = entity.posX - otherEntity.posX;
        double deltaY = entity.posY - otherEntity.posY;
        double deltaZ = entity.posZ - otherEntity.posZ;

        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ));
    }
}


