package net.thunderstar__vt.quantumcraft.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.thunderstar__vt.quantumcraft.object.item.ModItems;
import net.thunderstar__vt.quantumcraft.util.Reference;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Reference.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.ELEMENT.value());
        basicItem(ModItems.PHOTON.value());
    }
}
