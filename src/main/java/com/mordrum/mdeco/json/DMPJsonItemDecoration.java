package com.mordrum.mdeco.json;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.object.DMPDecoration;
import com.mordrum.mdeco.object.DMPDecorationType;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public abstract class DMPJsonItemDecoration {
   public static int createItemModelFiles(String rootPath) {
      int filesGenerated = 0;
      DMPDecoration[] unlocalizedName = DMPDecoration.values();
      int var3 = unlocalizedName.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         DMPDecoration decoration = unlocalizedName[var4];
         if(decoration.decorationType == DMPDecorationType.barrelSmall) {
            createBlockModelFile(rootPath + decoration.name(), decoration.name());
         } else if(decoration.decorationType != DMPDecorationType.benchWood && decoration.decorationType != DMPDecorationType.benchWoodMetalArm && decoration.decorationType != DMPDecorationType.benchStone) {
            if(decoration.decorationType != DMPDecorationType.chairBasic && decoration.decorationType != DMPDecorationType.chairBasicArm) {
               if(decoration.decorationType != DMPDecorationType.coinBag && decoration.decorationType != DMPDecorationType.coinStack) {
                  if(decoration.decorationType == DMPDecorationType.curtainRod) {
                     createBlockModelFile(rootPath + decoration.name(), decoration.name() + "0");
                  } else if(decoration.decorationType == DMPDecorationType.curtainWool) {
                     createBlockModelFile(rootPath + decoration.name(), decoration.name() + "0");
                  } else if(decoration.decorationType == DMPDecorationType.deskHutchWoodBasic) {
                     createBlockModelFile(rootPath + decoration.name(), decoration.name());
                  } else if(decoration.decorationType == DMPDecorationType.deskWoodBasic) {
                     createBlockModelFile(rootPath + decoration.name(), decoration.name());
                  } else if(decoration.decorationType == DMPDecorationType.endTableMetalRound) {
                     createBlockModelFile(rootPath + decoration.name(), decoration.name());
                  } else if(decoration.decorationType == DMPDecorationType.fireplaceScreen) {
                     createBlockModelFile(rootPath + decoration.name(), decoration.name() + "0");
                  } else if(decoration.decorationType != DMPDecorationType.foodTrough && decoration.decorationType != DMPDecorationType.waterTrough) {
                     if(decoration.decorationType == DMPDecorationType.ingotStack) {
                        createBlockModelFile(rootPath + decoration.name(), decoration.name() + "0");
                        createBlockModelFile(rootPath + decoration.name() + "0", decoration.name() + "0");
                        createBlockModelFile(rootPath + decoration.name() + "1", decoration.name() + "1");
                        createBlockModelFile(rootPath + decoration.name() + "2", decoration.name() + "2");
                        createBlockModelFile(rootPath + decoration.name() + "3", decoration.name() + "3");
                        createBlockModelFile(rootPath + decoration.name() + "4", decoration.name() + "4");
                        createBlockModelFile(rootPath + decoration.name() + "5", decoration.name() + "5");
                     } else if(decoration.decorationType == DMPDecorationType.kitchenKettle) {
                        createBlockModelFile(rootPath + decoration.name(), decoration.name() + "0");
                     } else if(decoration.decorationType == DMPDecorationType.kitchenPot) {
                        createBlockModelFile(rootPath + decoration.name(), decoration.name() + "0");
                     } else if(decoration.decorationType == DMPDecorationType.kitchenShakers) {
                        createBlockModelFile(rootPath + decoration.name(), decoration.name() + "0");
                     } else if(decoration.decorationType == DMPDecorationType.kitchenWallUtensils) {
                        createBlockModelFile(rootPath + decoration.name(), decoration.name() + "0");
                     } else if((decoration.decorationType == DMPDecorationType.lanternOval || decoration.decorationType == DMPDecorationType.lanternPyramid || decoration.decorationType == DMPDecorationType.lanternPyramidGlass || decoration.decorationType == DMPDecorationType.lanternRectangle || decoration.decorationType == DMPDecorationType.lanternRectangleGlass || decoration.decorationType == DMPDecorationType.lanternRound) && MDeco.settings.contentLantern) {
                        createBlockModelFile(rootPath + decoration.name(), decoration.name() + "0");
                     } else if(decoration.decorationType == DMPDecorationType.mantle) {
                        createBlockModelFile(rootPath + decoration.name(), decoration.name() + "3");
                     } else if(decoration.decorationType == DMPDecorationType.mantleColumn) {
                        createBlockModelFile(rootPath + decoration.name(), decoration.name() + "0");
                     } else if(decoration.decorationType == DMPDecorationType.marketCrate) {
                        createBlockModelFile(rootPath + decoration.name(), decoration.name() + "0");
                     } else if(decoration.decorationType == DMPDecorationType.marketStand) {
                        createBlockModelFile(rootPath + decoration.name(), decoration.name() + "0");
                     } else if(decoration.decorationType == DMPDecorationType.palletStack) {
                        createBlockModelFile(rootPath + decoration.name(), decoration.name() + "0");
                        createBlockModelFile(rootPath + decoration.name() + "0", decoration.name() + "0");
                        createBlockModelFile(rootPath + decoration.name() + "1", decoration.name() + "1");
                        createBlockModelFile(rootPath + decoration.name() + "2", decoration.name() + "2");
                        createBlockModelFile(rootPath + decoration.name() + "3", decoration.name() + "3");
                        createBlockModelFile(rootPath + decoration.name() + "4", decoration.name() + "4");
                     } else if(decoration.decorationType == DMPDecorationType.poleMetal) {
                        createBlockModelFile(rootPath + decoration.name(), decoration.name() + "3");
                     } else if(decoration.decorationType == DMPDecorationType.poleMetalConnector) {
                        createBlockModelFile(rootPath + decoration.name(), decoration.name() + "8");
                     } else if(decoration.decorationType == DMPDecorationType.sofa) {
                        createBlockModelFile(rootPath + decoration.name(), decoration.name() + "0");
                     } else if(decoration.decorationType != DMPDecorationType.standSmallMetal && decoration.decorationType != DMPDecorationType.standSmallStone) {
                        if(decoration.decorationType != DMPDecorationType.shopSignCornerCut && decoration.decorationType != DMPDecorationType.shopSignDome && decoration.decorationType != DMPDecorationType.shopSignGable && decoration.decorationType != DMPDecorationType.shopSignHexagon && decoration.decorationType != DMPDecorationType.shopSignRound && decoration.decorationType != DMPDecorationType.shopSignSquare) {
                           if(decoration.decorationType == DMPDecorationType.tableWood) {
                              createBlockModelFile(rootPath + decoration.name(), decoration.name() + "0");
                           } else if(decoration.decorationType == DMPDecorationType.wallLantern) {
                              createBlockModelFile(rootPath + decoration.name(), decoration.name());
                           } else if(decoration.decorationType == DMPDecorationType.woodCrate) {
                              createBlockModelFile(rootPath + decoration.name(), decoration.name() + "0");
                           } else if(decoration.decorationType == DMPDecorationType.woodPanel) {
                              createBlockModelFile(rootPath + decoration.name(), decoration.name() + "0");
                           } else if(decoration.decorationType != DMPDecorationType.woodTimber && decoration.decorationType != DMPDecorationType.woodBarkTimber) {
                              if(decoration.decorationType != DMPDecorationType.woodTimberConnector && decoration.decorationType != DMPDecorationType.woodBarkTimberConnector) {
                                 if(decoration.decorationType == DMPDecorationType.woodTrim) {
                                    createBlockModelFile(rootPath + decoration.name(), decoration.name() + "0");
                                 } else {
                                    createBlockModelFile(rootPath + decoration.name(), decoration.name());
                                 }
                              } else {
                                 createBlockModelFile(rootPath + decoration.name(), decoration.name() + "8");
                              }
                           } else {
                              createBlockModelFile(rootPath + decoration.name(), decoration.name() + "3");
                           }
                        } else {
                           createBlockModelFile(rootPath + decoration.name(), decoration.name());
                        }
                     } else {
                        createBlockModelFile(rootPath + decoration.name(), decoration.name() + "3");
                     }
                  } else {
                     createBlockModelFile(rootPath + decoration.name(), decoration.name() + "0");
                  }
               } else {
                  createBlockModelFile(rootPath + decoration.name(), decoration.name());
               }
            } else {
               createBlockModelFile(rootPath + decoration.name(), decoration.name());
            }
         } else {
            createBlockModelFile(rootPath + decoration.name(), decoration.name() + "0");
         }

         ++filesGenerated;
      }

      String var6 = MDeco.blocks.blockBlackIron.getUnlocalizedName().substring(5);
      createBlockModelFile(rootPath + var6, var6);
      ++filesGenerated;
      var6 = MDeco.items.blackIronIngot.getUnlocalizedName().substring(5);
      createItemModelFile(rootPath + var6, var6);
      ++filesGenerated;
      var6 = MDeco.items.blackIronNugget.getUnlocalizedName().substring(5);
      createItemModelFile(rootPath + var6, var6);
      ++filesGenerated;
      var6 = MDeco.blocks.workbenchFoundry.getUnlocalizedName().substring(5);
      createBlockModelFile(rootPath + var6, var6);
      ++filesGenerated;
      var6 = MDeco.blocks.workbenchSaw.getUnlocalizedName().substring(5);
      createBlockModelFile(rootPath + var6, var6 + "0");
      ++filesGenerated;
      return filesGenerated;
   }

   private static void createBlockModelFile(String file, String texture) {
      File jsonFile = new File(file + ".json");

      try {
         if(jsonFile != null) {
            if(!jsonFile.exists()) {
               jsonFile.createNewFile();
            }

            PrintWriter e = new PrintWriter(new FileWriter(jsonFile));
            if(e != null) {
               e.println("{");
               e.println(String.format("\"parent\":\"mdeco:block/%s\"", new Object[]{texture}));
               e.println("}");
               e.close();
            }
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   private static void createItemModelFile(String file, String model) {
      File jsonFile = new File(file + ".json");

      try {
         if(jsonFile != null) {
            if(!jsonFile.exists()) {
               jsonFile.createNewFile();
            }

            PrintWriter e = new PrintWriter(new FileWriter(jsonFile));
            if(e != null) {
               e.println("{");
               e.println("\"parent\":\"item/generated\",");
               e.println("\"textures\":{");
               e.println(String.format("\"layer0\":\"mdeco:items/%s\"", new Object[]{model}));
               e.println("}");
               e.println("}");
               e.close();
            }
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }
}
