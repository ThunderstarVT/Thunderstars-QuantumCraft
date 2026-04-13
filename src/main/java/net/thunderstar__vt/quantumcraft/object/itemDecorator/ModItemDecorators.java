package net.thunderstar__vt.quantumcraft.object.itemDecorator;

import com.mojang.blaze3d.vertex.PoseStack;
import net.neoforged.neoforge.client.IItemDecorator;
import net.neoforged.neoforge.client.event.RegisterItemDecorationsEvent;
import net.thunderstar__vt.quantumcraft.object.dataComponent.ModDataComponents;
import net.thunderstar__vt.quantumcraft.object.item.ModItems;
import net.thunderstar__vt.quantumcraft.object.item.custom.ElementItem;

public class ModItemDecorators {
    public static final IItemDecorator ELEMENT_DECORATOR = (guiGraphics, font, stack, x, y) -> {
        ElementItem.AtomData data = stack.getOrDefault(ModDataComponents.ATOM_DATA.value(), ElementItem.AtomData.DEFAULT);

        int p = data.protons();
        int n = data.neutrons();
        int e = data.electrons();

        int charge = p - e;
        int isotope = p + n;

        boolean isAnti = p < 0 || (p == 0 && n < 0);

        String symbolStr = ElementItem.ElementRegistry.getSymbol(Math.abs(p));
        String massStr = String.valueOf(Math.abs(isotope));
        String chargeStr = (charge > 0 ? "+" : "") + charge;
        String antiStr = "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯";

        PoseStack pose = guiGraphics.pose();

        pose.pushPose();

        pose.translate(0, 0, 200);

        guiGraphics.drawString(font, symbolStr,
                x + 8 - font.width(symbolStr)/2, y + 8 - font.lineHeight/2,
                0xFFFFFF, true);

        if (isAnti) {
            guiGraphics.drawString(font, antiStr.substring(0, symbolStr.length()),
                    x + 8 - font.width(antiStr.substring(0, symbolStr.length())) / 2, y + 8 - font.lineHeight / 2,
                    0xFFFFFF, true);
            guiGraphics.drawString(font, antiStr.substring(0, symbolStr.length()-1),
                    x + 8 - font.width(antiStr.substring(0, symbolStr.length()-1)) / 2, y + 8 - font.lineHeight / 2,
                    0xFFFFFF, true);
        }

        pose.scale(1f/3f, 1f/3f, 1f);

        guiGraphics.drawString(font, massStr,
                3*x + 3, 3*y + 3,
                0xFFFFFF, true);

        guiGraphics.drawString(font, chargeStr,
                3*x + 45 - font.width(chargeStr), 3*y + 3,
                0xFFFFFF, true);

        guiGraphics.drawString(font, data.muonic() ? "μ" : "e",
                3*x + 3, 3*y + 45 - font.lineHeight,
                0xFFFFFF, true);

        pose.popPose();

        return true;
    };


    public static void register(RegisterItemDecorationsEvent event) {
        event.register(ModItems.ELEMENT.value(), ELEMENT_DECORATOR);
    }
}
