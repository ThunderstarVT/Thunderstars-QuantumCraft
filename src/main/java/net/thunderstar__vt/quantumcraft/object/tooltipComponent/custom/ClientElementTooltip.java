package net.thunderstar__vt.quantumcraft.object.tooltipComponent.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.thunderstar__vt.quantumcraft.object.item.ModItems;
import net.thunderstar__vt.quantumcraft.object.item.custom.ElementItem;

@OnlyIn(Dist.CLIENT)
public class ClientElementTooltip implements ClientTooltipComponent {
    private final ElementItem.AtomData data;

    public ClientElementTooltip(ElementTooltip elementTooltip) {
        this.data = elementTooltip.data();
    }

    @Override
    public int getHeight() {
        return 96;
    }

    @Override
    public int getWidth(Font font) {
        return 96;
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics guiGraphics) {
        PoseStack pose = guiGraphics.pose();

        pose.pushPose();
        pose.translate(x, y, 300);

        // TMP
        guiGraphics.renderItem(ModItems.createPhotonStack(2.5), 40, 40);

        pose.popPose();
    }
}
