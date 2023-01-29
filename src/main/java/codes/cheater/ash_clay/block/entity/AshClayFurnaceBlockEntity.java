package codes.cheater.ash_clay.block.entity;

import codes.cheater.ash_clay.block.AshClayBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.FurnaceScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class AshClayFurnaceBlockEntity extends AbstractFurnaceBlockEntity {
    public AshClayFurnaceBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(AshClayBlocks.ASH_CLAY_FURNACE_BLOCK_ENTITY_TYPE, blockPos, blockState, RecipeType.SMELTING);
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.ash_clay_furnace");
    }

    @Override
    protected ScreenHandler createScreenHandler(int i, PlayerInventory playerInventory) {
        return new FurnaceScreenHandler(i, playerInventory, this, this.propertyDelegate);
    }
}
