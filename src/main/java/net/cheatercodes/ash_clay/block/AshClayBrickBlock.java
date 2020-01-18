package net.cheatercodes.ash_clay.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class AshClayBrickBlock extends HorizontalFacingBlock {
    private static final VoxelShape X_AXIS_SHAPE = Block.createCuboidShape(4, 0, 6, 12, 3, 10);
    private static final VoxelShape Z_AXIS_SHAPE = Block.createCuboidShape(6, 0, 4, 10, 3, 12);

    protected AshClayBrickBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HorizontalFacingBlock.FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getPlayerFacing());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext ePos) {
        if(state.get(FACING).getAxis() == Direction.Axis.X)
            return X_AXIS_SHAPE;
        else
            return Z_AXIS_SHAPE;    }
}
