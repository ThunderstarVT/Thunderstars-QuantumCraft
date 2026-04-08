package net.thunderstar__vt.quantumcraft.object.item.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.thunderstar__vt.quantumcraft.object.dataComponent.ModDataComponents;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElementItem extends Item {
    public record AtomData(int protons, int neutrons, int electrons) {
        public static final AtomData DEFAULT = new AtomData(1, 0, 1);

        public static final Codec<AtomData> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.INT.fieldOf("protons").forGetter(AtomData::protons),
                        Codec.INT.fieldOf("neutrons").forGetter(AtomData::neutrons),
                        Codec.INT.fieldOf("electrons").forGetter(AtomData::electrons)
                ).apply(instance, AtomData::new)
        );

        public static final StreamCodec<FriendlyByteBuf, AtomData> STREAM_CODEC =
                StreamCodec.of(
                        (buf, data) -> {
                            buf.writeInt(data.protons());
                            buf.writeInt(data.neutrons());
                            buf.writeInt(data.electrons());
                        },
                        buf -> new AtomData(
                                buf.readInt(),
                                buf.readInt(),
                                buf.readInt()
                        )
                );
    }

    public ElementItem(Properties properties) {
        super(properties);
    }

    @Override
    public Component getName(ItemStack stack) {
        AtomData data = stack.getOrDefault(ModDataComponents.ATOM_DATA.value(), AtomData.DEFAULT);

        int p = data.protons;
        int n = data.neutrons;
        int e = data.electrons;

        int charge = p - e;
        int isotope = p + n;

        boolean isAnti = p < 0 || (p == 0 && n < 0);

        Component elementName = ElementRegistry.getName(p);

        Component anti = isAnti
                ? Component.translatable("text.quantumcraft.anti")
                : Component.empty();

        Component chargeText;
        if (charge == 0) {
            chargeText = Component.translatable("text.quantumcraft.charge.neutral");
        } else if (charge > 0) {
            chargeText = Component.translatable("text.quantumcraft.charge.positive", charge);
        } else {
            chargeText = Component.translatable("text.quantumcraft.charge.negative", Math.abs(charge));
        }

        return Component.translatable(
                "item.quantumcraft.element.format",
                anti,
                elementName,
                isotope,
                chargeText
        );
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {

    }

    @Override
    public void verifyComponentsAfterLoad(ItemStack stack) {
        if (!stack.has(ModDataComponents.ATOM_DATA.value())) {
            stack.set(ModDataComponents.ATOM_DATA.value(), AtomData.DEFAULT);
        }
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        stack.set(ModDataComponents.ATOM_DATA.value(), AtomData.DEFAULT);
        return stack;
    }

    public class ElementRegistry {
        private static final Map<Integer, String> NAMES = new HashMap<>();

        static {
            NAMES.put(0, "neutronium");
            NAMES.put(1, "hydrogen");
            NAMES.put(2, "");
            NAMES.put(3, "");
            NAMES.put(4, "");
            NAMES.put(5, "");
            NAMES.put(6, "");
            NAMES.put(7, "");
            NAMES.put(8, "");
            NAMES.put(9, "");
            NAMES.put(10, "");
            NAMES.put(11, "");
            NAMES.put(12, "");
            NAMES.put(13, "");
            NAMES.put(14, "");
            NAMES.put(15, "");
            NAMES.put(16, "");
            NAMES.put(17, "");
            NAMES.put(18, "");
            NAMES.put(19, "");
            NAMES.put(20, "");
            NAMES.put(21, "");
            NAMES.put(22, "");
            NAMES.put(23, "");
            NAMES.put(24, "");
            NAMES.put(25, "");
            NAMES.put(26, "");
            NAMES.put(27, "");
            NAMES.put(28, "");
            NAMES.put(29, "");
            NAMES.put(30, "");
            NAMES.put(31, "");
            NAMES.put(32, "");
            NAMES.put(33, "");
            NAMES.put(34, "");
            NAMES.put(35, "");
            NAMES.put(36, "");
            NAMES.put(37, "");
            NAMES.put(38, "");
            NAMES.put(39, "");
            NAMES.put(40, "");
            NAMES.put(41, "");
            NAMES.put(42, "");
            NAMES.put(43, "");
            NAMES.put(44, "");
            NAMES.put(45, "");
            NAMES.put(46, "");
            NAMES.put(47, "");
            NAMES.put(48, "");
            NAMES.put(49, "");
            NAMES.put(50, "");
            NAMES.put(51, "");
            NAMES.put(52, "");
            NAMES.put(53, "");
            NAMES.put(54, "");
            NAMES.put(55, "");
            NAMES.put(56, "");
            NAMES.put(57, "");
            NAMES.put(58, "");
            NAMES.put(59, "");
            NAMES.put(60, "");
            NAMES.put(61, "");
            NAMES.put(62, "");
            NAMES.put(63, "");
            NAMES.put(64, "");
            NAMES.put(65, "");
            NAMES.put(66, "");
            NAMES.put(67, "");
            NAMES.put(68, "");
            NAMES.put(69, "");
            NAMES.put(70, "");
            NAMES.put(71, "");
            NAMES.put(72, "");
            NAMES.put(73, "");
            NAMES.put(74, "");
            NAMES.put(75, "");
            NAMES.put(76, "");
            NAMES.put(77, "");
            NAMES.put(78, "");
            NAMES.put(79, "");
            NAMES.put(80, "");
            NAMES.put(81, "");
            NAMES.put(82, "");
            NAMES.put(83, "");
            NAMES.put(84, "");
            NAMES.put(85, "");
            NAMES.put(86, "");
            NAMES.put(87, "");
            NAMES.put(88, "");
            NAMES.put(89, "");
            NAMES.put(90, "");
            NAMES.put(91, "");
            NAMES.put(92, "");
            NAMES.put(93, "");
            NAMES.put(94, "");
            NAMES.put(95, "");
            NAMES.put(96, "");
            NAMES.put(97, "");
            NAMES.put(98, "");
            NAMES.put(99, "");
            NAMES.put(100, "");
            NAMES.put(101, "");
            NAMES.put(102, "");
            NAMES.put(103, "");
            NAMES.put(104, "");
            NAMES.put(105, "");
            NAMES.put(106, "");
            NAMES.put(107, "");
            NAMES.put(108, "");
            NAMES.put(109, "");
            NAMES.put(110, "");
            NAMES.put(111, "");
            NAMES.put(112, "");
            NAMES.put(113, "");
            NAMES.put(114, "");
            NAMES.put(115, "");
            NAMES.put(116, "");
            NAMES.put(117, "");
            NAMES.put(118, "");
        }

        public static Component getName(int protons) {
            //if (NAMES.containsKey(protons)) return Component.translatable("element.quantumcraft." + NAMES.get(protons));

            return getSystematicName(protons);
        }

        private static final String[] ROOTS = {
                "nil",
                "un",
                "bi",
                "tri",
                "quad",
                "pent",
                "hex",
                "sept",
                "oct",
                "enn"
        };

        public static Component getSystematicName(int protons) {
            return Component.literal(buildSystematicName(protons));
        }

        private static String buildSystematicName(int protons) {
            String digits = String.valueOf(protons);
            StringBuilder name = new StringBuilder();

            for (char c : digits.toCharArray()) {
                int digit = c - '0';
                name.append(ROOTS[digit]);
            }

            name.append(name.toString().endsWith("i") ? "um" : "ium");

            String nameStr = name.toString();

            return nameStr.substring(0, 1).toUpperCase() + nameStr.substring(1).toLowerCase();
        }
    }
}
