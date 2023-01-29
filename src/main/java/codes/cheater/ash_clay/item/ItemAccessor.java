package codes.cheater.ash_clay.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public abstract class ItemAccessor extends Item {
    public ItemAccessor(Settings settings) {
        super(settings);
    }

    public static BlockHitResult rayTrace(World world, PlayerEntity player, RaycastContext.FluidHandling fluidHandling) {
        return Item.raycast(world, player, fluidHandling);
    }
}
