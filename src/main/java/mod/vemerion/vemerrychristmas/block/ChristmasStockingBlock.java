package mod.vemerion.vemerrychristmas.block;

import java.util.EnumMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class ChristmasStockingBlock extends Block {
	public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
	public static final Map<Direction, VoxelShape> SHAPES = new EnumMap<>(
			ImmutableMap.of(Direction.NORTH, Block.createCuboidShape(4D, 0, 13.0D, 12D, 15.0D, 16.0D), Direction.SOUTH,
					Block.createCuboidShape(4D, 0, 0.0D, 12D, 15.0D, 3.0D), Direction.WEST,
					Block.createCuboidShape(13.0D, 0, 4D, 16.0D, 15.0D, 12D), Direction.EAST,
					Block.createCuboidShape(0.0D, 0, 4D, 3.0D, 15.0D, 12D)));

	public ChristmasStockingBlock(Settings settings) {
		super(settings);
		this.setDefaultState(stateManager.getDefaultState().with(FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPES.get(state.get(FACING));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState rotate(BlockState state, BlockRotation rotation) {
		return state.with(FACING, rotation.rotate(state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, BlockMirror mirror) {
		return state.rotate(mirror.getRotation(state.get(FACING)));
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState,
			WorldAccess world, BlockPos pos, BlockPos posFrom) {
		return direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos)
				? Blocks.AIR.getDefaultState()
				: state;
	}

	@Override
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		Direction direction = state.get(FACING);
		BlockPos blockPos = pos.offset(direction.getOpposite());
		return world.getBlockState(blockPos).isSideSolidFullSquare(world, blockPos, direction);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		BlockState blockState = getDefaultState();
		Direction[] directions = ctx.getPlacementDirections();

		for (int i = 0; i < directions.length; i++) {
			Direction direction = directions[i];
			if (direction.getAxis().isHorizontal()) {
				blockState = blockState.with(FACING, direction.getOpposite());
				if (blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
					return blockState;
				}
			}
		}

		return null;
	}

}
