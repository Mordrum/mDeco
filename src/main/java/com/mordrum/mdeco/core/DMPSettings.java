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
            MDeco.instance.logOutput(String.format("Created config dir: %s", pmpConfigDir.getName()));
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
	            switch (exception[0]) {
		            case "contentBarrelLarge":
			            this.contentBarrelLarge = exception[1].equals("true");
			            break;
		            case "contentBarrelSmall":
			            this.contentBarrelSmall = exception[1].equals("true");
			            break;
		            case "contentBenchStone":
			            this.contentBenchStone = exception[1].equals("true");
			            break;
		            case "contentBenchWood":
			            this.contentBenchWood = exception[1].equals("true");
			            break;
		            case "contentBenchWoodMetalArm":
			            this.contentBenchWoodMetalArm = exception[1].equals("true");
			            break;
		            case "contentCabinetBase":
			            this.contentCabinetBase = exception[1].equals("true");
			            break;
		            case "contentCabinetWall":
			            this.contentCabinetWall = exception[1].equals("true");
			            break;
		            case "contentCabinetWallGlass":
			            this.contentCabinetWallGlass = exception[1].equals("true");
			            break;
		            case "contentCaps":
			            this.contentCaps = exception[1].equals("true");
			            break;
		            case "contentChain":
			            this.contentChain = exception[1].equals("true");
			            break;
		            case "contentChairBasic":
			            this.contentChairBasic = exception[1].equals("true");
			            break;
		            case "contentChairBasicArm":
			            this.contentChairBasicArm = exception[1].equals("true");
			            break;
		            case "contentChandelierSmall":
			            this.contentChandelierSmallRound = exception[1].equals("true");
			            break;
		            case "contentCoinBag":
			            this.contentCoinBag = exception[1].equals("true");
			            break;
		            case "contentCoinStack":
			            this.contentCoinStack = exception[1].equals("true");
			            break;
		            case "contentCurioCabinet":
			            this.contentCurioCabinet = exception[1].equals("true");
			            break;
		            case "contentCurtainRod":
			            this.contentCurtainRod = exception[1].equals("true");
			            break;
		            case "contentCurtainWool":
			            this.contentCurtainWool = exception[1].equals("true");
			            break;
		            case "contentDeskHutchWoodBasic":
			            this.contentDeskHutchWoodBasic = exception[1].equals("true");
			            break;
		            case "contentDeskWoodBasic":
			            this.contentDeskWoodBasic = exception[1].equals("true");
			            break;
		            case "contentEndTableMetalRound":
			            this.contentEndTableMetalRound = exception[1].equals("true");
			            break;
		            case "contentEndTableWood":
			            this.contentEndTableWood = exception[1].equals("true");
			            break;
		            case "contentFireplaceBellows":
			            this.contentFireplaceBellows = exception[1].equals("true");
			            break;
		            case "contentFireplacePokerSet":
			            this.contentFireplacePokerSet = exception[1].equals("true");
			            break;
		            case "contentFireplaceScreen":
			            this.contentFireplaceScreen = exception[1].equals("true");
			            break;
		            case "contentFireplaceWoodStack":
			            this.contentFireplaceWoodStack = exception[1].equals("true");
			            break;
		            case "contentFoodTrough":
			            this.contentFoodTrough = exception[1].equals("true");
			            break;
		            case "contentFountainLava":
			            this.contentFountainLava = exception[1].equals("true");
			            break;
		            case "contentFountainWater":
			            this.contentFountainWater = exception[1].equals("true");
			            break;
		            case "contentIngotStack":
			            this.contentIngotStack = exception[1].equals("true");
			            break;
		            case "contentLantern":
			            this.contentLantern = exception[1].equals("true");
			            break;
		            case "contentMantle":
			            this.contentMantle = exception[1].equals("true");
			            break;
		            case "contentMantleColumn":
			            this.contentMantleColumn = exception[1].equals("true");
			            break;
		            case "contentMarketCrate":
			            this.contentMarketCrate = exception[1].equals("true");
			            break;
		            case "contentMarketStand":
			            this.contentMarketStand = exception[1].equals("true");
			            break;
		            case "contentPalletStack":
			            this.contentPalletStack = exception[1].equals("true");
			            break;
		            case "contentPillarLarge":
			            this.contentPillarLarge = exception[1].equals("true");
			            break;
		            case "contentPillarSmall":
			            this.contentPillarSmall = exception[1].equals("true");
			            break;
		            case "contentPoleMetal":
			            this.contentPoleMetal = exception[1].equals("true");
			            break;
		            case "contentPoleSign":
			            this.contentPoleSign = exception[1].equals("true");
			            break;
		            case "contentShelf":
			            this.contentShelf = exception[1].equals("true");
			            break;
		            case "contentShopSignCornerCut":
			            this.contentShopSignCornerCut = exception[1].equals("true");
			            break;
		            case "contentShopSignDome":
			            this.contentShopSignDome = exception[1].equals("true");
			            break;
		            case "contentShopSignGable":
			            this.contentShopSignGable = exception[1].equals("true");
			            break;
		            case "contentShopSignHexagon":
			            this.contentShopSignHexagon = exception[1].equals("true");
			            break;
		            case "contentShopSignRound":
			            this.contentShopSignRound = exception[1].equals("true");
			            break;
		            case "contentShopSignSquare":
			            this.contentShopSignSquare = exception[1].equals("true");
			            break;
		            case "contentSofa":
			            this.contentSofa = exception[1].equals("true");
			            break;
		            case "contentStandLargeMetal":
			            this.contentStandLargeMetal = exception[1].equals("true");
			            break;
		            case "contentStandLargeStone":
			            this.contentStandLargeStone = exception[1].equals("true");
			            break;
		            case "contentStandSmallMetal":
			            this.contentStandSmallMetal = exception[1].equals("true");
			            break;
		            case "contentStandSmallStone":
			            this.contentStandSmallStone = exception[1].equals("true");
			            break;
		            case "contentStandSmallWood":
			            this.contentStandSmallWood = exception[1].equals("true");
			            break;
		            case "contentSupportPole":
			            this.contentSupportPole = exception[1].equals("true");
			            break;
		            case "contentTableWood":
			            this.contentTableWood = exception[1].equals("true");
			            break;
		            case "contentTile":
			            this.contentTile = exception[1].equals("true");
			            break;
		            case "contentWallLantern":
			            this.contentWallLantern = exception[1].equals("true");
			            break;
		            case "contentWaterTrough":
			            this.contentWaterTrough = exception[1].equals("true");
			            break;
		            case "contentWoodBox":
			            this.contentWoodBox = exception[1].equals("true");
			            break;
		            case "contentWoodCrate":
			            this.contentWoodCrate = exception[1].equals("true");
			            break;
		            case "contentWoodPanel":
			            this.contentWoodPanel = exception[1].equals("true");
			            break;
		            case "contentWoodTimber":
			            this.contentWoodTimber = exception[1].equals("true");
			            break;
		            case "contentWoodTrim":
			            this.contentWoodTrim = exception[1].equals("true");
			            break;
		            case "workbenchSawCutTime":
			            this.workbenchSawCutTime = Integer.parseInt(exception[1]);
			            break;
		            case "workbenchFoundryParticles":
			            this.workbenchFoundryParticles = exception[1].equals("true");
			            break;
		            case "updateNotify":
			            this.updateNotify = exception[1].equals("true");
			            break;
		            default:
			            MDeco.instance.logOutput("Unrecognized config option \"%s\" ignored" + exception[0]);
			            break;
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
