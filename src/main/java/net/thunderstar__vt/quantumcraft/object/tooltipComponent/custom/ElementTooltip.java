package net.thunderstar__vt.quantumcraft.object.tooltipComponent.custom;

import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.thunderstar__vt.quantumcraft.object.item.custom.ElementItem;

public record ElementTooltip(ElementItem.AtomData data) implements TooltipComponent {}

