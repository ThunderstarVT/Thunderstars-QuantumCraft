package net.thunderstar__vt.quantumcraft;

import java.util.List;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue PROCESS_THREADS = BUILDER
            .comment("How many threads machines and such can use while ticking, recommended value: 0.5 * CPU threads")
            .defineInRange("processThreads", (int) (0.5 * Runtime.getRuntime().availableProcessors()), 1, Integer.MAX_VALUE);

    static final ModConfigSpec SPEC = BUILDER.build();

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }
}
