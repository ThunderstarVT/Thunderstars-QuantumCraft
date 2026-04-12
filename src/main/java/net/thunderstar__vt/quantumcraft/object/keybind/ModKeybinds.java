package net.thunderstar__vt.quantumcraft.object.keybind;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

public class ModKeybinds {
    public static final KeyMapping SHOW_DETAILS = new KeyMapping(
            "key.quantumcraft.show_details",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_LEFT_SHIFT,
            "key.categories.quantumcraft"
    );


    public static boolean isDown(KeyMapping key) {
        Minecraft mc = Minecraft.getInstance();
        return InputConstants.isKeyDown(mc.getWindow().getWindow(), key.getKey().getValue());
    }

    public static void register(RegisterKeyMappingsEvent event) {
        event.register(SHOW_DETAILS);
    }
}
