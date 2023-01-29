package codes.cheater.ash_clay.block;

import codes.cheater.ash_clay.block.entity.AshClayFurnaceBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder;

public class AshClayBlocks {
    public static final AshClayBlock ASH_CLAY = new AshClayBlock(
            AbstractBlock.Settings.of(Material.DECORATION).sounds(BlockSoundGroup.GRAVEL).breakInstantly());
    public static final AshClayBrickBlock ASH_CLAY_BRICK =
            new AshClayBrickBlock(AbstractBlock.Settings.of(Material.DECORATION).breakInstantly());
    public static final Block ASH_CLAY_BRICKS = new Block(Block.Settings.copy(Blocks.BRICKS));
    public static final AshClayFurnaceBlock ASH_CLAY_FURNACE =
            new AshClayFurnaceBlock(Block.Settings.copy(ASH_CLAY_BRICKS));

    public static final BlockEntityType<AshClayFurnaceBlockEntity> ASH_CLAY_FURNACE_BLOCK_ENTITY_TYPE =
            QuiltBlockEntityTypeBuilder.create(AshClayFurnaceBlockEntity::new, ASH_CLAY_FURNACE).build();
    public static void register() {
        Registry.register(Registries.BLOCK, new Identifier("ash_clay", "ash_clay"), ASH_CLAY);
        Registry.register(Registries.BLOCK, new Identifier("ash_clay", "ash_clay_brick"), ASH_CLAY_BRICK);
        Registry.register(Registries.BLOCK, new Identifier("ash_clay", "ash_clay_bricks"), ASH_CLAY_BRICKS);
        Registry.register(Registries.BLOCK, new Identifier("ash_clay", "ash_clay_furnace"), ASH_CLAY_FURNACE);

        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier("ash_clay", "ash_clay_furnace"),
                ASH_CLAY_FURNACE_BLOCK_ENTITY_TYPE);

    }
}
