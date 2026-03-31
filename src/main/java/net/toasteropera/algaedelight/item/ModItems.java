package net.toasteropera.algaedelight.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationModifier(0.3F).fast().build())));

    public static final DeferredItem<Item> SURFACE_ALGAE = ITEMS.register("surface_algae",
            () -> new PlaceOnWaterBlockItem(ModBlocks.SURFACE_ALGAE.get(), new Item.Properties()));

    public static final DeferredItem<Item> CUT_ALGAE = ITEMS.register("cut_algae",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DICED_ALGAE = ITEMS.register("diced_algae",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ALGAE_FLOUR = ITEMS.register("algae_flour",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ALGAE_DOUGH = ITEMS.register("algae_dough",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_ALGAE_NOODLES = ITEMS.register("raw_algae_noodles",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_ALGAE_PATTY = ITEMS.register("raw_algae_patty",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ALGAE_PATTY = ITEMS.register("algae_patty",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ALGAE_STRIPS = ITEMS.register("algae_strips",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.3F).build())));
    public static final DeferredItem<Item> ALGAE_BREAD = ITEMS.register("algae_bread",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationModifier(0.7F).build())));
    public static final DeferredItem<Item> ALGAE_NOODLES = ITEMS.register("algae_noodles",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationModifier(0.8F).build()).craftRemainder(Items.BOWL).stacksTo(16)));
    public static final DeferredItem<Item> ALGAE_BURGER = ITEMS.register("algae_burger",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(10).saturationModifier(0.8F).build())));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
