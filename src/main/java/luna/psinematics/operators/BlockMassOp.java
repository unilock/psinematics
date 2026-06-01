package luna.psinematics.operators;

import dev.ryanhcode.sable.physics.config.block_properties.PhysicsBlockPropertyHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import vazkii.psi.api.internal.Vector3;
import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.param.ParamVector;
import vazkii.psi.api.spell.piece.PieceOperator;

public class BlockMassOp extends PieceOperator{
	
	private static final SpellParam<Vector3> position = new ParamVector(SpellParam.GENERIC_NAME_POSITION, SpellParam.BLUE, false, false);
	
	public BlockMassOp(Spell spell){
		super(spell);
	}
	
	public void initParams(){
		addParam(position);
	}
	
	public Class<?> getEvaluationType(){
		return Double.class;
	}
	
	public Double execute(SpellContext context) throws SpellRuntimeException{
		// checks validity and nullity
		Level level = context.focalPoint.level();
		BlockPos pos = SpellHelpers.getBlockPos(this, context, position, false, false);
		return PhysicsBlockPropertyHelper.getMass(level, pos, level.getBlockState(pos));
	}
}