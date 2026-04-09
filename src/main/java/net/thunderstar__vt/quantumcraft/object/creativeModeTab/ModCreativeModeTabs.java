package net.thunderstar__vt.quantumcraft.object.creativeModeTab;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thunderstar__vt.quantumcraft.object.item.ModItems;
import net.thunderstar__vt.quantumcraft.util.Reference;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MOD_ID);


    public static final Supplier<CreativeModeTab> ELEMENTS_TAB = CREATIVE_MODE_TABS.register("elements_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> ModItems.createElementStack(1, 0, 1, false))
                    .title(Component.translatable("creativetab.quantumcraft.elements"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.createElementStack(1, 0, 1, false));
                        output.accept(ModItems.createElementStack(2, 2, 2, false));
                        output.accept(ModItems.createElementStack(3, 4, 3, false));
                        output.accept(ModItems.createElementStack(4, 5, 4, false));
                        output.accept(ModItems.createElementStack(5, 6, 5, false));
                        output.accept(ModItems.createElementStack(6, 6, 6, false));
                        output.accept(ModItems.createElementStack(7, 7, 7, false));
                        output.accept(ModItems.createElementStack(8, 8, 8, false));
                        output.accept(ModItems.createElementStack(9, 10, 9, false));
                        output.accept(ModItems.createElementStack(10, 10, 10, false));
                        output.accept(ModItems.createElementStack(11, 12, 11, false));
                        output.accept(ModItems.createElementStack(12, 12, 12, false));
                        output.accept(ModItems.createElementStack(13, 14, 13, false));
                        output.accept(ModItems.createElementStack(14, 14, 14, false));
                        output.accept(ModItems.createElementStack(15, 16, 15, false));
                        output.accept(ModItems.createElementStack(16, 16, 16, false));
                        output.accept(ModItems.createElementStack(17, 18, 17, false));
                        output.accept(ModItems.createElementStack(18, 22, 18, false));
                        output.accept(ModItems.createElementStack(19, 20, 19, false));
                        output.accept(ModItems.createElementStack(20, 20, 20, false));
                        output.accept(ModItems.createElementStack(21, 24, 21, false));
                        output.accept(ModItems.createElementStack(22, 26, 22, false));
                        output.accept(ModItems.createElementStack(23, 28, 23, false));
                        output.accept(ModItems.createElementStack(24, 28, 24, false));
                        output.accept(ModItems.createElementStack(25, 30, 25, false));
                        output.accept(ModItems.createElementStack(26, 30, 26, false));
                        output.accept(ModItems.createElementStack(27, 32, 27, false));
                        output.accept(ModItems.createElementStack(28, 31, 28, false));
                        output.accept(ModItems.createElementStack(29, 35, 29, false));
                        output.accept(ModItems.createElementStack(30, 35, 30, false));
                        output.accept(ModItems.createElementStack(31, 39, 31, false));
                        output.accept(ModItems.createElementStack(32, 41, 32, false));
                        output.accept(ModItems.createElementStack(33, 42, 33, false));
                        output.accept(ModItems.createElementStack(34, 45, 34, false));
                        output.accept(ModItems.createElementStack(35, 45, 35, false));
                        output.accept(ModItems.createElementStack(36, 48, 36, false));
                        output.accept(ModItems.createElementStack(37, 48, 37, false));
                        output.accept(ModItems.createElementStack(38, 50, 38, false));
                        output.accept(ModItems.createElementStack(39, 50, 39, false));
                        output.accept(ModItems.createElementStack(40, 51, 40, false));
                        output.accept(ModItems.createElementStack(41, 52, 41, false));
                        output.accept(ModItems.createElementStack(42, 54, 42, false));
                        output.accept(ModItems.createElementStack(43, 55, 43, false));
                        output.accept(ModItems.createElementStack(44, 57, 44, false));
                        output.accept(ModItems.createElementStack(45, 58, 45, false));
                        output.accept(ModItems.createElementStack(46, 60, 46, false));
                        output.accept(ModItems.createElementStack(47, 61, 47, false));
                        output.accept(ModItems.createElementStack(48, 64, 48, false));
                        output.accept(ModItems.createElementStack(49, 66, 49, false));
                        output.accept(ModItems.createElementStack(50, 69, 50, false));
                        output.accept(ModItems.createElementStack(51, 71, 51, false));
                        output.accept(ModItems.createElementStack(52, 76, 52, false));
                        output.accept(ModItems.createElementStack(53, 74, 53, false));
                        output.accept(ModItems.createElementStack(54, 77, 54, false));
                        output.accept(ModItems.createElementStack(55, 78, 55, false));
                        output.accept(ModItems.createElementStack(56, 81, 56, false));
                        output.accept(ModItems.createElementStack(57, 82, 57, false));
                        output.accept(ModItems.createElementStack(58, 82, 58, false));
                        output.accept(ModItems.createElementStack(59, 82, 59, false));
                        output.accept(ModItems.createElementStack(60, 84, 60, false));
                        output.accept(ModItems.createElementStack(61, 84, 61, false));
                        output.accept(ModItems.createElementStack(62, 88, 62, false));
                        output.accept(ModItems.createElementStack(63, 89, 63, false));
                        output.accept(ModItems.createElementStack(64, 93, 64, false));
                        output.accept(ModItems.createElementStack(65, 94, 65, false));
                        output.accept(ModItems.createElementStack(66, 96, 66, false));
                        output.accept(ModItems.createElementStack(67, 98, 67, false));
                        output.accept(ModItems.createElementStack(68, 99, 68, false));
                        output.accept(ModItems.createElementStack(69, 100, 69, false));
                        output.accept(ModItems.createElementStack(70, 103, 70, false));
                        output.accept(ModItems.createElementStack(71, 104, 71, false));
                        output.accept(ModItems.createElementStack(72, 106, 72, false));
                        output.accept(ModItems.createElementStack(73, 108, 73, false));
                        output.accept(ModItems.createElementStack(74, 110, 74, false));
                        output.accept(ModItems.createElementStack(75, 111, 75, false));
                        output.accept(ModItems.createElementStack(76, 114, 76, false));
                        output.accept(ModItems.createElementStack(77, 115, 77, false));
                        output.accept(ModItems.createElementStack(78, 117, 78, false));
                        output.accept(ModItems.createElementStack(79, 118, 79, false));
                        output.accept(ModItems.createElementStack(80, 120, 80, false));
                        output.accept(ModItems.createElementStack(81, 123, 81, false));
                        output.accept(ModItems.createElementStack(82, 125, 82, false));
                        output.accept(ModItems.createElementStack(83, 126, 83, false));
                        output.accept(ModItems.createElementStack(84, 125, 84, false));
                        output.accept(ModItems.createElementStack(85, 125, 85, false));
                        output.accept(ModItems.createElementStack(86, 136, 86, false));
                        output.accept(ModItems.createElementStack(87, 136, 87, false));
                        output.accept(ModItems.createElementStack(88, 138, 88, false));
                        output.accept(ModItems.createElementStack(89, 138, 89, false));
                        output.accept(ModItems.createElementStack(90, 142, 90, false));
                        output.accept(ModItems.createElementStack(91, 140, 91, false));
                        output.accept(ModItems.createElementStack(92, 146, 92, false));
                        output.accept(ModItems.createElementStack(93, 144, 93, false));
                        output.accept(ModItems.createElementStack(94, 150, 94, false));
                        output.accept(ModItems.createElementStack(95, 148, 95, false));
                        output.accept(ModItems.createElementStack(96, 151, 96, false));
                        output.accept(ModItems.createElementStack(97, 150, 97, false));
                        output.accept(ModItems.createElementStack(98, 153, 98, false));
                        output.accept(ModItems.createElementStack(99, 153, 99, false));
                        output.accept(ModItems.createElementStack(100, 157, 100, false));
                        output.accept(ModItems.createElementStack(101, 157, 101, false));
                        output.accept(ModItems.createElementStack(102, 157, 102, false));
                        output.accept(ModItems.createElementStack(103, 163, 103, false));
                        output.accept(ModItems.createElementStack(104, 157, 104, false));
                        output.accept(ModItems.createElementStack(105, 157, 105, false));
                        output.accept(ModItems.createElementStack(106, 163, 106, false));
                        output.accept(ModItems.createElementStack(107, 160, 107, false));
                        output.accept(ModItems.createElementStack(108, 161, 108, false));
                        output.accept(ModItems.createElementStack(109, 169, 109, false));
                        output.accept(ModItems.createElementStack(110, 171, 110, false));
                        output.accept(ModItems.createElementStack(111, 171, 111, false));
                        output.accept(ModItems.createElementStack(112, 173, 112, false));
                        output.accept(ModItems.createElementStack(113, 173, 113, false));
                        output.accept(ModItems.createElementStack(114, 175, 114, false));
                        output.accept(ModItems.createElementStack(115, 173, 115, false));
                        output.accept(ModItems.createElementStack(116, 177, 116, false));
                        output.accept(ModItems.createElementStack(117, 177, 117, false));
                        output.accept(ModItems.createElementStack(118, 176, 118, false));
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
