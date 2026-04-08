package net.thunderstar__vt.quantumcraft.object.dataComponent;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thunderstar__vt.quantumcraft.object.item.custom.ElementItem;
import net.thunderstar__vt.quantumcraft.util.Reference;

public class ModDataComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Reference.MOD_ID);


    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ElementItem.AtomData>> ATOM_DATA =
            DATA_COMPONENTS.register("atom_data",
                    () -> DataComponentType.<ElementItem.AtomData>builder()
                            .persistent(ElementItem.AtomData.CODEC)
                            .networkSynchronized(ElementItem.AtomData.STREAM_CODEC)
                            .build());


    public static void register(IEventBus eventBus) {
        DATA_COMPONENTS.register(eventBus);
    }
}
