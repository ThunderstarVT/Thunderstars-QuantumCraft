package net.thunderstar__vt.quantumcraft.object.item.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.thunderstar__vt.quantumcraft.object.dataComponent.ModDataComponents;
import net.thunderstar__vt.quantumcraft.util.PhysUtils;

public class PhotonItem extends Item {
    public record PhotonData(double energy) {
        public static final PhotonData DEFAULT = new PhotonData(2.5);

        public static final Codec<PhotonData> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.DOUBLE.fieldOf("energy").forGetter(PhotonData::energy)
                ).apply(instance, PhotonData::new)
        );

        public static final StreamCodec<FriendlyByteBuf, PhotonData> STREAM_CODEC =
                StreamCodec.of(
                        (buf, data) -> {
                            buf.writeDouble(data.energy());
                        },
                        buf -> new PhotonData(
                                buf.readDouble()
                        )
                );
    }

    public PhotonItem(Properties properties) {
        super(properties);
    }

    @Override
    public Component getName(ItemStack stack) {
        PhotonData data = stack.getOrDefault(ModDataComponents.PHOTON_DATA.value(), PhotonData.DEFAULT);

        double energy = data.energy();

        return Component.literal(String.format("%s (%s)", super.getName(stack).getString(), PhysUtils.formatUnit(PhysUtils.energyToWavelength(energy), PhysUtils.Unit.METER).getString()));
    }

    @Override
    public void verifyComponentsAfterLoad(ItemStack stack) {
        if (!stack.has(ModDataComponents.PHOTON_DATA.value())) {
            stack.set(ModDataComponents.PHOTON_DATA.value(), PhotonData.DEFAULT);
        }
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        stack.set(ModDataComponents.PHOTON_DATA.value(), PhotonData.DEFAULT);
        return stack;
    }
}
