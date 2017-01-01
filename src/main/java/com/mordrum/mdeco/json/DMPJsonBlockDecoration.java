package com.mordrum.mdeco.json;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.block.DMPBlockKitchenKettle;
import com.mordrum.mdeco.block.DMPBlockKitchenPot;
import com.mordrum.mdeco.block.DMPBlockKitchenShakers;
import com.mordrum.mdeco.block.DMPBlockKitchenWallUtensils;
import com.mordrum.mdeco.object.DMPDecoration;
import com.mordrum.mdeco.object.DMPDecorationType;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public abstract class DMPJsonBlockDecoration {
   public static int createBlockModelFiles(String rootPath) {
      int filesGenerated = 0;
      DMPDecoration[] var4 = DMPDecoration.values();
      int var5 = var4.length;

	   for (DMPDecoration decoration : var4) {
		   if (decoration.decorationType == DMPDecorationType.barrelLarge) {
			   createDecorationModelFile(decoration, rootPath, true, true, false);
			   ++filesGenerated;
		   } else if (decoration.decorationType == DMPDecorationType.barrelSmall) {
			   createDecorationModelFile(decoration, rootPath, true, true, false);
			   ++filesGenerated;
		   } else {
			   int index;
			   if (decoration.decorationType == DMPDecorationType.benchWood) {
				   for (index = 0; index < 4; ++index) {
					   createDecorationModelFileWithVariant(decoration, rootPath, index, true, false);
					   ++filesGenerated;
				   }
			   } else if (decoration.decorationType == DMPDecorationType.benchWoodMetalArm) {
				   for (index = 0; index < 4; ++index) {
					   createDecorationModelFileWithVariant(decoration, rootPath, index, false, true);
					   ++filesGenerated;
				   }
			   } else if (decoration.decorationType == DMPDecorationType.benchStone) {
				   for (index = 0; index < 4; ++index) {
					   createDecorationModelFileWithVariant(decoration, rootPath, index, false, false);
					   ++filesGenerated;
				   }
			   } else if (decoration.decorationType == DMPDecorationType.cabinetBase) {
				   createDecorationModelFile(decoration, rootPath, true, true, false);
				   ++filesGenerated;
			   } else if (decoration.decorationType == DMPDecorationType.cabinetWall) {
				   createDecorationModelFile(decoration, rootPath, true, true, false);
				   ++filesGenerated;
			   } else if (decoration.decorationType == DMPDecorationType.cabinetWallGlass) {
				   createDecorationModelFile(decoration, rootPath, true, true, true);
				   ++filesGenerated;
			   } else if (decoration.decorationType != DMPDecorationType.capSmallPyramid &&
					   decoration.decorationType != DMPDecorationType.capLargePyramid &&
					   decoration.decorationType != DMPDecorationType.capOval &&
					   decoration.decorationType != DMPDecorationType.capPlus &&
					   decoration.decorationType != DMPDecorationType.capRound &&
					   decoration.decorationType != DMPDecorationType.capSquare) {
				   if (decoration.decorationType != DMPDecorationType.chain &&
						   decoration.decorationType != DMPDecorationType.chainLarge) {
					   if (decoration.decorationType == DMPDecorationType.chandelierSmallRound) {
						   createChandelierModelFile(decoration, rootPath, true);
						   ++filesGenerated;
					   } else if (decoration.decorationType == DMPDecorationType.coinBag) {
						   createDecorationModelFile(decoration, rootPath, true, true, false);
						   ++filesGenerated;
					   } else if (decoration.decorationType == DMPDecorationType.curtainRod) {
						   createDecorationModelFileWithVariant(decoration, rootPath, 0, true, false);
						   createDecorationModelFileWithVariant(decoration, rootPath, 1, true, false);
						   filesGenerated += 2;
					   } else if (decoration.decorationType == DMPDecorationType.curtainWool) {
						   createDecorationModelFileWithVariant(decoration, rootPath, 0, true, false);
						   createDecorationModelFileWithVariant(decoration, rootPath, 1, true, false);
						   filesGenerated += 2;
					   } else if (decoration.decorationType == DMPDecorationType.deskHutchWoodBasic) {
						   createDecorationModelFile(decoration, rootPath, true, true, false);
						   ++filesGenerated;
					   } else if (decoration.decorationType == DMPDecorationType.deskWoodBasic) {
						   createDecorationModelFile(decoration, rootPath, true, true, false);
						   ++filesGenerated;
					   } else if (decoration.decorationType == DMPDecorationType.endTableMetalRound) {
						   createDecorationModelFile(decoration, rootPath, false, true, false);
						   ++filesGenerated;
					   } else if (decoration.decorationType == DMPDecorationType.fireplaceBellows) {
						   createDecorationModelFile(decoration, rootPath, true, true, false);
						   ++filesGenerated;
					   } else if (decoration.decorationType == DMPDecorationType.fireplacePokerSet) {
						   createDecorationModelFile(decoration, rootPath, true, true, false);
						   ++filesGenerated;
					   } else if (decoration.decorationType == DMPDecorationType.fireplaceScreen) {
						   for (index = 0; index < 8; ++index) {
							   createDecorationModelFileWithVariant(decoration, rootPath, index, true, false);
							   ++filesGenerated;
						   }
					   } else if (decoration.decorationType == DMPDecorationType.fireplaceWoodRack) {
						   createDecorationModelFile(decoration, rootPath, true, true, false);
						   ++filesGenerated;
					   } else if (decoration.decorationType != DMPDecorationType.foodTrough &&
							   decoration.decorationType != DMPDecorationType.waterTrough) {
						   if (decoration.decorationType != DMPDecorationType.fountainLava &&
								   decoration.decorationType != DMPDecorationType.fountainWater) {
							   if (decoration.decorationType == DMPDecorationType.ingotStack) {
								   for (index = 0; index < 6; ++index) {
									   createDecorationModelFileWithVariant(decoration, rootPath, index, true, true);
									   ++filesGenerated;
								   }
							   } else {
								   int variantCount;
								   if (decoration.decorationType == DMPDecorationType.kitchenKettle) {
									   variantCount = DMPBlockKitchenKettle.EnumType.values().length;

									   for (index = 0; index < variantCount; ++index) {
										   createDecorationModelFileWithVariant(decoration, rootPath, index, true, false);
										   ++filesGenerated;
									   }
								   } else if (decoration.decorationType == DMPDecorationType.kitchenPot) {
									   variantCount = DMPBlockKitchenPot.EnumType.values().length;

									   for (index = 0; index < variantCount; ++index) {
										   createDecorationModelFileWithVariant(decoration, rootPath, index, true, false);
										   ++filesGenerated;
									   }
								   } else if (decoration.decorationType == DMPDecorationType.kitchenShakers) {
									   variantCount = DMPBlockKitchenShakers.EnumType.values().length;

									   for (index = 0; index < variantCount; ++index) {
										   createDecorationModelFileWithVariant(decoration, rootPath, index, true, true);
										   ++filesGenerated;
									   }
								   } else if (decoration.decorationType == DMPDecorationType.kitchenTableSetting) {
									   createDecorationModelFile(decoration, rootPath, true, true, false);
									   ++filesGenerated;
								   } else if (decoration.decorationType == DMPDecorationType.kitchenWallUtensils) {
									   variantCount = DMPBlockKitchenWallUtensils.EnumType.values().length;

									   for (index = 0; index < variantCount; ++index) {
										   createDecorationModelFileWithVariant(decoration, rootPath, index, false, false);
										   ++filesGenerated;
									   }
								   } else if ((decoration.decorationType == DMPDecorationType.lanternOval ||
										   decoration.decorationType == DMPDecorationType.lanternPyramid ||
										   decoration.decorationType == DMPDecorationType.lanternPyramidGlass ||
										   decoration.decorationType == DMPDecorationType.lanternRectangle ||
										   decoration.decorationType == DMPDecorationType.lanternRectangleGlass ||
										   decoration.decorationType == DMPDecorationType.lanternRound) &&
										   MDeco.settings.contentLantern) {
									   for (index = 0; index < 6; ++index) {
										   createLanternModelFile(decoration, rootPath, index, true);
										   ++filesGenerated;
									   }
								   } else if (decoration.decorationType == DMPDecorationType.mantle) {
									   for (index = 0; index < 8; ++index) {
										   createDecorationModelFileWithVariant(decoration, rootPath, index, true, false);
										   ++filesGenerated;
									   }
								   } else if (decoration.decorationType == DMPDecorationType.mantleColumn) {
									   for (index = 0; index < 4; ++index) {
										   createDecorationModelFileWithVariant(decoration, rootPath, index, true, false);
										   ++filesGenerated;
									   }
								   } else if (decoration.decorationType == DMPDecorationType.marketCrate) {
									   for (index = 0; index < 4; ++index) {
										   createDecorationModelFileWithVariant(decoration, rootPath, index, true, false);
										   ++filesGenerated;
									   }
								   } else if (decoration.decorationType == DMPDecorationType.marketStand) {
									   for (index = 0; index < 4; ++index) {
										   createDecorationModelFileWithVariant(decoration, rootPath, index, true, true);
										   ++filesGenerated;
									   }
								   } else if (decoration.decorationType == DMPDecorationType.palletStack) {
									   for (index = 0; index < 5; ++index) {
										   createDecorationModelFileWithVariant(decoration, rootPath, index, true, false);
										   ++filesGenerated;
									   }
								   } else if (decoration.decorationType == DMPDecorationType.poleMetal) {
									   for (index = 0; index < 5; ++index) {
										   createDecorationModelFileWithVariant(decoration, rootPath, index, false, false);
										   ++filesGenerated;
									   }
								   } else if (decoration.decorationType == DMPDecorationType.poleMetalConnector) {
									   for (index = 0; index < 12; ++index) {
										   createDecorationModelFileWithVariant(decoration, rootPath, index, false, false);
										   ++filesGenerated;
									   }
								   } else if (decoration.decorationType == DMPDecorationType.poleSign) {
									   createDecorationModelFile(decoration, rootPath, false, true, false);
									   ++filesGenerated;
								   } else if (decoration.decorationType == DMPDecorationType.shelf) {
									   createDecorationModelFile(decoration, rootPath, true, false, false);
									   ++filesGenerated;
								   } else if (decoration.decorationType != DMPDecorationType.shopSignCornerCut &&
										   decoration.decorationType != DMPDecorationType.shopSignDome &&
										   decoration.decorationType != DMPDecorationType.shopSignGable &&
										   decoration.decorationType != DMPDecorationType.shopSignHexagon &&
										   decoration.decorationType != DMPDecorationType.shopSignRound &&
										   decoration.decorationType != DMPDecorationType.shopSignSquare) {
									   if (decoration.decorationType == DMPDecorationType.sofa) {
										   for (index = 0; index < 5; ++index) {
											   createDecorationModelFileWithVariant(decoration, rootPath, index, true, false);
											   ++filesGenerated;
										   }
									   } else if (decoration.decorationType != DMPDecorationType.standSmallMetal &&
											   decoration.decorationType != DMPDecorationType.standSmallStone) {
										   if (decoration.decorationType == DMPDecorationType.supportPole) {
											   createDecorationModelFile(decoration, rootPath, false, false, false);
											   ++filesGenerated;
										   } else if (decoration.decorationType == DMPDecorationType.tableWood) {
											   for (index = 0; index < 16; ++index) {
												   createDecorationModelFileWithVariant(decoration, rootPath, index, true, false);
												   ++filesGenerated;
											   }
										   } else if (decoration.decorationType == DMPDecorationType.wallLantern) {
											   createDecorationModelFile(decoration, rootPath, true, true, false);
											   ++filesGenerated;
										   } else if (decoration.decorationType == DMPDecorationType.woodCrate) {
											   for (index = 0; index < 9; ++index) {
												   createDecorationModelFileWithVariant(decoration, rootPath, index, false, true);
												   ++filesGenerated;
											   }
										   } else if (decoration.decorationType == DMPDecorationType.woodPanel) {
											   for (index = 0; index < 2; ++index) {
												   createDecorationModelFileWithVariant(decoration, rootPath, index, true, false);
												   ++filesGenerated;
											   }
										   } else if (decoration.decorationType != DMPDecorationType.woodTimber &&
												   decoration.decorationType != DMPDecorationType.woodBarkTimber) {
											   if (decoration.decorationType != DMPDecorationType.woodTimberConnector &&
													   decoration.decorationType !=
															   DMPDecorationType.woodBarkTimberConnector) {
												   if (decoration.decorationType == DMPDecorationType.woodTrim) {
													   for (index = 0; index < 3; ++index) {
														   createDecorationModelFileWithVariant(decoration, rootPath, index, true, false);
														   ++filesGenerated;
													   }
												   } else {
													   createDecorationModelFile(decoration, rootPath, true, false, false);
													   ++filesGenerated;
												   }
											   } else {
												   for (index = 0; index < 12; ++index) {
													   createDecorationModelFileWithVariant(decoration, rootPath, index, true, false);
													   ++filesGenerated;
												   }
											   }
										   } else {
											   for (index = 0; index < 5; ++index) {
												   createDecorationModelFileWithVariant(decoration, rootPath, index, true, false);
												   ++filesGenerated;
											   }
										   }
									   } else {
										   for (index = 0; index < 4; ++index) {
											   createDecorationModelFileWithVariant(decoration, rootPath, index, true, false);
											   ++filesGenerated;
										   }
									   }
								   } else {
									   createDecorationModelFile(decoration, rootPath, true, true, false);
									   ++filesGenerated;
								   }
							   }
						   } else {
							   createDecorationModelFile(decoration, rootPath, true, true, false);
							   ++filesGenerated;
						   }
					   } else {
						   for (index = 0; index < 5; ++index) {
							   createDecorationModelFileWithVariant(decoration, rootPath, index, true, true);
							   ++filesGenerated;
						   }
					   }
				   } else {
					   createDecorationModelFile(decoration, rootPath, false, false, false);
					   ++filesGenerated;
				   }
			   } else {
				   createDecorationModelFile(decoration, rootPath, false, false, false);
				   ++filesGenerated;
			   }
		   }
	   }

      filesGenerated += createBlockFiles(rootPath);
      filesGenerated += createWorkbenchFiles(rootPath);
      return filesGenerated;
   }

   private static int createBlockFiles(String rootPath) {
      byte filesGenerated = 0;
      String blockName = MDeco.blocks.blockBlackIron.getUnlocalizedName().substring(5);
      createBlockModelFile(rootPath, blockName);
	   return filesGenerated + 1;
   }

   private static int createWorkbenchFiles(String rootPath) {
      byte filesGenerated = 0;
      String blockName = MDeco.blocks.workbenchFoundry.getUnlocalizedName().substring(5);
      createNoStateWorkbenchModelFile(rootPath, blockName);
      int filesGenerated1 = filesGenerated + 2;
      blockName = MDeco.blocks.workbenchSaw.getUnlocalizedName().substring(5);
      createWorkbenchModelFile(rootPath, blockName, false);
      createWorkbenchModelFile(rootPath, blockName, true);
      filesGenerated1 += 3;
      return filesGenerated1;
   }

   private static void createBlockModelFile(String rootPath, String blockName) {
      File jsonFile = new File(String.format("%s%s.json", new Object[]{rootPath, blockName}));

      try {
         if(!jsonFile.exists()) {
            jsonFile.createNewFile();
         }

         if(jsonFile != null) {
            PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
            if(printwriter != null) {
               printwriter.println("{");
               printwriter.println("\"parent\":\"block/cube_all\",");
               printwriter.println("\"textures\":{");
               printwriter.println(String.format("\"all\":\"mdeco:blocks/%s\"}", new Object[]{blockName}));
               printwriter.println("}");
               printwriter.close();
            }
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }

   }

   private static void createNoStateWorkbenchModelFile(String rootPath, String blockName) {
      File jsonFile = new File(String.format("%s%s.json", new Object[]{rootPath, blockName}));

      try {
         if(!jsonFile.exists()) {
            jsonFile.createNewFile();
         }

         if(jsonFile != null) {
            PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
            if(printwriter != null) {
               printwriter.println("{");
               printwriter.println("\"parent\":\"mdeco:block/_" + blockName + "\",");
               printwriter.println("\"textures\":{");
               printwriter.println(String.format("\"top\":\"mdeco:blocks/%s0\",", new Object[]{blockName}));
               printwriter.println(String.format("\"side\":\"mdeco:blocks/%s1\",", new Object[]{blockName}));
               printwriter.println(String.format("\"back\":\"mdeco:blocks/%s2\",", new Object[]{blockName}));
               printwriter.println(String.format("\"front\":\"mdeco:blocks/%s3\",", new Object[]{blockName}));
               printwriter.println(String.format("\"bottom\":\"mdeco:blocks/%s4\"}", new Object[]{blockName}));
               printwriter.println("}");
               printwriter.close();
            }
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }

   }

   private static void createWorkbenchModelFile(String rootPath, String blockName, boolean active) {
      File jsonFile = new File(String.format("%s%s%d.json", new Object[]{rootPath, blockName, Integer.valueOf(active?1:0)}));

      try {
         if(!jsonFile.exists()) {
            jsonFile.createNewFile();
         }

         if(jsonFile != null) {
            PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
            if(printwriter != null) {
               printwriter.println("{");
               printwriter.println("\"parent\":\"mdeco:block/_" + blockName + "\",");
               printwriter.println("\"textures\":{");
               printwriter.println(String.format("\"top\":\"mdeco:blocks/%s0\",", new Object[]{blockName}));
               printwriter.println(String.format("\"side\":\"mdeco:blocks/%s1\",", new Object[]{blockName}));
               printwriter.println(String.format("\"back\":\"mdeco:blocks/%s2\",", new Object[]{blockName}));
               printwriter.println(String.format("\"front\":\"mdeco:blocks/%s%d\",", new Object[]{blockName, Integer.valueOf(active?4:3)}));
               printwriter.println(String.format("\"bottom\":\"mdeco:blocks/%s5\"}", new Object[]{blockName}));
               printwriter.println("}");
               printwriter.close();
            }
         }
      } catch (Exception var6) {
         var6.printStackTrace();
      }

   }

   private static void createDecorationModelFile(DMPDecoration decoration, String rootPath, boolean trim, boolean hardware, boolean extra) {
      File jsonFile = new File(String.format("%s%s.json", new Object[]{rootPath, decoration.name()}));

      try {
         if(!jsonFile.exists()) {
            jsonFile.createNewFile();
         }

         if(jsonFile != null) {
            PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
            if(printwriter != null) {
               printwriter.println("{");
               printwriter.println("\"parent\":\"mdeco:block/_" + decoration.decorationType.name() + "\",");
               printwriter.println("\"textures\":{");
               if(!trim && !hardware && !extra) {
                  printwriter.println("\"primary\":\"" + decoration.texturePrimary + "\"}");
               } else {
                  printwriter.println("\"primary\":\"" + decoration.texturePrimary + "\",");
               }

               if(trim) {
                  if(!hardware && !extra) {
                     printwriter.println("\"trim\":\"" + decoration.textureTrim + "\"}");
                  } else {
                     printwriter.println("\"trim\":\"" + decoration.textureTrim + "\",");
                  }
               }

               if(hardware) {
                  if(!extra) {
                     printwriter.println("\"hardware\":\"" + decoration.textureHardware + "\"}");
                  } else {
                     printwriter.println("\"hardware\":\"" + decoration.textureHardware + "\",");
                  }
               }

               if(extra) {
                  printwriter.println("\"extra\":\"" + decoration.textureExtra + "\"}");
               }

               printwriter.println("}");
               printwriter.close();
            }
         }
      } catch (Exception var8) {
         var8.printStackTrace();
      }

   }

   private static void createDecorationModelFileWithVariant(DMPDecoration decoration, String rootPath, int variant, boolean trim, boolean hardware) {
      File jsonFile = new File(String.format("%s%s%d.json", new Object[]{rootPath, decoration.name(), Integer.valueOf(variant)}));

      try {
         if(!jsonFile.exists()) {
            jsonFile.createNewFile();
         }

         if(jsonFile != null) {
            PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
            if(printwriter != null) {
               printwriter.println("{");
               printwriter.println(String.format("\"parent\":\"mdeco:block/_" + decoration.decorationType.name() + "%d\",", new Object[]{Integer.valueOf(variant)}));
               printwriter.println("\"textures\":{");
               if(trim) {
                  printwriter.println("\"primary\":\"" + decoration.texturePrimary + "\",");
                  if(hardware) {
                     printwriter.println("\"trim\":\"" + decoration.textureTrim + "\",");
                     printwriter.println("\"hardware\":\"" + decoration.textureHardware + "\"}");
                  } else {
                     printwriter.println("\"trim\":\"" + decoration.textureTrim + "\"}");
                  }
               } else if(hardware) {
                  printwriter.println("\"primary\":\"" + decoration.texturePrimary + "\",");
                  printwriter.println("\"hardware\":\"" + decoration.textureHardware + "\"}");
               } else {
                  printwriter.println("\"primary\":\"" + decoration.texturePrimary + "\"}");
               }

               printwriter.println("}");
               printwriter.close();
            }
         }
      } catch (Exception var8) {
         var8.printStackTrace();
      }

   }

   private static void createChandelierModelFile(DMPDecoration decoration, String rootPath, boolean trim) {
      File jsonFile = new File(String.format("%s%s.json", new Object[]{rootPath, decoration.name()}));

      try {
         if(!jsonFile.exists()) {
            jsonFile.createNewFile();
         }

         if(jsonFile != null) {
            PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
            if(printwriter != null) {
               printwriter.println("{");
               printwriter.println("\"parent\":\"mdeco:block/_" + decoration.decorationType.name() + "\",");
               printwriter.println("\"textures\":{");
               printwriter.println("\"primary\":\"" + decoration.texturePrimary + "\",");
               if(trim && !decoration.textureTrim.isEmpty()) {
                  printwriter.println("\"trim\":\"" + decoration.textureTrim + "\",");
               }

               printwriter.println("\"candle\":\"" + decoration.textureHardware + "\"}");
               printwriter.println("}");
               printwriter.close();
            }
         }
      } catch (Exception var6) {
         var6.printStackTrace();
      }

   }

   private static void createLanternModelFile(DMPDecoration decoration, String rootPath, int variant, boolean trim) {
      File jsonFile = new File(String.format("%s%s%d.json", new Object[]{rootPath, decoration.name(), Integer.valueOf(variant)}));

      try {
         if(!jsonFile.exists()) {
            jsonFile.createNewFile();
         }

         if(jsonFile != null) {
            PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
            if(printwriter != null) {
               printwriter.println("{");
               printwriter.println(String.format("\"parent\":\"mdeco:block/_" + decoration.decorationType.name() + "%d\",", new Object[]{Integer.valueOf(variant)}));
               printwriter.println("\"textures\":{");
               printwriter.println("\"primary\":\"" + decoration.texturePrimary + "\",");
               if(trim && !decoration.textureTrim.isEmpty()) {
                  printwriter.println("\"trim\":\"" + decoration.textureTrim + "\",");
               }

               printwriter.println("\"candle\":\"" + decoration.textureHardware + "\"}");
               printwriter.println("}");
               printwriter.close();
            }
         }
      } catch (Exception var7) {
         var7.printStackTrace();
      }

   }
}
