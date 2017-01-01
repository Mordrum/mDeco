package com.mordrum.mdeco.core;

import java.io.File;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class DMPHelper {
   public static boolean createDirectory(String path) {
      File pmpConfigDir = new File(path);
      if(!pmpConfigDir.exists()) {
         try {
            pmpConfigDir.mkdir();
         } catch (Exception var3) {
            var3.printStackTrace();
            return false;
         }
      }

      return true;
   }

   public static void deleteOutputDirectory(File directory) {
      if(directory.isDirectory()) {
         File[] files = directory.listFiles();
         if(files != null && files.length > 0) {
            File[] var2 = files;
            int var3 = files.length;

            for(int var4 = 0; var4 < var3; ++var4) {
               File aFile = var2[var4];
               deleteOutputDirectory(aFile);
            }
         }

         directory.delete();
      } else {
         directory.delete();
      }

   }

   public static void playObjectSound(World worldIn, BlockPos pos, EntityPlayer playerIn, Block block) {
      worldIn.playSound((EntityPlayer)null, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), block.getSoundType().getPlaceSound(), SoundCategory.BLOCKS, 0.75F, 0.8F);
   }
}
