package net.toasteropera.algaedelight;

import java.util.List;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue APPLY_OOZING = BUILDER
            .comment("Whether swimming in algae should have a chance of applying the Oozing effect")
            .define("applyOozing", true);

    static final ModConfigSpec SPEC = BUILDER.build();
}
