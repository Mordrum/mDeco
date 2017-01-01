package com.mordrum.mdeco.core;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.block.DMPBlockChair;
import com.mordrum.mdeco.block.DMPBlockChandelierSmall;
import com.mordrum.mdeco.block.DMPBlockCoinBag;
import com.mordrum.mdeco.block.DMPBlockCurtain;
import com.mordrum.mdeco.block.DMPBlockEndTableMetalRound;
import com.mordrum.mdeco.block.DMPBlockKitchenTableSetting;
import com.mordrum.mdeco.block.DMPBlockKitchenWallUtensils;
import com.mordrum.mdeco.block.DMPBlockPillar;
import com.mordrum.mdeco.block.DMPBlockShelf;
import com.mordrum.mdeco.block.DMPBlockSofa;
import com.mordrum.mdeco.block.DMPBlockTableBasic;
import com.mordrum.mdeco.block.DMPBlockWoodBox;
import com.mordrum.mdeco.block.DMPBlockWoodPanel;
import com.mordrum.mdeco.block.DMPBlockWoodTrim;
import com.mordrum.mdeco.block.DMPBlockWorkbenchSaw;
import com.mordrum.mdeco.object.DMPBuildingBlock;
import com.mordrum.mdeco.object.DMPBuildingBlockType;
import com.mordrum.mdeco.block.DMPBlockBarrelLarge;
import com.mordrum.mdeco.block.DMPBlockBarrelSmall;
import com.mordrum.mdeco.block.DMPBlockBenchStone;
import com.mordrum.mdeco.block.DMPBlockBenchWood;
import com.mordrum.mdeco.block.DMPBlockBenchWoodMetalArm;
import com.mordrum.mdeco.block.DMPBlockBlackIron;
import com.mordrum.mdeco.block.DMPBlockBrick;
import com.mordrum.mdeco.block.DMPBlockBrickHeadstone;
import com.mordrum.mdeco.block.DMPBlockBrickPillar;
import com.mordrum.mdeco.block.DMPBlockBrickSlabDouble;
import com.mordrum.mdeco.block.DMPBlockBrickSlabHalf;
import com.mordrum.mdeco.block.DMPBlockBrickStairs;
import com.mordrum.mdeco.block.DMPBlockBrickWall;
import com.mordrum.mdeco.block.DMPBlockCabinetBase;
import com.mordrum.mdeco.block.DMPBlockCabinetWall;
import com.mordrum.mdeco.block.DMPBlockCabinetWallGlass;
import com.mordrum.mdeco.block.DMPBlockCap;
import com.mordrum.mdeco.block.DMPBlockChain;
import com.mordrum.mdeco.block.DMPBlockCoinStack;
import com.mordrum.mdeco.block.DMPBlockCurioBase;
import com.mordrum.mdeco.block.DMPBlockCurioTop;
import com.mordrum.mdeco.block.DMPBlockCurtainRod;
import com.mordrum.mdeco.block.DMPBlockDeskHutchWoodBasic;
import com.mordrum.mdeco.block.DMPBlockDeskWoodBasic;
import com.mordrum.mdeco.block.DMPBlockEndTableWood;
import com.mordrum.mdeco.block.DMPBlockFeedingTrough;
import com.mordrum.mdeco.block.DMPBlockFireplaceBellows;
import com.mordrum.mdeco.block.DMPBlockFireplacePokerSet;
import com.mordrum.mdeco.block.DMPBlockFireplaceScreen;
import com.mordrum.mdeco.block.DMPBlockFireplaceWoodRack;
import com.mordrum.mdeco.block.DMPBlockFountain;
import com.mordrum.mdeco.block.DMPBlockIngotStack;
import com.mordrum.mdeco.block.DMPBlockKitchenKettle;
import com.mordrum.mdeco.block.DMPBlockKitchenPot;
import com.mordrum.mdeco.block.DMPBlockKitchenShakers;
import com.mordrum.mdeco.block.DMPBlockLantern;
import com.mordrum.mdeco.block.DMPBlockMantle;
import com.mordrum.mdeco.block.DMPBlockMantleColumn;
import com.mordrum.mdeco.block.DMPBlockMarketCrate;
import com.mordrum.mdeco.block.DMPBlockMarketStand;
import com.mordrum.mdeco.block.DMPBlockPalletStack;
import com.mordrum.mdeco.block.DMPBlockPole;
import com.mordrum.mdeco.block.DMPBlockPoleConnector;
import com.mordrum.mdeco.block.DMPBlockPoleSign;
import com.mordrum.mdeco.block.DMPBlockShopSign;
import com.mordrum.mdeco.block.DMPBlockStandLarge;
import com.mordrum.mdeco.block.DMPBlockStandSmall;
import com.mordrum.mdeco.block.DMPBlockStandSmallWood;
import com.mordrum.mdeco.block.DMPBlockSupportPole;
import com.mordrum.mdeco.block.DMPBlockTile;
import com.mordrum.mdeco.block.DMPBlockWallLantern;
import com.mordrum.mdeco.block.DMPBlockWoodCrate;
import com.mordrum.mdeco.block.DMPBlockWoodTimber;
import com.mordrum.mdeco.block.DMPBlockWoodTimberConnector;
import com.mordrum.mdeco.block.DMPBlockWorkbenchFoundry;
import com.mordrum.mdeco.object.DMPAncientBlockType;
import com.mordrum.mdeco.object.DMPDecoration;
import com.mordrum.mdeco.object.DMPDecorationType;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class DMPBlocks {
   public Map ancientBlocks = new HashMap(DMPAncientBlockType.values().length);
   public Map ancientSlabs = new HashMap(DMPAncientBlockType.values().length);
   public Map ancientSlabsDouble = new HashMap(DMPAncientBlockType.values().length);
   public Map ancientStairs = new HashMap(DMPAncientBlockType.values().length);
   public Map ancientWalls = new HashMap(DMPAncientBlockType.values().length);
   public Map ancientPillarsLarge = new HashMap(DMPAncientBlockType.values().length);
   public Map ancientPillarsSmall = new HashMap(DMPAncientBlockType.values().length);
   public Map ancientHeadstones = new HashMap(DMPAncientBlockType.values().length);
   public Map pillars = new HashMap();
   public Map barrelLarge = new HashMap();
   public Map barrelSmall = new HashMap();
   public Map benchStone = new HashMap();
   public Map benchWood = new HashMap();
   public Map benchWoodMetalArm = new HashMap();
   public Map cabinetBase = new HashMap();
   public Map cabinetWall = new HashMap();
   public Map cabinetWallGlass = new HashMap();
   public Map cap = new HashMap();
   public Map chain = new HashMap();
   public Map chainLarge = new HashMap();
   public Map chairBasic = new HashMap();
   public Map chairBasicArm = new HashMap();
   public Map chandelierSmallRound = new HashMap();
   public DMPBlockCoinBag coinBag;
   public DMPBlockCoinStack coinStack;
   public DMPBlockKitchenKettle kitchenKettle;
   public DMPBlockKitchenPot kitchenPot;
   public DMPBlockKitchenShakers kitchenShakers;
   public DMPBlockKitchenTableSetting kitchenTableSetting;
   public DMPBlockKitchenWallUtensils kitchenWallUtensils;
   public Map curioBase = new HashMap();
   public Map curioTop = new HashMap();
   public Map curtainRod = new HashMap();
   public Map curtainWool = new HashMap();
   public Map deskHutchWoodBasic = new HashMap();
   public Map deskWoodBasic = new HashMap();
   public Map endTableMetalRound = new HashMap();
   public Map endTableWood = new HashMap();
   public Map foodTrough = new HashMap();
   public Map waterTrough = new HashMap();
   public Map fireBellows = new HashMap();
   public Map fireplaceScreen = new HashMap();
   public Map firePokerSet = new HashMap();
   public Map firewoodRack = new HashMap();
   public Map fountain = new HashMap();
   public Map ingotStack = new HashMap();
   public Map lantern = new HashMap();
   public Map mantle = new HashMap();
   public Map mantleColumn = new HashMap();
   public Map marketCrate = new HashMap();
   public Map marketStand = new HashMap();
   public Map palletStack = new HashMap();
   public Map poleMetal = new HashMap();
   public Map poleMetalConnector = new HashMap();
   public Map poleSign = new HashMap();
   public Map shelf = new HashMap();
   public Map shopSignCornerCut = new HashMap();
   public Map shopSignDome = new HashMap();
   public Map shopSignGable = new HashMap();
   public Map shopSignHexagon = new HashMap();
   public Map shopSignRound = new HashMap();
   public Map shopSignSquare = new HashMap();
   public Map sofa = new HashMap();
   public Map standSmallWood = new HashMap();
   public Map standSmallStone = new HashMap();
   public Map standSmallMetal = new HashMap();
   public Map standLargeStone = new HashMap();
   public Map standLargeMetal = new HashMap();
   public Map supportPole = new HashMap();
   public Map tableWood = new HashMap();
   public Map tile = new HashMap();
   public Map wallLantern = new HashMap();
   public Map woodBox = new HashMap();
   public Map woodCrate = new HashMap();
   public Map woodPanel = new HashMap();
   public Map woodTimber = new HashMap();
   public Map woodTrim = new HashMap();
   public Map woodTimberConnector = new HashMap();
   public DMPBlockBlackIron blockBlackIron;
   public DMPBlockWorkbenchFoundry workbenchFoundry;
   public DMPBlockWorkbenchSaw workbenchSaw;

   public DMPBlocks() {
      this.createBlocks();
      this.createAncientStoneBlocks();
      this.createBuildingBlocks();
      this.createDecorationBlocks();
      this.createWorkbenchBlocks();
   }

   public void preInit() {
      this.preInitAncientStoneBlocks();
      this.preInitDecorations();
   }

   public void preInitAncientStoneBlocks() {
      DMPAncientBlockType[] var2 = DMPAncientBlockType.values();
      int var3 = var2.length;

      for (DMPAncientBlockType blockType : var2) {
         DMPBlockBrick.EnumType[] var6 = DMPBlockBrick.EnumType.values();
         int var7 = var6.length;

         String variantName;
         int var8;
         for (var8 = 0; var8 < var7; ++var8) {
            DMPBlockBrick.EnumType variantHeadstone = var6[var8];
            variantName = String.format("%s_%s_block", blockType.name(), variantHeadstone.name());
            ModelBakery.registerItemVariants(Item.getItemFromBlock((Block) this.ancientBlocks.get(blockType.name())), new ResourceLocation(
                    "mdeco:" + variantName));
            variantName = String.format("%s_%s_slab", blockType.name(), variantHeadstone.name());
            ModelBakery.registerItemVariants(Item.getItemFromBlock((Block) this.ancientSlabs.get(blockType.name())), new ResourceLocation(
                    "mdeco:" + variantName));
            variantName = String.format("%s_%s_slab_double", blockType.name(), variantHeadstone.name());
            ModelBakery.registerItemVariants(Item.getItemFromBlock((Block) this.ancientSlabsDouble.get(blockType.name())), new ResourceLocation(
                    "mdeco:" + variantName));
            variantName = String.format("%s_%s_wall", blockType.name(), variantHeadstone.name());
            ModelBakery.registerItemVariants(Item.getItemFromBlock((Block) this.ancientWalls.get(blockType.name())), new ResourceLocation(
                    "mdeco:" + variantName));
            variantName = String.format("%s_%s_pillar_large", blockType.name(), variantHeadstone.name());
            ModelBakery.registerItemVariants(Item.getItemFromBlock((Block) this.ancientPillarsLarge.get(blockType.name())), new ResourceLocation(
                    "mdeco:" + variantName));
            variantName = String.format("%s_%s_pillar_small", blockType.name(), variantHeadstone.name());
            ModelBakery.registerItemVariants(Item.getItemFromBlock((Block) this.ancientPillarsSmall.get(blockType.name())), new ResourceLocation(
                    "mdeco:" + variantName));
         }

         DMPBlockBrickHeadstone.EnumType[] var10 = DMPBlockBrickHeadstone.EnumType.values();
         var7 = var10.length;

         for (var8 = 0; var8 < var7; ++var8) {
            DMPBlockBrickHeadstone.EnumType var11 = var10[var8];
            variantName = String.format("%s_headstone_%s", blockType.name(), var11.name());
            ModelBakery.registerItemVariants(Item.getItemFromBlock((Block) this.ancientHeadstones.get(blockType.name())), new ResourceLocation(
                    "mdeco:" + variantName));
         }
      }

   }

   public void preInitDecorations() {
      DMPDecoration[] var3 = DMPDecoration.values();
      int var4 = var3.length;

      for (DMPDecoration decoration : var3) {
         String variantName;
         int i;
         if (decoration.decorationType == DMPDecorationType.ingotStack) {
            for (i = 0; i < 6; ++i) {
               variantName = String.format("%s:%s%d", "mdeco", decoration.name(), i);
               ModelBakery.registerItemVariants(Item.getItemFromBlock((Block) this.ingotStack.get(decoration.name())), new ResourceLocation(variantName));
            }
         } else if (decoration.decorationType == DMPDecorationType.palletStack) {
            for (i = 0; i < 5; ++i) {
               variantName = String.format("%s:%s%d", "mdeco", decoration.name(), i);
               ModelBakery.registerItemVariants(Item.getItemFromBlock((Block) this.palletStack.get(decoration.name())), new ResourceLocation(variantName));
            }
         }
      }

   }

   private void createBlocks() {
      this.blockBlackIron = new DMPBlockBlackIron("blockBlackIron");
      DMPReference.addToRegisteredBlocks(1);
   }

   private void createAncientStoneBlocks() {
      DMPAncientBlockType[] var4 = DMPAncientBlockType.values();
      int var5 = var4.length;

      for (DMPAncientBlockType blockType : var4) {
         try {
            DMPBlockBrick structureBlock = new DMPBlockBrick(blockType.name() + "_block");
            if (structureBlock != null) {
               this.ancientBlocks.put(blockType.name(), structureBlock);
            }

            DMPBlockBrickSlabHalf slabHalf = new DMPBlockBrickSlabHalf(blockType.name());
            DMPBlockBrickSlabDouble slabDouble = new DMPBlockBrickSlabDouble(blockType.name());
            if (slabHalf != null && slabDouble != null) {
               this.ancientSlabs.put(blockType.name(), slabHalf);
               this.ancientSlabsDouble.put(blockType.name(), slabDouble);
//               FIXME
//               Util.registerBlockAndItem(slabHalf, DMPItemBrickSlab.class, blockType.name() + "_slab");
//               Util.registerBlockAndItem(slabDouble, DMPItemBrickSlab.class, blockType.name() + "_slab_double");
            }

            DMPBlockBrickStairs var10 = new DMPBlockBrickStairs(blockType.name() + "_stairs");
            if (var10 != null) {
               this.ancientStairs.put(blockType.name(), var10);
            }

            DMPBlockBrickWall var11 = new DMPBlockBrickWall(blockType.name() + "_wall");
            if (var11 != null) {
               this.ancientWalls.put(blockType.name(), var11);
            }

            DMPBlockBrickPillar var12 = new DMPBlockBrickPillar(blockType.name() + "_pillar_large", false);
            if (var12 != null) {
               this.ancientPillarsLarge.put(blockType.name(), var12);
            }

            var12 = new DMPBlockBrickPillar(blockType.name() + "_pillar_small", true);
            if (var12 != null) {
               this.ancientPillarsSmall.put(blockType.name(), var12);
            }

            DMPBlockBrickHeadstone var13 = new DMPBlockBrickHeadstone(blockType.name() + "_headstone");
            if (var13 != null) {
               this.ancientHeadstones.put(blockType.name(), var13);
            }
         } catch (Exception var9) {
            var9.printStackTrace();
         }
      }

   }

   private void createBuildingBlocks() {
      DMPBuildingBlock[] var1 = DMPBuildingBlock.values();
      int var2 = var1.length;

      for (DMPBuildingBlock buildingBlock : var1) {
         if (buildingBlock.blockType == DMPBuildingBlockType.pillarLarge && MDeco.settings.contentPillarLarge) {
            this.pillars.put(buildingBlock.name(), new DMPBlockPillar(buildingBlock, false));
            DMPReference.addToRegisteredBlocks(1);
         } else if (buildingBlock.blockType == DMPBuildingBlockType.pillarSmall && MDeco.settings.contentPillarSmall) {
            this.pillars.put(buildingBlock.name(), new DMPBlockPillar(buildingBlock, true));
            DMPReference.addToRegisteredBlocks(1);
         }
      }

   }

   private void createDecorationBlocks() {
      DMPDecoration[] var1 = DMPDecoration.values();
      int var2 = var1.length;

      for (DMPDecoration decoration : var1) {
         if (decoration.decorationType == DMPDecorationType.barrelLarge && MDeco.settings.contentBarrelLarge) {
            this.barrelLarge.put(decoration.name(), new DMPBlockBarrelLarge(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.barrelSmall && MDeco.settings.contentBarrelSmall) {
            this.barrelSmall.put(decoration.name(), new DMPBlockBarrelSmall(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.benchWood && MDeco.settings.contentBenchWood) {
            this.benchWood.put(decoration.name(), new DMPBlockBenchWood(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.benchWoodMetalArm &&
                 MDeco.settings.contentBenchWoodMetalArm) {
            this.benchWoodMetalArm.put(decoration.name(), new DMPBlockBenchWoodMetalArm(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.benchStone && MDeco.settings.contentBenchStone) {
            this.benchStone.put(decoration.name(), new DMPBlockBenchStone(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.cabinetBase && MDeco.settings.contentCabinetBase) {
            this.cabinetBase.put(decoration.name(), new DMPBlockCabinetBase(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.cabinetWall && MDeco.settings.contentCabinetWall) {
            this.cabinetWall.put(decoration.name(), new DMPBlockCabinetWall(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.cabinetWallGlass &&
                 MDeco.settings.contentCabinetWallGlass) {
            this.cabinetWallGlass.put(decoration.name(), new DMPBlockCabinetWallGlass(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if ((decoration.decorationType == DMPDecorationType.capSmallPyramid ||
                 decoration.decorationType == DMPDecorationType.capLargePyramid ||
                 decoration.decorationType == DMPDecorationType.capOval ||
                 decoration.decorationType == DMPDecorationType.capPlus ||
                 decoration.decorationType == DMPDecorationType.capRound ||
                 decoration.decorationType == DMPDecorationType.capSquare) && MDeco.settings.contentCaps) {
            this.cap.put(decoration.name(), new DMPBlockCap(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.chain && MDeco.settings.contentChain) {
            this.chain.put(decoration.name(), new DMPBlockChain(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.chainLarge && MDeco.settings.contentChain) {
            this.chainLarge.put(decoration.name(), new DMPBlockChain(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.chairBasic && MDeco.settings.contentChairBasic) {
            this.chairBasic.put(decoration.name(), new DMPBlockChair(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.chairBasicArm &&
                 MDeco.settings.contentChairBasicArm) {
            this.chairBasicArm.put(decoration.name(), new DMPBlockChair(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.chandelierSmallRound &&
                 MDeco.settings.contentChandelierSmallRound) {
            this.chandelierSmallRound.put(decoration.name(), new DMPBlockChandelierSmall(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.coinBag) {
            this.coinBag = new DMPBlockCoinBag(decoration);
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.coinStack) {
            this.coinStack = new DMPBlockCoinStack(decoration);
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.curioBase) {
            this.curioBase.put(decoration.name(), new DMPBlockCurioBase(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.curioTop) {
            this.curioTop.put(decoration.name(), new DMPBlockCurioTop(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.curtainRod && MDeco.settings.contentCurtainRod) {
            this.curtainRod.put(decoration.name(), new DMPBlockCurtainRod(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.curtainWool && MDeco.settings.contentCurtainWool) {
            this.curtainWool.put(decoration.name(), new DMPBlockCurtain(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.deskHutchWoodBasic) {
            this.deskHutchWoodBasic.put(decoration.name(), new DMPBlockDeskHutchWoodBasic(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.deskWoodBasic) {
            this.deskWoodBasic.put(decoration.name(), new DMPBlockDeskWoodBasic(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.endTableMetalRound &&
                 MDeco.settings.contentEndTableMetalRound) {
            this.endTableMetalRound.put(decoration.name(), new DMPBlockEndTableMetalRound(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.endTableWood && MDeco.settings.contentEndTableWood) {
            this.endTableWood.put(decoration.name(), new DMPBlockEndTableWood(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.fireplaceBellows) {
            this.fireBellows.put(decoration.name(), new DMPBlockFireplaceBellows(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.fireplaceScreen) {
            this.fireplaceScreen.put(decoration.name(), new DMPBlockFireplaceScreen(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.fireplacePokerSet) {
            this.firePokerSet.put(decoration.name(), new DMPBlockFireplacePokerSet(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.fireplaceWoodRack) {
            this.firewoodRack.put(decoration.name(), new DMPBlockFireplaceWoodRack(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType == DMPDecorationType.foodTrough) {
            this.foodTrough.put(decoration.name(), new DMPBlockFeedingTrough(decoration));
            DMPReference.addToRegisteredBlocks(1);
         } else if (decoration.decorationType != DMPDecorationType.fountainLava &&
                 decoration.decorationType != DMPDecorationType.fountainWater) {
            if (decoration.decorationType == DMPDecorationType.ingotStack) {
               this.ingotStack.put(decoration.name(), new DMPBlockIngotStack(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.kitchenKettle) {
               this.kitchenKettle = new DMPBlockKitchenKettle(decoration);
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.kitchenPot) {
               this.kitchenPot = new DMPBlockKitchenPot(decoration);
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.kitchenShakers) {
               this.kitchenShakers = new DMPBlockKitchenShakers(decoration);
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.kitchenTableSetting) {
               this.kitchenTableSetting = new DMPBlockKitchenTableSetting(decoration);
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.kitchenWallUtensils) {
               this.kitchenWallUtensils = new DMPBlockKitchenWallUtensils(decoration);
               DMPReference.addToRegisteredBlocks(1);
            } else if ((decoration.decorationType == DMPDecorationType.lanternOval ||
                    decoration.decorationType == DMPDecorationType.lanternPyramid ||
                    decoration.decorationType == DMPDecorationType.lanternPyramidGlass ||
                    decoration.decorationType == DMPDecorationType.lanternRectangle ||
                    decoration.decorationType == DMPDecorationType.lanternRectangleGlass ||
                    decoration.decorationType == DMPDecorationType.lanternRound) && MDeco.settings.contentLantern) {
               this.lantern.put(decoration.name(), new DMPBlockLantern(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.mantle) {
               this.mantle.put(decoration.name(), new DMPBlockMantle(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.mantleColumn) {
               this.mantleColumn.put(decoration.name(), new DMPBlockMantleColumn(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.marketCrate) {
               this.marketCrate.put(decoration.name(), new DMPBlockMarketCrate(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.marketStand) {
               this.marketStand.put(decoration.name(), new DMPBlockMarketStand(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.palletStack &&
                    MDeco.settings.contentPalletStack) {
               this.palletStack.put(decoration.name(), new DMPBlockPalletStack(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.poleMetal && MDeco.settings.contentPoleMetal) {
               this.poleMetal.put(decoration.name(), new DMPBlockPole(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.poleMetalConnector &&
                    MDeco.settings.contentPoleMetal) {
               this.poleMetalConnector.put(decoration.name(), new DMPBlockPoleConnector(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.poleSign) {
               this.poleSign.put(decoration.name(), new DMPBlockPoleSign(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.shelf && MDeco.settings.contentShelf) {
               this.shelf.put(decoration.name(), new DMPBlockShelf(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.shopSignCornerCut &&
                    MDeco.settings.contentShopSignCornerCut) {
               this.shopSignCornerCut.put(decoration.name(), new DMPBlockShopSign(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.shopSignDome &&
                    MDeco.settings.contentShopSignDome) {
               this.shopSignDome.put(decoration.name(), new DMPBlockShopSign(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.shopSignGable &&
                    MDeco.settings.contentShopSignGable) {
               this.shopSignGable.put(decoration.name(), new DMPBlockShopSign(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.shopSignHexagon &&
                    MDeco.settings.contentShopSignHexagon) {
               this.shopSignHexagon.put(decoration.name(), new DMPBlockShopSign(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.shopSignRound &&
                    MDeco.settings.contentShopSignRound) {
               this.shopSignRound.put(decoration.name(), new DMPBlockShopSign(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.shopSignSquare &&
                    MDeco.settings.contentShopSignSquare) {
               this.shopSignSquare.put(decoration.name(), new DMPBlockShopSign(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.sofa && MDeco.settings.contentSofa) {
               this.sofa.put(decoration.name(), new DMPBlockSofa(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.standSmallWood &&
                    MDeco.settings.contentStandSmallWood) {
               this.standSmallWood.put(decoration.name(), new DMPBlockStandSmallWood(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.standSmallStone &&
                    MDeco.settings.contentStandSmallStone) {
               this.standSmallStone.put(decoration.name(), new DMPBlockStandSmall(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.standSmallMetal &&
                    MDeco.settings.contentStandSmallMetal) {
               this.standSmallMetal.put(decoration.name(), new DMPBlockStandSmall(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.standLargeStone &&
                    MDeco.settings.contentStandLargeStone) {
               this.standLargeStone.put(decoration.name(), new DMPBlockStandLarge(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.standLargeMetal &&
                    MDeco.settings.contentStandLargeMetal) {
               this.standLargeMetal.put(decoration.name(), new DMPBlockStandLarge(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.supportPole &&
                    MDeco.settings.contentSupportPole) {
               this.supportPole.put(decoration.name(), new DMPBlockSupportPole(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.tableWood && MDeco.settings.contentTableWood) {
               this.tableWood.put(decoration.name(), new DMPBlockTableBasic(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.tile && MDeco.settings.contentTile) {
               this.tile.put(decoration.name(), new DMPBlockTile(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.wallLantern &&
                    MDeco.settings.contentWallLantern) {
               this.wallLantern.put(decoration.name(), new DMPBlockWallLantern(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.waterTrough) {
               this.waterTrough.put(decoration.name(), new DMPBlockFeedingTrough(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.woodBox && MDeco.settings.contentWoodBox) {
               this.woodBox.put(decoration.name(), new DMPBlockWoodBox(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.woodCrate && MDeco.settings.contentWoodCrate) {
               this.woodCrate.put(decoration.name(), new DMPBlockWoodCrate(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.woodPanel && MDeco.settings.contentWoodPanel) {
               this.woodPanel.put(decoration.name(), new DMPBlockWoodPanel(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if ((decoration.decorationType == DMPDecorationType.woodTimber ||
                    decoration.decorationType == DMPDecorationType.woodBarkTimber) &&
                    MDeco.settings.contentWoodTimber) {
               this.woodTimber.put(decoration.name(), new DMPBlockWoodTimber(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if (decoration.decorationType == DMPDecorationType.woodTrim && MDeco.settings.contentWoodTrim) {
               this.woodTrim.put(decoration.name(), new DMPBlockWoodTrim(decoration));
               DMPReference.addToRegisteredBlocks(1);
            } else if ((decoration.decorationType == DMPDecorationType.woodTimberConnector ||
                    decoration.decorationType == DMPDecorationType.woodBarkTimberConnector) &&
                    MDeco.settings.contentWoodTimber) {
               this.woodTimberConnector.put(decoration.name(), new DMPBlockWoodTimberConnector(decoration));
               DMPReference.addToRegisteredBlocks(1);
            }
         } else {
            this.fountain.put(decoration.name(), new DMPBlockFountain(decoration));
            DMPReference.addToRegisteredBlocks(1);
         }
      }

   }

   private void createWorkbenchBlocks() {
      this.workbenchFoundry = new DMPBlockWorkbenchFoundry("workbenchFoundry");
      this.workbenchSaw = new DMPBlockWorkbenchSaw("workbenchSaw");
      DMPReference.addToRegisteredBlocks(2);
   }

   public void init() {
      RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
      if(renderItem != null) {
         this.initAncientStoneBlocks(renderItem);
         this.initBuildingBlocks(renderItem);
         this.initDecorations(renderItem);
      }
   }

   private void initAncientStoneBlocks(RenderItem renderItem) {
      DMPAncientBlockType[] var3 = DMPAncientBlockType.values();
      int var4 = var3.length;

      for (DMPAncientBlockType blockType : var3) {
         DMPBlockBrick.EnumType[] var7 = DMPBlockBrick.EnumType.values();
         int var8 = var7.length;

         String variantName;
         int var9;
         for (var9 = 0; var9 < var8; ++var9) {
            DMPBlockBrick.EnumType variantHeadstone = var7[var9];
            variantName = String.format("%s_%s_block", blockType.name(), variantHeadstone.name());
            renderItem.getItemModelMesher()
                    .register(Item.getItemFromBlock((Block) this.ancientBlocks.get(blockType.name())), variantHeadstone.getMetadata(), new ModelResourceLocation(
                            "mdeco:" + variantName, "inventory"));
            variantName = String.format("%s_%s_slab", blockType.name(), variantHeadstone.name());
            renderItem.getItemModelMesher()
                    .register(Item.getItemFromBlock((Block) this.ancientSlabs.get(blockType.name())), variantHeadstone.getMetadata(), new ModelResourceLocation(
                            "mdeco:" + variantName, "inventory"));
            variantName = String.format("%s_%s_wall", blockType.name(), variantHeadstone.name());
            renderItem.getItemModelMesher()
                    .register(Item.getItemFromBlock((Block) this.ancientWalls.get(blockType.name())), variantHeadstone.getMetadata(), new ModelResourceLocation(
                            "mdeco:" + variantName, "inventory"));
            variantName = String.format("%s_%s_pillar_large", blockType.name(), variantHeadstone.name());
            renderItem.getItemModelMesher()
                    .register(Item.getItemFromBlock((Block) this.ancientPillarsLarge.get(blockType.name())), variantHeadstone
                            .getMetadata(), new ModelResourceLocation("mdeco:" + variantName, "inventory"));
            variantName = String.format("%s_%s_pillar_small", blockType.name(), variantHeadstone.name());
            renderItem.getItemModelMesher()
                    .register(Item.getItemFromBlock((Block) this.ancientPillarsSmall.get(blockType.name())), variantHeadstone
                            .getMetadata(), new ModelResourceLocation("mdeco:" + variantName, "inventory"));
         }

         variantName = String.format("%s_stairs", blockType.name());
         renderItem.getItemModelMesher()
                 .register(Item.getItemFromBlock((Block) this.ancientStairs.get(blockType.name())), 0, new ModelResourceLocation(
                         "mdeco:" + variantName, "inventory"));
         DMPBlockBrickHeadstone.EnumType[] var11 = DMPBlockBrickHeadstone.EnumType.values();
         var8 = var11.length;

         for (var9 = 0; var9 < var8; ++var9) {
            DMPBlockBrickHeadstone.EnumType var12 = var11[var9];
            variantName = String.format("%s_headstone_%s", blockType.name(), var12.name());
            renderItem.getItemModelMesher()
                    .register(Item.getItemFromBlock((Block) this.ancientHeadstones.get(blockType.name())), var12.getMetadata(), new ModelResourceLocation(
                            "mdeco:" + variantName, "inventory"));
         }
      }

   }

   private void initBuildingBlocks(RenderItem renderItem) {
      DMPBuildingBlock[] var4 = DMPBuildingBlock.values();
      int var5 = var4.length;

      for (DMPBuildingBlock buildingBlock : var4) {
         Item item = null;
         boolean registerSingle = true;
         if (buildingBlock.blockType == DMPBuildingBlockType.pillarLarge && MDeco.settings.contentPillarLarge) {
            item = Item.getItemFromBlock((Block) this.pillars.get(buildingBlock.name()));
         } else if (buildingBlock.blockType == DMPBuildingBlockType.pillarSmall && MDeco.settings.contentPillarSmall) {
            item = Item.getItemFromBlock((Block) this.pillars.get(buildingBlock.name()));
         }

         if (item != null && registerSingle) {
            renderItem.getItemModelMesher()
                    .register(item, 0, new ModelResourceLocation("mdeco:" + buildingBlock.name(), "inventory"));
         }
      }

   }

   public boolean initDecorations(RenderItem renderItem) {
      DMPDecoration[] var5 = DMPDecoration.values();
      int var6 = var5.length;

      for (DMPDecoration decoration : var5) {
         Item item = null;
         boolean registerSingle = true;
         if (decoration.decorationType == DMPDecorationType.barrelLarge) {
            item = Item.getItemFromBlock((Block) this.barrelLarge.get(decoration.name()));
         } else if (decoration.decorationType == DMPDecorationType.barrelSmall) {
            item = Item.getItemFromBlock((Block) this.barrelSmall.get(decoration.name()));
         } else if (decoration.decorationType == DMPDecorationType.benchWood) {
            item = Item.getItemFromBlock((Block) this.benchWood.get(decoration.name()));
         } else if (decoration.decorationType == DMPDecorationType.benchWoodMetalArm) {
            item = Item.getItemFromBlock((Block) this.benchWoodMetalArm.get(decoration.name()));
         } else if (decoration.decorationType == DMPDecorationType.benchStone) {
            item = Item.getItemFromBlock((Block) this.benchStone.get(decoration.name()));
         } else if (decoration.decorationType == DMPDecorationType.cabinetBase) {
            item = Item.getItemFromBlock((Block) this.cabinetBase.get(decoration.name()));
         } else if (decoration.decorationType == DMPDecorationType.cabinetWall) {
            item = Item.getItemFromBlock((Block) this.cabinetWall.get(decoration.name()));
         } else if (decoration.decorationType == DMPDecorationType.cabinetWallGlass) {
            item = Item.getItemFromBlock((Block) this.cabinetWallGlass.get(decoration.name()));
         } else if (decoration.decorationType != DMPDecorationType.capSmallPyramid &&
                 decoration.decorationType != DMPDecorationType.capLargePyramid &&
                 decoration.decorationType != DMPDecorationType.capOval &&
                 decoration.decorationType != DMPDecorationType.capPlus &&
                 decoration.decorationType != DMPDecorationType.capRound &&
                 decoration.decorationType != DMPDecorationType.capSquare) {
            if (decoration.decorationType == DMPDecorationType.chain) {
               item = Item.getItemFromBlock((Block) this.chain.get(decoration.name()));
            } else if (decoration.decorationType == DMPDecorationType.chainLarge) {
               item = Item.getItemFromBlock((Block) this.chainLarge.get(decoration.name()));
            } else if (decoration.decorationType == DMPDecorationType.chairBasic) {
               item = Item.getItemFromBlock((Block) this.chairBasic.get(decoration.name()));
            } else if (decoration.decorationType == DMPDecorationType.chairBasicArm) {
               item = Item.getItemFromBlock((Block) this.chairBasicArm.get(decoration.name()));
            } else if (decoration.decorationType == DMPDecorationType.chandelierSmallRound) {
               item = Item.getItemFromBlock((Block) this.chandelierSmallRound.get(decoration.name()));
            } else if (decoration.decorationType == DMPDecorationType.coinBag) {
               item = Item.getItemFromBlock(this.coinBag);
            } else if (decoration.decorationType == DMPDecorationType.coinStack) {
               item = Item.getItemFromBlock(this.coinStack);
            } else if (decoration.decorationType == DMPDecorationType.curioBase) {
               item = Item.getItemFromBlock((Block) this.curioBase.get(decoration.name()));
            } else if (decoration.decorationType == DMPDecorationType.curioTop) {
               item = Item.getItemFromBlock((Block) this.curioTop.get(decoration.name()));
            } else if (decoration.decorationType == DMPDecorationType.curtainRod) {
               item = Item.getItemFromBlock((Block) this.curtainRod.get(decoration.name()));
            } else if (decoration.decorationType == DMPDecorationType.curtainWool) {
               item = Item.getItemFromBlock((Block) this.curtainWool.get(decoration.name()));
            } else if (decoration.decorationType == DMPDecorationType.deskHutchWoodBasic) {
               item = Item.getItemFromBlock((Block) this.deskHutchWoodBasic.get(decoration.name()));
            } else if (decoration.decorationType == DMPDecorationType.deskWoodBasic) {
               item = Item.getItemFromBlock((Block) this.deskWoodBasic.get(decoration.name()));
            } else if (decoration.decorationType == DMPDecorationType.endTableMetalRound) {
               item = Item.getItemFromBlock((Block) this.endTableMetalRound.get(decoration.name()));
            } else if (decoration.decorationType == DMPDecorationType.endTableWood) {
               item = Item.getItemFromBlock((Block) this.endTableWood.get(decoration.name()));
            } else if (decoration.decorationType == DMPDecorationType.fireplaceBellows) {
               item = Item.getItemFromBlock((Block) this.fireBellows.get(decoration.name()));
            } else if (decoration.decorationType == DMPDecorationType.fireplaceScreen) {
               item = Item.getItemFromBlock((Block) this.fireplaceScreen.get(decoration.name()));
            } else if (decoration.decorationType == DMPDecorationType.fireplacePokerSet) {
               item = Item.getItemFromBlock((Block) this.firePokerSet.get(decoration.name()));
            } else if (decoration.decorationType == DMPDecorationType.fireplaceWoodRack) {
               item = Item.getItemFromBlock((Block) this.firewoodRack.get(decoration.name()));
            } else if (decoration.decorationType == DMPDecorationType.foodTrough) {
               item = Item.getItemFromBlock((Block) this.foodTrough.get(decoration.name()));
            } else if (decoration.decorationType != DMPDecorationType.fountainLava &&
                    decoration.decorationType != DMPDecorationType.fountainWater) {
               String variant;
               int i;
               if (decoration.decorationType == DMPDecorationType.ingotStack) {
                  for (i = 0; i < 6; ++i) {
                     variant = String.format("%s:%s%d", "mdeco", decoration.name(), i);
                     renderItem.getItemModelMesher()
                             .register(Item.getItemFromBlock((Block) this.ingotStack.get(decoration.name())), i, new ModelResourceLocation(variant, "inventory"));
                  }

                  registerSingle = false;
               } else if (decoration.decorationType == DMPDecorationType.kitchenKettle) {
                  item = Item.getItemFromBlock(this.kitchenKettle);
               } else if (decoration.decorationType == DMPDecorationType.kitchenPot) {
                  item = Item.getItemFromBlock(this.kitchenPot);
               } else if (decoration.decorationType == DMPDecorationType.kitchenShakers) {
                  item = Item.getItemFromBlock(this.kitchenShakers);
               } else if (decoration.decorationType == DMPDecorationType.kitchenTableSetting) {
                  item = Item.getItemFromBlock(this.kitchenTableSetting);
               } else if (decoration.decorationType == DMPDecorationType.kitchenWallUtensils) {
                  item = Item.getItemFromBlock(this.kitchenWallUtensils);
               } else if (decoration.decorationType != DMPDecorationType.lanternOval &&
                       decoration.decorationType != DMPDecorationType.lanternPyramid &&
                       decoration.decorationType != DMPDecorationType.lanternPyramidGlass &&
                       decoration.decorationType != DMPDecorationType.lanternRectangle &&
                       decoration.decorationType != DMPDecorationType.lanternRectangleGlass &&
                       decoration.decorationType != DMPDecorationType.lanternRound) {
                  if (decoration.decorationType == DMPDecorationType.mantle) {
                     item = Item.getItemFromBlock((Block) this.mantle.get(decoration.name()));
                  } else if (decoration.decorationType == DMPDecorationType.mantleColumn) {
                     item = Item.getItemFromBlock((Block) this.mantleColumn.get(decoration.name()));
                  } else if (decoration.decorationType == DMPDecorationType.marketCrate) {
                     item = Item.getItemFromBlock((Block) this.marketCrate.get(decoration.name()));
                  } else if (decoration.decorationType == DMPDecorationType.marketStand) {
                     item = Item.getItemFromBlock((Block) this.marketStand.get(decoration.name()));
                  } else if (decoration.decorationType != DMPDecorationType.palletStack) {
                     if (decoration.decorationType == DMPDecorationType.poleMetal) {
                        item = Item.getItemFromBlock((Block) this.poleMetal.get(decoration.name()));
                     } else if (decoration.decorationType == DMPDecorationType.poleMetalConnector) {
                        item = Item.getItemFromBlock((Block) this.poleMetalConnector.get(decoration.name()));
                     } else if (decoration.decorationType == DMPDecorationType.poleSign) {
                        item = Item.getItemFromBlock((Block) this.poleSign.get(decoration.name()));
                     } else if (decoration.decorationType == DMPDecorationType.shelf) {
                        item = Item.getItemFromBlock((Block) this.shelf.get(decoration.name()));
                     } else if (decoration.decorationType == DMPDecorationType.shopSignCornerCut) {
                        item = Item.getItemFromBlock((Block) this.shopSignCornerCut.get(decoration.name()));
                     } else if (decoration.decorationType == DMPDecorationType.shopSignDome) {
                        item = Item.getItemFromBlock((Block) this.shopSignDome.get(decoration.name()));
                     } else if (decoration.decorationType == DMPDecorationType.shopSignGable) {
                        item = Item.getItemFromBlock((Block) this.shopSignGable.get(decoration.name()));
                     } else if (decoration.decorationType == DMPDecorationType.shopSignHexagon) {
                        item = Item.getItemFromBlock((Block) this.shopSignHexagon.get(decoration.name()));
                     } else if (decoration.decorationType == DMPDecorationType.shopSignRound) {
                        item = Item.getItemFromBlock((Block) this.shopSignRound.get(decoration.name()));
                     } else if (decoration.decorationType == DMPDecorationType.shopSignSquare) {
                        item = Item.getItemFromBlock((Block) this.shopSignSquare.get(decoration.name()));
                     } else if (decoration.decorationType == DMPDecorationType.sofa) {
                        item = Item.getItemFromBlock((Block) this.sofa.get(decoration.name()));
                     } else if (decoration.decorationType == DMPDecorationType.standSmallWood) {
                        item = Item.getItemFromBlock((Block) this.standSmallWood.get(decoration.name()));
                     } else if (decoration.decorationType == DMPDecorationType.standSmallStone) {
                        item = Item.getItemFromBlock((Block) this.standSmallStone.get(decoration.name()));
                     } else if (decoration.decorationType == DMPDecorationType.standSmallMetal) {
                        item = Item.getItemFromBlock((Block) this.standSmallMetal.get(decoration.name()));
                     } else if (decoration.decorationType == DMPDecorationType.standLargeStone) {
                        item = Item.getItemFromBlock((Block) this.standLargeStone.get(decoration.name()));
                     } else if (decoration.decorationType == DMPDecorationType.standLargeMetal) {
                        item = Item.getItemFromBlock((Block) this.standLargeMetal.get(decoration.name()));
                     } else if (decoration.decorationType == DMPDecorationType.supportPole) {
                        item = Item.getItemFromBlock((Block) this.supportPole.get(decoration.name()));
                     } else if (decoration.decorationType == DMPDecorationType.tableWood) {
                        item = Item.getItemFromBlock((Block) this.tableWood.get(decoration.name()));
                     } else if (decoration.decorationType == DMPDecorationType.tile) {
                        item = Item.getItemFromBlock((Block) this.tile.get(decoration.name()));
                     } else if (decoration.decorationType == DMPDecorationType.wallLantern) {
                        item = Item.getItemFromBlock((Block) this.wallLantern.get(decoration.name()));
                     } else if (decoration.decorationType == DMPDecorationType.waterTrough) {
                        item = Item.getItemFromBlock((Block) this.waterTrough.get(decoration.name()));
                     } else if (decoration.decorationType == DMPDecorationType.woodBox) {
                        item = Item.getItemFromBlock((Block) this.woodBox.get(decoration.name()));
                     } else if (decoration.decorationType == DMPDecorationType.woodCrate) {
                        item = Item.getItemFromBlock((Block) this.woodCrate.get(decoration.name()));
                     } else if (decoration.decorationType == DMPDecorationType.woodPanel) {
                        item = Item.getItemFromBlock((Block) this.woodPanel.get(decoration.name()));
                     } else if (decoration.decorationType != DMPDecorationType.woodTimber &&
                             decoration.decorationType != DMPDecorationType.woodBarkTimber) {
                        if (decoration.decorationType != DMPDecorationType.woodTimberConnector &&
                                decoration.decorationType != DMPDecorationType.woodBarkTimberConnector) {
                           if (decoration.decorationType == DMPDecorationType.woodTrim) {
                              item = Item.getItemFromBlock((Block) this.woodTrim.get(decoration.name()));
                           }
                        } else {
                           item = Item.getItemFromBlock((Block) this.woodTimberConnector.get(decoration.name()));
                        }
                     } else {
                        item = Item.getItemFromBlock((Block) this.woodTimber.get(decoration.name()));
                     }
                  } else {
                     for (i = 0; i < 5; ++i) {
                        variant = String.format("%s:%s%d", "mdeco", decoration.name(), i);
                        renderItem.getItemModelMesher()
                                .register(Item.getItemFromBlock((Block) this.palletStack.get(decoration.name())), i, new ModelResourceLocation(variant, "inventory"));
                     }

                     registerSingle = false;
                  }
               } else {
                  item = Item.getItemFromBlock((Block) this.lantern.get(decoration.name()));
               }
            } else {
               item = Item.getItemFromBlock((Block) this.fountain.get(decoration.name()));
            }
         } else {
            item = Item.getItemFromBlock((Block) this.cap.get(decoration.name()));
         }

         if (item != null && registerSingle) {
            renderItem.getItemModelMesher()
                    .register(item, 0, new ModelResourceLocation("mdeco:" + decoration.name(), "inventory"));
         }
      }

      renderItem.getItemModelMesher().register(Item.getItemFromBlock(this.blockBlackIron), 0, new ModelResourceLocation("mdeco:blockBlackIron", "inventory"));
      renderItem.getItemModelMesher().register(Item.getItemFromBlock(this.workbenchFoundry), 0, new ModelResourceLocation("mdeco:workbenchFoundry", "inventory"));
      renderItem.getItemModelMesher().register(Item.getItemFromBlock(this.workbenchSaw), 0, new ModelResourceLocation("mdeco:workbenchSaw", "inventory"));
      return true;
   }
}
