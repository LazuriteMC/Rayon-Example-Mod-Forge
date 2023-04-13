package dev.lazurite.rayon.example.item;

import dev.lazurite.rayon.example.RayonExampleMod;
import dev.lazurite.rayon.example.entity.StoneBlockEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;

import java.util.Objects;

/**
 * A test item that spawns a {@link StoneBlockEntity}.
 */
public class WandItem extends Item {

    public WandItem(Item.Properties settings) {
        super(settings);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player user, InteractionHand hand) {
        var itemStack = user.getItemInHand(hand);
        var hitResult = getPlayerPOVHitResult(level, user, ClipContext.Fluid.NONE);

        if (!level.isClientSide()) {
            var entity = Objects.requireNonNull(RayonExampleMod.STONE_BLOCK_ENTITY.get().create(level));
            entity.moveTo(hitResult.getLocation());
            entity.setOldPosAndRot();
            level.addFreshEntity(entity);
            return InteractionResultHolder.success(itemStack);
        }

        return InteractionResultHolder.pass(itemStack);
    }

}