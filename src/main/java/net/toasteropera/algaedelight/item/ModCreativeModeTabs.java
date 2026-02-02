package net.toasteropera.algaedelight.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.toasteropera.algaedelight.AlgaeDelight;
import net.toasteropera.algaedelight.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AlgaeDelight.MODID);

    public static final Supplier<CreativeModeTab> ALGAE_DELIGHT_TAB = CREATIVE_MODE_TAB.register("algae_delight_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.ALGAE.get()))
                    .title(Component.translatable("creativetab.algaedelight.algae_delight"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.ALGAE);
                        output.accept(ModItems.COOKED_ALGAE);
                        output.accept(ModBlocks.ALGAE_BLOCK);
                        output.accept(ModBlocks.SURFACE_ALGAE);
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
