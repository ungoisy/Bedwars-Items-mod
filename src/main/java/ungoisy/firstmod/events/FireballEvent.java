package ungoisy.firstmod.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ungoisy.firstmod.items.ItemModFireball;

public class FireballEvent {

    //int cooldown = 1;

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

            if (player.getCurrentEquippedItem().getItem() instanceof ItemModFireball) {
                Vec3 vector = player.getLookVec();
                player.posY += 0.7;
                final EntityFireball fireball = new EntityFireball(player.getEntityWorld(), player, vector.xCoord*80, vector.yCoord*80, vector.zCoord*80) {
                    @Override
                    protected void onImpact(MovingObjectPosition movingObject) {
                        double jumpStrength = 1.5;
                        Vec3 movePlayer = new Vec3((player.posX - this.posX), (player.posY+1 - this.posY), (player.posZ - this.posZ)).normalize();
                        this.worldObj.createExplosion(player, this.posX, this.posY, this.posZ, 2, true);
                        if (FireballEvent.getDistanceBetweenEntities(this, player) > 3) {
                            this.kill();
                            return;
                        }
                        player.attackEntityFrom(DamageSource.inFire, 2);
                        player.motionX = movePlayer.xCoord*jumpStrength;
                        player.motionY = movePlayer.yCoord*jumpStrength;
                        player.motionZ = movePlayer.zCoord*jumpStrength;
                        player.velocityChanged = true;
                        this.kill();

                    }
                };
                player.posY -= 0.7;
                System.out.println("fireball clicked");
                player.getEntityWorld().spawnEntityInWorld(fireball);

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

