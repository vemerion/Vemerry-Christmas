package mod.vemerion.vemerrychristmas.block;

import mod.vemerion.vemerrychristmas.blockentity.ChristmasPresentBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class ChristmasPresentBlock extends Block implements BlockEntityProvider {
	public static final BooleanProperty FULL = BooleanProperty.of("full");


	private static final VoxelShape SHAPE = Block.createCuboidShape(4, 0, 4, 12, 8, 12);

	public ChristmasPresentBlock(Settings settings) {
		super(settings);
		this.setDefaultState(stateManager.getDefaultState().with(FULL, false));
	}
	
	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
			BlockHitResult hit) {
		if (state.get(FULL).booleanValue())
			return ActionResult.PASS;
		
		if (!world.isClient) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof ChristmasPresentBlockEntity) {
				ChristmasPresentBlockEntity presentEntity = (ChristmasPresentBlockEntity) blockEntity;
				presentEntity.fill(player, hand);
			}
		}
		return ActionResult.SUCCESS;
	}

	@Override
	public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
		if (!state.isOf(newState.getBlock())) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof ChristmasPresentBlockEntity) {
				((ChristmasPresentBlockEntity) blockEntity).drop();
			}

			super.onStateReplaced(state, world, pos, newState, moved);
		}
	}
	
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}
	
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FULL);
	}

	@Override
	public BlockEntity createBlockEntity(BlockView world) {
		return new ChristmasPresentBlockEntity();
	}
}
