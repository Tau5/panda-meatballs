package com.github.tau5.meatballs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.PandaEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PandaMeatballs implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors. 
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");
	private static final Identifier PANDA_LOOT_TABLE_ID = new Identifier("minecraft", "entities/panda");
	public static final FoodComponent PANDA_MEAT_FOOD = new FoodComponent.Builder().hunger(4).saturationModifier(0.4f).statusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, (int)(2.5*20), 1), 1.0f).statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, (int)(2.5*20), 1), 1.0f).statusEffect(new StatusEffectInstance(StatusEffects.UNLUCK, Integer.MAX_VALUE, 1), 1.0f).meat().build();
	public static final Item PANDA_MEAT = new Item(new FabricItemSettings().group(ItemGroup.FOOD).food(PANDA_MEAT_FOOD));
	public static final FoodComponent PANDA_MEATBALLS_FOOD = new FoodComponent.Builder().hunger(9).saturationModifier(1.0f).build();
	public static final Item PANDA_MEATBALLS = new Item(new FabricItemSettings().group(ItemGroup.FOOD).food(PANDA_MEATBALLS_FOOD));
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Registry.register(Registry.ITEM, new Identifier("panda_meatballs", "panda_meat"), PANDA_MEAT);
		Registry.register(Registry.ITEM, new Identifier("panda_meatballs", "panda_meatballs"), PANDA_MEATBALLS);

		LOGGER.info("Hello Fabric world!");
		LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
			if (PANDA_LOOT_TABLE_ID.equals(id)) {
				FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
					.rolls(ConstantLootNumberProvider.create(1))
					.with(ItemEntry.builder(PANDA_MEAT));
				table.pool(poolBuilder);
			}
		});
	}

}
