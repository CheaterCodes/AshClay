package codes.cheater.ash_clay.item;

import codes.cheater.ash_clay.block.AshClayBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AshClayItems {
    public static final Item ASH = new Item(new Item.Settings());
    public static final Item ASH_CLAY = new BlockItem(AshClayBlocks.ASH_CLAY, new Item.Settings());
    public static final Item ASH_CLAY_BRICK = new BlockItem(AshClayBlocks.ASH_CLAY_BRICK, new Item.Settings());
    public static final Item ASH_CLAY_BRICKS = new BlockItem(AshClayBlocks.ASH_CLAY_BRICKS, new Item.Settings());
    public static final Item ASH_CLAY_FURNACE = new BlockItem(AshClayBlocks.ASH_CLAY_FURNACE, new Item.Settings());
    public static final Item WATER_BOWL = new Item(new Item.Settings().recipeRemainder(Items.BOWL));

    public static void register() {
        Registry.register(Registries.ITEM, new Identifier("ash_clay", "ash"), ASH);
        Registry.register(Registries.ITEM, new Identifier("ash_clay", "ash_clay"), ASH_CLAY);
        Registry.register(Registries.ITEM, new Identifier("ash_clay", "ash_clay_brick"), ASH_CLAY_BRICK);
        Registry.register(Registries.ITEM, new Identifier("ash_clay", "ash_clay_bricks"), ASH_CLAY_BRICKS);
        Registry.register(Registries.ITEM, new Identifier("ash_clay", "ash_clay_furnace"), ASH_CLAY_FURNACE);
        Registry.register(Registries.ITEM, new Identifier("ash_clay", "water_bowl"), WATER_BOWL);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.addItem(ASH);
            entries.addItem(ASH_CLAY);
            entries.addItem(ASH_CLAY_BRICK);
            entries.addItem(WATER_BOWL);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.addItem(ASH_CLAY_BRICKS);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.addItem(ASH_CLAY_FURNACE);
        });
    }
}
