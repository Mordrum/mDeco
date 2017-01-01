package com.mordrum.mdeco.json;

import com.mordrum.mdeco.block.DMPBlockBrick;
import com.mordrum.mdeco.block.DMPBlockBrickHeadstone;
import com.mordrum.mdeco.object.DMPAncientBlockType;
import com.mordrum.mdeco.object.DMPBuildingBlock;
import com.mordrum.mdeco.object.DMPBuildingBlockType;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class DMPJsonBlockBuildingBlock {
   public static int createBlockModelFiles(String rootPath) {
      byte filesGenerated = 0;
      int filesGenerated1 = filesGenerated + createAncientStoneModelFiles(rootPath);
      filesGenerated1 += createBuildingBlockModelFiles(rootPath);
      return filesGenerated1;
   }

   private static int createAncientStoneModelFiles(String rootPath) {
      int filesGenerated = 0;
      DMPAncientBlockType[] var2 = DMPAncientBlockType.values();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         DMPAncientBlockType blockType = var2[var4];
         DMPBlockBrick.EnumType[] var6 = DMPBlockBrick.EnumType.values();
         int var7 = var6.length;

         int var8;
         for(var8 = 0; var8 < var7; ++var8) {
            DMPBlockBrick.EnumType variantHeadstone = var6[var8];
            createStructureBlockModelFile(rootPath, blockType.name(), variantHeadstone.name(), "_block");
            ++filesGenerated;
            createStructureBlockModelFile(rootPath, blockType.name(), variantHeadstone.name(), "_slab_bottom");
            ++filesGenerated;
            createStructureBlockModelFile(rootPath, blockType.name(), variantHeadstone.name(), "_slab_top");
            ++filesGenerated;
            createStructureBlockModelFile(rootPath, blockType.name(), variantHeadstone.name(), "_slab_double");
            ++filesGenerated;
            createStoneWallModelFile(rootPath, blockType.name(), variantHeadstone.name(), "_wall_inventory");
            ++filesGenerated;
            createStoneWallModelFile(rootPath, blockType.name(), variantHeadstone.name(), "_wall_post");
            ++filesGenerated;
            createStoneWallModelFile(rootPath, blockType.name(), variantHeadstone.name(), "_wall_n");
            ++filesGenerated;
            createStoneWallModelFile(rootPath, blockType.name(), variantHeadstone.name(), "_wall_ne");
            ++filesGenerated;
            createStoneWallModelFile(rootPath, blockType.name(), variantHeadstone.name(), "_wall_ns");
            ++filesGenerated;
            createStoneWallModelFile(rootPath, blockType.name(), variantHeadstone.name(), "_wall_nse");
            ++filesGenerated;
            createStoneWallModelFile(rootPath, blockType.name(), variantHeadstone.name(), "_wall_nsew");
            ++filesGenerated;
            createStoneWallModelFile(rootPath, blockType.name(), variantHeadstone.name(), "_wall_ns_above");
            ++filesGenerated;
            createAncientStonePillarModelFile(rootPath, blockType.name(), variantHeadstone.name(), "_pillar_large");
            ++filesGenerated;
            createAncientStonePillarModelFile(rootPath, blockType.name(), variantHeadstone.name(), "_pillar_large_side");
            ++filesGenerated;
            createAncientStonePillarModelFile(rootPath, blockType.name(), variantHeadstone.name(), "_pillar_small");
            ++filesGenerated;
            createAncientStonePillarModelFile(rootPath, blockType.name(), variantHeadstone.name(), "_pillar_small_side");
            ++filesGenerated;
         }

         createStairsModelFile(rootPath, blockType.name(), "_stairs");
         ++filesGenerated;
         createStairsModelFile(rootPath, blockType.name(), "_stairs_inner");
         ++filesGenerated;
         createStairsModelFile(rootPath, blockType.name(), "_stairs_outer");
         ++filesGenerated;
         DMPBlockBrickHeadstone.EnumType[] var10 = DMPBlockBrickHeadstone.EnumType.values();
         var7 = var10.length;

         for(var8 = 0; var8 < var7; ++var8) {
            DMPBlockBrickHeadstone.EnumType var11 = var10[var8];
            createHeadstoneFile(rootPath, blockType.name(), var11.name());
            ++filesGenerated;
         }
      }

      return filesGenerated;
   }

   private static void createStairsModelFile(String rootPath, String blockName, String filenameSuffix) {
      String texture = String.format("%s_normal", new Object[]{blockName});
      File jsonFile = new File(String.format("%s%s%s.json", new Object[]{rootPath, blockName, filenameSuffix}));

      try {
         if(!jsonFile.exists()) {
            jsonFile.createNewFile();
         }

         if(jsonFile != null) {
            PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
            if(printwriter != null) {
               printwriter.println("{");
               if(filenameSuffix.matches("_stairs")) {
                  printwriter.println("\"parent\":\"block/stairs\",");
               } else if(filenameSuffix.matches("_stairs_inner")) {
                  printwriter.println("\"parent\":\"block/inner_stairs\",");
               } else if(filenameSuffix.matches("_stairs_outer")) {
                  printwriter.println("\"parent\":\"block/outer_stairs\",");
               }

               printwriter.println("\"textures\":{");
               printwriter.println(String.format("\"bottom\":\"%s:blocks/%s\",", new Object[]{"mdeco", texture}));
               printwriter.println(String.format("\"top\":\"%s:blocks/%s\",", new Object[]{"mdeco", texture}));
               printwriter.println(String.format("\"side\":\"%s:blocks/%s\"}", new Object[]{"mdeco", texture}));
               printwriter.println("}");
               printwriter.close();
            }
         }
      } catch (Exception var7) {
         var7.printStackTrace();
      }

   }

   private static int createBuildingBlockModelFiles(String rootPath) {
      int filesGenerated = 0;
      DMPBuildingBlock[] var2 = DMPBuildingBlock.values();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         DMPBuildingBlock buildingBlock = var2[var4];
         if(buildingBlock.blockType == DMPBuildingBlockType.pillarLarge) {
            createPillarModelFile(rootPath, buildingBlock, "_pillar_large", false);
            ++filesGenerated;
            createPillarModelFile(rootPath, buildingBlock, "_pillar_large_side", true);
            ++filesGenerated;
         } else if(buildingBlock.blockType == DMPBuildingBlockType.pillarSmall) {
            createPillarModelFile(rootPath, buildingBlock, "_pillar_small", false);
            ++filesGenerated;
            createPillarModelFile(rootPath, buildingBlock, "_pillar_small_side", true);
            ++filesGenerated;
         }
      }

      return filesGenerated;
   }

   private static void createStructureBlockModelFile(String rootPath, String blockName, String variant, String filenameSuffix) {
      String texture = String.format("%s_%s", new Object[]{blockName, variant});
      File jsonFile = new File(String.format("%s%s_%s%s.json", new Object[]{rootPath, blockName, variant, filenameSuffix}));

      try {
         if(!jsonFile.exists()) {
            jsonFile.createNewFile();
         }

         if(jsonFile != null) {
            PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
            if(printwriter != null) {
               printwriter.println("{");
               if(filenameSuffix.matches("_block")) {
                  printwriter.println("\"parent\":\"block/cube_all\",");
                  printwriter.println("\"textures\":{");
                  printwriter.println(String.format("\"all\":\"%s:blocks/%s\"}", new Object[]{"mdeco", texture}));
               } else if(filenameSuffix.matches("_slab_bottom")) {
                  printwriter.println("\"parent\":\"block/half_slab\",");
                  printwriter.println("\"textures\":{");
                  printwriter.println(String.format("\"bottom\":\"%s:blocks/%s\",", new Object[]{"mdeco", texture}));
                  printwriter.println(String.format("\"top\":\"%s:blocks/%s\",", new Object[]{"mdeco", texture}));
                  printwriter.println(String.format("\"side\":\"%s:blocks/%s\"}", new Object[]{"mdeco", texture}));
               } else if(filenameSuffix.matches("_slab_top")) {
                  printwriter.println("\"parent\":\"block/upper_slab\",");
                  printwriter.println("\"textures\":{");
                  printwriter.println(String.format("\"bottom\":\"%s:blocks/%s\",", new Object[]{"mdeco", texture}));
                  printwriter.println(String.format("\"top\":\"%s:blocks/%s\",", new Object[]{"mdeco", texture}));
                  printwriter.println(String.format("\"side\":\"%s:blocks/%s\"}", new Object[]{"mdeco", texture}));
               } else if(filenameSuffix.matches("_slab_double")) {
                  printwriter.println("\"parent\":\"block/cube_all\",");
                  printwriter.println("\"textures\":{");
                  printwriter.println(String.format("\"all\":\"%s:blocks/%s\"}", new Object[]{"mdeco", texture}));
               }

               printwriter.println("}");
               printwriter.close();
            }
         }
      } catch (Exception var8) {
         var8.printStackTrace();
      }

   }

   private static void createAncientStonePillarModelFile(String rootPath, String blockName, String variant, String filenameSuffix) {
      String texture = String.format("%s_%s", new Object[]{blockName, variant});
      File jsonFile = new File(String.format("%s%s_%s%s.json", new Object[]{rootPath, blockName, variant, filenameSuffix}));

      try {
         if(!jsonFile.exists()) {
            jsonFile.createNewFile();
         }

         if(jsonFile != null) {
            PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
            if(printwriter != null) {
               printwriter.println("{");
               printwriter.println(String.format("\"parent\":\"%s:block/%s\",", new Object[]{"mdeco", filenameSuffix}));
               printwriter.println("\"textures\":{");
               printwriter.println(String.format("\"material\":\"%s:blocks/%s\"}", new Object[]{"mdeco", texture}));
               printwriter.println("}");
               printwriter.close();
            }
         }
      } catch (Exception var8) {
         var8.printStackTrace();
      }

   }

   private static void createStoneWallModelFile(String rootPath, String blockName, String variant, String filenameSuffix) {
      String texture = String.format("%s_%s", new Object[]{blockName, variant});
      File jsonFile = new File(String.format("%s%s_%s%s.json", new Object[]{rootPath, blockName, variant, filenameSuffix}));

      try {
         if(!jsonFile.exists()) {
            jsonFile.createNewFile();
         }

         if(jsonFile != null) {
            PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
            if(printwriter != null) {
               printwriter.println("{");
               if(filenameSuffix.matches("_wall_inventory")) {
                  printwriter.println(String.format("\"parent\":\"%s:block/_wall_inventory\",", new Object[]{"mdeco"}));
                  printwriter.println("\"textures\":{");
                  printwriter.println(String.format("\"wall\":\"%s:blocks/%s\"}", new Object[]{"mdeco", texture}));
               } else if(filenameSuffix.matches("_wall_post")) {
                  printwriter.println(String.format("\"parent\":\"%s:block/_wall_post\",", new Object[]{"mdeco"}));
                  printwriter.println("\"textures\":{");
                  printwriter.println(String.format("\"wall\":\"%s:blocks/%s\"}", new Object[]{"mdeco", texture}));
               } else if(filenameSuffix.matches("_wall_n")) {
                  printwriter.println(String.format("\"parent\":\"%s:block/_wall_n\",", new Object[]{"mdeco"}));
                  printwriter.println("\"textures\":{");
                  printwriter.println(String.format("\"wall\":\"%s:blocks/%s\"}", new Object[]{"mdeco", texture}));
               } else if(filenameSuffix.matches("_wall_ne")) {
                  printwriter.println(String.format("\"parent\":\"%s:block/_wall_ne\",", new Object[]{"mdeco"}));
                  printwriter.println("\"textures\":{");
                  printwriter.println(String.format("\"wall\":\"%s:blocks/%s\"}", new Object[]{"mdeco", texture}));
               } else if(filenameSuffix.matches("_wall_ns")) {
                  printwriter.println(String.format("\"parent\":\"%s:block/_wall_ns\",", new Object[]{"mdeco"}));
                  printwriter.println("\"textures\":{");
                  printwriter.println(String.format("\"wall\":\"%s:blocks/%s\"}", new Object[]{"mdeco", texture}));
               } else if(filenameSuffix.matches("_wall_nse")) {
                  printwriter.println(String.format("\"parent\":\"%s:block/_wall_nse\",", new Object[]{"mdeco"}));
                  printwriter.println("\"textures\":{");
                  printwriter.println(String.format("\"wall\":\"%s:blocks/%s\"}", new Object[]{"mdeco", texture}));
               } else if(filenameSuffix.matches("_wall_nsew")) {
                  printwriter.println(String.format("\"parent\":\"%s:block/_wall_nsew\",", new Object[]{"mdeco"}));
                  printwriter.println("\"textures\":{");
                  printwriter.println(String.format("\"wall\":\"%s:blocks/%s\"}", new Object[]{"mdeco", texture}));
               } else if(filenameSuffix.matches("_wall_ns_above")) {
                  printwriter.println(String.format("\"parent\":\"%s:block/_wall_ns_above\",", new Object[]{"mdeco"}));
                  printwriter.println("\"textures\":{");
                  printwriter.println(String.format("\"wall\":\"%s:blocks/%s\"}", new Object[]{"mdeco", texture}));
               }

               printwriter.println("}");
               printwriter.close();
            }
         }
      } catch (Exception var8) {
         var8.printStackTrace();
      }

   }

   private static void createHeadstoneFile(String rootPath, String blockName, String variant) {
      File jsonFile = new File(String.format("%s%s_headstone_%s.json", new Object[]{rootPath, blockName, variant}));

      try {
         if(!jsonFile.exists()) {
            jsonFile.createNewFile();
         }

         if(jsonFile != null) {
            PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
            if(printwriter != null) {
               printwriter.println("{");
               printwriter.println(String.format("\"parent\":\"%s:block/_headstone_%s\",", new Object[]{"mdeco", variant}));
               printwriter.println("\"textures\":{");
               printwriter.println(String.format("\"texture\":\"%s:blocks/%s_normal\"}", new Object[]{"mdeco", blockName}));
               printwriter.println("}");
               printwriter.close();
            }
         }
      } catch (Exception var6) {
         var6.printStackTrace();
      }

   }

   private static void createPillarModelFile(String rootPath, DMPBuildingBlock block, String modelType, boolean side) {
      File jsonFile = new File(String.format("%s%s.json", new Object[]{rootPath, block.name() + (side?"_side":"")}));

      try {
         if(!jsonFile.exists()) {
            jsonFile.createNewFile();
         }

         if(jsonFile != null) {
            PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
            if(printwriter != null) {
               printwriter.println("{");
               printwriter.println(String.format("\"parent\":\"%s:block/%s\",", new Object[]{"mdeco", modelType}));
               printwriter.println("\"textures\":{");
               printwriter.println(String.format("\"material\":\"%s\"}", new Object[]{block.texturePrimary}));
               printwriter.println("}");
               printwriter.close();
            }
         }
      } catch (Exception var7) {
         var7.printStackTrace();
      }

   }
}
