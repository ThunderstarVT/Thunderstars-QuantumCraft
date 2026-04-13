package net.thunderstar__vt.quantumcraft.object.block;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thunderstar__vt.quantumcraft.util.Reference;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Reference.MOD_ID);





    public static void register(IEventBus event) {
        BLOCKS.register(event);
    }
}
