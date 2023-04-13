package dev.lazurite.rayon.example;

import dev.lazurite.rayon.example.entity.StoneBlockEntity;
import dev.lazurite.rayon.example.item.WandItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(RayonExampleMod.MODID)
public class RayonExampleMod {

    public static final String MODID = "rayon_example_mod_forge";

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MODID);
    public static final RegistryObject<Item> WAND_ITEM = ITEMS.register("wand_item", () -> new WandItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<EntityType<? extends LivingEntity>> STONE_BLOCK_ENTITY = ENTITIES.register("stone_block_entity",
            () -> EntityType.Builder.of(StoneBlockEntity::new, MobCategory.MISC)
                    .sized(0.75f, 0.25f)
                    .setTrackingRange(80)
                    .build(new ResourceLocation(MODID, "stone_block_entity").toString()));

    public RayonExampleMod() {
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
        FMLJavaModLoadingContext.get().getModEventBus().register(RayonExampleModClient.class);
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @SubscribeEvent
    public void onRegisterAttributes(EntityAttributeCreationEvent event) {
        event.put(STONE_BLOCK_ENTITY.get(), LivingEntity.createLivingAttributes().build());
    }

    @SubscribeEvent
    public void onRegisterCreativeTab(CreativeModeTabEvent.BuildContents event) {
        if (event.getTab() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(WAND_ITEM);
        }
    }

}
