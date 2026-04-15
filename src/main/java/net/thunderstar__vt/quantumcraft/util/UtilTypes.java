package net.thunderstar__vt.quantumcraft.util;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

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
}
