package dev.lazurite.rayon.example;

import dev.lazurite.rayon.example.entity.StoneBlockEntity;
import dev.lazurite.rayon.example.render.StoneBlockEntityModel;
import dev.lazurite.rayon.example.render.StoneBlockEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class RayonExampleModClient {

    @SubscribeEvent
    public static void onInitializeClient(FMLClientSetupEvent event) {
        EntityRenderers.register((EntityType<StoneBlockEntity>) RayonExampleMod.STONE_BLOCK_ENTITY.get(), (context) -> new StoneBlockEntityRenderer(context, new StoneBlockEntityModel(12, 4, 12)));
    }

}
