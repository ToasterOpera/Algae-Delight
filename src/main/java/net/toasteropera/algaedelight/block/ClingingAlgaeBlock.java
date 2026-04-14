package net.toasteropera.algaedelight.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

import static java.lang.Math.min;

public class ClingingAlgaeBlock extends MultifaceBlock implements BonemealableBlock, SimpleWaterloggedBlock {
    public static final MapCodec<ClingingAlgaeBlock> CODEC = simpleCodec(ClingingAlgaeBlock::new);
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private final MultifaceSpreader spreader = new MultifaceSpreader(this);

    public static final int MAX_AGE = 15;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE);

    public ClingingAlgaeBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(AGE, Integer.valueOf(MAX_AGE)).setValue(WATERLOGGED, Boolean.valueOf(false)));

    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < MAX_AGE;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(AGE) < MAX_AGE) {
            if (this.spreader.spreadFromRandomFaceTowardRandomDirection(state, level, pos, random).isPresent()) {
                level.setBlockAndUpdate(pos, state.setValue(AGE, min(state.getValue(AGE) + random.nextInt(2, 5), MAX_AGE)));
                this.spreader.spreadFromRandomFaceTowardRandomDirection(state, level, pos, random);
            }
        }
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

    @Override
    protected MapCodec<? extends MultifaceBlock> codec() {
        return CODEC;
    }

    @Override
    public MultifaceSpreader getSpreader() {
        return this.spreader;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED);
        builder.add(AGE);
    }

    @Override
    protected BlockState updateShape(
            BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos
    ) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }
}
