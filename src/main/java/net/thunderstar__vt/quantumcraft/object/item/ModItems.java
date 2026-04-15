package net.thunderstar__vt.quantumcraft.object.item;

import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thunderstar__vt.quantumcraft.object.dataComponent.ModDataComponents;
import net.thunderstar__vt.quantumcraft.object.item.custom.ElementItem;
import net.thunderstar__vt.quantumcraft.object.item.custom.ParticleItem;
import net.thunderstar__vt.quantumcraft.object.item.custom.PhotonItem;
import net.thunderstar__vt.quantumcraft.util.Reference;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Reference.MOD_ID);


    public static final DeferredItem<ElementItem> ELEMENT = ITEMS.register("element",
            () -> new ElementItem(new Item.Properties().stacksTo(96)));

    public static final DeferredItem<ParticleItem> PARTICLE = ITEMS.register("particle",
            () -> new ParticleItem(new Item.Properties().stacksTo(96)));

    public static final DeferredItem<PhotonItem> PHOTON = ITEMS.register("photon",
            () -> new PhotonItem(new Item.Properties().stacksTo(96)));


    public static ItemStack createElementStack(int protons, int neutrons, int electrons, boolean muonic) {
        ItemStack stack = new ItemStack(ELEMENT.value());

        stack.set(ModDataComponents.ATOM_DATA.value(),
                new ElementItem.AtomData(protons, neutrons, electrons, muonic));

        return stack;
    }

    public static ItemStack createParticleStack(ParticleItem.ParticleType type, boolean anti) {
        ItemStack stack = new ItemStack(PARTICLE.value());

        stack.set(ModDataComponents.PARTICLE_DATA.value(),
                new ParticleItem.ParticleData(type, anti));

        return stack;
    }

    public static ItemStack createPhotonStack(double energy) {
        ItemStack stack = new ItemStack(PHOTON.value());

        stack.set(ModDataComponents.PHOTON_DATA.value(),
                new PhotonItem.PhotonData(energy));

        return stack;
    }


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
