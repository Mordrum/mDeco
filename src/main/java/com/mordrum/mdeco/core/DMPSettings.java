package com.mordrum.mdeco.core;

import com.mordrum.mdeco.MDeco;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class DMPSettings {
   protected File optionsFile;
   public boolean contentBarrelLarge;
   public boolean contentBarrelSmall;
   public boolean contentBenchStone;
   public boolean contentBenchWood;
   public boolean contentBenchWoodMetalArm;
   public boolean contentCabinetBase;
   public boolean contentCabinetWall;
   public boolean contentCabinetWallGlass;
   public boolean contentCaps;
   public boolean contentChain;
   public boolean contentChairBasic;
   public boolean contentChairBasicArm;
   public boolean contentChandelierSmallRound;
   public boolean contentCoinBag;
   public boolean contentCoinStack;
   public boolean contentCurioCabinet;
   public boolean contentCurtainRod;
   public boolean contentCurtainWool;
   public boolean contentDeskHutchWoodBasic;
   public boolean contentDeskWoodBasic;
   public boolean contentEndTableMetalRound;
   public boolean contentEndTableWood;
   public boolean contentFireplaceBellows;
   public boolean contentFireplacePokerSet;
   public boolean contentFireplaceScreen;
   public boolean contentFireplaceWoodStack;
   public boolean contentFoodTrough;
   public boolean contentFountainLava;
   public boolean contentFountainWater;
   public boolean contentIngotStack;
   public boolean contentLantern;
   public boolean contentMantle;
   public boolean contentMantleColumn;
   public boolean contentMarketCrate;
   public boolean contentMarketStand;
   public boolean contentPalletStack;
   public boolean contentPillarLarge;
   public boolean contentPillarSmall;
   public boolean contentPoleMetal;
   public boolean contentPoleSign;
   public boolean contentShelf;
   public boolean contentShopSignCornerCut;
   public boolean contentShopSignDome;
   public boolean contentShopSignGable;
   public boolean contentShopSignHexagon;
   public boolean contentShopSignRound;
   public boolean contentShopSignSquare;
   public boolean contentSofa;
   public boolean contentStandLargeMetal;
   public boolean contentStandLargeStone;
   public boolean contentStandSmallMetal;
   public boolean contentStandSmallStone;
   public boolean contentStandSmallWood;
   public boolean contentSupportPole;
   public boolean contentTableWood;
   public boolean contentTile;
   public boolean contentWallLantern;
   public boolean contentWaterTrough;
   public boolean contentWoodBox;
   public boolean contentWoodCrate;
   public boolean contentWoodPanel;
   public boolean contentWoodTimber;
   public boolean contentWoodTrim;
   public boolean updateNotify;
   public int workbenchSawCutTime;
   public boolean workbenchFoundryParticles;

   public DMPSettings(String configDirPath) {
      this.optionsFile = new File(configDirPath + "settings.cfg");
      this.initCoreSettings();
      this.resetContent();
      this.resetTools();
      this.checkConfigDirs(configDirPath);
      this.loadOptions();
   }

   private void checkConfigDirs(String configDirPath) {
      File pmpConfigDir = new File(configDirPath);
      if(!pmpConfigDir.exists()) {
         try {
            pmpConfigDir.mkdir();
            MDeco.instance.logOutput(String.format("Created config dir: %s", new Object[]{pmpConfigDir.getName()}));
         } catch (Exception var4) {
            var4.printStackTrace();
         }
      }

   }

   public void initCoreSettings() {
      this.updateNotify = true;
   }

   public void resetContent() {
      this.contentBarrelLarge = true;
      this.contentBarrelSmall = true;
      this.contentBenchStone = true;
      this.contentBenchWood = true;
      this.contentBenchWoodMetalArm = true;
      this.contentCabinetBase = true;
      this.contentCabinetWall = true;
      this.contentCabinetWallGlass = true;
      this.contentCaps = true;
      this.contentChain = true;
      this.contentChairBasic = true;
      this.contentChairBasicArm = true;
      this.contentChandelierSmallRound = true;
      this.contentCoinBag = true;
      this.contentCoinStack = true;
      this.contentCurioCabinet = true;
      this.contentCurtainRod = true;
      this.contentCurtainWool = true;
      this.contentDeskHutchWoodBasic = true;
      this.contentDeskWoodBasic = true;
      this.contentEndTableMetalRound = true;
      this.contentEndTableWood = true;
      this.contentFireplaceBellows = true;
      this.contentFireplacePokerSet = true;
      this.contentFireplaceScreen = true;
      this.contentFireplaceWoodStack = true;
      this.contentFoodTrough = true;
      this.contentFountainLava = true;
      this.contentFountainWater = true;
      this.contentIngotStack = true;
      this.contentLantern = true;
      this.contentMantle = true;
      this.contentMantleColumn = true;
      this.contentMarketCrate = true;
      this.contentMarketStand = true;
      this.contentPalletStack = true;
      this.contentPillarLarge = true;
      this.contentPillarSmall = true;
      this.contentPoleMetal = true;
      this.contentPoleSign = true;
      this.contentShelf = true;
      this.contentShopSignCornerCut = true;
      this.contentShopSignDome = true;
      this.contentShopSignGable = true;
      this.contentShopSignHexagon = true;
      this.contentShopSignRound = true;
      this.contentShopSignSquare = true;
      this.contentSofa = true;
      this.contentStandLargeMetal = true;
      this.contentStandLargeStone = true;
      this.contentStandSmallMetal = true;
      this.contentStandSmallStone = true;
      this.contentStandSmallWood = true;
      this.contentSupportPole = true;
      this.contentTableWood = true;
      this.contentTile = true;
      this.contentWallLantern = true;
      this.contentWaterTrough = true;
      this.contentWoodBox = true;
      this.contentWoodCrate = true;
      this.contentWoodPanel = true;
      this.contentWoodTimber = true;
      this.contentWoodTrim = true;
   }

   public void resetTools() {
      this.workbenchSawCutTime = 100;
      this.workbenchFoundryParticles = true;
   }

   public void loadOptions() {
      try {
         if(!this.optionsFile.exists()) {
            this.saveOptions(false);
            return;
         }

         BufferedReader exception1 = new BufferedReader(new FileReader(this.optionsFile));
         String line = "";

         while(true) {
            if((line = exception1.readLine()) == null) {
               exception1.close();
               break;
            }

            try {
               String[] exception = line.split(":");
               if(exception[0].equals("contentBarrelLarge")) {
                  this.contentBarrelLarge = exception[1].equals("true");
               } else if(exception[0].equals("contentBarrelSmall")) {
                  this.contentBarrelSmall = exception[1].equals("true");
               } else if(exception[0].equals("contentBenchStone")) {
                  this.contentBenchStone = exception[1].equals("true");
               } else if(exception[0].equals("contentBenchWood")) {
                  this.contentBenchWood = exception[1].equals("true");
               } else if(exception[0].equals("contentBenchWoodMetalArm")) {
                  this.contentBenchWoodMetalArm = exception[1].equals("true");
               } else if(exception[0].equals("contentCabinetBase")) {
                  this.contentCabinetBase = exception[1].equals("true");
               } else if(exception[0].equals("contentCabinetWall")) {
                  this.contentCabinetWall = exception[1].equals("true");
               } else if(exception[0].equals("contentCabinetWallGlass")) {
                  this.contentCabinetWallGlass = exception[1].equals("true");
               } else if(exception[0].equals("contentCaps")) {
                  this.contentCaps = exception[1].equals("true");
               } else if(exception[0].equals("contentChain")) {
                  this.contentChain = exception[1].equals("true");
               } else if(exception[0].equals("contentChairBasic")) {
                  this.contentChairBasic = exception[1].equals("true");
               } else if(exception[0].equals("contentChairBasicArm")) {
                  this.contentChairBasicArm = exception[1].equals("true");
               } else if(exception[0].equals("contentChandelierSmall")) {
                  this.contentChandelierSmallRound = exception[1].equals("true");
               } else if(exception[0].equals("contentCoinBag")) {
                  this.contentCoinBag = exception[1].equals("true");
               } else if(exception[0].equals("contentCoinStack")) {
                  this.contentCoinStack = exception[1].equals("true");
               } else if(exception[0].equals("contentCurioCabinet")) {
                  this.contentCurioCabinet = exception[1].equals("true");
               } else if(exception[0].equals("contentCurtainRod")) {
                  this.contentCurtainRod = exception[1].equals("true");
               } else if(exception[0].equals("contentCurtainWool")) {
                  this.contentCurtainWool = exception[1].equals("true");
               } else if(exception[0].equals("contentDeskHutchWoodBasic")) {
                  this.contentDeskHutchWoodBasic = exception[1].equals("true");
               } else if(exception[0].equals("contentDeskWoodBasic")) {
                  this.contentDeskWoodBasic = exception[1].equals("true");
               } else if(exception[0].equals("contentEndTableMetalRound")) {
                  this.contentEndTableMetalRound = exception[1].equals("true");
               } else if(exception[0].equals("contentEndTableWood")) {
                  this.contentEndTableWood = exception[1].equals("true");
               } else if(exception[0].equals("contentFireplaceBellows")) {
                  this.contentFireplaceBellows = exception[1].equals("true");
               } else if(exception[0].equals("contentFireplacePokerSet")) {
                  this.contentFireplacePokerSet = exception[1].equals("true");
               } else if(exception[0].equals("contentFireplaceScreen")) {
                  this.contentFireplaceScreen = exception[1].equals("true");
               } else if(exception[0].equals("contentFireplaceWoodStack")) {
                  this.contentFireplaceWoodStack = exception[1].equals("true");
               } else if(exception[0].equals("contentFoodTrough")) {
                  this.contentFoodTrough = exception[1].equals("true");
               } else if(exception[0].equals("contentFountainLava")) {
                  this.contentFountainLava = exception[1].equals("true");
               } else if(exception[0].equals("contentFountainWater")) {
                  this.contentFountainWater = exception[1].equals("true");
               } else if(exception[0].equals("contentIngotStack")) {
                  this.contentIngotStack = exception[1].equals("true");
               } else if(exception[0].equals("contentLantern")) {
                  this.contentLantern = exception[1].equals("true");
               } else if(exception[0].equals("contentMantle")) {
                  this.contentMantle = exception[1].equals("true");
               } else if(exception[0].equals("contentMantleColumn")) {
                  this.contentMantleColumn = exception[1].equals("true");
               } else if(exception[0].equals("contentMarketCrate")) {
                  this.contentMarketCrate = exception[1].equals("true");
               } else if(exception[0].equals("contentMarketStand")) {
                  this.contentMarketStand = exception[1].equals("true");
               } else if(exception[0].equals("contentPalletStack")) {
                  this.contentPalletStack = exception[1].equals("true");
               } else if(exception[0].equals("contentPillarLarge")) {
                  this.contentPillarLarge = exception[1].equals("true");
               } else if(exception[0].equals("contentPillarSmall")) {
                  this.contentPillarSmall = exception[1].equals("true");
               } else if(exception[0].equals("contentPoleMetal")) {
                  this.contentPoleMetal = exception[1].equals("true");
               } else if(exception[0].equals("contentPoleSign")) {
                  this.contentPoleSign = exception[1].equals("true");
               } else if(exception[0].equals("contentShelf")) {
                  this.contentShelf = exception[1].equals("true");
               } else if(exception[0].equals("contentShopSignCornerCut")) {
                  this.contentShopSignCornerCut = exception[1].equals("true");
               } else if(exception[0].equals("contentShopSignDome")) {
                  this.contentShopSignDome = exception[1].equals("true");
               } else if(exception[0].equals("contentShopSignGable")) {
                  this.contentShopSignGable = exception[1].equals("true");
               } else if(exception[0].equals("contentShopSignHexagon")) {
                  this.contentShopSignHexagon = exception[1].equals("true");
               } else if(exception[0].equals("contentShopSignRound")) {
                  this.contentShopSignRound = exception[1].equals("true");
               } else if(exception[0].equals("contentShopSignSquare")) {
                  this.contentShopSignSquare = exception[1].equals("true");
               } else if(exception[0].equals("contentSofa")) {
                  this.contentSofa = exception[1].equals("true");
               } else if(exception[0].equals("contentStandLargeMetal")) {
                  this.contentStandLargeMetal = exception[1].equals("true");
               } else if(exception[0].equals("contentStandLargeStone")) {
                  this.contentStandLargeStone = exception[1].equals("true");
               } else if(exception[0].equals("contentStandSmallMetal")) {
                  this.contentStandSmallMetal = exception[1].equals("true");
               } else if(exception[0].equals("contentStandSmallStone")) {
                  this.contentStandSmallStone = exception[1].equals("true");
               } else if(exception[0].equals("contentStandSmallWood")) {
                  this.contentStandSmallWood = exception[1].equals("true");
               } else if(exception[0].equals("contentSupportPole")) {
                  this.contentSupportPole = exception[1].equals("true");
               } else if(exception[0].equals("contentTableWood")) {
                  this.contentTableWood = exception[1].equals("true");
               } else if(exception[0].equals("contentTile")) {
                  this.contentTile = exception[1].equals("true");
               } else if(exception[0].equals("contentWallLantern")) {
                  this.contentWallLantern = exception[1].equals("true");
               } else if(exception[0].equals("contentWaterTrough")) {
                  this.contentWaterTrough = exception[1].equals("true");
               } else if(exception[0].equals("contentWoodBox")) {
                  this.contentWoodBox = exception[1].equals("true");
               } else if(exception[0].equals("contentWoodCrate")) {
                  this.contentWoodCrate = exception[1].equals("true");
               } else if(exception[0].equals("contentWoodPanel")) {
                  this.contentWoodPanel = exception[1].equals("true");
               } else if(exception[0].equals("contentWoodTimber")) {
                  this.contentWoodTimber = exception[1].equals("true");
               } else if(exception[0].equals("contentWoodTrim")) {
                  this.contentWoodTrim = exception[1].equals("true");
               } else if(exception[0].equals("workbenchSawCutTime")) {
                  this.workbenchSawCutTime = Integer.parseInt(exception[1]);
               } else if(exception[0].equals("workbenchFoundryParticles")) {
                  this.workbenchFoundryParticles = exception[1].equals("true");
               } else if(exception[0].equals("updateNotify")) {
                  this.updateNotify = exception[1].equals("true");
               } else {
                  MDeco.instance.logOutput(String.format("Unrecognized config option \"%s\" ignored" + exception[0], new Object[0]));
               }
            } catch (Exception var4) {
               MDeco.instance.logOutput("Skipping bad config line: " + line);
            }
         }
      } catch (Exception var5) {
         MDeco.instance.logOutput("Load config file: settings.cfg FAILED");
         return;
      }

      MDeco.instance.logOutput("Load config file: settings.cfg");
   }

   public void saveOptions(boolean fileUpgrade) {
      try {
         PrintWriter exception = new PrintWriter(new FileWriter(this.optionsFile));
         exception.println("contentBarrelLarge:" + this.contentBarrelLarge);
         exception.println("contentBarrelSmall:" + this.contentBarrelSmall);
         exception.println("contentBenchStone:" + this.contentBenchStone);
         exception.println("contentBenchWood:" + this.contentBenchWood);
         exception.println("contentBenchWoodMetalArm:" + this.contentBenchWoodMetalArm);
         exception.println("contentCabinetBase:" + this.contentCabinetBase);
         exception.println("contentCabinetWall:" + this.contentCabinetWall);
         exception.println("contentCabinetWallGlass:" + this.contentCabinetWallGlass);
         exception.println("contentCaps:" + this.contentCaps);
         exception.println("contentChain:" + this.contentChain);
         exception.println("contentChairBasic:" + this.contentChairBasic);
         exception.println("contentChairBasicArm:" + this.contentChairBasicArm);
         exception.println("contentChandelierSmall:" + this.contentChandelierSmallRound);
         exception.println("contentCoinBag:" + this.contentCoinBag);
         exception.println("contentCoinStack:" + this.contentCoinStack);
         exception.println("contentCurioCabinet:" + this.contentCurioCabinet);
         exception.println("contentCurtainRod:" + this.contentCurtainRod);
         exception.println("contentCurtainWool:" + this.contentCurtainWool);
         exception.println("contentDeskHutchWoodBasic:" + this.contentDeskHutchWoodBasic);
         exception.println("contentDeskWoodBasic:" + this.contentDeskWoodBasic);
         exception.println("contentEndTableMetalRound:" + this.contentEndTableMetalRound);
         exception.println("contentEndTableWood:" + this.contentEndTableWood);
         exception.println("contentFireplaceBellows:" + this.contentFireplaceBellows);
         exception.println("contentFireplacePokerSet:" + this.contentFireplacePokerSet);
         exception.println("contentFireplaceScreen:" + this.contentFireplaceScreen);
         exception.println("contentFireplaceWoodStack:" + this.contentFireplaceWoodStack);
         exception.println("contentFoodTrough:" + this.contentFoodTrough);
         exception.println("contentFountainLava:" + this.contentFountainLava);
         exception.println("contentFountainWater:" + this.contentFountainWater);
         exception.println("contentIngotStack:" + this.contentIngotStack);
         exception.println("contentLantern:" + this.contentLantern);
         exception.println("contentMantle:" + this.contentMantle);
         exception.println("contentMantleColumn:" + this.contentMantleColumn);
         exception.println("contentMarketCrate:" + this.contentMarketCrate);
         exception.println("contentMarketStand:" + this.contentMarketStand);
         exception.println("contentPalletStack:" + this.contentPalletStack);
         exception.println("contentPillarLarge:" + this.contentPillarLarge);
         exception.println("contentPillarSmall:" + this.contentPillarSmall);
         exception.println("contentPoleMetal:" + this.contentPoleMetal);
         exception.println("contentPoleSign:" + this.contentPoleSign);
         exception.println("contentShelf:" + this.contentShelf);
         exception.println("contentShopSignCornerCut:" + this.contentShopSignCornerCut);
         exception.println("contentShopSignDome:" + this.contentShopSignDome);
         exception.println("contentShopSignGable:" + this.contentShopSignGable);
         exception.println("contentShopSignHexagon:" + this.contentShopSignHexagon);
         exception.println("contentShopSignRound:" + this.contentShopSignRound);
         exception.println("contentShopSignSquare:" + this.contentShopSignSquare);
         exception.println("contentSofa:" + this.contentSofa);
         exception.println("contentStandLargeMetal:" + this.contentStandLargeMetal);
         exception.println("contentStandLargeStone:" + this.contentStandLargeStone);
         exception.println("contentStandSmallMetal:" + this.contentStandSmallMetal);
         exception.println("contentStandSmallStone:" + this.contentStandSmallStone);
         exception.println("contentStandSmallWood:" + this.contentStandSmallWood);
         exception.println("contentSupportPole:" + this.contentSupportPole);
         exception.println("contentTableWood:" + this.contentTableWood);
         exception.println("contentTile:" + this.contentTile);
         exception.println("contentWallLantern:" + this.contentWallLantern);
         exception.println("contentWaterTrough:" + this.contentWaterTrough);
         exception.println("contentWoodBox:" + this.contentWoodBox);
         exception.println("contentWoodCrate:" + this.contentWoodCrate);
         exception.println("contentWoodPanel:" + this.contentWoodPanel);
         exception.println("contentWoodTimber:" + this.contentWoodTimber);
         exception.println("contentWoodTrim:" + this.contentWoodTrim);
         exception.println("workbenchSawCutTime:" + this.workbenchSawCutTime);
         exception.println("workbenchFoundryParticles:" + this.workbenchFoundryParticles);
         exception.println("updateNotify:" + this.updateNotify);
         exception.close();
      } catch (Exception var3) {
         MDeco.instance.logOutput("Failed to save settings.cfg");
      }
   }
}
