package net.thunderstar__vt.quantumcraft.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.thunderstar__vt.quantumcraft.util.Reference;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Reference.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }

    private void cubeAllWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.value(), cubeAll(deferredBlock.get()));
    }
}
