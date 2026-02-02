package net.toasteropera.algaedelight.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.toasteropera.algaedelight.AlgaeDelight;
import net.toasteropera.algaedelight.block.ModBlocks;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AlgaeDelight.MODID);

    public static final DeferredItem<Item> ALGAE = ITEMS.register("algae",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COOKED_ALGAE = ITEMS.register("cooked_algae",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SURFACE_ALGAE = ITEMS.register("surface_algae",
            () -> new PlaceOnWaterBlockItem(ModBlocks.SURFACE_ALGAE.get(), new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
