package com.mordrum.mdeco.json;

import com.mordrum.mdeco.block.DMPBlockBrick;
import com.mordrum.mdeco.block.DMPBlockBrickHeadstone;
import com.mordrum.mdeco.object.DMPBuildingBlock;
import com.mordrum.mdeco.object.DMPBuildingBlockType;
import com.mordrum.mdeco.object.DMPAncientBlockType;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class DMPJsonStateBuildingBlock {
   public static int createBlockstateFiles(String rootPath) {
      byte filesGenerated = 0;
      int filesGenerated1 = filesGenerated + createAncientStoneBlockstateFiles(rootPath);
      filesGenerated1 += createBuildingBlockBlockstateFiles(rootPath);
      return filesGenerated1;
   }

   private static int createAncientStoneBlockstateFiles(String rootPath) {
      int filesGenerated = 0;
      DMPAncientBlockType[] var2 = DMPAncientBlockType.values();
      int var3 = var2.length;

	   for (DMPAncientBlockType blockType : var2) {
		   createBlockFile(rootPath, blockType.name());
		   ++filesGenerated;
		   createSlabFile(rootPath, blockType.name());
		   ++filesGenerated;
		   createDoubleSlabFile(rootPath, blockType.name());
		   ++filesGenerated;
		   createStairsFile(rootPath, blockType.name());
		   ++filesGenerated;
		   createWallFile(rootPath, blockType.name());
		   ++filesGenerated;
		   createAncientStonePillarFile(rootPath, blockType.name(), true);
		   ++filesGenerated;
		   createAncientStonePillarFile(rootPath, blockType.name(), false);
		   ++filesGenerated;
		   createHeadstoneFile(rootPath, blockType.name());
		   ++filesGenerated;
	   }

      return filesGenerated;
   }

   private static int createBuildingBlockBlockstateFiles(String rootPath) {
      int filesGenerated = 0;
      DMPBuildingBlock[] var2 = DMPBuildingBlock.values();
      int var3 = var2.length;

	   for (DMPBuildingBlock buildingBlock : var2) {
		   if (buildingBlock.blockType == DMPBuildingBlockType.pillarLarge) {
			   createPillarFile(rootPath, buildingBlock);
			   ++filesGenerated;
		   } else if (buildingBlock.blockType == DMPBuildingBlockType.pillarSmall) {
			   createPillarFile(rootPath, buildingBlock);
			   ++filesGenerated;
		   }
	   }

      return filesGenerated;
   }

   private static void createBlockFile(String rootPath, String name) {
      try {
         File jsonFile = new File(String.format("%s%s_block.json", new Object[]{rootPath, name}));
         if(jsonFile != null) {
            if(!jsonFile.exists()) {
               jsonFile.createNewFile();
            }

            PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
            if(printwriter != null) {
               printwriter.println("{");
               printwriter.println("\"variants\":{");
               int index = 0;
               int variantCount = DMPBlockBrick.EnumType.values().length;
               DMPBlockBrick.EnumType[] e = DMPBlockBrick.EnumType.values();
               int var8 = e.length;

	            for (DMPBlockBrick.EnumType variant : e) {
		            String variantName = String.format("%s_%s_block", name, variant.name());
		            printwriter.println(String.format("\"variant=%s\":{\"model\":\"%s:%s\"}%s", new Object[]{
				            variant.name(),
				            "mdeco",
				            variantName,
				            index < variantCount - 1 ? "," : ""
		            }));
		            ++index;
	            }

               printwriter.println("}");
               printwriter.println("}");
               printwriter.close();
            }
         }
      } catch (Exception var11) {
         var11.printStackTrace();
      }

   }

   private static void createSlabFile(String rootPath, String name) {
      try {
         File jsonFile = new File(String.format("%s%s_slab.json", new Object[]{rootPath, name}));
         if(jsonFile != null) {
            if(!jsonFile.exists()) {
               jsonFile.createNewFile();
            }

            PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
            if(printwriter != null) {
               printwriter.println("{");
               printwriter.println("\"variants\":{");
               int index = 0;
               int variantCount = DMPBlockBrick.EnumType.values().length;
               DMPBlockBrick.EnumType[] e = DMPBlockBrick.EnumType.values();
               int var8 = e.length;

	            for (DMPBlockBrick.EnumType variant : e) {
		            String modelFile = String.format("%s_%s", name, variant.name());
		            printwriter.println(String.format("\"half=bottom,variant=%s\":{\"model\":\"%s:%s_slab_bottom\"},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"half=top,variant=%s\":{\"model\":\"%s:%s_slab_top\"}%s", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile,
				            index < variantCount - 1 ? "," : ""
		            }));
		            ++index;
	            }

               printwriter.println("}");
               printwriter.println("}");
               printwriter.close();
            }
         }
      } catch (Exception var11) {
         var11.printStackTrace();
      }

   }

   private static void createDoubleSlabFile(String rootPath, String name) {
      try {
         File jsonFile = new File(String.format("%s%s_slab_double.json", new Object[]{rootPath, name}));
         if(jsonFile != null) {
            if(!jsonFile.exists()) {
               jsonFile.createNewFile();
            }

            PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
            if(printwriter != null) {
               printwriter.println("{");
               printwriter.println("\"variants\":{");
               int index = 0;
               int variantCount = DMPBlockBrick.EnumType.values().length;
               DMPBlockBrick.EnumType[] e = DMPBlockBrick.EnumType.values();
               int var8 = e.length;

	            for (DMPBlockBrick.EnumType variant : e) {
		            String variantName = String.format("%s_%s", name, variant.name());
		            printwriter.println(String.format("\"half=bottom,variant=%s\":{\"model\":\"%s:%s_slab_double\"},", new Object[]{
				            variant.name(),
				            "mdeco",
				            variantName
		            }));
		            printwriter.println(String.format("\"half=top,variant=%s\":{\"model\":\"%s:%s_slab_double\"}%s", new Object[]{
				            variant.name(),
				            "mdeco",
				            variantName,
				            index < variantCount - 1 ? "," : ""
		            }));
		            ++index;
	            }

               printwriter.println("}");
               printwriter.println("}");
               printwriter.close();
            }
         }
      } catch (Exception var11) {
         var11.printStackTrace();
      }

   }

   private static void createStairsFile(String rootPath, String name) {
      try {
         File jsonFile = new File(String.format("%s%s_stairs.json", new Object[]{rootPath, name}));
         if(jsonFile != null) {
            if(!jsonFile.exists()) {
               jsonFile.createNewFile();
            }

            PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
            if(printwriter != null) {
               printwriter.println("{");
               printwriter.println("\"variants\":{");
               printwriter.println(String.format("\"facing=east,half=bottom,shape=straight\":{\"model\":\"%s:%s_stairs\"},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=west,half=bottom,shape=straight\":{\"model\":\"%s:%s_stairs\",\"y\":180,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=south,half=bottom,shape=straight\":{\"model\":\"%s:%s_stairs\",\"y\":90,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=north,half=bottom,shape=straight\":{\"model\":\"%s:%s_stairs\",\"y\":270,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=east,half=bottom,shape=outer_right\":{\"model\":\"%s:%s_stairs_outer\"},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=west,half=bottom,shape=outer_right\":{\"model\":\"%s:%s_stairs_outer\",\"y\":180,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=south,half=bottom,shape=outer_right\":{\"model\":\"%s:%s_stairs_outer\",\"y\":90,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=north,half=bottom,shape=outer_right\":{\"model\":\"%s:%s_stairs_outer\",\"y\":270,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=east,half=bottom,shape=outer_left\":{\"model\":\"%s:%s_stairs_outer\",\"y\":270,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=west,half=bottom,shape=outer_left\":{\"model\":\"%s:%s_stairs_outer\",\"y\":90,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=south,half=bottom,shape=outer_left\":{\"model\":\"%s:%s_stairs_outer\"},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=north,half=bottom,shape=outer_left\":{\"model\":\"%s:%s_stairs_outer\",\"y\":180,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=east,half=bottom,shape=inner_right\":{\"model\":\"%s:%s_stairs_inner\"},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=west,half=bottom,shape=inner_right\":{\"model\":\"%s:%s_stairs_inner\",\"y\":180,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=south,half=bottom,shape=inner_right\":{\"model\":\"%s:%s_stairs_inner\",\"y\":90,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=north,half=bottom,shape=inner_right\":{\"model\":\"%s:%s_stairs_inner\",\"y\":270,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=east,half=bottom,shape=inner_left\":{\"model\":\"%s:%s_stairs_inner\",\"y\":270,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=west,half=bottom,shape=inner_left\":{\"model\":\"%s:%s_stairs_inner\",\"y\":90,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=south,half=bottom,shape=inner_left\":{\"model\":\"%s:%s_stairs_inner\"},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=north,half=bottom,shape=inner_left\":{\"model\":\"%s:%s_stairs_inner\",\"y\":180,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=east,half=top,shape=straight\":{\"model\":\"%s:%s_stairs\",\"x\":180,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=west,half=top,shape=straight\":{\"model\":\"%s:%s_stairs\",\"x\":180,\"y\":180,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=south,half=top,shape=straight\":{\"model\":\"%s:%s_stairs\",\"x\":180,\"y\":90,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=north,half=top,shape=straight\":{\"model\":\"%s:%s_stairs\",\"x\":180,\"y\":270,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=east,half=top,shape=outer_right\":{\"model\":\"%s:%s_stairs_outer\",\"x\":180,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=west,half=top,shape=outer_right\":{\"model\":\"%s:%s_stairs_outer\",\"x\":180,\"y\":180,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=south,half=top,shape=outer_right\":{\"model\":\"%s:%s_stairs_outer\",\"x\":180,\"y\":90,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=north,half=top,shape=outer_right\":{\"model\":\"%s:%s_stairs_outer\",\"x\":180,\"y\":270,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=east,half=top,shape=outer_left\":{\"model\":\"%s:%s_stairs_outer\",\"x\":180,\"y\":90,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=west,half=top,shape=outer_left\":{\"model\":\"%s:%s_stairs_outer\",\"x\":180,\"y\":270,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=south,half=top,shape=outer_left\":{\"model\":\"%s:%s_stairs_outer\",\"x\":180,\"y\":180,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=north,half=top,shape=outer_left\":{\"model\":\"%s:%s_stairs_outer\",\"x\":180,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=east,half=top,shape=inner_right\":{\"model\":\"%s:%s_stairs_inner\",\"x\":180,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=west,half=top,shape=inner_right\":{\"model\":\"%s:%s_stairs_inner\",\"x\":180,\"y\":180,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=south,half=top,shape=inner_right\":{\"model\":\"%s:%s_stairs_inner\",\"x\":180,\"y\":90,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=north,half=top,shape=inner_right\":{\"model\":\"%s:%s_stairs_inner\",\"x\":180,\"y\":270,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=east,half=top,shape=inner_left\":{\"model\":\"%s:%s_stairs_inner\",\"x\":180,\"y\":90,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=west,half=top,shape=inner_left\":{\"model\":\"%s:%s_stairs_inner\",\"x\":180,\"y\":270,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=south,half=top,shape=inner_left\":{\"model\":\"%s:%s_stairs_inner\",\"x\":180,\"y\":180,\"uvlock\":true},", new Object[]{"mdeco", name}));
               printwriter.println(String.format("\"facing=north,half=top,shape=inner_left\":{\"model\":\"%s:%s_stairs_inner\",\"x\":180,\"uvlock\":true}", new Object[]{"mdeco", name}));
               printwriter.println("}");
               printwriter.println("}");
               printwriter.close();
            }
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }

   }

   private static void createAncientStonePillarFile(String rootPath, String name, boolean largePillar) {
      String pillarType = largePillar?"_pillar_large":"_pillar_small";

      try {
         File jsonFile = new File(String.format("%s%s%s.json", new Object[]{rootPath, name, pillarType}));
         if(jsonFile != null) {
            if(!jsonFile.exists()) {
               jsonFile.createNewFile();
            }

            PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
            if(printwriter != null) {
               printwriter.println("{");
               printwriter.println("\"variants\":{");
               int index = 0;
               int variantCount = DMPBlockBrick.EnumType.values().length;
               DMPBlockBrick.EnumType[] e = DMPBlockBrick.EnumType.values();
               int var10 = e.length;

	            for (DMPBlockBrick.EnumType variant : e) {
		            String modelFile = String.format("%s_%s%s", name, variant.name(), pillarType);
		            printwriter.println(String.format("\"axis=x,variant=%s\":{\"model\":\"%s:%s_side\",\"y\":90},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"axis=y,variant=%s\":{\"model\":\"%s:%s\"},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"axis=z,variant=%s\":{\"model\":\"%s:%s_side\"},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"axis=none,variant=%s\":{\"model\":\"%s:%s\"}%s", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile,
				            index < variantCount - 1 ? "," : ""
		            }));
		            ++index;
	            }

               printwriter.println("}");
               printwriter.println("}");
               printwriter.close();
            }
         }
      } catch (Exception var13) {
         var13.printStackTrace();
      }

   }

   private static void createWallFile(String rootPath, String name) {
      try {
         File jsonFile = new File(String.format("%s%s_wall.json", new Object[]{rootPath, name}));
         if(jsonFile != null) {
            if(!jsonFile.exists()) {
               jsonFile.createNewFile();
            }

            PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
            if(printwriter != null) {
               printwriter.println("{");
               printwriter.println("\"variants\":{");
               int index = 0;
               int variantCount = DMPBlockBrick.EnumType.values().length;
               DMPBlockBrick.EnumType[] e = DMPBlockBrick.EnumType.values();
               int var8 = e.length;

	            for (DMPBlockBrick.EnumType variant : e) {
		            String modelFile = String.format("%s_%s_wall", name, variant.name());
		            printwriter.println(String.format("\"east=false,north=false,south=false,up=false,variant=%s,west=false\":{\"model\":\"%s:%s_post\"},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=false,north=true,south=false,up=false,variant=%s,west=false\":{\"model\":\"%s:%s_n\"},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=true,north=false,south=false,up=false,variant=%s,west=false\":{\"model\":\"%s:%s_n\",\"y\":90,\"uvlock\":true},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=false,north=false,south=true,up=false,variant=%s,west=false\":{\"model\":\"%s:%s_n\",\"y\":180,\"uvlock\":true},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=false,north=false,south=false,up=false,variant=%s,west=true\":{\"model\":\"%s:%s_n\",\"y\":270,\"uvlock\":true},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=true,north=true,south=false,up=false,variant=%s,west=false\":{\"model\":\"%s:%s_ne\"},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=true,north=false,south=true,up=false,variant=%s,west=false\":{\"model\":\"%s:%s_ne\",\"y\":90,\"uvlock\":true},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=false,north=false,south=true,up=false,variant=%s,west=true\":{\"model\":\"%s:%s_ne\",\"y\":180,\"uvlock\":true},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=false,north=true,south=false,up=false,variant=%s,west=true\":{\"model\":\"%s:%s_ne\",\"y\":270,\"uvlock\":true},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=false,north=true,south=true,up=false,variant=%s,west=false\":{\"model\":\"%s:%s_ns\"},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=true,north=false,south=false,up=false,variant=%s,west=true\":{\"model\":\"%s:%s_ns\",\"y\":90,\"uvlock\":true},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=true,north=true,south=true,up=false,variant=%s,west=false\":{\"model\":\"%s:%s_nse\"},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=true,north=false,south=true,up=false,variant=%s,west=true\":{\"model\":\"%s:%s_nse\",\"y\":90,\"uvlock\":true},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=false,north=true,south=true,up=false,variant=%s,west=true\":{\"model\":\"%s:%s_nse\",\"y\":180,\"uvlock\":true},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=true,north=true,south=false,up=false,variant=%s,west=true\":{\"model\":\"%s:%s_nse\",\"y\":270,\"uvlock\":true},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=true,north=true,south=true,up=false,variant=%s,west=true\":{\"model\":\"%s:%s_nsew\"},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=false,north=false,south=false,up=true,variant=%s,west=false\":{\"model\":\"%s:%s_post\"},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=false,north=true,south=false,up=true,variant=%s,west=false\":{\"model\":\"%s:%s_n\"},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=true,north=false,south=false,up=true,variant=%s,west=false\":{\"model\":\"%s:%s_n\",\"y\":90,\"uvlock\":true},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=false,north=false,south=true,up=true,variant=%s,west=false\":{\"model\":\"%s:%s_n\",\"y\":180,\"uvlock\":true},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=false,north=false,south=false,up=true,variant=%s,west=true\":{\"model\":\"%s:%s_n\",\"y\":270,\"uvlock\":true},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=true,north=true,south=false,up=true,variant=%s,west=false\":{\"model\":\"%s:%s_ne\"},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=true,north=false,south=true,up=true,variant=%s,west=false\":{\"model\":\"%s:%s_ne\",\"y\":90,\"uvlock\":true},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=false,north=false,south=true,up=true,variant=%s,west=true\":{\"model\":\"%s:%s_ne\",\"y\":180,\"uvlock\":true},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=false,north=true,south=false,up=true,variant=%s,west=true\":{\"model\":\"%s:%s_ne\",\"y\":270,\"uvlock\":true},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=false,north=true,south=true,up=true,variant=%s,west=false\":{\"model\":\"%s:%s_ns_above\"},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=true,north=false,south=false,up=true,variant=%s,west=true\":{\"model\":\"%s:%s_ns_above\",\"y\":90,\"uvlock\":true},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=true,north=true,south=true,up=true,variant=%s,west=false\":{\"model\":\"%s:%s_nse\"},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=true,north=false,south=true,up=true,variant=%s,west=true\":{\"model\":\"%s:%s_nse\",\"y\":90,\"uvlock\":true},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=false,north=true,south=true,up=true,variant=%s,west=true\":{\"model\":\"%s:%s_nse\",\"y\":180,\"uvlock\":true},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=true,north=true,south=false,up=true,variant=%s,west=true\":{\"model\":\"%s:%s_nse\",\"y\":270,\"uvlock\":true},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"east=true,north=true,south=true,up=true,variant=%s,west=true\":{\"model\":\"%s:%s_nsew\"}%s", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile,
				            index < variantCount - 1 ? "," : ""
		            }));
		            ++index;
	            }

               printwriter.println("}");
               printwriter.println("}");
               printwriter.close();
            }
         }
      } catch (Exception var11) {
         var11.printStackTrace();
      }

   }

   private static void createHeadstoneFile(String rootPath, String name) {
      try {
         File jsonFile = new File(String.format("%s%s_headstone.json", new Object[]{rootPath, name}));
         if(jsonFile != null) {
            if(!jsonFile.exists()) {
               jsonFile.createNewFile();
            }

            PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
            if(printwriter != null) {
               printwriter.println("{");
               printwriter.println("\"variants\":{");
               int index = 0;
               int variantCount = DMPBlockBrickHeadstone.EnumType.values().length;
               DMPBlockBrickHeadstone.EnumType[] e = DMPBlockBrickHeadstone.EnumType.values();
               int var8 = e.length;

	            for (DMPBlockBrickHeadstone.EnumType variant : e) {
		            String modelFile = String.format("%s_headstone_%s", name, variant.name());
		            printwriter.println(String.format("\"facing=north_south,variant=%s\":{\"model\":\"%s:%s\"},", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile
		            }));
		            printwriter.println(String.format("\"facing=west_east,variant=%s\":{\"model\":\"%s:%s\",\"y\":90}%s", new Object[]{
				            variant.name(),
				            "mdeco",
				            modelFile,
				            index < variantCount - 1 ? "," : ""
		            }));
		            ++index;
	            }

               printwriter.println("}");
               printwriter.println("}");
               printwriter.close();
            }
         }
      } catch (Exception var11) {
         var11.printStackTrace();
      }

   }

   private static void createPillarFile(String rootPath, DMPBuildingBlock buildingBlock) {
      try {
         File jsonFile = new File(String.format("%s%s.json", new Object[]{rootPath, buildingBlock.name()}));
         if(jsonFile != null) {
            if(!jsonFile.exists()) {
               jsonFile.createNewFile();
            }

            PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
            if(printwriter != null) {
               printwriter.println("{");
               printwriter.println("\"variants\":{");
               printwriter.println(String.format("\"axis=x\":{\"model\":\"%s:%s_side\",\"y\":90},", new Object[]{"mdeco", buildingBlock.name()}));
               printwriter.println(String.format("\"axis=y\":{\"model\":\"%s:%s\"},", new Object[]{"mdeco", buildingBlock.name()}));
               printwriter.println(String.format("\"axis=z\":{\"model\":\"%s:%s_side\"},", new Object[]{"mdeco", buildingBlock.name()}));
               printwriter.println(String.format("\"axis=none\":{\"model\":\"%s:%s\"}", new Object[]{"mdeco", buildingBlock.name()}));
               printwriter.println("}");
               printwriter.println("}");
               printwriter.close();
            }
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }

   }
}
