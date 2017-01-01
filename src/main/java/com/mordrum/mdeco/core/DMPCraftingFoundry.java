package com.mordrum.mdeco.core;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mordrum.mdeco.MDeco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class DMPCraftingFoundry {
   private static final DMPCraftingFoundry instance = new DMPCraftingFoundry();
   private final List recipes = Lists.newArrayList();

   public static DMPCraftingFoundry getInstance() {
      return instance;
   }

   private DMPCraftingFoundry() {
      this.initAccessories();
      this.initCaps();
      this.initChains();
      this.initKitchenAccessories();
      this.initPoles();
      Collections.sort(this.recipes, new Comparator() {
         public int compare(IRecipe recipe1, IRecipe recipe2) {
            return recipe1 instanceof ShapelessRecipes && recipe2 instanceof ShapedRecipes?1:(recipe2 instanceof ShapelessRecipes && recipe1 instanceof ShapedRecipes?-1:(recipe2.getRecipeSize() < recipe1.getRecipeSize()?-1:(recipe2.getRecipeSize() > recipe1.getRecipeSize()?1:0)));
         }

         public int compare(Object object1, Object object2) {
            return this.compare((IRecipe)object1, (IRecipe)object2);
         }
      });
   }

   public ShapedRecipes addRecipe(ItemStack stackIn, Object... recipeComponents) {
      String s = "";
      int i = 0;
      int j = 0;
      int k = 0;
      if(recipeComponents[i] instanceof String[]) {
         String[] var11 = (String[])((String[])((String[])recipeComponents[i++]));

         for(int aitemstack = 0; aitemstack < var11.length; ++aitemstack) {
            String shapedrecipes = var11[aitemstack];
            ++k;
            j = shapedrecipes.length();
            s = s + shapedrecipes;
         }
      } else {
         while(recipeComponents[i] instanceof String) {
            String hashmap = (String)recipeComponents[i++];
            ++k;
            j = hashmap.length();
            s = s + hashmap;
         }
      }

      HashMap var12;
      for(var12 = Maps.newHashMap(); i < recipeComponents.length; i += 2) {
         Character var13 = (Character)recipeComponents[i];
         ItemStack var15 = null;
         if(recipeComponents[i + 1] instanceof Item) {
            var15 = new ItemStack((Item)recipeComponents[i + 1]);
         } else if(recipeComponents[i + 1] instanceof Block) {
            var15 = new ItemStack((Block)recipeComponents[i + 1], 1, 32767);
         } else if(recipeComponents[i + 1] instanceof ItemStack) {
            var15 = (ItemStack)recipeComponents[i + 1];
         }

         var12.put(var13, var15);
      }

      ItemStack[] var14 = new ItemStack[j * k];

      for(int var16 = 0; var16 < j * k; ++var16) {
         char c0 = s.charAt(var16);
         if(var12.containsKey(Character.valueOf(c0))) {
            var14[var16] = ((ItemStack)var12.get(Character.valueOf(c0))).copy();
         } else {
            var14[var16] = null;
         }
      }

      ShapedRecipes var17 = new ShapedRecipes(j, k, var14, stackIn);
      this.recipes.add(var17);
      return var17;
   }

   public void addShapelessRecipe(ItemStack stackIn, Object... recipeComponents) {
      ArrayList arraylist = Lists.newArrayList();
      Object[] aobject = recipeComponents;
      int i = recipeComponents.length;

      for(int j = 0; j < i; ++j) {
         Object object1 = aobject[j];
         if(object1 instanceof ItemStack) {
            arraylist.add(((ItemStack)object1).copy());
         } else if(object1 instanceof Item) {
            arraylist.add(new ItemStack((Item)object1));
         } else {
            if(!(object1 instanceof Block)) {
               throw new IllegalArgumentException("Invalid shapeless recipe: unknown type " + object1.getClass().getName() + "!");
            }

            arraylist.add(new ItemStack((Block)object1));
         }
      }

      this.recipes.add(new ShapelessRecipes(stackIn, arraylist));
   }

   public void addRecipe(IRecipe recipe) {
      this.recipes.add(recipe);
   }

   public ItemStack findMatchingRecipe(InventoryCrafting inventoryIn, World worldIn) {
      Iterator iterator = this.recipes.iterator();

      while(iterator.hasNext()) {
         IRecipe irecipe = (IRecipe)iterator.next();
         if(irecipe.matches(inventoryIn, worldIn)) {
            return irecipe.getCraftingResult(inventoryIn);
         }
      }

      return null;
   }

   public NonNullList<ItemStack> getRecipeItemStack(InventoryCrafting inventoryIn, World worldIn) {

      for (Object recipe : this.recipes) {
         IRecipe aitemstack = (IRecipe) recipe;
         if (aitemstack.matches(inventoryIn, worldIn)) {
            return aitemstack.getRemainingItems(inventoryIn);
         }
      }

      NonNullList<ItemStack> itemStacks = NonNullList.withSize(inventoryIn.getSizeInventory(), ItemStack.EMPTY);

      for(int i = 0; i < inventoryIn.getSizeInventory(); ++i) {
         itemStacks.add(inventoryIn.getStackInSlot(i));
      }

      return itemStacks;
   }

   public List getRecipeList() {
      return this.recipes;
   }

   private void initAccessories() {
      if(MDeco.settings.contentCoinStack) {
         this.addRecipe(new ShapedOreRecipe(new ItemStack(MDeco.blocks.coinStack, 2, 0), new Object[]{"   ", " x ", "xxx", Character.valueOf('x'), new ItemStack(Items.GOLD_NUGGET, 1, 0)}));
      }

   }

   private void initCaps() {
      if(MDeco.settings.contentCaps) {
         this.addRecipe(new ShapedOreRecipe(new ItemStack((Block) MDeco.blocks.cap.get("capSmallPyramidIron"), 4, 0), new Object[]{"   ", " x ", "xxx", Character.valueOf('x'), new ItemStack(Items.IRON_INGOT, 1, 0)}));
         this.addRecipe(new ShapelessOreRecipe(new ItemStack((Block) MDeco.blocks.cap.get("capLargePyramidIron"), 1, 0), new Object[]{new ItemStack(Items.IRON_INGOT, 1, 0), new ItemStack((Block) MDeco.blocks.cap.get("capSmallPyramidIron"), 1, 0)}));
         this.addRecipe(new ShapedOreRecipe(new ItemStack((Block) MDeco.blocks.cap.get("capOvalIron"), 4, 0), new Object[]{" x ", "xx ", " x ", Character.valueOf('x'), new ItemStack(Items.IRON_INGOT, 1, 0)}));
         this.addRecipe(new ShapedOreRecipe(new ItemStack((Block) MDeco.blocks.cap.get("capPlusIron"), 5, 0), new Object[]{" x ", "xxx", " x ", Character.valueOf('x'), new ItemStack(Items.IRON_INGOT, 1, 0)}));
         this.addRecipe(new ShapedOreRecipe(new ItemStack((Block) MDeco.blocks.cap.get("capRoundIron"), 4, 0), new Object[]{" x ", "x x", " x ", Character.valueOf('x'), new ItemStack(Items.IRON_INGOT, 1, 0)}));
         this.addRecipe(new ShapelessOreRecipe(new ItemStack((Block) MDeco.blocks.cap.get("capSquareIron"), 1, 0), new Object[]{new ItemStack(Items.IRON_INGOT, 1, 0), new ItemStack((Block) MDeco.blocks.cap.get("capRoundIron"), 1, 0)}));
         this.addRecipe(new ShapedOreRecipe(new ItemStack((Block) MDeco.blocks.cap.get("capSmallPyramidBlackIron"), 4, 0), new Object[]{"   ", " x ", "xxx", Character.valueOf('x'), new ItemStack(MDeco.items.blackIronIngot, 1, 0)}));
         this.addRecipe(new ShapelessOreRecipe(new ItemStack((Block) MDeco.blocks.cap.get("capLargePyramidBlackIron"), 1, 0), new Object[]{new ItemStack(MDeco.items.blackIronIngot, 1, 0), new ItemStack((Block) MDeco.blocks.cap.get("capSmallPyramidBlackIron"), 1, 0)}));
         this.addRecipe(new ShapedOreRecipe(new ItemStack((Block) MDeco.blocks.cap.get("capOvalBlackIron"), 4, 0), new Object[]{" x ", "xx ", " x ", Character.valueOf('x'), new ItemStack(MDeco.items.blackIronIngot, 1, 0)}));
         this.addRecipe(new ShapedOreRecipe(new ItemStack((Block) MDeco.blocks.cap.get("capPlusBlackIron"), 5, 0), new Object[]{" x ", "xxx", " x ", Character.valueOf('x'), new ItemStack(MDeco.items.blackIronIngot, 1, 0)}));
         this.addRecipe(new ShapedOreRecipe(new ItemStack((Block) MDeco.blocks.cap.get("capRoundBlackIron"), 4, 0), new Object[]{" x ", "x x", " x ", Character.valueOf('x'), new ItemStack(MDeco.items.blackIronIngot, 1, 0)}));
         this.addRecipe(new ShapelessOreRecipe(new ItemStack((Block) MDeco.blocks.cap.get("capSquareBlackIron"), 1, 0), new Object[]{new ItemStack(MDeco.items.blackIronIngot, 1, 0), new ItemStack((Block) MDeco.blocks.cap.get("capRoundBlackIron"), 1, 0)}));
         this.addRecipe(new ShapedOreRecipe(new ItemStack((Block) MDeco.blocks.cap.get("capSmallPyramidGold"), 4, 0), new Object[]{"   ", " x ", "xxx", Character.valueOf('x'), new ItemStack(Items.GOLD_INGOT, 1, 0)}));
         this.addRecipe(new ShapelessOreRecipe(new ItemStack((Block) MDeco.blocks.cap.get("capLargePyramidGold"), 1, 0), new Object[]{new ItemStack(Items.GOLD_INGOT, 1, 0), new ItemStack((Block) MDeco.blocks.cap.get("capSmallPyramidGold"), 1, 0)}));
         this.addRecipe(new ShapedOreRecipe(new ItemStack((Block) MDeco.blocks.cap.get("capOvalGold"), 4, 0), new Object[]{" x ", "xx ", " x ", Character.valueOf('x'), new ItemStack(Items.GOLD_INGOT, 1, 0)}));
         this.addRecipe(new ShapedOreRecipe(new ItemStack((Block) MDeco.blocks.cap.get("capPlusGold"), 5, 0), new Object[]{" x ", "xxx", " x ", Character.valueOf('x'), new ItemStack(Items.GOLD_INGOT, 1, 0)}));
         this.addRecipe(new ShapedOreRecipe(new ItemStack((Block) MDeco.blocks.cap.get("capRoundGold"), 4, 0), new Object[]{" x ", "x x", " x ", Character.valueOf('x'), new ItemStack(Items.GOLD_INGOT, 1, 0)}));
         this.addRecipe(new ShapelessOreRecipe(new ItemStack((Block) MDeco.blocks.cap.get("capSquareGold"), 1, 0), new Object[]{new ItemStack(Items.GOLD_INGOT, 1, 0), new ItemStack((Block) MDeco.blocks.cap.get("capRoundGold"), 1, 0)}));
      }

   }

   private void initChains() {
      if(MDeco.settings.contentChain) {
         this.addRecipe(new ShapedOreRecipe(new ItemStack((Block) MDeco.blocks.chain.get("chainIron"), 6, 0), new Object[]{"x  ", " x ", "x  ", Character.valueOf('x'), new ItemStack(Items.IRON_INGOT, 1, 0)}));
         this.addRecipe(new ShapedOreRecipe(new ItemStack((Block) MDeco.blocks.chain.get("chainBlackIron"), 6, 0), new Object[]{"x  ", " x ", "x  ", Character.valueOf('x'), new ItemStack(MDeco.items.blackIronIngot, 1, 0)}));
         this.addRecipe(new ShapedOreRecipe(new ItemStack((Block) MDeco.blocks.chain.get("chainGold"), 6, 0), new Object[]{"x  ", " x ", "x  ", Character.valueOf('x'), new ItemStack(Items.GOLD_INGOT, 1, 0)}));
         this.addRecipe(new ShapedOreRecipe(new ItemStack((Block) MDeco.blocks.chainLarge.get("chainLargeIron"), 2, 0), new Object[]{"x  ", " x ", "x  ", Character.valueOf('x'), new ItemStack((Block) MDeco.blocks.chain.get("chainIron"), 1, 0)}));
         this.addRecipe(new ShapedOreRecipe(new ItemStack((Block) MDeco.blocks.chainLarge.get("chainLargeBlackIron"), 2, 0), new Object[]{"x  ", " x ", "x  ", Character.valueOf('x'), new ItemStack((Block) MDeco.blocks.chain.get("chainBlackIron"), 1, 0)}));
         this.addRecipe(new ShapedOreRecipe(new ItemStack((Block) MDeco.blocks.chainLarge.get("chainLargeGold"), 2, 0), new Object[]{"x  ", " x ", "x  ", Character.valueOf('x'), new ItemStack((Block) MDeco.blocks.chain.get("chainGold"), 1, 0)}));
      }

   }

   private void initKitchenAccessories() {
      this.addRecipe(new ShapedOreRecipe(new ItemStack(MDeco.blocks.kitchenKettle, 1, 0), new Object[]{"   ", "x x", "xxx", Character.valueOf('x'), new ItemStack(MDeco.items.blackIronNugget, 1, 0)}));
      this.addRecipe(new ShapedOreRecipe(new ItemStack(MDeco.blocks.kitchenPot, 1, 0), new Object[]{"   ", "xy ", "   ", Character.valueOf('x'), new ItemStack(MDeco.blocks.kitchenKettle, 1, 0), Character.valueOf('y'), new ItemStack(MDeco.items.blackIronNugget, 1, 0)}));
      this.addRecipe(new ShapedOreRecipe(new ItemStack(MDeco.blocks.kitchenShakers, 1, 0), new Object[]{"   ", "x x", "x x", Character.valueOf('x'), new ItemStack(MDeco.items.blackIronNugget, 1, 0)}));
      this.addRecipe(new ShapedOreRecipe(new ItemStack(MDeco.blocks.kitchenTableSetting, 4, 0), new Object[]{"  x", "xxy", "xxy", Character.valueOf('x'), new ItemStack(MDeco.items.blackIronIngot, 1, 0), Character.valueOf('y'), new ItemStack(MDeco.items.blackIronNugget, 1, 0)}));
      this.addRecipe(new ShapedOreRecipe(new ItemStack(MDeco.blocks.kitchenWallUtensils, 1, 0), new Object[]{" x ", "yyy", "yyy", Character.valueOf('x'), new ItemStack(MDeco.items.blackIronIngot, 1, 0), Character.valueOf('y'), new ItemStack(MDeco.items.blackIronNugget, 1, 0)}));
   }

   private void initPoles() {
      if(MDeco.settings.contentPoleMetal) {
         this.addRecipe(new ShapedOreRecipe(new ItemStack((Block) MDeco.blocks.poleMetal.get("poleIron"), 3, 0), new Object[]{" x ", " x ", " x ", Character.valueOf('x'), new ItemStack(Items.IRON_INGOT, 1, 0)}));
         this.addRecipe(new ShapedOreRecipe(new ItemStack((Block) MDeco.blocks.poleMetal.get("poleBlackIron"), 3, 0), new Object[]{" x ", " x ", " x ", Character.valueOf('x'), new ItemStack(MDeco.items.blackIronIngot, 1, 0)}));
         this.addRecipe(new ShapedOreRecipe(new ItemStack((Block) MDeco.blocks.poleMetal.get("poleGold"), 3, 0), new Object[]{" x ", " x ", " x ", Character.valueOf('x'), new ItemStack(Items.GOLD_INGOT, 1, 0)}));
         this.addRecipe(new ShapedOreRecipe(new ItemStack((Block) MDeco.blocks.poleMetalConnector.get("poleConnectorIron"), 6, 0), new Object[]{" y ", "yxy", " y ", Character.valueOf('x'), new ItemStack((Block) MDeco.blocks.poleMetal.get("poleIron"), 1, 0), Character.valueOf('y'), new ItemStack(Items.IRON_INGOT, 1, 0)}));
         this.addRecipe(new ShapedOreRecipe(new ItemStack((Block) MDeco.blocks.poleMetalConnector.get("poleConnectorBlackIron"), 6, 0), new Object[]{" y ", "yxy", " y ", Character.valueOf('x'), new ItemStack((Block) MDeco.blocks.poleMetal.get("poleBlackIron"), 1, 0), Character.valueOf('y'), new ItemStack(MDeco.items.blackIronIngot, 1, 0)}));
         this.addRecipe(new ShapedOreRecipe(new ItemStack((Block) MDeco.blocks.poleMetalConnector.get("poleConnectorGold"), 6, 0), new Object[]{" y ", "yxy", " y ", Character.valueOf('x'), new ItemStack((Block) MDeco.blocks.poleMetal.get("poleGold"), 1, 0), Character.valueOf('y'), new ItemStack(Items.GOLD_INGOT, 1, 0)}));
      }

   }
}
