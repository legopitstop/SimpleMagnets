package dev.lpsmods.magnet.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MagnetBlock extends DirectionalBlock {
    public static final MapCodec<MagnetBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return instance.group(
                Codec.FLOAT.fieldOf("radius").forGetter((MagnetBlock o) -> o.radius),
                Codec.INT.fieldOf("delay").forGetter((MagnetBlock o) -> o.delay),
                createSettingsCodec()
        ).apply(instance, MagnetBlock::new);
    });
    public static final BooleanProperty POWERED = BooleanProperty.create("powered");
    public float radius;
    public int delay;

    public MagnetBlock(float radius, int delay, BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.NORTH)).setValue(POWERED, false));
        this.radius = radius;
        this.delay = delay;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return (BlockState)((BlockState)this.defaultBlockState().setValue(FACING, ctx.getNearestLookingDirection().getOpposite())).setValue(POWERED, false);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (level.isClientSide) {
            return;
        }
        boolean bl = state.getValue(POWERED);
        if (bl != level.isReceivingRedstonePower(pos)) {
            if (bl) {
                level.scheduleTick(pos, this, this.delay);
            } else {
                level.setBlock(pos, (BlockState)state.cycle(POWERED), Block.NOTIFY_LISTENERS);
                this.teleport(state, level, pos);
            }
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(POWERED).booleanValue() && !world.isReceivingRedstonePower(pos)) {
            world.setBlockState(pos, (BlockState)state.cycle(POWERED), Block.NOTIFY_LISTENERS);
        }
    }

    public Vec3d teleportPos(BlockState state, BlockPos pos) {
        BlockPos pos2 = pos.offset(state.get(FACING));
        return new Vec3d(pos2.getX(), pos2.getY(), pos2.getZ());
    }

    public void teleport(BlockState state, World world, BlockPos pos) {
        Vec3d vecPos = new Vec3d(pos.getX(), pos.getY(), pos.getZ());
        List<ItemEntity> items = world.getEntitiesByType(EntityType.ITEM, Box.of(vecPos, this.radius*2, this.radius*2, this.radius*2), (e) -> {
            return true;
        });
        Vec3d vecPos2 = this.teleportPos(state, pos).add(0.5, 0, 0.5);
        for (ItemEntity item : items) {
            item.setPosition(vecPos2);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Override
    protected MapCodec<? extends DirectionalBlock> codec() {
        return CODEC;
    }
}
