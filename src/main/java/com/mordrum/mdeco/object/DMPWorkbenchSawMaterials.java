package com.mordrum.mdeco.object;

import com.mordrum.mdeco.MDeco;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public enum DMPWorkbenchSawMaterials {
   TILE_ANDESITE(Item.getItemFromBlock(Blocks.STONE), 5, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileAndesite")), 6),
   TILE_COBBLESTONE(Item.getItemFromBlock(Blocks.COBBLESTONE), 0, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileCobblestone")), 6),
   TILE_DIAMOND(Item.getItemFromBlock(Blocks.DIAMOND_BLOCK), 0, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileDiamond")), 6),
   TILE_DIORITE(Item.getItemFromBlock(Blocks.STONE), 3, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileDiorite")), 6),
   TILE_EMERALD(Item.getItemFromBlock(Blocks.EMERALD_BLOCK), 0, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileEmerald")), 6),
   TILE_ENDSTONE(Item.getItemFromBlock(Blocks.END_STONE), 0, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileEndStone")), 6),
   TILE_GRANITE(Item.getItemFromBlock(Blocks.STONE), 1, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileGranite")), 6),
   TILE_LAPIS(Item.getItemFromBlock(Blocks.LAPIS_BLOCK), 0, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileLapis")), 6),
   TILE_NETHERBRICK(Item.getItemFromBlock(Blocks.NETHER_BRICK), 0, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileNetherBrick")), 6),
   TILE_OBSIDIAN(Item.getItemFromBlock(Blocks.OBSIDIAN), 0, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileObsidian")), 6),
   TILE_PRISMARINE(Item.getItemFromBlock(Blocks.PRISMARINE), 0, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tilePrismarine")), 6),
   TILE_QUARTZ(Item.getItemFromBlock(Blocks.QUARTZ_BLOCK), 0, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileQuartz")), 6),
   TILE_REDSANDSTONE(Item.getItemFromBlock(Blocks.RED_SANDSTONE), 0, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileRedSandstone")), 6),
   TILE_SANDSTONE(Item.getItemFromBlock(Blocks.SANDSTONE), 0, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileSandstone")), 6),
   TILE_STONE(Item.getItemFromBlock(Blocks.STONE), 0, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileStone")), 6),
   TILE_IRON(Item.getItemFromBlock(Blocks.IRON_BLOCK), 0, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileIron")), 6),
   TILE_BLACKIRON(Item.getItemFromBlock(MDeco.blocks.blockBlackIron), 0, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileBlackIron")), 6),
   TILE_GOLD(Item.getItemFromBlock(Blocks.GOLD_BLOCK), 0, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileGold")), 6),
   TILE_CLAY_WHITE(Item.getItemFromBlock(Blocks.STAINED_HARDENED_CLAY), 0, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileClayWhite")), 6),
   TILE_CLAY_ORANGE(Item.getItemFromBlock(Blocks.STAINED_HARDENED_CLAY), 1, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileClayOrange")), 6),
   TILE_CLAY_MAGENTA(Item.getItemFromBlock(Blocks.STAINED_HARDENED_CLAY), 2, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileClayMagenta")), 6),
   TILE_CLAY_LIGHTBLUE(Item.getItemFromBlock(Blocks.STAINED_HARDENED_CLAY), 3, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileClayLightBlue")), 6),
   TILE_CLAY_YELLOW(Item.getItemFromBlock(Blocks.STAINED_HARDENED_CLAY), 4, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileClayYellow")), 6),
   TILE_CLAY_LIME(Item.getItemFromBlock(Blocks.STAINED_HARDENED_CLAY), 5, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileClayLime")), 6),
   TILE_CLAY_PINK(Item.getItemFromBlock(Blocks.STAINED_HARDENED_CLAY), 6, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileClayPink")), 6),
   TILE_CLAY_GRAY(Item.getItemFromBlock(Blocks.STAINED_HARDENED_CLAY), 7, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileClayGray")), 6),
   TILE_CLAY_LIGHTGRAY(Item.getItemFromBlock(Blocks.STAINED_HARDENED_CLAY), 8, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileClayLightGray")), 6),
   TILE_CLAY_CYAN(Item.getItemFromBlock(Blocks.STAINED_HARDENED_CLAY), 9, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileClayCyan")), 6),
   TILE_CLAY_PURPLE(Item.getItemFromBlock(Blocks.STAINED_HARDENED_CLAY), 10, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileClayPurple")), 6),
   TILE_CLAY_BLUE(Item.getItemFromBlock(Blocks.STAINED_HARDENED_CLAY), 11, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileClayBlue")), 6),
   TILE_CLAY_BROWN(Item.getItemFromBlock(Blocks.STAINED_HARDENED_CLAY), 12, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileClayBrown")), 6),
   TILE_CLAY_GREEN(Item.getItemFromBlock(Blocks.STAINED_HARDENED_CLAY), 13, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileClayGreen")), 6),
   TILE_CLAY_RED(Item.getItemFromBlock(Blocks.STAINED_HARDENED_CLAY), 14, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileClayRed")), 6),
   TILE_CLAY_BLACK(Item.getItemFromBlock(Blocks.STAINED_HARDENED_CLAY), 15, Item.getItemFromBlock((Block) MDeco.blocks.tile.get("tileClayBlack")), 6),
   WOODPANEL_ACACIA(Item.getItemFromBlock((Block) MDeco.blocks.woodTimber.get("woodTimberAcacia")), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodPanel.get("woodPanelAcacia")), 4),
   WOODPANEL_BIRCH(Item.getItemFromBlock((Block) MDeco.blocks.woodTimber.get("woodTimberBirch")), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodPanel.get("woodPanelBirch")), 4),
   WOODPANEL_DARKOAK(Item.getItemFromBlock((Block) MDeco.blocks.woodTimber.get("woodTimberDarkOak")), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodPanel.get("woodPanelDarkOak")), 4),
   WOODPANEL_JUNGLE(Item.getItemFromBlock((Block) MDeco.blocks.woodTimber.get("woodTimberJungle")), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodPanel.get("woodPanelJungle")), 4),
   WOODPANEL_OAK(Item.getItemFromBlock((Block) MDeco.blocks.woodTimber.get("woodTimberOak")), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodPanel.get("woodPanelOak")), 4),
   WOODPANEL_SPRUCE(Item.getItemFromBlock((Block) MDeco.blocks.woodTimber.get("woodTimberSpruce")), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodPanel.get("woodPanelSpruce")), 4),
   WOODBARKPANEL_ACACIA(Item.getItemFromBlock((Block) MDeco.blocks.woodTimber.get("woodBarkTimberAcacia")), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodPanel.get("woodBarkPanelAcacia")), 4),
   WOODBARKPANEL_BIRCH(Item.getItemFromBlock((Block) MDeco.blocks.woodTimber.get("woodBarkTimberBirch")), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodPanel.get("woodBarkPanelBirch")), 4),
   WOODBARKPANEL_DARKOAK(Item.getItemFromBlock((Block) MDeco.blocks.woodTimber.get("woodBarkTimberDarkOak")), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodPanel.get("woodBarkPanelDarkOak")), 4),
   WOODBARKPANEL_JUNGLE(Item.getItemFromBlock((Block) MDeco.blocks.woodTimber.get("woodBarkTimberJungle")), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodPanel.get("woodBarkPanelJungle")), 4),
   WOODBARKPANEL_OAK(Item.getItemFromBlock((Block) MDeco.blocks.woodTimber.get("woodBarkTimberOak")), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodPanel.get("woodBarkPanelOak")), 4),
   WOODBARKPANEL_SPRUCE(Item.getItemFromBlock((Block) MDeco.blocks.woodTimber.get("woodBarkTimberSpruce")), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodPanel.get("woodBarkPanelSpruce")), 4),
   WOODTIMBER_ACACIA(Item.getItemFromBlock(Blocks.PLANKS), 4, Item.getItemFromBlock((Block) MDeco.blocks.woodTimber.get("woodTimberAcacia")), 4),
   WOODTIMBER_BIRCH(Item.getItemFromBlock(Blocks.PLANKS), 2, Item.getItemFromBlock((Block) MDeco.blocks.woodTimber.get("woodTimberBirch")), 4),
   WOODTIMBER_DARKOAK(Item.getItemFromBlock(Blocks.PLANKS), 5, Item.getItemFromBlock((Block) MDeco.blocks.woodTimber.get("woodTimberDarkOak")), 4),
   WOODTIMBER_JUNGLE(Item.getItemFromBlock(Blocks.PLANKS), 3, Item.getItemFromBlock((Block) MDeco.blocks.woodTimber.get("woodTimberJungle")), 4),
   WOODTIMBER_OAK(Item.getItemFromBlock(Blocks.PLANKS), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodTimber.get("woodTimberOak")), 4),
   WOODTIMBER_SPRUCE(Item.getItemFromBlock(Blocks.PLANKS), 1, Item.getItemFromBlock((Block) MDeco.blocks.woodTimber.get("woodTimberSpruce")), 4),
   WOODBARKTIMBER_ACACIA(Item.getItemFromBlock(Blocks.LOG2), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodTimber.get("woodBarkTimberAcacia")), 4),
   WOODBARKTIMBER_BIRCH(Item.getItemFromBlock(Blocks.LOG), 2, Item.getItemFromBlock((Block) MDeco.blocks.woodTimber.get("woodBarkTimberBirch")), 4),
   WOODBARKTIMBER_DARKOAK(Item.getItemFromBlock(Blocks.LOG2), 1, Item.getItemFromBlock((Block) MDeco.blocks.woodTimber.get("woodBarkTimberDarkOak")), 4),
   WOODBARKTIMBER_JUNGLE(Item.getItemFromBlock(Blocks.LOG), 3, Item.getItemFromBlock((Block) MDeco.blocks.woodTimber.get("woodBarkTimberJungle")), 4),
   WOODBARKTIMBER_OAK(Item.getItemFromBlock(Blocks.LOG), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodTimber.get("woodBarkTimberOak")), 4),
   WOODBARKTIMBER_SPRUCE(Item.getItemFromBlock(Blocks.LOG), 1, Item.getItemFromBlock((Block) MDeco.blocks.woodTimber.get("woodBarkTimberSpruce")), 4),
   WOODTRIM_ACACIA(Item.getItemFromBlock((Block) MDeco.blocks.woodPanel.get("woodPanelAcacia")), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodTrim.get("woodTrimAcacia")), 4),
   WOODTRIM_BIRCH(Item.getItemFromBlock((Block) MDeco.blocks.woodPanel.get("woodPanelBirch")), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodTrim.get("woodTrimBirch")), 4),
   WOODTRIM_DARKOAK(Item.getItemFromBlock((Block) MDeco.blocks.woodPanel.get("woodPanelDarkOak")), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodTrim.get("woodTrimDarkOak")), 4),
   WOODTRIM_JUNGLE(Item.getItemFromBlock((Block) MDeco.blocks.woodPanel.get("woodPanelJungle")), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodTrim.get("woodTrimJungle")), 4),
   WOODTRIM_OAK(Item.getItemFromBlock((Block) MDeco.blocks.woodPanel.get("woodPanelOak")), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodTrim.get("woodTrimOak")), 4),
   WOODTRIM_SPRUCE(Item.getItemFromBlock((Block) MDeco.blocks.woodPanel.get("woodPanelSpruce")), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodTrim.get("woodTrimSpruce")), 4),
   WOODBARKTRIM_ACACIA(Item.getItemFromBlock((Block) MDeco.blocks.woodPanel.get("woodBarkPanelAcacia")), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodTrim.get("woodBarkTrimAcacia")), 4),
   WOODBARKTRIM_BIRCH(Item.getItemFromBlock((Block) MDeco.blocks.woodPanel.get("woodBarkPanelBirch")), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodTrim.get("woodBarkTrimBirch")), 4),
   WOODBARKTRIM_DARKOAK(Item.getItemFromBlock((Block) MDeco.blocks.woodPanel.get("woodBarkPanelDarkOak")), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodTrim.get("woodBarkTrimDarkOak")), 4),
   WOODBARKTRIM_JUNGLE(Item.getItemFromBlock((Block) MDeco.blocks.woodPanel.get("woodBarkPanelJungle")), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodTrim.get("woodBarkTrimJungle")), 4),
   WOODBARKTRIM_OAK(Item.getItemFromBlock((Block) MDeco.blocks.woodPanel.get("woodBarkPanelOak")), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodTrim.get("woodBarkTrimOak")), 4),
   WOODBARKTRIM_SPRUCE(Item.getItemFromBlock((Block) MDeco.blocks.woodPanel.get("woodBarkPanelSpruce")), 0, Item.getItemFromBlock((Block) MDeco.blocks.woodTrim.get("woodBarkTrimSpruce")), 4);

   public Item itemInput;
   public int variantInput;
   public Item itemOutput;
   public int outputAmount;

   private DMPWorkbenchSawMaterials(Item itemInput, int variantInput, Item itemOutput, int outputAmount) {
      this.itemInput = itemInput;
      this.variantInput = variantInput;
      this.itemOutput = itemOutput;
      this.outputAmount = outputAmount;
   }
}
