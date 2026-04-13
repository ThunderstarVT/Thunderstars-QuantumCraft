package net.thunderstar__vt.quantumcraft.object.colorHandlers;

import net.minecraft.client.color.item.ItemColor;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.thunderstar__vt.quantumcraft.object.dataComponent.ModDataComponents;
import net.thunderstar__vt.quantumcraft.object.item.ModItems;
import net.thunderstar__vt.quantumcraft.object.item.custom.PhotonItem;
import net.thunderstar__vt.quantumcraft.util.PhysUtils;

import java.awt.*;

public class ModColorHandlers {

    public static final ItemColor PHOTON_COLOR = (stack, tintIndex) -> {
        if (tintIndex != 0) return -1;

        PhotonItem.PhotonData data = stack.getOrDefault(ModDataComponents.PHOTON_DATA.value(), PhotonItem.PhotonData.DEFAULT);

        double energy = data.energy();
        double wavelength = PhysUtils.energyToWavelength(energy);
        Color color = PhysUtils.wavelengthToColor(wavelength);

        return color.getRGB();
    };


    public static void registerItems(RegisterColorHandlersEvent.Item event) {
        event.register(PHOTON_COLOR, ModItems.PHOTON.value());
    }
}
