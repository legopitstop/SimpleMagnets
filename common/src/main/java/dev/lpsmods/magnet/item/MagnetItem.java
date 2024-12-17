package dev.lpsmods.magnet.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class MagnetItem extends ToolItem {
    public double radius;
    public int delay;

    public MagnetItem(ToolMaterial toolMaterial, Settings settings,double radius, int delay) {
        super(toolMaterial, settings);
        this.radius = radius;
        this.delay = delay;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.getItemCooldownManager().set(this, this.delay);
        ItemStack mainHand = user.getStackInHand(hand);
        if (world.isClient) {return TypedActionResult.pass(mainHand);}
        mainHand.damage(1, user, (player) -> user.sendToolBreakStatus(user.getActiveHand()));
        List<ItemEntity> items = world.getEntitiesByType(EntityType.ITEM, Box.of(user.getPos(), this.radius*2, this.radius*2, this.radius*2), (e) -> {
            return true;
        });
        for (ItemEntity item : items) {
            item.setPosition(user.getPos());
        }
        return TypedActionResult.success(mainHand);
    }
}
