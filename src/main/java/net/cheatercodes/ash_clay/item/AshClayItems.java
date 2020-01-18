package net.cheatercodes.ash_clay.item;

import net.cheatercodes.ash_clay.block.AshClayBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AshClayItems {
    public static final Item ASH = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item ASH_CLAY = new BlockItem(AshClayBlocks.ASH_CLAY, new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item ASH_CLAY_BRICK = new BlockItem(AshClayBlocks.ASH_CLAY_BRICK, new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item ASH_CLAY_BRICKS = new BlockItem(AshClayBlocks.ASH_CLAY_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item ASH_CLAY_FURNACE = new BlockItem(AshClayBlocks.ASH_CLAY_FURNACE, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item WATER_BOWL = new Item(new Item.Settings().recipeRemainder(Items.BOWL).group(ItemGroup.MATERIALS));

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier("ash_clay", "ash"), ASH);
        Registry.register(Registry.ITEM, new Identifier("ash_clay", "ash_clay"), ASH_CLAY);
        Registry.register(Registry.ITEM, new Identifier("ash_clay", "ash_clay_brick"), ASH_CLAY_BRICK);
        Registry.register(Registry.ITEM, new Identifier("ash_clay", "ash_clay_bricks"), ASH_CLAY_BRICKS);
        Registry.register(Registry.ITEM, new Identifier("ash_clay", "ash_clay_furnace"), ASH_CLAY_FURNACE);
        Registry.register(Registry.ITEM, new Identifier("ash_clay", "water_bowl"), WATER_BOWL);
    }
}
