package codes.cheater.ash_clay.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;

import java.util.Random;

public class AshClayBlock extends AshClayBrickBlock {
    public static final IntProperty DRY_STATE = IntProperty.of("dry_state", 0, 2);

    public AshClayBlock(Settings settings) {
        super(settings);

        setDefaultState(this.getDefaultState().with(DRY_STATE, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(DRY_STATE);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, RandomGenerator random) {
        int dry_state = state.get(DRY_STATE);
        if(dry_state < 2) {
            world.setBlockState(pos, state.with(DRY_STATE, dry_state + 1), 2);
        }
        else {
            world.setBlockState(pos, AshClayBlocks.ASH_CLAY_BRICK.getDefaultState().with(FACING, state.get(FACING)));
        }
    }
}
