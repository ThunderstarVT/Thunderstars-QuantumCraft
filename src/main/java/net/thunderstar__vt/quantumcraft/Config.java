package net.thunderstar__vt.quantumcraft;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue MACHINE_PROCESSING_THREADS = BUILDER
            .comment("The number of threads machines can use to process tasks.")
            .comment("This controls how much parallelism a *single machine* can use for complex tasks that can be optimized by parallelization.")
            .defineInRange("machineProcessingThreads", (int) Math.max(0.5 * Runtime.getRuntime().availableProcessors(), 1), 1, Integer.MAX_VALUE);

    static final ModConfigSpec SPEC = BUILDER.build();

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }
}
