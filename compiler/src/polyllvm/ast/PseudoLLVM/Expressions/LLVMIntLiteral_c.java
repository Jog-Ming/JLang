package polyllvm.ast.PseudoLLVM.Expressions;

import polyglot.ast.Ext;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import polyglot.visit.PrettyPrinter;
import polyllvm.ast.PseudoLLVM.LLVMNode_c;

public class LLVMIntLiteral_c extends LLVMNode_c implements LLVMIntLiteral {
    private static final long serialVersionUID = SerialVersionUID.generate();

    protected int value;

    public LLVMIntLiteral_c(Position pos, int value, Ext ext) {
        super(pos, ext);
        this.value = value;
    }

    protected <N extends LLVMIntLiteral_c> N value(N n, int value) {
        if (n.value == value) return n;
        n = copyIfNeeded(n);
        n.value = value;
        return n;
    }

    protected <N extends LLVMIntLiteral_c> N reconstruct(N n, int value) {
        return value(n, value);
    }

    @Override
    public void prettyPrint(CodeWriter w, PrettyPrinter pp) {
        w.write(toString());
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

}
