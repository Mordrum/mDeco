package com.mordrum.mdeco.gui;

import com.mordrum.mdeco.gui.container.DMPGuiContainerCabinetWall;
import com.mordrum.mdeco.gui.container.DMPGuiContainerDeskWoodBasic;
import com.mordrum.mdeco.gui.container.DMPGuiContainerMarketStand;
import com.mordrum.mdeco.gui.container.DMPGuiContainerWoodBox;
import com.mordrum.mdeco.gui.container.DMPGuiContainerWoodCrate;
import com.mordrum.mdeco.inventory.DMPContainerWorkbenchSaw;
import com.mordrum.mdeco.gui.container.DMPGuiContainerCabinetBase;
import com.mordrum.mdeco.gui.container.DMPGuiContainerCabinetWallGlass;
import com.mordrum.mdeco.gui.container.DMPGuiContainerCurioBase;
import com.mordrum.mdeco.gui.container.DMPGuiContainerCurioTop;
import com.mordrum.mdeco.gui.container.DMPGuiContainerKitchenTableSetting;
import com.mordrum.mdeco.gui.container.DMPGuiContainerMantle;
import com.mordrum.mdeco.gui.container.DMPGuiContainerMarketCrate;
import com.mordrum.mdeco.gui.container.DMPGuiContainerPoleSign;
import com.mordrum.mdeco.gui.container.DMPGuiContainerShelf;
import com.mordrum.mdeco.gui.container.DMPGuiContainerShopSign;
import com.mordrum.mdeco.gui.container.DMPGuiContainerSofa;
import com.mordrum.mdeco.gui.container.DMPGuiContainerWallLantern;
import com.mordrum.mdeco.gui.container.DMPGuiContainerWorkbenchFoundry;
import com.mordrum.mdeco.gui.container.DMPGuiContainerWorkbenchSaw;
import com.mordrum.mdeco.inventory.DMPContainerCabinetBase;
import com.mordrum.mdeco.inventory.DMPContainerCabinetWall;
import com.mordrum.mdeco.inventory.DMPContainerCabinetWallGlass;
import com.mordrum.mdeco.inventory.DMPContainerCurioBase;
import com.mordrum.mdeco.inventory.DMPContainerCurioTop;
import com.mordrum.mdeco.inventory.DMPContainerDeskWoodBasic;
import com.mordrum.mdeco.inventory.DMPContainerKitchenTableSetting;
import com.mordrum.mdeco.inventory.DMPContainerMantle;
import com.mordrum.mdeco.inventory.DMPContainerMarketCrate;
import com.mordrum.mdeco.inventory.DMPContainerMarketStand;
import com.mordrum.mdeco.inventory.DMPContainerPoleSign;
import com.mordrum.mdeco.inventory.DMPContainerShelf;
import com.mordrum.mdeco.inventory.DMPContainerShopSign;
import com.mordrum.mdeco.inventory.DMPContainerSofa;
import com.mordrum.mdeco.inventory.DMPContainerWallLantern;
import com.mordrum.mdeco.inventory.DMPContainerWoodBox;
import com.mordrum.mdeco.inventory.DMPContainerWoodCrate;
import com.mordrum.mdeco.inventory.DMPContainerWorkbenchFoundry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class DMPGuiHandler implements IGuiHandler {
   public static final int DMP_GUI_WORKBENCH_FOUNDRY = 0;
   public static final int DMP_GUI_WORKBENCH_SAW = 1;
   public static final int DMP_GUI_BOX = 10;
   public static final int DMP_GUI_CABINET_BASE = 11;
   public static final int DMP_GUI_CABINET_WALL = 12;
   public static final int DMP_GUI_CABINET_WALL_GLASS = 13;
   public static final int DMP_GUI_CURIO_BASE = 14;
   public static final int DMP_GUI_CURIO_TOP = 15;
   public static final int DMP_GUI_CRATE = 16;
   public static final int DMP_GUI_DESK = 17;
   public static final int DMP_GUI_KITCHEN_TABLESETTING = 18;
   public static final int DMP_GUI_MANTLE = 19;
   public static final int DMP_GUI_MARKET_CRATE = 20;
   public static final int DMP_GUI_MARKET_STAND = 21;
   public static final int DMP_GUI_POLE_SIGN = 22;
   public static final int DMP_GUI_SHELF = 23;
   public static final int DMP_GUI_SHOP_SIGN = 24;
   public static final int DMP_GUI_SOFA = 25;
   public static final int DMP_GUI_WALL_LANTERN = 26;

   public Object getServerGuiElement(int ID, EntityPlayer player, World worldIn, int x, int y, int z) {
      switch(ID) {
      case 0:
         return new DMPContainerWorkbenchFoundry(worldIn, new BlockPos(x, y, z), player);
      case 1:
         return new DMPContainerWorkbenchSaw(worldIn, new BlockPos(x, y, z), player);
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      default:
         return null;
      case 10:
         return new DMPContainerWoodBox(worldIn, new BlockPos(x, y, z), player);
      case 11:
         return new DMPContainerCabinetBase(worldIn, new BlockPos(x, y, z), player);
      case 12:
         return new DMPContainerCabinetWall(worldIn, new BlockPos(x, y, z), player);
      case 13:
         return new DMPContainerCabinetWallGlass(worldIn, new BlockPos(x, y, z), player);
      case 14:
         return new DMPContainerCurioBase(worldIn, new BlockPos(x, y, z), player);
      case 15:
         return new DMPContainerCurioTop(worldIn, new BlockPos(x, y, z), player);
      case 16:
         return new DMPContainerWoodCrate(worldIn, new BlockPos(x, y, z), player);
      case 17:
         return new DMPContainerDeskWoodBasic(worldIn, new BlockPos(x, y, z), player);
      case 18:
         return new DMPContainerKitchenTableSetting(worldIn, new BlockPos(x, y, z), player);
      case 19:
         return new DMPContainerMantle(worldIn, new BlockPos(x, y, z), player);
      case 20:
         return new DMPContainerMarketCrate(worldIn, new BlockPos(x, y, z), player);
      case 21:
         return new DMPContainerMarketStand(worldIn, new BlockPos(x, y, z), player);
      case 22:
         return new DMPContainerPoleSign(worldIn, new BlockPos(x, y, z), player);
      case 23:
         return new DMPContainerShelf(worldIn, new BlockPos(x, y, z), player);
      case 24:
         return new DMPContainerShopSign(worldIn, new BlockPos(x, y, z), player);
      case 25:
         return new DMPContainerSofa(worldIn, new BlockPos(x, y, z), player);
      case 26:
         return new DMPContainerWallLantern(worldIn, new BlockPos(x, y, z), player);
      }
   }

   public Object getClientGuiElement(int ID, EntityPlayer player, World worldIn, int x, int y, int z) {
      switch(ID) {
      case 0:
         return new DMPGuiContainerWorkbenchFoundry((DMPContainerWorkbenchFoundry)this.getServerGuiElement(ID, player, worldIn, x, y, z), worldIn, player, new BlockPos(x, y, z));
      case 1:
         return new DMPGuiContainerWorkbenchSaw((DMPContainerWorkbenchSaw)this.getServerGuiElement(ID, player, worldIn, x, y, z), worldIn, player, new BlockPos(x, y, z));
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      default:
         return null;
      case 10:
         return new DMPGuiContainerWoodBox((DMPContainerWoodBox)this.getServerGuiElement(ID, player, worldIn, x, y, z), worldIn, player, new BlockPos(x, y, z));
      case 11:
         return new DMPGuiContainerCabinetBase((DMPContainerCabinetBase)this.getServerGuiElement(ID, player, worldIn, x, y, z), worldIn, player, new BlockPos(x, y, z));
      case 12:
         return new DMPGuiContainerCabinetWall((DMPContainerCabinetWall)this.getServerGuiElement(ID, player, worldIn, x, y, z), worldIn, player, new BlockPos(x, y, z));
      case 13:
         return new DMPGuiContainerCabinetWallGlass((DMPContainerCabinetWallGlass)this.getServerGuiElement(ID, player, worldIn, x, y, z), worldIn, player, new BlockPos(x, y, z));
      case 14:
         return new DMPGuiContainerCurioBase((DMPContainerCurioBase)this.getServerGuiElement(ID, player, worldIn, x, y, z), worldIn, player, new BlockPos(x, y, z));
      case 15:
         return new DMPGuiContainerCurioTop((DMPContainerCurioTop)this.getServerGuiElement(ID, player, worldIn, x, y, z), worldIn, player, new BlockPos(x, y, z));
      case 16:
         return new DMPGuiContainerWoodCrate((DMPContainerWoodCrate)this.getServerGuiElement(ID, player, worldIn, x, y, z), worldIn, player, new BlockPos(x, y, z));
      case 17:
         return new DMPGuiContainerDeskWoodBasic((DMPContainerDeskWoodBasic)this.getServerGuiElement(ID, player, worldIn, x, y, z), worldIn, player, new BlockPos(x, y, z));
      case 18:
         return new DMPGuiContainerKitchenTableSetting((DMPContainerKitchenTableSetting)this.getServerGuiElement(ID, player, worldIn, x, y, z), worldIn, player, new BlockPos(x, y, z));
      case 19:
         return new DMPGuiContainerMantle((DMPContainerMantle)this.getServerGuiElement(ID, player, worldIn, x, y, z), worldIn, player, new BlockPos(x, y, z));
      case 20:
         return new DMPGuiContainerMarketCrate((DMPContainerMarketCrate)this.getServerGuiElement(ID, player, worldIn, x, y, z), worldIn, player, new BlockPos(x, y, z));
      case 21:
         return new DMPGuiContainerMarketStand((DMPContainerMarketStand)this.getServerGuiElement(ID, player, worldIn, x, y, z), worldIn, player, new BlockPos(x, y, z));
      case 22:
         return new DMPGuiContainerPoleSign((DMPContainerPoleSign)this.getServerGuiElement(ID, player, worldIn, x, y, z), worldIn, player, new BlockPos(x, y, z));
      case 23:
         return new DMPGuiContainerShelf((DMPContainerShelf)this.getServerGuiElement(ID, player, worldIn, x, y, z), worldIn, player, new BlockPos(x, y, z));
      case 24:
         return new DMPGuiContainerShopSign((DMPContainerShopSign)this.getServerGuiElement(ID, player, worldIn, x, y, z), worldIn, player, new BlockPos(x, y, z));
      case 25:
         return new DMPGuiContainerSofa((DMPContainerSofa)this.getServerGuiElement(ID, player, worldIn, x, y, z), worldIn, player, new BlockPos(x, y, z));
      case 26:
         return new DMPGuiContainerWallLantern((DMPContainerWallLantern)this.getServerGuiElement(ID, player, worldIn, x, y, z), worldIn, player, new BlockPos(x, y, z));
      }
   }
}
