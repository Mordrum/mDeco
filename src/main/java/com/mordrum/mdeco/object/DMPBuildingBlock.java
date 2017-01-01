package com.mordrum.mdeco.object;

import net.minecraft.block.material.Material;

public enum DMPBuildingBlock {
   pillarLargeWoodAcacia("woodPillarLargeAcacia", DMPBuildingBlockType.pillarLarge, Material.WOOD, "minecraft:blocks/log_acacia", "", "", ""),
   pillarLargeWoodBirch("woodPillarLargeBirch", DMPBuildingBlockType.pillarLarge, Material.WOOD, "minecraft:blocks/log_birch", "", "", ""),
   pillarLargeWoodDarkOak("woodPillarLargeDarkOak", DMPBuildingBlockType.pillarLarge, Material.WOOD, "minecraft:blocks/log_big_oak", "", "", ""),
   pillarLargeWoodJungle("woodPillarLargeJungle", DMPBuildingBlockType.pillarLarge, Material.WOOD, "minecraft:blocks/log_jungle", "", "", ""),
   pillarLargeWoodOak("woodPillarLargeOak", DMPBuildingBlockType.pillarLarge, Material.WOOD, "minecraft:blocks/log_oak", "", "", ""),
   pillarLargeWoodSpruce("woodPillarLargeSpruce", DMPBuildingBlockType.pillarLarge, Material.WOOD, "minecraft:blocks/log_spruce", "", "", ""),
   pillarLargeAndesite("pillarLargeAndesite", DMPBuildingBlockType.pillarLarge, Material.ROCK, "minecraft:blocks/stone_andesite", "", "", ""),
   pillarLargeCobblestone("pillarLargeCobblestone", DMPBuildingBlockType.pillarLarge, Material.ROCK, "minecraft:blocks/cobblestone", "", "", ""),
   pillarLargeDiorite("pillarLargeDiorite", DMPBuildingBlockType.pillarLarge, Material.ROCK, "minecraft:blocks/stone_diorite", "", "", ""),
   pillarLargeEndStone("pillarLargeEndStone", DMPBuildingBlockType.pillarLarge, Material.ROCK, "minecraft:blocks/end_stone", "", "", ""),
   pillarLargeGranite("pillarLargeGranite", DMPBuildingBlockType.pillarLarge, Material.ROCK, "minecraft:blocks/stone_granite", "", "", ""),
   pillarLargeNetherBrick("pillarLargeNetherBrick", DMPBuildingBlockType.pillarLarge, Material.ROCK, "minecraft:blocks/nether_brick", "", "", ""),
   pillarLargeObsidian("pillarLargeObsidian", DMPBuildingBlockType.pillarLarge, Material.ROCK, "minecraft:blocks/obsidian", "", "", ""),
   pillarLargePrismarine("pillarLargePrismarine", DMPBuildingBlockType.pillarLarge, Material.ROCK, "minecraft:blocks/prismarine_rough", "", "", ""),
   pillarLargeQuartz("pillarLargeQuartz", DMPBuildingBlockType.pillarLarge, Material.ROCK, "minecraft:blocks/quartz_block_top", "", "", ""),
   pillarLargeRedSandstone("pillarLargeRedSandstone", DMPBuildingBlockType.pillarLarge, Material.ROCK, "minecraft:blocks/red_sandstone_smooth", "", "", ""),
   pillarLargeSandstone("pillarLargeSandstone", DMPBuildingBlockType.pillarLarge, Material.ROCK, "minecraft:blocks/sandstone_smooth", "", "", ""),
   pillarLargeStone("pillarLargeStone", DMPBuildingBlockType.pillarLarge, Material.ROCK, "minecraft:blocks/stone", "", "", ""),
   pillarSmallWoodAcacia("woodPillarLargeAcacia", DMPBuildingBlockType.pillarSmall, Material.WOOD, "minecraft:blocks/log_acacia", "", "", ""),
   pillarSmallWoodBirch("woodPillarLargeBirch", DMPBuildingBlockType.pillarSmall, Material.WOOD, "minecraft:blocks/log_birch", "", "", ""),
   pillarSmallWoodDarkOak("woodPillarLargeDarkOak", DMPBuildingBlockType.pillarSmall, Material.WOOD, "minecraft:blocks/log_big_oak", "", "", ""),
   pillarSmallWoodJungle("woodPillarLargeJungle", DMPBuildingBlockType.pillarSmall, Material.WOOD, "minecraft:blocks/log_jungle", "", "", ""),
   pillarSmallWoodOak("woodPillarLargeOak", DMPBuildingBlockType.pillarSmall, Material.WOOD, "minecraft:blocks/log_oak", "", "", ""),
   pillarSmallWoodSpruce("woodPillarLargeSpruce", DMPBuildingBlockType.pillarSmall, Material.WOOD, "minecraft:blocks/log_spruce", "", "", ""),
   pillarSmallAndesite("pillarSmallAndesite", DMPBuildingBlockType.pillarSmall, Material.ROCK, "minecraft:blocks/stone_andesite", "", "", ""),
   pillarSmallCobblestone("pillarSmallCobblestone", DMPBuildingBlockType.pillarSmall, Material.ROCK, "minecraft:blocks/cobblestone", "", "", ""),
   pillarSmallDiorite("pillarSmallDiorite", DMPBuildingBlockType.pillarSmall, Material.ROCK, "minecraft:blocks/stone_diorite", "", "", ""),
   pillarSmallEndStone("pillarSmallEndStone", DMPBuildingBlockType.pillarSmall, Material.ROCK, "minecraft:blocks/end_stone", "", "", ""),
   pillarSmallGranite("pillarSmallGranite", DMPBuildingBlockType.pillarSmall, Material.ROCK, "minecraft:blocks/stone_granite", "", "", ""),
   pillarSmallNetherBrick("pillarSmallNetherBrick", DMPBuildingBlockType.pillarSmall, Material.ROCK, "minecraft:blocks/nether_brick", "", "", ""),
   pillarSmallObsidian("pillarSmallObsidian", DMPBuildingBlockType.pillarSmall, Material.ROCK, "minecraft:blocks/obsidian", "", "", ""),
   pillarSmallPrismarine("pillarSmallPrismarine", DMPBuildingBlockType.pillarSmall, Material.ROCK, "minecraft:blocks/prismarine_rough", "", "", ""),
   pillarSmallQuartz("pillarSmallQuartz", DMPBuildingBlockType.pillarSmall, Material.ROCK, "minecraft:blocks/quartz_block_top", "", "", ""),
   pillarSmallRedSandstone("pillarSmallRedSandstone", DMPBuildingBlockType.pillarSmall, Material.ROCK, "minecraft:blocks/red_sandstone_smooth", "", "", ""),
   pillarSmallSandstone("pillarSmallSandstone", DMPBuildingBlockType.pillarSmall, Material.ROCK, "minecraft:blocks/sandstone_smooth", "", "", ""),
   pillarSmallStone("pillarSmallStone", DMPBuildingBlockType.pillarSmall, Material.ROCK, "minecraft:blocks/stone", "", "", "");

   public final String oreDictName;
   public final DMPBuildingBlockType blockType;
   public final Material material;
   public final String texturePrimary;
   public final String textureTrim;
   public final String textureHardware;
   public final String textureExtra;

   DMPBuildingBlock(String oreDictName, DMPBuildingBlockType blockType, Material material, String texturePrimary, String textureTrim, String textureHardware, String textureExtra) {
      this.oreDictName = oreDictName;
      this.blockType = blockType;
      this.material = material;
      this.texturePrimary = texturePrimary;
      this.textureTrim = textureTrim;
      this.textureHardware = textureHardware;
      this.textureExtra = textureExtra;
   }
}
