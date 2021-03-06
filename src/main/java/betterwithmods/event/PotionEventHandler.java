package betterwithmods.event;

import betterwithmods.BWRegistry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PotionEventHandler {
    @SubscribeEvent
    public void onPotionUpdate(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntity() instanceof EntityPlayer) {

            EntityPlayer player = (EntityPlayer) e.getEntity();
            World world = player.worldObj;

            if (player.isPotionActive(BWRegistry.POTION_TRUESIGHT)) {

                if (world.isRemote) {
                    Minecraft mc = Minecraft.getMinecraft();
                    int var3 = mc.gameSettings.particleSetting;
                    if (!mc.isGamePaused() && (world.provider.getDimension() == 0 || world.provider.getDimension() == 1)) {

                        int var4 = MathHelper.floor_double(player.posX);
                        int var5 = MathHelper.floor_double(player.posY);
                        int var6 = MathHelper.floor_double(player.posZ);
                        int radius = 10;
                        int x = var4;
                        int y = var5;
                        int z = var6;
                        for (x = var4 - radius; x <= var4 + radius; ++x) {
                            for (y = var5 - radius; y <= var5 + radius; ++y) {
                                for (z = var6 - radius; z <= var6 + radius; ++z) {

                                    if (canMobsSpawnHere(world, new BlockPos(x, y, z)) && (var3 == 0 || world.rand.nextInt(12) <= 2 - var3 << 1)) {

                                        double i = (double) x + world.rand.nextDouble();
                                        double j = (double) y + world.rand.nextDouble() * 0.25D;
                                        double k = (double) z + world.rand.nextDouble();
                                        world.spawnParticle(EnumParticleTypes.SPELL_MOB, i, j, k, 0, 0, 0);

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    public static boolean canMobsSpawnHere(World world, BlockPos pos) {
        if (!world.isSideSolid(pos.down(), EnumFacing.UP)) {
            return false;
        } else if (!world.isBlockNormalCube(pos, false) && !world.isBlockNormalCube(pos.up(), false) && !world.getBlockState(pos).getMaterial().isLiquid()) {
            IBlockState state = world.getBlockState(pos);
            if (state == Blocks.BEDROCK.getDefaultState()) {
                return false;
            } else if (world.getWorldTime() < 11615 && world.getLightFor(EnumSkyBlock.SKY, pos) >= 15) {
                return false;
            } else {
                int lightlevel = world.getLightFor(EnumSkyBlock.BLOCK, pos);
                if (lightlevel >= 8) {
                    return false;
                } else {
                    return world.isAirBlock(pos) || state.getCollisionBoundingBox(world, pos) == null;
                }
            }
        } else {
            return false;
        }

    }
}
