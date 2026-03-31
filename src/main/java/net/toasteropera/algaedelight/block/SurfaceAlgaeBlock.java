package net.toasteropera.algaedelight.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import static java.lang.Math.*;

public class SurfaceAlgaeBlock extends Block implements BonemealableBlock{

    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 15);
    public static final int MAX_AGE = 15;

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 15;
    }

    public SurfaceAlgaeBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Block.box(0.0, 0.0, 0.0, 16.0, 0.5, 16.0);
    }

    //Prevents algae from being placed ing the air. Stay tuned for angel Algae
    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return !level.getBlockState(pos.below()).is(BlockTags.AIR);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(AGE) < 15){
            int times = random.nextInt(1, 2);
            for (int i = 0; i < times; i++) {
                int dx = random.nextInt(-1, 2);
                int dz = random.nextInt(-1, 2);
                if (level.isEmptyBlock(pos.offset(dx, 0, dz)) && level.isWaterAt(pos.offset(dx, -1, dz))) {
                    level.setBlockAndUpdate(pos.offset(dx, 0, dz), this.getGrowIntoState(state, level.random));
                    level.setBlockAndUpdate(pos, state.setValue(AGE, min(state.getValue(AGE) + 5, MAX_AGE)));
                    net.neoforged.neoforge.common.CommonHooks.fireCropGrowPost(level, pos.offset(dx, 0, dz), this.defaultBlockState());
                }
            }
        }
    }

    protected BlockState getGrowIntoState(BlockState state, RandomSource random) {
        int newAge = min((int) ((state.getValue(AGE) + MAX_AGE + 1) / (random.nextDouble() + 1)), MAX_AGE);
        BlockState newState = state.setValue(AGE, newAge);
        return newState;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return state.getValue(AGE) > 0;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        level.setBlockAndUpdate(pos, state.setValue(AGE, 0));
    }
}
