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
	         int var3 = files.length;

            for (File aFile : files) {
               deleteOutputDirectory(aFile);
            }
         }

         directory.delete();
      } else {
         directory.delete();
      }

   }

   public static void playObjectSound(World worldIn, BlockPos pos, EntityPlayer playerIn, Block block) {
      worldIn.playSound(null, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), block.getSoundType().getPlaceSound(), SoundCategory.BLOCKS, 0.75F, 0.8F);
   }
}
