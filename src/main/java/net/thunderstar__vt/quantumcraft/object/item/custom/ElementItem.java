package net.thunderstar__vt.quantumcraft.object.item.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.thunderstar__vt.quantumcraft.object.dataComponent.ModDataComponents;
import net.thunderstar__vt.quantumcraft.util.ChemUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElementItem extends Item {
    public record AtomData(int protons, int neutrons, int electrons, boolean muonic) {
        public static final AtomData DEFAULT = new AtomData(1, 0, 1, false);

        public static final Codec<AtomData> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.INT.fieldOf("protons").forGetter(AtomData::protons),
                        Codec.INT.fieldOf("neutrons").forGetter(AtomData::neutrons),
                        Codec.INT.fieldOf("electrons").forGetter(AtomData::electrons),
                        Codec.BOOL.fieldOf("muonic").forGetter(AtomData::muonic)
                ).apply(instance, AtomData::new)
        );

        public static final StreamCodec<FriendlyByteBuf, AtomData> STREAM_CODEC =
                StreamCodec.of(
                        (buf, data) -> {
                            buf.writeInt(data.protons());
                            buf.writeInt(data.neutrons());
                            buf.writeInt(data.electrons());
                            buf.writeBoolean(data.muonic());
                        },
                        buf -> new AtomData(
                                buf.readInt(),
                                buf.readInt(),
                                buf.readInt(),
                                buf.readBoolean()
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

        Component muonic = data.muonic
                ? Component.translatable("text.quantumcraft.muonic")
                : Component.empty();

        Component anti = isAnti
                ? Component.translatable("text.quantumcraft.anti")
                : Component.empty();

        Component chargeText;
        if (charge == 0) {
            chargeText = Component.translatable("text.quantumcraft.charge.neutral");
        } else if (charge > 0) {
            chargeText = Component.translatable("text.quantumcraft.charge.positive", charge);
        } else {
            chargeText = Component.translatable("text.quantumcraft.charge.negative", -charge);
        }

        return Component.translatable(
                "item.quantumcraft.element.format",
                muonic,
                anti,
                elementName,
                isotope,
                chargeText
        );
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) {
            AtomData data = stack.getOrDefault(ModDataComponents.ATOM_DATA.value(), AtomData.DEFAULT);

            tooltipComponents.add(Component.translatable("tooltip.quantumcraft.element.size",
                    ChemUtils.formatUnit(ChemUtils.computeAtomSize(data), ChemUtils.Unit.ANGSTROM)));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.quantumcraft.hold_shift"));
        }
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
            NAMES.put(2, "helium");
            NAMES.put(3, "lithium");
            NAMES.put(4, "beryllium");
            NAMES.put(5, "boron");
            NAMES.put(6, "carbon");
            NAMES.put(7, "nitrogen");
            NAMES.put(8, "oxygen");
            NAMES.put(9, "fluorine");
            NAMES.put(10, "neon");
            NAMES.put(11, "sodium");
            NAMES.put(12, "magnesium");
            NAMES.put(13, "aluminum");
            NAMES.put(14, "silicon");
            NAMES.put(15, "phosphorus");
            NAMES.put(16, "sulfur");
            NAMES.put(17, "chlorine");
            NAMES.put(18, "argon");
            NAMES.put(19, "potassium");
            NAMES.put(20, "calcium");
            NAMES.put(21, "scandium");
            NAMES.put(22, "titanium");
            NAMES.put(23, "vanadium");
            NAMES.put(24, "chromium");
            NAMES.put(25, "manganese");
            NAMES.put(26, "iron");
            NAMES.put(27, "cobalt");
            NAMES.put(28, "nickel");
            NAMES.put(29, "copper");
            NAMES.put(30, "zinc");
            NAMES.put(31, "gallium");
            NAMES.put(32, "germanium");
            NAMES.put(33, "arsenic");
            NAMES.put(34, "selenium");
            NAMES.put(35, "bromine");
            NAMES.put(36, "krypton");
            NAMES.put(37, "rubidium");
            NAMES.put(38, "strontium");
            NAMES.put(39, "yttrium");
            NAMES.put(40, "zirconium");
            NAMES.put(41, "niobium");
            NAMES.put(42, "molybdenum");
            NAMES.put(43, "technetium");
            NAMES.put(44, "ruthenium");
            NAMES.put(45, "rhodium");
            NAMES.put(46, "palladium");
            NAMES.put(47, "silver");
            NAMES.put(48, "cadmium");
            NAMES.put(49, "indium");
            NAMES.put(50, "tin");
            NAMES.put(51, "antimony");
            NAMES.put(52, "tellurium");
            NAMES.put(53, "iodine");
            NAMES.put(54, "xenon");
            NAMES.put(55, "caesium");
            NAMES.put(56, "barium");
            NAMES.put(57, "lanthanum");
            NAMES.put(58, "cerium");
            NAMES.put(59, "praseodymium");
            NAMES.put(60, "neodymium");
            NAMES.put(61, "promethium");
            NAMES.put(62, "samarium");
            NAMES.put(63, "europium");
            NAMES.put(64, "gadolinium");
            NAMES.put(65, "terbium");
            NAMES.put(66, "dysprosium");
            NAMES.put(67, "holmium");
            NAMES.put(68, "erbium");
            NAMES.put(69, "thulium");
            NAMES.put(70, "ytterbium");
            NAMES.put(71, "lutetium");
            NAMES.put(72, "hafnium");
            NAMES.put(73, "tantalum");
            NAMES.put(74, "tungsten");
            NAMES.put(75, "rhenium");
            NAMES.put(76, "osmium");
            NAMES.put(77, "iridium");
            NAMES.put(78, "platinum");
            NAMES.put(79, "gold");
            NAMES.put(80, "mercury");
            NAMES.put(81, "thallium");
            NAMES.put(82, "lead");
            NAMES.put(83, "bismuth");
            NAMES.put(84, "polonium");
            NAMES.put(85, "astatine");
            NAMES.put(86, "radon");
            NAMES.put(87, "francium");
            NAMES.put(88, "radium");
            NAMES.put(89, "actinium");
            NAMES.put(90, "thorium");
            NAMES.put(91, "protactinium");
            NAMES.put(92, "uranium");
            NAMES.put(93, "neptunium");
            NAMES.put(94, "plutonium");
            NAMES.put(95, "americium");
            NAMES.put(96, "curium");
            NAMES.put(97, "berkelium");
            NAMES.put(98, "californium");
            NAMES.put(99, "einsteinium");
            NAMES.put(100, "fermium");
            NAMES.put(101, "mendelevium");
            NAMES.put(102, "nobelium");
            NAMES.put(103, "lawrencium");
            NAMES.put(104, "rutherfordium");
            NAMES.put(105, "dubnium");
            NAMES.put(106, "seaborgium");
            NAMES.put(107, "bohrium");
            NAMES.put(108, "hassium");
            NAMES.put(109, "meitnerium");
            NAMES.put(110, "darmstadtium");
            NAMES.put(111, "roentgenium");
            NAMES.put(112, "copernicium");
            NAMES.put(113, "nihonium");
            NAMES.put(114, "flerovium");
            NAMES.put(115, "moscovium");
            NAMES.put(116, "livermorium");
            NAMES.put(117, "tennessine");
            NAMES.put(118, "oganesson");
        }

        public static Component getName(int protons) {
            if (NAMES.containsKey(protons)) return Component.translatable("element.quantumcraft." + NAMES.get(protons));

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
