package com.interitus.interitus_flint_tools;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(Interitus_flint_tools.MODID)
public class Interitus_flint_tools {
    public static final String MODID = "interitus_flint_tools";
    public static final TagKey<Block> NEEDS_FLINT_TOOL = TagKey.create(
            Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(MODID, "needs_flint_tool"));
    public static final TagKey<Block> NEEDS_WOODEN_TOOL = TagKey.create(
            Registries.BLOCK, ResourceLocation.withDefaultNamespace("needs_wooden_tool"));

    private static final Tier FLINT_TIER = new SimpleTier(
            BlockTags.INCORRECT_FOR_STONE_TOOL,
            90,
            3.0F,
            0.5F,
            12,
            () -> Ingredient.of(Items.FLINT)
    );

    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredItem<SwordItem> FLINT_SWORD = ITEMS.register("flint_sword",
            () -> new SwordItem(FLINT_TIER, new Item.Properties()
                    .attributes(SwordItem.createAttributes(FLINT_TIER, 3, -2.4F))));
    public static final DeferredItem<PickaxeItem> FLINT_PICKAXE = ITEMS.register("flint_pickaxe",
            () -> new PickaxeItem(FLINT_TIER, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(FLINT_TIER, 1, -2.8F))));
    public static final DeferredItem<AxeItem> FLINT_AXE = ITEMS.register("flint_axe",
            () -> new AxeItem(FLINT_TIER, new Item.Properties()
                    .attributes(AxeItem.createAttributes(FLINT_TIER, 6, -3.2F))));
    public static final DeferredItem<ShovelItem> FLINT_SHOVEL = ITEMS.register("flint_shovel",
            () -> new ShovelItem(FLINT_TIER, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(FLINT_TIER, 1.5F, -3.0F))));
    public static final DeferredItem<HoeItem> FLINT_HOE = ITEMS.register("flint_hoe",
            () -> new HoeItem(FLINT_TIER, new Item.Properties()
                    .attributes(HoeItem.createAttributes(FLINT_TIER, -0.5F, -2.5F))));

    public Interitus_flint_tools(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        modEventBus.addListener(this::addCreative);
        NeoForge.EVENT_BUS.addListener(this::onHarvestCheck);
    }

    // Blocks that don't natively require a tool for drops need an explicit check
    private void onHarvestCheck(PlayerEvent.HarvestCheck event) {
        var state = event.getTargetBlock();
        if (state.is(NEEDS_WOODEN_TOOL)) {
            event.setCanHarvest(event.getEntity().getMainHandItem().isCorrectToolForDrops(state));
        }
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(FLINT_SWORD);
        } else if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.remove(Items.WOODEN_PICKAXE.getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.accept(FLINT_PICKAXE);
            event.accept(FLINT_AXE);
            event.accept(FLINT_SHOVEL);
            event.accept(FLINT_HOE);
        }
    }
}
