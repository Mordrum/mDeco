package com.mordrum.mdeco;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.lang.reflect.InvocationTargetException;

public class Util {
	public static void registerBlockAndItem(Block block, Class<? extends ItemBlock> itemClass, String registryName) {
		try {
			block.setRegistryName(registryName);
			GameRegistry.register(block);

			ItemBlock itemBlock = itemClass.getConstructor(Block.class).newInstance(block);
			itemBlock.setRegistryName(registryName);
			GameRegistry.register(itemBlock);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
		}
	}
}
