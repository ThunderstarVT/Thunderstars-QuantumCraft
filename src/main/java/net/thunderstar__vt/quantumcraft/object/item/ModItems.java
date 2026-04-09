package net.thunderstar__vt.quantumcraft.object.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thunderstar__vt.quantumcraft.object.dataComponent.ModDataComponents;
import net.thunderstar__vt.quantumcraft.object.item.custom.ElementItem;
import net.thunderstar__vt.quantumcraft.util.Reference;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Reference.MOD_ID);


    public static final DeferredItem<ElementItem> ELEMENT = ITEMS.register("element",
            () -> new ElementItem(new Item.Properties().stacksTo(96)));


    public static ItemStack createElementStack(int protons, int neutrons, int electrons, boolean muonic) {
        ItemStack stack = new ItemStack(ELEMENT.value());

        stack.set(ModDataComponents.ATOM_DATA.value(),
                new ElementItem.AtomData(protons, neutrons, electrons, muonic));

        return stack;
    }


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
