package luna.psinematics.operators;

import dev.ryanhcode.sable.companion.SubLevelAccess;
import dev.ryanhcode.sable.sublevel.ServerSubLevel;
import luna.psinematics.PhySpellHelper;
import luna.psinematics.SubLevelParam;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.api.spell.SpellParam;
import vazkii.psi.api.spell.SpellRuntimeException;
import vazkii.psi.api.spell.piece.PieceOperator;

public class SubLevelMassOp extends PieceOperator{
	
	private static final SpellParam<SubLevelAccess> target = new SubLevelParam(SpellParam.GENERIC_NAME_TARGET, SpellParam.RED, false);
	
	public SubLevelMassOp(Spell spell){
		super(spell);
	}
	
	public void initParams(){
		addParam(target);
	}
	
	public Class<?> getEvaluationType(){
		return Double.class;
	}
	
	public Double execute(SpellContext context) throws SpellRuntimeException{
		SubLevelAccess subLevel = getParamValue(context, target);
		if(!(subLevel instanceof ServerSubLevel ssl))
			throw new SpellRuntimeException(PhySpellHelper.NULL_CONSTRUCT);
		return ssl.getMassTracker().getMass();
	}
}