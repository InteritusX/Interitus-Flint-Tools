package com.interitus.interitus_flint_tools.integration;

import com.interitus.interitus_flint_tools.Interitus_flint_tools;
import java.util.List;
import net.minecraft.world.item.Items;
import snownee.jade.addon.harvest.HarvestToolProvider;
import snownee.jade.addon.harvest.SimpleToolHandler;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.JadeIds;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public final class JadeFlintToolProvider implements IWailaPlugin {
    @Override
    public void registerClient(IWailaClientRegistration registration) {
        // Re-registering under Jade's own pickaxe id replaces the vanilla handler in place,
        // so the flint pickaxe is picked before stone for blocks that need it
        HarvestToolProvider.registerHandler(SimpleToolHandler.create(JadeIds.JADE("pickaxe"), List.of(
                Items.WOODEN_PICKAXE,
                Items.GOLDEN_PICKAXE,
                Interitus_flint_tools.FLINT_PICKAXE.get(),
                Items.STONE_PICKAXE,
                Items.IRON_PICKAXE,
                Items.DIAMOND_PICKAXE,
                Items.NETHERITE_PICKAXE)));
    }
}
