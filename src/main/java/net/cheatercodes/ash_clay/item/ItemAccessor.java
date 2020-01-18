package net.cheatercodes.ash_clay.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.RayTraceContext;
import net.minecraft.world.World;

public abstract class ItemAccessor extends Item {
    public ItemAccessor(Settings settings) {
        super(settings);
    }

    public static HitResult rayTrace(World world, PlayerEntity player, RayTraceContext.FluidHandling fluidHandling) {
        return Item.rayTrace(world, player, fluidHandling);
    }
}
