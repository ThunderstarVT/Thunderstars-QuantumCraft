package net.thunderstar__vt.quantumcraft.object.tooltipComponent.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Tuple;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.thunderstar__vt.quantumcraft.object.item.ModItems;
import net.thunderstar__vt.quantumcraft.object.item.custom.ElementItem;
import net.thunderstar__vt.quantumcraft.util.QuantUtils;
import net.thunderstar__vt.quantumcraft.util.Reference;
import org.checkerframework.checker.units.qual.C;

import java.util.*;
import java.util.random.RandomGenerator;

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
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;

        double time = mc.level.getGameTime() / 20.0;

        PoseStack pose = guiGraphics.pose();

        pose.pushPose();
        pose.translate(x, y, 300);

        List<QuantUtils.FilledOrbital> filledOrbitals = QuantUtils.getFilledOrbitals(data.electrons());

        Map<Integer, Integer> shellFill = new HashMap<>();

        for (QuantUtils.FilledOrbital orbital : filledOrbitals) {
            shellFill.put(orbital.n, orbital.electrons + shellFill.getOrDefault(orbital.n, 0));
        }

        for (int i = 0; i < shellFill.size(); i++) {
            float radius = 8f + (i + 1f) / (shellFill.size() + 1f) * 40f;

            int electrons = shellFill.get(i + 1);

            drawCircle(guiGraphics, 48f, 48f, radius, 0.5f, 0x808080);

            for (int j = 0; j < electrons; j++) {
                Tuple<Float, Float> pos = getPointOnCircle(0.1f, (float) j / electrons, time);

                float size = data.muonic() ? 2f : 1f;

                int color = data.electrons() < 0 ? 0xFF00FF : 0x00FF00;

                fillCircle(guiGraphics, 48f + pos.getA() * radius, 48f + pos.getB() * radius, size, color);
            }
        }

        int protonColor = data.protons() < 0 ? 0x00FFFF : 0xFF0000;
        int neutronColor = data.protons() < 0 ? 0xFFFF00 : 0x0000FF;

        int protonCount = Math.abs(data.protons());
        int neutronCount = Math.abs(data.neutrons());

        long nucleusSeed = Objects.hash(data.protons(), data.neutrons());
        Random nucleusRandom = new Random(nucleusSeed);

        List<Boolean> isProton = new ArrayList<>();
        for (int i = 0; i < protonCount; i++) isProton.add(true);
        for (int i = 0; i < neutronCount; i++) isProton.add(false);
        Collections.shuffle(isProton, nucleusRandom);

        float nucleusSize = (float)Math.clamp(Math.cbrt(isProton.size()) - 1, 0.0, 6.0);

        for (boolean nucleon : isProton) {
            Tuple<Float, Float> pos = getPointOnCircle(1f, nucleusRandom.nextFloat(), 0);
            float r = (float)Math.sqrt(nucleusRandom.nextFloat()) * nucleusSize;

            fillCircle(guiGraphics, 48f + pos.getA() * r, 48f + pos.getB() * r, 3f, nucleon ? protonColor : neutronColor);
        }

        pose.popPose();
    }


    private void drawCircle(GuiGraphics guiGraphics, float x, float y, float radius, float weight, int color) {
        int Color = 0xFF000000 | color;

        int minX = (int)Math.floor(x - radius - weight);
        int maxX = (int)Math.ceil(x + radius + weight);
        int minY = (int)Math.floor(y - radius - weight);
        int maxY = (int)Math.ceil(y + radius + weight);

        for (int Y = minY; Y <= maxY; Y++) {
            for (int X = minX; X <= maxX; X++) {
                if (Math.abs(Math.hypot(X-x, Y-y) - radius) < weight) {
                    guiGraphics.fill(X, Y, X+1, Y+1, Color);
                }
            }
        }
    }

    private void fillCircle(GuiGraphics guiGraphics, float x, float y, float radius, int color) {
        int Color = 0xFF000000 | color;

        int minX = (int)Math.floor(x - radius);
        int maxX = (int)Math.ceil(x + radius);
        int minY = (int)Math.floor(y - radius);
        int maxY = (int)Math.ceil(y + radius);

        for (int Y = minY; Y <= maxY; Y++) {
            for (int X = minX; X <= maxX; X++) {
                if (Math.hypot(X-x, Y-y) < radius) {
                    guiGraphics.fill(X, Y, X+1, Y+1, Color);
                }
            }
        }
    }

    private Tuple<Float, Float> getPointOnCircle(float frequency, float phaseOffset, double time) {
        double angle = 2 * Math.PI * (frequency * time + phaseOffset);

        float x = (float) Math.cos(angle);
        float y = (float) Math.sin(angle);

        return new Tuple<>(x, y);
    }
}
