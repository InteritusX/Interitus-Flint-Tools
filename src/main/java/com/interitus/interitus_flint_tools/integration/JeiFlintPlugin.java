package com.interitus.interitus_flint_tools.integration;

import com.interitus.interitus_flint_tools.Interitus_flint_tools;
import java.util.List;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

@JeiPlugin
public final class JeiFlintPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(Interitus_flint_tools.MODID, "jei");
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime runtime) {
        runtime.getIngredientManager().removeIngredientsAtRuntime(
                VanillaTypes.ITEM_STACK, List.of(new ItemStack(Items.WOODEN_PICKAXE)));
    }
}
