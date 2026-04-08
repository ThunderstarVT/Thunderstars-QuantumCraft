package net.thunderstar__vt.quantumcraft.object.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thunderstar__vt.quantumcraft.object.item.custom.ElementItem;
import net.thunderstar__vt.quantumcraft.util.Reference;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Reference.MOD_ID);


    public static final DeferredItem<ElementItem> ELEMENT = ITEMS.register("element",
            () -> new ElementItem(new Item.Properties().stacksTo(1024)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
