package net.vinithekidd.overhaulzmod.keybinding;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    // Declaração da tecla personalizada
    public static final KeyMapping ATTRIBUTE_SCREEN_KEY =
            new KeyMapping( // Nome interno e configuração da tecla
                    "key.overhaulzmod.open_attribute_screen", // Identificador único para o mapeamento
                    InputConstants.Type.KEYSYM, // Tipo de entrada (teclado)
                    GLFW.GLFW_KEY_K, // Tecla padrão (K)
                    "key.categories.overhaulzmod" // Categoria (customizada para seu mod)
            );

    // Registro da tecla no evento apropriado
    public static void register(RegisterKeyMappingsEvent event) {
        event.register(ATTRIBUTE_SCREEN_KEY);
    }
}
