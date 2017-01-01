package com.mordrum.mdeco.json;

import com.mordrum.mdeco.MDeco;
import com.mordrum.mdeco.block.DMPBlockKitchenWallUtensils;
import com.mordrum.mdeco.core.DMPConnectionsAll;
import com.mordrum.mdeco.object.DMPDecorationType;
import com.mordrum.mdeco.block.DMPBlockKitchenKettle;
import com.mordrum.mdeco.block.DMPBlockKitchenPot;
import com.mordrum.mdeco.block.DMPBlockKitchenShakers;
import com.mordrum.mdeco.object.DMPDecoration;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public abstract class DMPJsonStateDecoration {
   public static int createBlockstateFiles(String rootPath) {
      int filesGenerated = 0;
      DMPDecoration[] var6 = DMPDecoration.values();
      int var7 = var6.length;

	   for (DMPDecoration decoration : var6) {
		   try {
			   File jsonFile = new File(rootPath + decoration.name() + ".json");
			   if (jsonFile != null) {
				   if (!jsonFile.exists()) {
					   jsonFile.createNewFile();
				   }

				   PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
				   if (printwriter != null) {
					   printwriter.println("{");
					   printwriter.println("\"variants\":{");
					   if (decoration.decorationType != DMPDecorationType.benchWood &&
							   decoration.decorationType != DMPDecorationType.benchWoodMetalArm &&
							   decoration.decorationType != DMPDecorationType.benchStone) {
						   if (decoration.decorationType != DMPDecorationType.capSmallPyramid &&
								   decoration.decorationType != DMPDecorationType.capLargePyramid &&
								   decoration.decorationType != DMPDecorationType.capOval &&
								   decoration.decorationType != DMPDecorationType.capPlus &&
								   decoration.decorationType != DMPDecorationType.capRound &&
								   decoration.decorationType != DMPDecorationType.capSquare) {
							   if (decoration.decorationType == DMPDecorationType.chain) {
								   printwriter.println(String.format("\"normal\":{\"model\":\"mdeco:%s\"}", new Object[]{
										   decoration.name()
								   }));
							   } else if (decoration.decorationType == DMPDecorationType.chainLarge) {
								   printwriter.println(String.format("\"normal\":{\"model\":\"mdeco:%s\"}", new Object[]{
										   decoration.name()
								   }));
							   } else if (decoration.decorationType == DMPDecorationType.chandelierSmallRound) {
								   printwriter.println(String.format("\"normal\":{\"model\":\"mdeco:%s\"}", new Object[]{
										   decoration.name()
								   }));
							   } else if (decoration.decorationType == DMPDecorationType.curtainRod) {
								   printwriter.println(String.format("\"connected=false,facing=north\":{\"model\":\"mdeco:%s0\"},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connected=false,facing=south\":{\"model\":\"mdeco:%s0\",\"y\":180},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connected=false,facing=west\":{\"model\":\"mdeco:%s0\",\"y\":270},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connected=false,facing=east\":{\"model\":\"mdeco:%s0\",\"y\":90},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connected=true,facing=north\":{\"model\":\"mdeco:%s1\"},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connected=true,facing=south\":{\"model\":\"mdeco:%s1\",\"y\":180},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connected=true,facing=west\":{\"model\":\"mdeco:%s1\",\"y\":270},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connected=true,facing=east\":{\"model\":\"mdeco:%s1\",\"y\":90}", new Object[]{
										   decoration.name()
								   }));
							   } else if (decoration.decorationType == DMPDecorationType.curtainWool) {
								   printwriter.println(String.format("\"bottom=false,facing=north\":{\"model\":\"mdeco:%s0\"},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=false,facing=south\":{\"model\":\"mdeco:%s0\",\"y\":180},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=false,facing=west\":{\"model\":\"mdeco:%s0\",\"y\":270},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=false,facing=east\":{\"model\":\"mdeco:%s0\",\"y\":90},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=true,facing=north\":{\"model\":\"mdeco:%s1\"},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=true,facing=south\":{\"model\":\"mdeco:%s1\",\"y\":180},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=true,facing=west\":{\"model\":\"mdeco:%s1\",\"y\":270},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=true,facing=east\":{\"model\":\"mdeco:%s1\",\"y\":90}", new Object[]{
										   decoration.name()
								   }));
							   } else if (decoration.decorationType == DMPDecorationType.fireplaceScreen) {
								   printwriter.println(String.format("\"bottom=false,facing=north,left=false,right=false\":{\"model\":\"mdeco:%s0\"},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=false,facing=east,left=false,right=false\":{\"model\":\"mdeco:%s0\",\"y\":90},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=false,facing=south,left=false,right=false\":{\"model\":\"mdeco:%s0\",\"y\":180},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=false,facing=west,left=false,right=false\":{\"model\":\"mdeco:%s0\",\"y\":270},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=false,facing=north,left=false,right=true\":{\"model\":\"mdeco:%s1\"},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=false,facing=east,left=false,right=true\":{\"model\":\"mdeco:%s1\",\"y\":90},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=false,facing=south,left=false,right=true\":{\"model\":\"mdeco:%s1\",\"y\":180},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=false,facing=west,left=false,right=true\":{\"model\":\"mdeco:%s1\",\"y\":270},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=false,facing=north,left=true,right=false\":{\"model\":\"mdeco:%s2\"},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=false,facing=east,left=true,right=false\":{\"model\":\"mdeco:%s2\",\"y\":90},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=false,facing=south,left=true,right=false\":{\"model\":\"mdeco:%s2\",\"y\":180},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=false,facing=west,left=true,right=false\":{\"model\":\"mdeco:%s2\",\"y\":270},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=false,facing=north,left=true,right=true\":{\"model\":\"mdeco:%s3\"},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=false,facing=east,left=true,right=true\":{\"model\":\"mdeco:%s3\",\"y\":90},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=false,facing=south,left=true,right=true\":{\"model\":\"mdeco:%s3\",\"y\":180},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=false,facing=west,left=true,right=true\":{\"model\":\"mdeco:%s3\",\"y\":270},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=true,facing=north,left=false,right=false\":{\"model\":\"mdeco:%s4\"},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=true,facing=east,left=false,right=false\":{\"model\":\"mdeco:%s4\",\"y\":90},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=true,facing=south,left=false,right=false\":{\"model\":\"mdeco:%s4\",\"y\":180},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=true,facing=west,left=false,right=false\":{\"model\":\"mdeco:%s4\",\"y\":270},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=true,facing=north,left=false,right=true\":{\"model\":\"mdeco:%s5\"},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=true,facing=east,left=false,right=true\":{\"model\":\"mdeco:%s5\",\"y\":90},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=true,facing=south,left=false,right=true\":{\"model\":\"mdeco:%s5\",\"y\":180},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=true,facing=west,left=false,right=true\":{\"model\":\"mdeco:%s5\",\"y\":270},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=true,facing=north,left=true,right=false\":{\"model\":\"mdeco:%s6\"},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=true,facing=east,left=true,right=false\":{\"model\":\"mdeco:%s6\",\"y\":90},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=true,facing=south,left=true,right=false\":{\"model\":\"mdeco:%s6\",\"y\":180},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=true,facing=west,left=true,right=false\":{\"model\":\"mdeco:%s6\",\"y\":270},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=true,facing=north,left=true,right=true\":{\"model\":\"mdeco:%s7\"},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=true,facing=east,left=true,right=true\":{\"model\":\"mdeco:%s7\",\"y\":90},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=true,facing=south,left=true,right=true\":{\"model\":\"mdeco:%s7\",\"y\":180},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"bottom=true,facing=west,left=true,right=true\":{\"model\":\"mdeco:%s7\",\"y\":270}", new Object[]{
										   decoration.name()
								   }));
							   } else if (decoration.decorationType != DMPDecorationType.foodTrough &&
									   decoration.decorationType != DMPDecorationType.waterTrough) {
								   if (decoration.decorationType == DMPDecorationType.ingotStack) {
									   printwriter.println(String.format("\"stacks=0\":{\"model\":\"mdeco:%s0\"},", new Object[]{
											   decoration.name()
									   }));
									   printwriter.println(String.format("\"stacks=1\":{\"model\":\"mdeco:%s1\"},", new Object[]{
											   decoration.name()
									   }));
									   printwriter.println(String.format("\"stacks=2\":{\"model\":\"mdeco:%s2\"},", new Object[]{
											   decoration.name()
									   }));
									   printwriter.println(String.format("\"stacks=3\":{\"model\":\"mdeco:%s3\"},", new Object[]{
											   decoration.name()
									   }));
									   printwriter.println(String.format("\"stacks=4\":{\"model\":\"mdeco:%s4\"},", new Object[]{
											   decoration.name()
									   }));
									   printwriter.println(String.format("\"stacks=5\":{\"model\":\"mdeco:%s5\"}", new Object[]{
											   decoration.name()
									   }));
								   } else {
									   int index;
									   int variantCount;
									   if (decoration.decorationType == DMPDecorationType.kitchenKettle) {
										   variantCount = DMPBlockKitchenKettle.EnumType.values().length;

										   for (index = 0; index < variantCount; ++index) {
											   printwriter.println(String.format("\"facing=north,variant=%s\":{\"model\":\"mdeco:%s%d\"},", new Object[]{
													   DMPBlockKitchenKettle.EnumType.byMetadata(index).name(),
													   decoration.name(),
													   Integer.valueOf(index)
											   }));
											   printwriter.println(String.format("\"facing=east,variant=%s\":{\"model\":\"mdeco:%s%d\",\"y\":90},", new Object[]{
													   DMPBlockKitchenKettle.EnumType.byMetadata(index).name(),
													   decoration.name(),
													   Integer.valueOf(index)
											   }));
											   printwriter.println(String.format("\"facing=south,variant=%s\":{\"model\":\"mdeco:%s%d\",\"y\":180},", new Object[]{
													   DMPBlockKitchenKettle.EnumType.byMetadata(index).name(),
													   decoration.name(),
													   Integer.valueOf(index)
											   }));
											   printwriter.println(String.format("\"facing=west,variant=%s\":{\"model\":\"mdeco:%s%d\",\"y\":270}%s", new Object[]{
													   DMPBlockKitchenKettle.EnumType.byMetadata(index).name(),
													   decoration.name(),
													   Integer.valueOf(index),
													   index < variantCount - 1 ? "," : ""
											   }));
										   }
									   } else if (decoration.decorationType == DMPDecorationType.kitchenPot) {
										   variantCount = DMPBlockKitchenPot.EnumType.values().length;

										   for (index = 0; index < variantCount; ++index) {
											   printwriter.println(String.format("\"facing=north,variant=%s\":{\"model\":\"mdeco:%s%d\"},", new Object[]{
													   DMPBlockKitchenPot.EnumType.byMetadata(index).name(),
													   decoration.name(),
													   Integer.valueOf(index)
											   }));
											   printwriter.println(String.format("\"facing=east,variant=%s\":{\"model\":\"mdeco:%s%d\",\"y\":90},", new Object[]{
													   DMPBlockKitchenPot.EnumType.byMetadata(index).name(),
													   decoration.name(),
													   Integer.valueOf(index)
											   }));
											   printwriter.println(String.format("\"facing=south,variant=%s\":{\"model\":\"mdeco:%s%d\",\"y\":180},", new Object[]{
													   DMPBlockKitchenPot.EnumType.byMetadata(index).name(),
													   decoration.name(),
													   Integer.valueOf(index)
											   }));
											   printwriter.println(String.format("\"facing=west,variant=%s\":{\"model\":\"mdeco:%s%d\",\"y\":270}%s", new Object[]{
													   DMPBlockKitchenPot.EnumType.byMetadata(index).name(),
													   decoration.name(),
													   Integer.valueOf(index),
													   index < variantCount - 1 ? "," : ""
											   }));
										   }
									   } else if (decoration.decorationType == DMPDecorationType.kitchenShakers) {
										   variantCount = DMPBlockKitchenShakers.EnumType.values().length;

										   for (index = 0; index < variantCount; ++index) {
											   printwriter.println(String.format("\"facing=north,variant=%s\":{\"model\":\"mdeco:%s%d\"},", new Object[]{
													   DMPBlockKitchenShakers.EnumType.byMetadata(index).name(),
													   decoration.name(),
													   Integer.valueOf(index)
											   }));
											   printwriter.println(String.format("\"facing=east,variant=%s\":{\"model\":\"mdeco:%s%d\",\"y\":90},", new Object[]{
													   DMPBlockKitchenShakers.EnumType.byMetadata(index).name(),
													   decoration.name(),
													   Integer.valueOf(index)
											   }));
											   printwriter.println(String.format("\"facing=south,variant=%s\":{\"model\":\"mdeco:%s%d\",\"y\":180},", new Object[]{
													   DMPBlockKitchenShakers.EnumType.byMetadata(index).name(),
													   decoration.name(),
													   Integer.valueOf(index)
											   }));
											   printwriter.println(String.format("\"facing=west,variant=%s\":{\"model\":\"mdeco:%s%d\",\"y\":270}%s", new Object[]{
													   DMPBlockKitchenShakers.EnumType.byMetadata(index).name(),
													   decoration.name(),
													   Integer.valueOf(index),
													   index < variantCount - 1 ? "," : ""
											   }));
										   }
									   } else if (decoration.decorationType == DMPDecorationType.kitchenWallUtensils) {
										   variantCount = DMPBlockKitchenWallUtensils.EnumType.values().length;

										   for (index = 0; index < variantCount; ++index) {
											   printwriter.println(String.format("\"facing=north,variant=%s\":{\"model\":\"mdeco:%s%d\"},", new Object[]{
													   DMPBlockKitchenWallUtensils.EnumType.byMetadata(index).name(),
													   decoration.name(),
													   Integer.valueOf(index)
											   }));
											   printwriter.println(String.format("\"facing=east,variant=%s\":{\"model\":\"mdeco:%s%d\",\"y\":90},", new Object[]{
													   DMPBlockKitchenWallUtensils.EnumType.byMetadata(index).name(),
													   decoration.name(),
													   Integer.valueOf(index)
											   }));
											   printwriter.println(String.format("\"facing=south,variant=%s\":{\"model\":\"mdeco:%s%d\",\"y\":180},", new Object[]{
													   DMPBlockKitchenWallUtensils.EnumType.byMetadata(index).name(),
													   decoration.name(),
													   Integer.valueOf(index)
											   }));
											   printwriter.println(String.format("\"facing=west,variant=%s\":{\"model\":\"mdeco:%s%d\",\"y\":270}%s", new Object[]{
													   DMPBlockKitchenWallUtensils.EnumType.byMetadata(index).name(),
													   decoration.name(),
													   Integer.valueOf(index),
													   index < variantCount - 1 ? "," : ""
											   }));
										   }
									   } else if ((decoration.decorationType == DMPDecorationType.lanternOval ||
											   decoration.decorationType == DMPDecorationType.lanternPyramid ||
											   decoration.decorationType == DMPDecorationType.lanternPyramidGlass ||
											   decoration.decorationType == DMPDecorationType.lanternRectangle ||
											   decoration.decorationType == DMPDecorationType.lanternRectangleGlass ||
											   decoration.decorationType == DMPDecorationType.lanternRound) &&
											   MDeco.settings.contentLantern) {
										   printwriter.println(String.format("\"east=false,north=false,south=false,west=false\":{\"model\":\"mdeco:%s0\"},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"east=false,north=true,south=false,west=false\":{\"model\":\"mdeco:%s1\"},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"east=true,north=false,south=false,west=false\":{\"model\":\"mdeco:%s1\",\"y\":90},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"east=false,north=false,south=true,west=false\":{\"model\":\"mdeco:%s1\",\"y\":180},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"east=false,north=false,south=false,west=true\":{\"model\":\"mdeco:%s1\",\"y\":270},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"east=true,north=true,south=false,west=false\":{\"model\":\"mdeco:%s3\",\"y\":90},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"east=true,north=false,south=true,west=false\":{\"model\":\"mdeco:%s3\",\"y\":180},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"east=false,north=false,south=true,west=true\":{\"model\":\"mdeco:%s3\",\"y\":270},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"east=false,north=true,south=false,west=true\":{\"model\":\"mdeco:%s3\"},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"east=false,north=true,south=true,west=false\":{\"model\":\"mdeco:%s2\"},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"east=true,north=false,south=false,west=true\":{\"model\":\"mdeco:%s2\",\"y\":90},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"east=true,north=true,south=true,west=false\":{\"model\":\"mdeco:%s4\",\"y\":90},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"east=true,north=false,south=true,west=true\":{\"model\":\"mdeco:%s4\",\"y\":180},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"east=false,north=true,south=true,west=true\":{\"model\":\"mdeco:%s4\",\"y\":270},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"east=true,north=true,south=false,west=true\":{\"model\":\"mdeco:%s4\"},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"east=true,north=true,south=true,west=true\":{\"model\":\"mdeco:%s5\"}", new Object[]{
												   decoration.name()
										   }));
									   } else if (decoration.decorationType == DMPDecorationType.mantle) {
										   printwriter.println(String.format("\"facing=north,position=0\":{\"model\":\"mdeco:%s0\"},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=south,position=0\":{\"model\":\"mdeco:%s0\",\"y\":180},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=west,position=0\":{\"model\":\"mdeco:%s0\",\"y\":270},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=east,position=0\":{\"model\":\"mdeco:%s0\",\"y\":90},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=north,position=1\":{\"model\":\"mdeco:%s1\"},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=south,position=1\":{\"model\":\"mdeco:%s1\",\"y\":180},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=west,position=1\":{\"model\":\"mdeco:%s1\",\"y\":270},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=east,position=1\":{\"model\":\"mdeco:%s1\",\"y\":90},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=north,position=2\":{\"model\":\"mdeco:%s2\"},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=south,position=2\":{\"model\":\"mdeco:%s2\",\"y\":180},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=west,position=2\":{\"model\":\"mdeco:%s2\",\"y\":270},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=east,position=2\":{\"model\":\"mdeco:%s2\",\"y\":90},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=north,position=3\":{\"model\":\"mdeco:%s3\"},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=south,position=3\":{\"model\":\"mdeco:%s3\",\"y\":180},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=west,position=3\":{\"model\":\"mdeco:%s3\",\"y\":270},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=east,position=3\":{\"model\":\"mdeco:%s3\",\"y\":90},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=north,position=4\":{\"model\":\"mdeco:%s4\"},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=south,position=4\":{\"model\":\"mdeco:%s4\",\"y\":180},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=west,position=4\":{\"model\":\"mdeco:%s4\",\"y\":270},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=east,position=4\":{\"model\":\"mdeco:%s4\",\"y\":90},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=north,position=5\":{\"model\":\"mdeco:%s5\"},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=south,position=5\":{\"model\":\"mdeco:%s5\",\"y\":180},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=west,position=5\":{\"model\":\"mdeco:%s5\",\"y\":270},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=east,position=5\":{\"model\":\"mdeco:%s5\",\"y\":90},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=north,position=6\":{\"model\":\"mdeco:%s6\"},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=south,position=6\":{\"model\":\"mdeco:%s6\",\"y\":180},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=west,position=6\":{\"model\":\"mdeco:%s6\",\"y\":270},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=east,position=6\":{\"model\":\"mdeco:%s6\",\"y\":90},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=north,position=7\":{\"model\":\"mdeco:%s7\"},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=south,position=7\":{\"model\":\"mdeco:%s7\",\"y\":180},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=west,position=7\":{\"model\":\"mdeco:%s7\",\"y\":270},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=east,position=7\":{\"model\":\"mdeco:%s7\",\"y\":90}", new Object[]{
												   decoration.name()
										   }));
									   } else if (decoration.decorationType == DMPDecorationType.mantleColumn) {
										   printwriter.println(String.format("\"facing=north,position=0\":{\"model\":\"mdeco:%s0\"},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=south,position=0\":{\"model\":\"mdeco:%s0\",\"y\":180},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=west,position=0\":{\"model\":\"mdeco:%s0\",\"y\":270},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=east,position=0\":{\"model\":\"mdeco:%s0\",\"y\":90},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=north,position=1\":{\"model\":\"mdeco:%s1\"},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=south,position=1\":{\"model\":\"mdeco:%s1\",\"y\":180},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=west,position=1\":{\"model\":\"mdeco:%s1\",\"y\":270},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=east,position=1\":{\"model\":\"mdeco:%s1\",\"y\":90},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=north,position=2\":{\"model\":\"mdeco:%s2\"},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=south,position=2\":{\"model\":\"mdeco:%s2\",\"y\":180},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=west,position=2\":{\"model\":\"mdeco:%s2\",\"y\":270},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=east,position=2\":{\"model\":\"mdeco:%s2\",\"y\":90},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=north,position=3\":{\"model\":\"mdeco:%s3\"},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=south,position=3\":{\"model\":\"mdeco:%s3\",\"y\":180},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=west,position=3\":{\"model\":\"mdeco:%s3\",\"y\":270},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=east,position=3\":{\"model\":\"mdeco:%s3\",\"y\":90}", new Object[]{
												   decoration.name()
										   }));
									   } else if (decoration.decorationType != DMPDecorationType.marketCrate &&
											   decoration.decorationType != DMPDecorationType.marketStand) {
										   if (decoration.decorationType == DMPDecorationType.palletStack) {
											   printwriter.println(String.format("\"stacks=0\":{\"model\":\"mdeco:%s0\"},", new Object[]{
													   decoration.name()
											   }));
											   printwriter.println(String.format("\"stacks=1\":{\"model\":\"mdeco:%s1\"},", new Object[]{
													   decoration.name()
											   }));
											   printwriter.println(String.format("\"stacks=2\":{\"model\":\"mdeco:%s2\"},", new Object[]{
													   decoration.name()
											   }));
											   printwriter.println(String.format("\"stacks=3\":{\"model\":\"mdeco:%s3\"},", new Object[]{
													   decoration.name()
											   }));
											   printwriter.println(String.format("\"stacks=4\":{\"model\":\"mdeco:%s4\"}", new Object[]{
													   decoration.name()
											   }));
										   } else if (decoration.decorationType == DMPDecorationType.poleMetal) {
											   printwriter.println(String.format("\"connected=0,orientation=0\":{\"model\":\"mdeco:%s0\",\"x\":270,\"y\":90},", new Object[]{
													   decoration.name()
											   }));
											   printwriter.println(String.format("\"connected=1,orientation=0\":{\"model\":\"mdeco:%s1\",\"x\":270,\"y\":90},", new Object[]{
													   decoration.name()
											   }));
											   printwriter.println(String.format("\"connected=2,orientation=0\":{\"model\":\"mdeco:%s2\",\"x\":270,\"y\":90},", new Object[]{
													   decoration.name()
											   }));
											   printwriter.println(String.format("\"connected=3,orientation=0\":{\"model\":\"mdeco:%s3\",\"x\":270,\"y\":90},", new Object[]{
													   decoration.name()
											   }));
											   printwriter.println(String.format("\"connected=0,orientation=1\":{\"model\":\"mdeco:%s0\"},", new Object[]{
													   decoration.name()
											   }));
											   printwriter.println(String.format("\"connected=1,orientation=1\":{\"model\":\"mdeco:%s1\"},", new Object[]{
													   decoration.name()
											   }));
											   printwriter.println(String.format("\"connected=2,orientation=1\":{\"model\":\"mdeco:%s2\"},", new Object[]{
													   decoration.name()
											   }));
											   printwriter.println(String.format("\"connected=3,orientation=1\":{\"model\":\"mdeco:%s3\"},", new Object[]{
													   decoration.name()
											   }));
											   printwriter.println(String.format("\"connected=0,orientation=2\":{\"model\":\"mdeco:%s0\",\"x\":270},", new Object[]{
													   decoration.name()
											   }));
											   printwriter.println(String.format("\"connected=1,orientation=2\":{\"model\":\"mdeco:%s1\",\"x\":270,\"y\":180},", new Object[]{
													   decoration.name()
											   }));
											   printwriter.println(String.format("\"connected=2,orientation=2\":{\"model\":\"mdeco:%s2\",\"x\":270,\"y\":180},", new Object[]{
													   decoration.name()
											   }));
											   printwriter.println(String.format("\"connected=3,orientation=2\":{\"model\":\"mdeco:%s3\",\"x\":270}", new Object[]{
													   decoration.name()
											   }));
										   } else {
											   String e;
											   DMPConnectionsAll[] var11;
											   int var12;
											   int var13;
											   DMPConnectionsAll connections;
											   if (decoration.decorationType == DMPDecorationType.poleMetalConnector) {
												   index = 0;
												   var11 = DMPConnectionsAll.values();
												   var12 = var11.length;

												   for (var13 = 0; var13 < var12; ++var13) {
													   connections = var11[var13];
													   e = String.format("\"%s\":{\"model\":\"mdeco:%s%d\"", connections.stateString, decoration
															   .name(), connections.modelID);
													   if (connections.xRotation > 0) {
														   e = e +
																   String.format(",\"x\":%d", connections.xRotation);
													   }

													   if (connections.yRotation > 0) {
														   e = e +
																   String.format(",\"y\":%d", connections.yRotation);
													   }

													   if (index < DMPConnectionsAll.values().length - 1) {
														   e = e + "},";
													   } else {
														   e = e + "}";
													   }

													   printwriter.println(e);
													   ++index;
												   }
											   } else if (decoration.decorationType == DMPDecorationType.sofa) {
												   printwriter.println(String.format("\"connectfore=false,connectleft=false,connectright=false,facing=north\":{\"model\":\"mdeco:%s0\"},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=false,connectleft=false,connectright=true,facing=north\":{\"model\":\"mdeco:%s1\"},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=false,connectleft=true,connectright=false,facing=north\":{\"model\":\"mdeco:%s2\"},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=false,connectleft=true,connectright=true,facing=north\":{\"model\":\"mdeco:%s3\"},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=true,connectleft=false,connectright=false,facing=north\":{\"model\":\"mdeco:%s0\"},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=true,connectleft=false,connectright=true,facing=north\":{\"model\":\"mdeco:%s4\",\"y\":270},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=true,connectleft=true,connectright=false,facing=north\":{\"model\":\"mdeco:%s4\"},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=true,connectleft=true,connectright=true,facing=north\":{\"model\":\"mdeco:%s0\"},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=false,connectleft=false,connectright=false,facing=south\":{\"model\":\"mdeco:%s0\",\"y\":180},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=false,connectleft=false,connectright=true,facing=south\":{\"model\":\"mdeco:%s1\",\"y\":180},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=false,connectleft=true,connectright=false,facing=south\":{\"model\":\"mdeco:%s2\",\"y\":180},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=false,connectleft=true,connectright=true,facing=south\":{\"model\":\"mdeco:%s3\",\"y\":180},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=true,connectleft=false,connectright=false,facing=south\":{\"model\":\"mdeco:%s0\"},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=true,connectleft=false,connectright=true,facing=south\":{\"model\":\"mdeco:%s4\",\"y\":90},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=true,connectleft=true,connectright=false,facing=south\":{\"model\":\"mdeco:%s4\",\"y\":180},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=true,connectleft=true,connectright=true,facing=south\":{\"model\":\"mdeco:%s0\",\"y\":180},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=false,connectleft=false,connectright=false,facing=west\":{\"model\":\"mdeco:%s0\",\"y\":270},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=false,connectleft=false,connectright=true,facing=west\":{\"model\":\"mdeco:%s1\",\"y\":270},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=false,connectleft=true,connectright=false,facing=west\":{\"model\":\"mdeco:%s2\",\"y\":270},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=false,connectleft=true,connectright=true,facing=west\":{\"model\":\"mdeco:%s3\",\"y\":270},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=true,connectleft=false,connectright=false,facing=west\":{\"model\":\"mdeco:%s0\"},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=true,connectleft=false,connectright=true,facing=west\":{\"model\":\"mdeco:%s4\",\"y\":180},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=true,connectleft=true,connectright=false,facing=west\":{\"model\":\"mdeco:%s4\",\"y\":270},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=true,connectleft=true,connectright=true,facing=west\":{\"model\":\"mdeco:%s0\",\"y\":270},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=false,connectleft=false,connectright=false,facing=east\":{\"model\":\"mdeco:%s0\",\"y\":90},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=false,connectleft=false,connectright=true,facing=east\":{\"model\":\"mdeco:%s1\",\"y\":90},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=false,connectleft=true,connectright=false,facing=east\":{\"model\":\"mdeco:%s2\",\"y\":90},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=false,connectleft=true,connectright=true,facing=east\":{\"model\":\"mdeco:%s3\",\"y\":90},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=true,connectleft=false,connectright=false,facing=east\":{\"model\":\"mdeco:%s0\"},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=true,connectleft=false,connectright=true,facing=east\":{\"model\":\"mdeco:%s4\"},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=true,connectleft=true,connectright=false,facing=east\":{\"model\":\"mdeco:%s4\",\"y\":90},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connectfore=true,connectleft=true,connectright=true,facing=east\":{\"model\":\"mdeco:%s0\",\"y\":90}", new Object[]{
														   decoration.name()
												   }));
											   } else if (
													   decoration.decorationType != DMPDecorationType.standSmallMetal &&
															   decoration.decorationType !=
																	   DMPDecorationType.standSmallStone) {
												   if (decoration.decorationType == DMPDecorationType.tableWood) {
													   printwriter.println(String.format("\"east=false,north=false,orientation=0,south=false,west=false\":{\"model\":\"mdeco:%s0\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=false,north=true,orientation=0,south=false,west=false\":{\"model\":\"mdeco:%s1\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=true,north=false,orientation=0,south=false,west=false\":{\"model\":\"mdeco:%s2\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=false,north=false,orientation=0,south=true,west=false\":{\"model\":\"mdeco:%s3\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=false,north=false,orientation=0,south=false,west=true\":{\"model\":\"mdeco:%s4\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=true,north=true,orientation=0,south=false,west=false\":{\"model\":\"mdeco:%s5\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=true,north=false,orientation=0,south=true,west=false\":{\"model\":\"mdeco:%s6\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=false,north=false,orientation=0,south=true,west=true\":{\"model\":\"mdeco:%s7\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=false,north=true,orientation=0,south=false,west=true\":{\"model\":\"mdeco:%s8\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=false,north=true,orientation=0,south=true,west=false\":{\"model\":\"mdeco:%s14\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=true,north=false,orientation=0,south=false,west=true\":{\"model\":\"mdeco:%s13\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=true,north=true,orientation=0,south=true,west=false\":{\"model\":\"mdeco:%s10\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=true,north=false,orientation=0,south=true,west=true\":{\"model\":\"mdeco:%s11\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=false,north=true,orientation=0,south=true,west=true\":{\"model\":\"mdeco:%s12\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=true,north=true,orientation=0,south=false,west=true\":{\"model\":\"mdeco:%s9\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=true,north=true,orientation=0,south=true,west=true\":{\"model\":\"mdeco:%s15\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=false,north=false,orientation=1,south=false,west=false\":{\"model\":\"mdeco:%s0\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=false,north=true,orientation=1,south=false,west=false\":{\"model\":\"mdeco:%s4\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=true,north=false,orientation=1,south=false,west=false\":{\"model\":\"mdeco:%s1\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=false,north=false,orientation=1,south=true,west=false\":{\"model\":\"mdeco:%s2\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=false,north=false,orientation=1,south=false,west=true\":{\"model\":\"mdeco:%s3\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=true,north=true,orientation=1,south=false,west=false\":{\"model\":\"mdeco:%s8\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=true,north=false,orientation=1,south=true,west=false\":{\"model\":\"mdeco:%s5\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=false,north=false,orientation=1,south=true,west=true\":{\"model\":\"mdeco:%s6\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=false,north=true,orientation=1,south=false,west=true\":{\"model\":\"mdeco:%s7\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=false,north=true,orientation=1,south=true,west=false\":{\"model\":\"mdeco:%s13\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=true,north=false,orientation=1,south=false,west=true\":{\"model\":\"mdeco:%s14\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=true,north=true,orientation=1,south=true,west=false\":{\"model\":\"mdeco:%s9\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=true,north=false,orientation=1,south=true,west=true\":{\"model\":\"mdeco:%s10\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=false,north=true,orientation=1,south=true,west=true\":{\"model\":\"mdeco:%s11\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=true,north=true,orientation=1,south=false,west=true\":{\"model\":\"mdeco:%s12\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"east=true,north=true,orientation=1,south=true,west=true\":{\"model\":\"mdeco:%s15\",\"y\":90}", new Object[]{
															   decoration.name()
													   }));
												   } else if (decoration.decorationType ==
														   DMPDecorationType.woodCrate) {
													   printwriter.println(String.format("\"connected=0,facing=north\":{\"model\":\"mdeco:%s0\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=0,facing=south\":{\"model\":\"mdeco:%s0\",\"y\":180},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=0,facing=west\":{\"model\":\"mdeco:%s0\",\"y\":270},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=0,facing=east\":{\"model\":\"mdeco:%s0\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=1,facing=north\":{\"model\":\"mdeco:%s1\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=1,facing=south\":{\"model\":\"mdeco:%s1\",\"y\":180},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=1,facing=west\":{\"model\":\"mdeco:%s1\",\"y\":270},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=1,facing=east\":{\"model\":\"mdeco:%s1\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=2,facing=north\":{\"model\":\"mdeco:%s2\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=2,facing=south\":{\"model\":\"mdeco:%s2\",\"y\":180},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=2,facing=west\":{\"model\":\"mdeco:%s2\",\"y\":270},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=2,facing=east\":{\"model\":\"mdeco:%s2\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=3,facing=north\":{\"model\":\"mdeco:%s3\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=3,facing=south\":{\"model\":\"mdeco:%s3\",\"y\":180},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=3,facing=west\":{\"model\":\"mdeco:%s3\",\"y\":270},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=3,facing=east\":{\"model\":\"mdeco:%s3\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=4,facing=north\":{\"model\":\"mdeco:%s4\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=4,facing=south\":{\"model\":\"mdeco:%s4\",\"y\":180},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=4,facing=west\":{\"model\":\"mdeco:%s4\",\"y\":270},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=4,facing=east\":{\"model\":\"mdeco:%s4\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=5,facing=north\":{\"model\":\"mdeco:%s5\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=5,facing=south\":{\"model\":\"mdeco:%s5\",\"y\":180},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=5,facing=west\":{\"model\":\"mdeco:%s5\",\"y\":270},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=5,facing=east\":{\"model\":\"mdeco:%s5\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=6,facing=north\":{\"model\":\"mdeco:%s6\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=6,facing=south\":{\"model\":\"mdeco:%s6\",\"y\":180},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=6,facing=west\":{\"model\":\"mdeco:%s6\",\"y\":270},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=6,facing=east\":{\"model\":\"mdeco:%s6\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=7,facing=north\":{\"model\":\"mdeco:%s7\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=7,facing=south\":{\"model\":\"mdeco:%s7\",\"y\":180},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=7,facing=west\":{\"model\":\"mdeco:%s7\",\"y\":270},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=7,facing=east\":{\"model\":\"mdeco:%s7\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=8,facing=north\":{\"model\":\"mdeco:%s8\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=8,facing=south\":{\"model\":\"mdeco:%s8\",\"y\":180},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=8,facing=west\":{\"model\":\"mdeco:%s8\",\"y\":270},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=8,facing=east\":{\"model\":\"mdeco:%s8\",\"y\":90}", new Object[]{
															   decoration.name()
													   }));
												   } else if (decoration.decorationType ==
														   DMPDecorationType.woodPanel) {
													   printwriter.println(String.format("\"connected=0,facing=north\":{\"model\":\"mdeco:%s0\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=0,facing=south\":{\"model\":\"mdeco:%s0\",\"y\":180},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=0,facing=west\":{\"model\":\"mdeco:%s0\",\"y\":270},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=0,facing=east\":{\"model\":\"mdeco:%s0\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=1,facing=north\":{\"model\":\"mdeco:%s1\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=1,facing=south\":{\"model\":\"mdeco:%s1\",\"y\":180},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=1,facing=west\":{\"model\":\"mdeco:%s1\",\"y\":270},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=1,facing=east\":{\"model\":\"mdeco:%s1\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=2,facing=north\":{\"model\":\"mdeco:%s1\",\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=2,facing=south\":{\"model\":\"mdeco:%s1\",\"y\":270},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=2,facing=west\":{\"model\":\"mdeco:%s1\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=2,facing=east\":{\"model\":\"mdeco:%s1\",\"y\":180}", new Object[]{
															   decoration.name()
													   }));
												   } else if (
														   decoration.decorationType != DMPDecorationType.woodTimber &&
																   decoration.decorationType !=
																		   DMPDecorationType.woodBarkTimber) {
													   if (decoration.decorationType !=
															   DMPDecorationType.woodTimberConnector &&
															   decoration.decorationType !=
																	   DMPDecorationType.woodBarkTimberConnector) {
														   if (decoration.decorationType ==
																   DMPDecorationType.woodTrim) {
															   printwriter.println(String.format("\"connected=0,facing=north\":{\"model\":\"mdeco:%s0\"},", new Object[]{
																	   decoration.name()
															   }));
															   printwriter.println(String.format("\"connected=0,facing=south\":{\"model\":\"mdeco:%s0\",\"y\":180},", new Object[]{
																	   decoration.name()
															   }));
															   printwriter.println(String.format("\"connected=0,facing=west\":{\"model\":\"mdeco:%s0\",\"y\":270},", new Object[]{
																	   decoration.name()
															   }));
															   printwriter.println(String.format("\"connected=0,facing=east\":{\"model\":\"mdeco:%s0\",\"y\":90},", new Object[]{
																	   decoration.name()
															   }));
															   printwriter.println(String.format("\"connected=1,facing=north\":{\"model\":\"mdeco:%s1\"},", new Object[]{
																	   decoration.name()
															   }));
															   printwriter.println(String.format("\"connected=1,facing=south\":{\"model\":\"mdeco:%s1\",\"y\":180},", new Object[]{
																	   decoration.name()
															   }));
															   printwriter.println(String.format("\"connected=1,facing=west\":{\"model\":\"mdeco:%s1\",\"y\":270},", new Object[]{
																	   decoration.name()
															   }));
															   printwriter.println(String.format("\"connected=1,facing=east\":{\"model\":\"mdeco:%s1\",\"y\":90},", new Object[]{
																	   decoration.name()
															   }));
															   printwriter.println(String.format("\"connected=2,facing=north\":{\"model\":\"mdeco:%s2\"},", new Object[]{
																	   decoration.name()
															   }));
															   printwriter.println(String.format("\"connected=2,facing=south\":{\"model\":\"mdeco:%s2\",\"y\":180},", new Object[]{
																	   decoration.name()
															   }));
															   printwriter.println(String.format("\"connected=2,facing=west\":{\"model\":\"mdeco:%s2\",\"y\":270},", new Object[]{
																	   decoration.name()
															   }));
															   printwriter.println(String.format("\"connected=2,facing=east\":{\"model\":\"mdeco:%s2\",\"y\":90}", new Object[]{
																	   decoration.name()
															   }));
														   } else {
															   printwriter.println(String.format("\"facing=north\":{\"model\":\"mdeco:%s\"},", new Object[]{
																	   decoration.name()
															   }));
															   printwriter.println(String.format("\"facing=south\":{\"model\":\"mdeco:%s\",\"y\":180},", new Object[]{
																	   decoration.name()
															   }));
															   printwriter.println(String.format("\"facing=west\":{\"model\":\"mdeco:%s\",\"y\":270},", new Object[]{
																	   decoration.name()
															   }));
															   printwriter.println(String.format("\"facing=east\":{\"model\":\"mdeco:%s\",\"y\":90}", new Object[]{
																	   decoration.name()
															   }));
														   }
													   } else {
														   index = 0;
														   var11 = DMPConnectionsAll.values();
														   var12 = var11.length;

														   for (var13 = 0; var13 < var12; ++var13) {
															   connections = var11[var13];
															   e = String.format("\"%s\":{\"model\":\"mdeco:%s%d\"", connections.stateString, decoration
																	   .name(), connections.modelID);
															   if (connections.xRotation > 0) {
																   e = e +
																		   String.format(",\"x\":%d", connections.xRotation);
															   }

															   if (connections.yRotation > 0) {
																   e = e +
																		   String.format(",\"y\":%d", connections.yRotation);
															   }

															   if (index < DMPConnectionsAll.values().length - 1) {
																   e = e + "},";
															   } else {
																   e = e + "}";
															   }

															   printwriter.println(e);
															   ++index;
														   }
													   }
												   } else {
													   printwriter.println(String.format("\"connected=0,orientation=0\":{\"model\":\"mdeco:%s0\",\"x\":270,\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=1,orientation=0\":{\"model\":\"mdeco:%s1\",\"x\":270,\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=2,orientation=0\":{\"model\":\"mdeco:%s2\",\"x\":270,\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=3,orientation=0\":{\"model\":\"mdeco:%s3\",\"x\":270,\"y\":90},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=0,orientation=1\":{\"model\":\"mdeco:%s0\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=1,orientation=1\":{\"model\":\"mdeco:%s1\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=2,orientation=1\":{\"model\":\"mdeco:%s2\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=3,orientation=1\":{\"model\":\"mdeco:%s3\"},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=0,orientation=2\":{\"model\":\"mdeco:%s0\",\"x\":270},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=1,orientation=2\":{\"model\":\"mdeco:%s1\",\"x\":270,\"y\":180},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=2,orientation=2\":{\"model\":\"mdeco:%s2\",\"x\":270,\"y\":180},", new Object[]{
															   decoration.name()
													   }));
													   printwriter.println(String.format("\"connected=3,orientation=2\":{\"model\":\"mdeco:%s3\",\"x\":270}", new Object[]{
															   decoration.name()
													   }));
												   }
											   } else {
												   printwriter.println(String.format("\"connected=0\":{\"model\":\"mdeco:%s0\"},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connected=1\":{\"model\":\"mdeco:%s1\"},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connected=2\":{\"model\":\"mdeco:%s2\"},", new Object[]{
														   decoration.name()
												   }));
												   printwriter.println(String.format("\"connected=3\":{\"model\":\"mdeco:%s3\"}", new Object[]{
														   decoration.name()
												   }));
											   }
										   }
									   } else {
										   printwriter.println(String.format("\"facing=north,left=false,right=false\":{\"model\":\"mdeco:%s0\"},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=east,left=false,right=false\":{\"model\":\"mdeco:%s0\",\"y\":90},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=south,left=false,right=false\":{\"model\":\"mdeco:%s0\",\"y\":180},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=west,left=false,right=false\":{\"model\":\"mdeco:%s0\",\"y\":270},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=north,left=false,right=true\":{\"model\":\"mdeco:%s1\"},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=east,left=false,right=true\":{\"model\":\"mdeco:%s1\",\"y\":90},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=south,left=false,right=true\":{\"model\":\"mdeco:%s1\",\"y\":180},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=west,left=false,right=true\":{\"model\":\"mdeco:%s1\",\"y\":270},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=north,left=true,right=false\":{\"model\":\"mdeco:%s2\"},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=east,left=true,right=false\":{\"model\":\"mdeco:%s2\",\"y\":90},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=south,left=true,right=false\":{\"model\":\"mdeco:%s2\",\"y\":180},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=west,left=true,right=false\":{\"model\":\"mdeco:%s2\",\"y\":270},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=north,left=true,right=true\":{\"model\":\"mdeco:%s3\"},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=east,left=true,right=true\":{\"model\":\"mdeco:%s3\",\"y\":90},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=south,left=true,right=true\":{\"model\":\"mdeco:%s3\",\"y\":180},", new Object[]{
												   decoration.name()
										   }));
										   printwriter.println(String.format("\"facing=west,left=true,right=true\":{\"model\":\"mdeco:%s3\",\"y\":270}", new Object[]{
												   decoration.name()
										   }));
									   }
								   }
							   } else {
								   printwriter.println(String.format("\"connectfore=false,connectleft=false,connectright=false,facing=north\":{\"model\":\"mdeco:%s0\"},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=false,connectleft=false,connectright=true,facing=north\":{\"model\":\"mdeco:%s1\"},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=false,connectleft=true,connectright=false,facing=north\":{\"model\":\"mdeco:%s2\"},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=false,connectleft=true,connectright=true,facing=north\":{\"model\":\"mdeco:%s3\"},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=true,connectleft=false,connectright=false,facing=north\":{\"model\":\"mdeco:%s0\"},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=true,connectleft=false,connectright=true,facing=north\":{\"model\":\"mdeco:%s4\",\"y\":270},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=true,connectleft=true,connectright=false,facing=north\":{\"model\":\"mdeco:%s4\"},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=true,connectleft=true,connectright=true,facing=north\":{\"model\":\"mdeco:%s0\"},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=false,connectleft=false,connectright=false,facing=south\":{\"model\":\"mdeco:%s0\",\"y\":180},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=false,connectleft=false,connectright=true,facing=south\":{\"model\":\"mdeco:%s1\",\"y\":180},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=false,connectleft=true,connectright=false,facing=south\":{\"model\":\"mdeco:%s2\",\"y\":180},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=false,connectleft=true,connectright=true,facing=south\":{\"model\":\"mdeco:%s3\",\"y\":180},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=true,connectleft=false,connectright=false,facing=south\":{\"model\":\"mdeco:%s0\"},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=true,connectleft=false,connectright=true,facing=south\":{\"model\":\"mdeco:%s4\",\"y\":90},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=true,connectleft=true,connectright=false,facing=south\":{\"model\":\"mdeco:%s4\",\"y\":180},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=true,connectleft=true,connectright=true,facing=south\":{\"model\":\"mdeco:%s0\",\"y\":180},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=false,connectleft=false,connectright=false,facing=west\":{\"model\":\"mdeco:%s0\",\"y\":270},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=false,connectleft=false,connectright=true,facing=west\":{\"model\":\"mdeco:%s1\",\"y\":270},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=false,connectleft=true,connectright=false,facing=west\":{\"model\":\"mdeco:%s2\",\"y\":270},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=false,connectleft=true,connectright=true,facing=west\":{\"model\":\"mdeco:%s3\",\"y\":270},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=true,connectleft=false,connectright=false,facing=west\":{\"model\":\"mdeco:%s0\"},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=true,connectleft=false,connectright=true,facing=west\":{\"model\":\"mdeco:%s4\",\"y\":180},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=true,connectleft=true,connectright=false,facing=west\":{\"model\":\"mdeco:%s4\",\"y\":270},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=true,connectleft=true,connectright=true,facing=west\":{\"model\":\"mdeco:%s0\",\"y\":270},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=false,connectleft=false,connectright=false,facing=east\":{\"model\":\"mdeco:%s0\",\"y\":90},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=false,connectleft=false,connectright=true,facing=east\":{\"model\":\"mdeco:%s1\",\"y\":90},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=false,connectleft=true,connectright=false,facing=east\":{\"model\":\"mdeco:%s2\",\"y\":90},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=false,connectleft=true,connectright=true,facing=east\":{\"model\":\"mdeco:%s3\",\"y\":90},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=true,connectleft=false,connectright=false,facing=east\":{\"model\":\"mdeco:%s0\"},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=true,connectleft=false,connectright=true,facing=east\":{\"model\":\"mdeco:%s4\"},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=true,connectleft=true,connectright=false,facing=east\":{\"model\":\"mdeco:%s4\",\"y\":90},", new Object[]{
										   decoration.name()
								   }));
								   printwriter.println(String.format("\"connectfore=true,connectleft=true,connectright=true,facing=east\":{\"model\":\"mdeco:%s0\",\"y\":90}", new Object[]{
										   decoration.name()
								   }));
							   }
						   } else {
							   printwriter.println(String.format("\"connected=0\":{\"model\":\"mdeco:%s\",\"x\":180},", new Object[]{
									   decoration.name()
							   }));
							   printwriter.println(String.format("\"connected=1\":{\"model\":\"mdeco:%s\"},", new Object[]{
									   decoration.name()
							   }));
							   printwriter.println(String.format("\"connected=2\":{\"model\":\"mdeco:%s\",\"x\":90},", new Object[]{
									   decoration.name()
							   }));
							   printwriter.println(String.format("\"connected=3\":{\"model\":\"mdeco:%s\",\"x\":270},", new Object[]{
									   decoration.name()
							   }));
							   printwriter.println(String.format("\"connected=4\":{\"model\":\"mdeco:%s\",\"x\":90,\"y\":270},", new Object[]{
									   decoration.name()
							   }));
							   printwriter.println(String.format("\"connected=5\":{\"model\":\"mdeco:%s\",\"x\":270,\"y\":270}", new Object[]{
									   decoration.name()
							   }));
						   }
					   } else {
						   printwriter.println(String.format("\"facing=north,left=false,right=false\":{\"model\":\"mdeco:%s0\"},", new Object[]{
								   decoration.name()
						   }));
						   printwriter.println(String.format("\"facing=east,left=false,right=false\":{\"model\":\"mdeco:%s0\",\"y\":90},", new Object[]{
								   decoration.name()
						   }));
						   printwriter.println(String.format("\"facing=south,left=false,right=false\":{\"model\":\"mdeco:%s0\",\"y\":180},", new Object[]{
								   decoration.name()
						   }));
						   printwriter.println(String.format("\"facing=west,left=false,right=false\":{\"model\":\"mdeco:%s0\",\"y\":270},", new Object[]{
								   decoration.name()
						   }));
						   printwriter.println(String.format("\"facing=north,left=false,right=true\":{\"model\":\"mdeco:%s1\"},", new Object[]{
								   decoration.name()
						   }));
						   printwriter.println(String.format("\"facing=east,left=false,right=true\":{\"model\":\"mdeco:%s1\",\"y\":90},", new Object[]{
								   decoration.name()
						   }));
						   printwriter.println(String.format("\"facing=south,left=false,right=true\":{\"model\":\"mdeco:%s1\",\"y\":180},", new Object[]{
								   decoration.name()
						   }));
						   printwriter.println(String.format("\"facing=west,left=false,right=true\":{\"model\":\"mdeco:%s1\",\"y\":270},", new Object[]{
								   decoration.name()
						   }));
						   printwriter.println(String.format("\"facing=north,left=true,right=false\":{\"model\":\"mdeco:%s2\"},", new Object[]{
								   decoration.name()
						   }));
						   printwriter.println(String.format("\"facing=east,left=true,right=false\":{\"model\":\"mdeco:%s2\",\"y\":90},", new Object[]{
								   decoration.name()
						   }));
						   printwriter.println(String.format("\"facing=south,left=true,right=false\":{\"model\":\"mdeco:%s2\",\"y\":180},", new Object[]{
								   decoration.name()
						   }));
						   printwriter.println(String.format("\"facing=west,left=true,right=false\":{\"model\":\"mdeco:%s2\",\"y\":270},", new Object[]{
								   decoration.name()
						   }));
						   printwriter.println(String.format("\"facing=north,left=true,right=true\":{\"model\":\"mdeco:%s3\"},", new Object[]{
								   decoration.name()
						   }));
						   printwriter.println(String.format("\"facing=east,left=true,right=true\":{\"model\":\"mdeco:%s3\",\"y\":90},", new Object[]{
								   decoration.name()
						   }));
						   printwriter.println(String.format("\"facing=south,left=true,right=true\":{\"model\":\"mdeco:%s3\",\"y\":180},", new Object[]{
								   decoration.name()
						   }));
						   printwriter.println(String.format("\"facing=west,left=true,right=true\":{\"model\":\"mdeco:%s3\",\"y\":270}", new Object[]{
								   decoration.name()
						   }));
					   }

					   printwriter.println("}");
					   printwriter.println("}");
					   printwriter.close();
					   ++filesGenerated;
				   }
			   }
		   } catch (Exception var15) {
			   var15.printStackTrace();
		   }
	   }

      filesGenerated += createBlockFiles(rootPath);
      filesGenerated += createWorkbenchFiles(rootPath);
      return filesGenerated;
   }

   private static int createBlockFiles(String rootPath) {
      byte filesGenerated = 0;
      String blockName = MDeco.blocks.blockBlackIron.getUnlocalizedName().substring(5);
      createBlockBlockstateFile(rootPath, blockName);
	   return filesGenerated + 1;
   }

   private static int createWorkbenchFiles(String rootPath) {
      byte filesGenerated = 0;
      String blockName = MDeco.blocks.workbenchFoundry.getUnlocalizedName().substring(5);
      createNoStateWorkbenchBlockstateFile(rootPath, blockName);
      int filesGenerated1 = filesGenerated + 2;
      blockName = MDeco.blocks.workbenchSaw.getUnlocalizedName().substring(5);
      createSingleWorkbenchBlockstateFile(rootPath, blockName);
      filesGenerated1 += 3;
      return filesGenerated1;
   }

   private static void createBlockBlockstateFile(String rootPath, String blockName) {
      try {
         File jsonFile = new File(rootPath + blockName + ".json");
         if(jsonFile != null) {
            if(!jsonFile.exists()) {
               jsonFile.createNewFile();
            }

            PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
            if(printwriter != null) {
               printwriter.println("{");
               printwriter.println("\"variants\":{");
               printwriter.println(String.format("\"normal\":{\"model\":\"mdeco:%s\"}", new Object[]{blockName}));
               printwriter.println("}");
               printwriter.println("}");
               printwriter.close();
            }
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }

   }

   private static void createNoStateWorkbenchBlockstateFile(String rootPath, String blockName) {
      try {
         File jsonFile = new File(rootPath + blockName + ".json");
         if(jsonFile != null) {
            if(!jsonFile.exists()) {
               jsonFile.createNewFile();
            }

            PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
            if(printwriter != null) {
               printwriter.println("{");
               printwriter.println("\"variants\":{");
               printwriter.println(String.format("\"facing=north\":{\"model\":\"mdeco:%s\"},", new Object[]{blockName}));
               printwriter.println(String.format("\"facing=south\":{\"model\":\"mdeco:%s\",\"y\":180},", new Object[]{blockName}));
               printwriter.println(String.format("\"facing=west\":{\"model\":\"mdeco:%s\",\"y\":270},", new Object[]{blockName}));
               printwriter.println(String.format("\"facing=east\":{\"model\":\"mdeco:%s\",\"y\":90}", new Object[]{blockName}));
               printwriter.println("}");
               printwriter.println("}");
               printwriter.close();
            }
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }

   }

   private static void createSingleWorkbenchBlockstateFile(String rootPath, String blockName) {
      try {
         File jsonFile = new File(rootPath + blockName + ".json");
         if(jsonFile != null) {
            if(!jsonFile.exists()) {
               jsonFile.createNewFile();
            }

            PrintWriter printwriter = new PrintWriter(new FileWriter(jsonFile));
            if(printwriter != null) {
               printwriter.println("{");
               printwriter.println("\"variants\":{");
               printwriter.println(String.format("\"active=false,facing=north\":{\"model\":\"mdeco:%s0\"},", new Object[]{blockName}));
               printwriter.println(String.format("\"active=false,facing=south\":{\"model\":\"mdeco:%s0\",\"y\":180},", new Object[]{blockName}));
               printwriter.println(String.format("\"active=false,facing=west\":{\"model\":\"mdeco:%s0\",\"y\":270},", new Object[]{blockName}));
               printwriter.println(String.format("\"active=false,facing=east\":{\"model\":\"mdeco:%s0\",\"y\":90},", new Object[]{blockName}));
               printwriter.println(String.format("\"active=true,facing=north\":{\"model\":\"mdeco:%s1\"},", new Object[]{blockName}));
               printwriter.println(String.format("\"active=true,facing=south\":{\"model\":\"mdeco:%s1\",\"y\":180},", new Object[]{blockName}));
               printwriter.println(String.format("\"active=true,facing=west\":{\"model\":\"mdeco:%s1\",\"y\":270},", new Object[]{blockName}));
               printwriter.println(String.format("\"active=true,facing=east\":{\"model\":\"mdeco:%s1\",\"y\":90}", new Object[]{blockName}));
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
