package net.thunderstar__vt.quantumcraft.util;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

import java.awt.*;

public class UtilTypes {
    public static class StringSubSup {
        public final String text;
        public final String sub;
        public final String sup;

        public StringSubSup(String text) {
            this(text, "", "");
        }

        public StringSubSup(String text, String sub) {
            this(text, sub, "");
        }

        public StringSubSup(String text, String sub, String sup) {
            this.text = text;
            this.sub = sub;
            this.sup = sup;
        }


        public int getWidth(Font font) {
            return font.width(text) + Math.max(font.width(sub), font.width(sup))/2;
        }

        public void drawString(GuiGraphics guiGraphics, Font font, int x, int y, int color, boolean dropShadow) {
            guiGraphics.drawString(font, text, x, y, color, dropShadow);

            PoseStack pose = guiGraphics.pose();
            pose.pushPose();
            pose.scale(0.5f, 0.5f, 1f);

            guiGraphics.drawString(font, sub, 2*x + font.width(text), 2*y + font.lineHeight, color, dropShadow);
            guiGraphics.drawString(font, sup, 2*x + font.width(text), 2*y, color, dropShadow);

            pose.popPose();
        }
    }

    public static class HDRColor {
        public final Color color;
        public final double intensity;

        public HDRColor(Color color, float intensity) {
            double max = (double)Math.max(Math.max(color.getRed(), color.getGreen()), color.getBlue()) / 255.0;

            if (max == 0.0) {
                this.intensity = 0.0;
                this.color = Color.white;

                return;
            }

            this.intensity = intensity * max;
            this.color = new Color((int) (color.getRed() / max), (int) (color.getGreen() / max), (int) (color.getBlue() / max), color.getAlpha());
        }

        public HDRColor(double red, double green, double blue) {
            double max = Math.max(Math.max(red, green), blue);

            if (max == 0.0) {
                this.intensity = 0.0;
                this.color = Color.white;

                return;
            }

            this.intensity = max;
            this.color = new Color((float)(red / max), (float)(green / max), (float)(blue / max));
        }

        public Color getColor(double intensityReduction) {
            double effectiveIntensity = this.intensity / intensityReduction;

            return new Color(
                    Math.clamp((int)(this.color.getRed() * effectiveIntensity), 0, 255),
                    Math.clamp((int)(this.color.getGreen() * effectiveIntensity), 0, 255),
                    Math.clamp((int)(this.color.getBlue() * effectiveIntensity), 0, 255),
                    this.color.getAlpha()
            );
        }
    }
}
