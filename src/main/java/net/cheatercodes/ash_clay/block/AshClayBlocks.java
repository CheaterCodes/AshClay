package net.cheatercodes.ash_clay.block;

import net.cheatercodes.ash_clay.block.entity.AshClayFurnaceBlockEntity;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AshClayBlocks {
    public static final AshClayBlock ASH_CLAY = new AshClayBlock(FabricBlockSettings.of(Material.PART).sounds(BlockSoundGroup.GRAVEL).breakInstantly().build());
    public static final AshClayBrickBlock ASH_CLAY_BRICK = new AshClayBrickBlock(FabricBlockSettings.of(Material.PART).breakInstantly().build());
    public static final Block ASH_CLAY_BRICKS = new Block(Block.Settings.copy(Blocks.BRICKS));
    public static final AshClayFurnaceBlock ASH_CLAY_FURNACE = new AshClayFurnaceBlock(Block.Settings.copy(ASH_CLAY_BRICKS));

    public static final BlockEntityType<AshClayFurnaceBlockEntity> ASH_CLAY_FURNACE_BLOCK_ENTITY_TYPE = BlockEntityType.Builder.create(AshClayFurnaceBlockEntity::new, ASH_CLAY_FURNACE).build(null);

    public static void register() {
        Registry.register(Registry.BLOCK, new Identifier("ash_clay", "ash_clay"), ASH_CLAY);
        Registry.register(Registry.BLOCK, new Identifier("ash_clay", "ash_clay_brick"), ASH_CLAY_BRICK);
        Registry.register(Registry.BLOCK, new Identifier("ash_clay", "ash_clay_bricks"), ASH_CLAY_BRICKS);
        Registry.register(Registry.BLOCK, new Identifier("ash_clay", "ash_clay_furnace"), ASH_CLAY_FURNACE);

        Registry.register(Registry.BLOCK_ENTITY, new Identifier("ash_clay", "ash_clay_furnace"), ASH_CLAY_FURNACE_BLOCK_ENTITY_TYPE);
    }
}
