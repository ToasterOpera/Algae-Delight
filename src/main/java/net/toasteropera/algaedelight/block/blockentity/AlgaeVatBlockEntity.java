package net.toasteropera.algaedelight.block.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

public class AlgaeVatBlockEntity extends BlockEntity {
    public AlgaeVatBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.ALGAE_VAT_BLOCK_ENTITY.get(), pos, blockState);
    }

    public final ItemStackHandler algaeItemStack = new ItemStackHandler(1) {
        @Override
        protected int getStackLimit(int slot, ItemStack stack) {
            return 64;
        }

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("algaeItemStack", algaeItemStack.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        algaeItemStack.deserializeNBT(registries, tag.getCompound("algaeItemStack"));
    }

    //    public void clearContents() {
//        algaeItemStack.setStackInSlot(0, ItemStack.EMPTY);
//    }
}
