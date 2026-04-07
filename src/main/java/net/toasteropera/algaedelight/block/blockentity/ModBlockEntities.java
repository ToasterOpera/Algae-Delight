package net.toasteropera.algaedelight.block.blockentity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.toasteropera.algaedelight.AlgaeDelight;
import net.toasteropera.algaedelight.block.ModBlocks;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, AlgaeDelight.MODID);

    public static final Supplier<BlockEntityType<AlgaeVatBlockEntity>> ALGAE_VAT_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("algae_vat_block_entity", () -> BlockEntityType.Builder.of(
                    AlgaeVatBlockEntity::new, ModBlocks.ALGAE_VAT.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
