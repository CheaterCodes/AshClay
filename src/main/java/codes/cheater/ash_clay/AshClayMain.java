package codes.cheater.ash_clay;

import codes.cheater.ash_clay.block.AshClayBlocks;
import codes.cheater.ash_clay.item.AshClayItems;
import codes.cheater.ash_clay.item.ItemAccessor;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
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
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class AshClayMain implements ModInitializer, UseItemCallback, LootTableEvents.Modify {
    private static final Identifier CAMPFIRE_LOOT_TABLE_ID = new Identifier("minecraft", "blocks/campfire");

    @Override
    public void onInitialize(ModContainer mod) {
        UseItemCallback.EVENT.register(this);
        LootTableEvents.MODIFY.register(this);

        AshClayItems.register();
        AshClayBlocks.register();
    }

    @Override
    public void modifyLootTable(ResourceManager resourceManager, LootManager lootManager, Identifier id,
                                LootTable.Builder tableBuilder, LootTableSource source) {
        if (CAMPFIRE_LOOT_TABLE_ID.equals(id)) {
            tableBuilder.pool(LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1, 2))
                    .with(ItemEntry.builder(AshClayItems.ASH).build())
                    .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create()
                            .enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, NumberRange.IntRange.ANY))
                    ).invert().build()));
        }
    }

    @Override
    public TypedActionResult<ItemStack> interact(PlayerEntity player, World world, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.getItem() == Items.BOWL) {
            BlockHitResult hitResult = ItemAccessor.rayTrace(world, player, RaycastContext.FluidHandling.SOURCE_ONLY);
            if (hitResult.getType() == HitResult.Type.BLOCK) {
                FluidState fluidState = world.getFluidState(hitResult.getBlockPos());
                if (fluidState.isSource() && fluidState.getFluid() == Fluids.WATER) {
                    if (!player.isCreative()) {
                        itemStack.decrement(1);
                    }
                    if (itemStack.isEmpty()) {
                        player.setStackInHand(hand, new ItemStack(AshClayItems.WATER_BOWL));
                    } else if (!player.getInventory().insertStack(new ItemStack(AshClayItems.WATER_BOWL))) {
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
