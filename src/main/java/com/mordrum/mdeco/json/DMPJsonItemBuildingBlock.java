package com.mordrum.mdeco.json;

import com.mordrum.mdeco.object.DMPBuildingBlock;
import com.mordrum.mdeco.block.DMPBlockBrick;
import com.mordrum.mdeco.block.DMPBlockBrickHeadstone;
import com.mordrum.mdeco.object.DMPAncientBlockType;
import com.mordrum.mdeco.object.DMPBuildingBlockType;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class DMPJsonItemBuildingBlock {
   public static int createItemModelFiles(String rootPath) {
      byte filesGenerated = 0;
      int filesGenerated1 = filesGenerated + createAncientStoneModelFiles(rootPath);
      filesGenerated1 += createBuildingBlockModelFiles(rootPath);
      return filesGenerated1;
   }

   private static int createAncientStoneModelFiles(String rootPath) {
      int filesGenerated = 0;
      DMPAncientBlockType[] var3 = DMPAncientBlockType.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         DMPAncientBlockType blockType = var3[var5];
         DMPBlockBrick.EnumType[] var7 = DMPBlockBrick.EnumType.values();
         int var8 = var7.length;

         String variantName;
         int var9;
         for(var9 = 0; var9 < var8; ++var9) {
            DMPBlockBrick.EnumType variantHeadstone = var7[var9];
            variantName = String.format("%s_%s_block", new Object[]{blockType.name(), variantHeadstone.name()});
            createBlockModelFile(rootPath, variantName);
            ++filesGenerated;
            variantName = String.format("%s_%s", new Object[]{blockType.name(), variantHeadstone.name()});
            createSlabModelFile(rootPath, variantName);
            ++filesGenerated;
            variantName = String.format("%s_%s", new Object[]{blockType.name(), variantHeadstone.name()});
            createDoubleSlabModelFile(rootPath, variantName);
            ++filesGenerated;
            variantName = String.format("%s_%s_wall", new Object[]{blockType.name(), variantHeadstone.name()});
            createWallModelFile(rootPath, variantName);
            ++filesGenerated;
            variantName = String.format("%s_%s_pillar_large", new Object[]{blockType.name(), variantHeadstone.name()});
            createBlockModelFile(rootPath, variantName);
            ++filesGenerated;
            variantName = String.format("%s_%s_pillar_small", new Object[]{blockType.name(), variantHeadstone.name()});
            createBlockModelFile(rootPath, variantName);
            ++filesGenerated;
         }

         createStairsModelFile(rootPath, blockType.name());
         ++filesGenerated;
         DMPBlockBrickHeadstone.EnumType[] var11 = DMPBlockBrickHeadstone.EnumType.values();
         var8 = var11.length;

         for(var9 = 0; var9 < var8; ++var9) {
            DMPBlockBrickHeadstone.EnumType var12 = var11[var9];
            variantName = String.format("%s_headstone_%s", new Object[]{blockType.name(), var12.name()});
            createBlockModelFile(rootPath, variantName);
            ++filesGenerated;
         }
      }

      return filesGenerated;
   }

   private static int createBuildingBlockModelFiles(String rootPath) {
      int filesGenerated = 0;
      DMPBuildingBlock[] var2 = DMPBuildingBlock.values();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         DMPBuildingBlock buildingBlock = var2[var4];
         if(buildingBlock.blockType == DMPBuildingBlockType.pillarLarge) {
            createBlockModelFile(rootPath, buildingBlock.name());
            ++filesGenerated;
         } else if(buildingBlock.blockType == DMPBuildingBlockType.pillarSmall) {
            createBlockModelFile(rootPath, buildingBlock.name());
            ++filesGenerated;
         }
      }

      return filesGenerated;
   }

   private static void createBlockModelFile(String rootPath, String name) {
      File jsonFile = new File(String.format("%s%s.json", new Object[]{rootPath, name}));

      try {
         if(jsonFile != null) {
            if(!jsonFile.exists()) {
               jsonFile.createNewFile();
            }

            PrintWriter e = new PrintWriter(new FileWriter(jsonFile));
            if(e != null) {
               e.println("{");
               e.println(String.format("\"parent\":\"%s:block/%s\"", new Object[]{"mdeco", name}));
               e.println("}");
               e.close();
            }
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   private static void createSlabModelFile(String rootPath, String name) {
      File jsonFile = new File(String.format("%s%s_slab.json", new Object[]{rootPath, name}));

      try {
         if(jsonFile != null) {
            if(!jsonFile.exists()) {
               jsonFile.createNewFile();
            }

            PrintWriter e = new PrintWriter(new FileWriter(jsonFile));
            if(e != null) {
               e.println("{");
               e.println(String.format("\"parent\":\"%s:block/%s_slab_bottom\"", new Object[]{"mdeco", name}));
               e.println("}");
               e.close();
            }
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   private static void createDoubleSlabModelFile(String rootPath, String name) {
      File jsonFile = new File(String.format("%s%s_slab_double.json", new Object[]{rootPath, name}));

      try {
         if(jsonFile != null) {
            if(!jsonFile.exists()) {
               jsonFile.createNewFile();
            }

            PrintWriter e = new PrintWriter(new FileWriter(jsonFile));
            if(e != null) {
               e.println("{");
               e.println(String.format("\"parent\":\"%s:block/%s_slab_double\"", new Object[]{"mdeco", name}));
               e.println("}");
               e.close();
            }
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   private static void createStairsModelFile(String rootPath, String name) {
      File jsonFile = new File(String.format("%s%s_stairs.json", new Object[]{rootPath, name}));

      try {
         if(jsonFile != null) {
            if(!jsonFile.exists()) {
               jsonFile.createNewFile();
            }

            PrintWriter e = new PrintWriter(new FileWriter(jsonFile));
            if(e != null) {
               e.println("{");
               e.println(String.format("\"parent\":\"%s:block/%s_stairs\"", new Object[]{"mdeco", name}));
               e.println("}");
               e.close();
            }
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   private static void createWallModelFile(String rootPath, String name) {
      File jsonFile = new File(String.format("%s%s.json", new Object[]{rootPath, name}));

      try {
         if(jsonFile != null) {
            if(!jsonFile.exists()) {
               jsonFile.createNewFile();
            }

            PrintWriter e = new PrintWriter(new FileWriter(jsonFile));
            if(e != null) {
               e.println("{");
               e.println(String.format("\"parent\":\"%s:block/%s_inventory\"", new Object[]{"mdeco", name}));
               e.println("}");
               e.close();
            }
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }
}
