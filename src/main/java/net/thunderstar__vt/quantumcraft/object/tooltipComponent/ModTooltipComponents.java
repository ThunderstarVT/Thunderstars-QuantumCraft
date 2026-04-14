package net.thunderstar__vt.quantumcraft.object.tooltipComponent;

import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.thunderstar__vt.quantumcraft.object.tooltipComponent.custom.ClientElementTooltip;
import net.thunderstar__vt.quantumcraft.object.tooltipComponent.custom.ElementTooltip;

public class ModTooltipComponents {
    public static void register(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(ElementTooltip.class, ClientElementTooltip::new);
    }
}
