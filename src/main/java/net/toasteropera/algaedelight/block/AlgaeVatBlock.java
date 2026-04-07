package net.toasteropera.algaedelight.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.toasteropera.algaedelight.block.blockentity.AlgaeVatBlockEntity;
import org.jetbrains.annotations.Nullable;

public class AlgaeVatBlock extends BaseEntityBlock {
    public static final MapCodec<AlgaeVatBlock> CODEC = simpleCodec(AlgaeVatBlock::new);

    public AlgaeVatBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AlgaeVatBlockEntity(pos, state);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (state.getBlock() != newState.getBlock()) {
            if (level.getBlockEntity(pos) instanceof AlgaeVatBlockEntity algaeVatBlockEntity) {
//                algaeVatBlockEntity.drops();
                level.updateNeighbourForOutputSignal(pos, this);
                //TODO: Remove self from multiblock, drop any items? Or drop self with items, fluid, etc inside
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }
}
