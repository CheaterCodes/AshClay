package net.cheatercodes.ash_clay.block.entity;

import net.cheatercodes.ash_clay.block.AshClayBlocks;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.FurnaceScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class AshClayFurnaceBlockEntity extends AbstractFurnaceBlockEntity {
    public AshClayFurnaceBlockEntity() {
        super(AshClayBlocks.ASH_CLAY_FURNACE_BLOCK_ENTITY_TYPE, RecipeType.SMELTING);
    }

    @Override
    protected Text getContainerName() {
        return new TranslatableText("container.ash_clay_furnace");
    }

    @Override
    protected ScreenHandler createScreenHandler(int i, PlayerInventory playerInventory) {
        return new FurnaceScreenHandler(i, playerInventory, this, this.propertyDelegate);
    }
}
