package net.thunderstar__vt.quantumcraft;

import com.mojang.blaze3d.vertex.PoseStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterItemDecorationsEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.thunderstar__vt.quantumcraft.object.colorHandlers.ModColorHandlers;
import net.thunderstar__vt.quantumcraft.object.creativeModeTab.ModCreativeModeTabs;
import net.thunderstar__vt.quantumcraft.object.dataComponent.ModDataComponents;
import net.thunderstar__vt.quantumcraft.object.item.ModItems;
import net.thunderstar__vt.quantumcraft.object.item.custom.ElementItem;
import net.thunderstar__vt.quantumcraft.object.itemDecorator.ModItemDecorators;
import net.thunderstar__vt.quantumcraft.object.keybind.ModKeybinds;
import net.thunderstar__vt.quantumcraft.util.Reference;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(Reference.MOD_ID)
public class Main {
    public Main(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);


        ModDataComponents.register(modEventBus);

        ModItems.register(modEventBus);

        ModCreativeModeTabs.register(modEventBus);


        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        Reference.Setup();
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @EventBusSubscriber(modid = Reference.MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }

        @SubscribeEvent
        public static void registerBindings(RegisterKeyMappingsEvent event) {
            ModKeybinds.register(event);
        }

        @SubscribeEvent
        public static void registerItemDecorators(RegisterItemDecorationsEvent event) {
            ModItemDecorators.register(event);
        }

        @SubscribeEvent
        public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
            ModColorHandlers.registerItems(event);
        }
    }
}
