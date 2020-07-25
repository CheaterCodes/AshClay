package net.cheatercodes.ash_clay;

import net.cheatercodes.ash_clay.block.AshClayBlocks;
import net.cheatercodes.ash_clay.item.AshClayItems;
import net.cheatercodes.ash_clay.item.ItemAccessor;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.UniformLootTableRange;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.resource.ResourceManager;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.RayTraceContext;
import net.minecraft.world.World;

public class AshClayMain implements ModInitializer, LootTableLoadingCallback, UseItemCallback {
	private static final Identifier CAMPFIRE_LOOT_TABLE_ID = new Identifier("minecraft", "blocks/campfire");

	@Override
	public void onInitialize() {
		LootTableLoadingCallback.EVENT.register(this);
		UseItemCallback.EVENT.register(this);

		AshClayItems.register();
        AshClayBlocks.register();
	}

	@Override
	public void onLootTableLoading(ResourceManager resourceManager, LootManager lootManager, Identifier identifier, FabricLootSupplierBuilder fabricLootSupplierBuilder, LootTableSetter lootTableSetter) {
		if(CAMPFIRE_LOOT_TABLE_ID.equals(identifier)) {
            LootCondition noSilkTouchCondition = MatchToolLootCondition.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, NumberRange.IntRange.ANY))).invert().build();

			FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder();
			poolBuilder.rolls(UniformLootTableRange.between(1,2));
			poolBuilder.withEntry(ItemEntry.builder(AshClayItems.ASH).build());
			poolBuilder.withCondition(noSilkTouchCondition);
			fabricLootSupplierBuilder.withPool(poolBuilder.build());
		}
	}

	@Override
	public TypedActionResult<ItemStack> interact(PlayerEntity player, World world, Hand hand) {
		ItemStack itemStack = player.getStackInHand(hand);
		if(itemStack.getItem() == Items.BOWL) {
			HitResult hitResult = ItemAccessor.rayTrace(world, player, RayTraceContext.FluidHandling.SOURCE_ONLY);
			if(hitResult.getType() == HitResult.Type.BLOCK) {
				FluidState fluidState = world.getFluidState(((BlockHitResult)hitResult).getBlockPos());
				if(fluidState.isStill() && fluidState.getFluid() == Fluids.WATER) {
					if(!player.abilities.creativeMode) {
						itemStack.decrement(1);
					}
					if(itemStack.isEmpty()) {
						player.setStackInHand(hand, new ItemStack(AshClayItems.WATER_BOWL));
					}
					else if(!player.inventory.insertStack(new ItemStack(AshClayItems.WATER_BOWL))){
						player.dropItem(new ItemStack(AshClayItems.WATER_BOWL), false);
					}

					player.playSound(SoundEvents.ITEM_BUCKET_FILL, 1, 1);
					return TypedActionResult.success(null);
				}
			}
		}
		return TypedActionResult.pass(itemStack);
	}
}
