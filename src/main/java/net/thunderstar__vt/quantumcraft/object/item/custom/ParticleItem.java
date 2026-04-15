package net.thunderstar__vt.quantumcraft.object.item.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.thunderstar__vt.quantumcraft.object.dataComponent.ModDataComponents;
import net.thunderstar__vt.quantumcraft.object.keybind.ModKeybinds;
import net.thunderstar__vt.quantumcraft.util.PhysUtils;
import net.thunderstar__vt.quantumcraft.util.QuantUtils;
import net.thunderstar__vt.quantumcraft.util.UtilTypes;

import java.util.List;

public class ParticleItem extends Item {
    public enum ParticleType {
        // leptons
        ELECTRON(510_998.950_69, -1, "electron", new UtilTypes.StringSubSup("e")),
        MUON(105_658_375.5, -1, "muon", new UtilTypes.StringSubSup("μ")),
        TAU(1_776_860_000.0, -1, "tau", new UtilTypes.StringSubSup("τ")),

        // lepton neutrinos, mass being average of upper and lower bounds on Wikipedia
        ELECTRON_NEUTRINO(0.4, 0, "electron_neutrino", new UtilTypes.StringSubSup("ν", "e")),
        MUON_NEUTRINO(950_000.0, 0, "muon_neutrino", new UtilTypes.StringSubSup("ν", "μ")),
        TAU_NEUTRINO(9_100_000.0, 0, "tau_neutrino", new UtilTypes.StringSubSup("ν", "τ")),

        // mesons
        CHARGED_PION(139_570_180.0, 1, "pion", new UtilTypes.StringSubSup("π")),
        NEUTRAL_PION(134_976_600.0, 0, "pion", new UtilTypes.StringSubSup("π", "", "0"), false),

        ETA_MESON(547_853_000.0, 0, "eta_meson", new UtilTypes.StringSubSup("η"), false),
        ETA_PRIME_MESON(957_660_000.0, 0, "eta_prime_meson", new UtilTypes.StringSubSup("η'"), false),
        CHARMED_ETA_MESON(2_980_300_000.0, 0, "charmed_eta_meson", new UtilTypes.StringSubSup("η", "c"), false),
        BOTTOM_ETA_MESON(9_300_000_000.0, 0, "bottom_eta_meson", new UtilTypes.StringSubSup("η", "b"), false),

        CHARGED_KAON(493_677_000.0, 1, "kaon", new UtilTypes.StringSubSup("K")),
        NEUTRAL_KAON(497_614_000.0, 0, "kaon", new UtilTypes.StringSubSup("K", "", "0")),
        KAON_SHORT(497_614_000.0, 0, "kaon_short", new UtilTypes.StringSubSup("K", "S", "0"), false),
        KAON_LONG(497_614_000.0, 0, "kaon_long", new UtilTypes.StringSubSup("K", "L", "0"), false),

        CHARGED_D_MESON(1_869_620_000.0, 1, "d_meson", new UtilTypes.StringSubSup("D")),
        NEUTRAL_D_MESON(1_864_840_000.0, 0, "d_meson", new UtilTypes.StringSubSup("D", "", "0")),
        STRANGE_D_MESON(1_968_490_000.0, 1, "strange_d_meson", new UtilTypes.StringSubSup("D", "s")),

        CHARGED_B_MESON(5_279_150_000.0, 1, "b_meson", new UtilTypes.StringSubSup("B")),
        NEUTRAL_B_MESON(5_279_530_000.0, 0, "b_meson", new UtilTypes.StringSubSup("B", "", "0")),
        STRANGE_B_MESON(5_366_300_000.0, 0, "strange_b_meson", new UtilTypes.StringSubSup("B", "s", "0")),
        CHARMED_B_MESON(6_276_000_000.0, 1, "charmed_b_meson", new UtilTypes.StringSubSup("B", "c")),

        VECTOR_CHARGED_RHO_MESON(775_400_000.0, 1, "rho_meson", new UtilTypes.StringSubSup("ρ")),
        VECTOR_NEUTRAL_RHO_MESON(775_490_000.0, 0, "rho_meson", new UtilTypes.StringSubSup("ρ", "", "0"), false),

        VECTOR_OMEGA_MESON(782_650_000.0, 0, "omega_meson", new UtilTypes.StringSubSup("ω"), false),

        VECTOR_PHI_MESON(1_019_445_000.0, 0, "phi_meson", new UtilTypes.StringSubSup("ϕ"), false),

        VECTOR_PSI_MESON(3_096_916_000.0, 0, "psi_meson", new UtilTypes.StringSubSup("ψ"), false),

        VECTOR_UPSILON_MESON(9_460_300_000.0, 0, "upsilon_meson", new UtilTypes.StringSubSup("Υ"), false),

        VECTOR_CHARGED_KAON(891_660_000.0, 1, "kaon", new UtilTypes.StringSubSup("K*")),
        VECTOR_NEUTRAL_KAON(896_000_000.0, 0, "kaon", new UtilTypes.StringSubSup("K*", "", "0")),

        VECTOR_CHARGED_D_MESON(2_010_270_000.0, 1, "d_meson", new UtilTypes.StringSubSup("D*")),
        VECTOR_NEUTRAL_D_MESON(2_006_970_000.0, 0, "d_meson", new UtilTypes.StringSubSup("D*", "", "0")),
        VECTOR_STRANGE_D_MESON(2_112_300_000.0, 1, "strange_d_meson", new UtilTypes.StringSubSup("D*", "s")),

        VECTOR_CHARGED_B_MESON(5_325_100_000.0, 1, "b_meson", new UtilTypes.StringSubSup("B*")),
        VECTOR_NEUTRAL_B_MESON(5_325_100_000.0, 0, "b_meson", new UtilTypes.StringSubSup("B*", "", "0")),
        VECTOR_STRANGE_B_MESON(5_412_800_000.0, 0, "strange_b_meson", new UtilTypes.StringSubSup("B*", "s", "0")),

        // baryons
        PROTON(938_272_088.816, 1, "proton", new UtilTypes.StringSubSup("p")),
        NEUTRON(939_565_420.52, 0, "neutron", new UtilTypes.StringSubSup("n"));


        public final double mass;
        public final int charge;
        public final String translation_key;
        public final UtilTypes.StringSubSup symbol;
        public final boolean hasAnti;

        ParticleType(double mass, int charge, String name, UtilTypes.StringSubSup symbol) {
            this.mass = mass;
            this.charge = charge;
            this.translation_key = name;
            this.symbol = symbol;
            this.hasAnti = true;
        }

        ParticleType(double mass, int charge, String name, UtilTypes.StringSubSup symbol, boolean hasAnti) {
            this.mass = mass;
            this.charge = charge;
            this.translation_key = name;
            this.symbol = symbol;
            this.hasAnti = hasAnti;
        }

        public static final Codec<ParticleType> CODEC = Codec.STRING.xmap(
                ParticleType::valueOf,
                ParticleType::name
        );
    }

    public record ParticleData(ParticleType type, boolean anti) {
        public static final ParticleData DEFAULT = new ParticleData(ParticleType.ELECTRON, false);

        public static final Codec<ParticleData> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        ParticleType.CODEC.fieldOf("type").forGetter(ParticleData::type),
                        Codec.BOOL.fieldOf("anti").forGetter(ParticleData::anti)
                ).apply(instance, ParticleData::new)
        );

        public static final StreamCodec<FriendlyByteBuf, ParticleData> STREAM_CODEC = StreamCodec.of(
                (buf, data) -> {
                    buf.writeEnum(data.type());
                    buf.writeBoolean(data.anti());
                },
                buf -> new ParticleData(
                        buf.readEnum(ParticleType.class),
                        buf.readBoolean()
                )
        );
    }

    public ParticleItem(Properties properties) {
        super(properties);
    }

    @Override
    public Component getName(ItemStack stack) {
        ParticleData data = stack.getOrDefault(ModDataComponents.PARTICLE_DATA.value(), ParticleData.DEFAULT);

        Component anti = data.anti() && data.type().hasAnti
                ? Component.translatable("text.quantumcraft.anti")
                : Component.empty();

        return Component.translatable("item.quantumcraft.particle.format",
                anti, Component.translatable("item.quantumcraft.particle." + data.type.translation_key));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (ModKeybinds.isDown(ModKeybinds.SHOW_DETAILS)) {
            ParticleData data = stack.getOrDefault(ModDataComponents.PARTICLE_DATA.value(), ParticleData.DEFAULT);

            int charge = data.anti() ? -data.type().charge : data.type().charge;
            double mass = data.type().mass;

            tooltipComponents.add(Component.translatable("tooltip.quantumcraft.particle.mass",
                    PhysUtils.formatUnit(mass, PhysUtils.Unit.ELECTRON_VOLT, 'e')));
            tooltipComponents.add(Component.translatable("tooltip.quantumcraft.particle.charge",
                    Component.literal(String.format("§e%s§r", (charge > 0 ? "+" : "") + charge))));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.quantumcraft.hold_shift",
                    Component.keybind("key.quantumcraft.show_details").withStyle(ChatFormatting.ITALIC)));
        }
    }

    @Override
    public void verifyComponentsAfterLoad(ItemStack stack) {
        if (!stack.has(ModDataComponents.PARTICLE_DATA.value())) {
            stack.set(ModDataComponents.PARTICLE_DATA.value(), ParticleData.DEFAULT);
        }
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        stack.set(ModDataComponents.PARTICLE_DATA.value(), ParticleData.DEFAULT);
        return stack;
    }
}
