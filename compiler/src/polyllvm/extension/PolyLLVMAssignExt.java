package polyllvm.extension;

import polyglot.ast.Assign;
import polyglot.util.SerialVersionUID;
import polyllvm.ast.PolyLLVMExt;
import polyllvm.visit.PseudoLLVMTranslator;

public class PolyLLVMAssignExt extends PolyLLVMExt {
    private static final long serialVersionUID = SerialVersionUID.generate();

    @Override
    public PseudoLLVMTranslator enterTranslatePseudoLLVM(PseudoLLVMTranslator v) {
        Assign n = (Assign) node();
        assert n.operator().equals(Assign.ASSIGN) : "Non-vanilla assignments should be de-sugared";
        return super.enterTranslatePseudoLLVM(v);
    }
}
