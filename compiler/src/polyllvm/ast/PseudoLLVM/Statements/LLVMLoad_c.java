package polyllvm.ast.PseudoLLVM.Statements;

import polyglot.ast.Ext;
import polyglot.ast.Node;
import polyglot.util.CodeWriter;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import polyglot.visit.NodeVisitor;
import polyglot.visit.PrettyPrinter;
import polyllvm.ast.PseudoLLVM.Expressions.LLVMOperand;
import polyllvm.ast.PseudoLLVM.Expressions.LLVMVariable;
import polyllvm.ast.PseudoLLVM.LLVMTypes.LLVMTypeNode;

public class LLVMLoad_c extends LLVMInstruction_c implements LLVMLoad {
    private static final long serialVersionUID = SerialVersionUID.generate();

    protected LLVMTypeNode typeNode;
    protected LLVMOperand ptr;

    public LLVMLoad_c(Position pos, LLVMVariable result, LLVMTypeNode typeNode,
            LLVMOperand ptr, Ext e) {
        super(pos, e);
        this.result = result;
        this.typeNode = typeNode;
        this.ptr = ptr;
    }

    @Override
    public void prettyPrint(CodeWriter w, PrettyPrinter pp) {
        super.prettyPrint(w, pp);
        w.write("load ");
        print(typeNode, w, pp);
        w.write(", ");
        print(typeNode, w, pp);
        w.write("* ");
        print(ptr, w, pp);
    }

    @Override
    public Node visitChildren(NodeVisitor v) {
        LLVMLoad_c n = (LLVMLoad_c) super.visitChildren(v);
        LLVMTypeNode tn = visitChild(typeNode, v);
        LLVMOperand p = visitChild(ptr, v);
        return reconstruct(n, tn, p);
    }

    protected <N extends LLVMLoad_c> N reconstruct(N n, LLVMTypeNode tn,
            LLVMOperand p) {
        n = typeNode(n, tn);
        n = ptr(n, p);
        return n;
    }

    protected <N extends LLVMLoad_c> N typeNode(N n, LLVMTypeNode tn) {
        if (n.typeNode == tn) return n;
        n = copyIfNeeded(n);
        n.typeNode = tn;
        return n;
    }

    protected <N extends LLVMLoad_c> N ptr(N n, LLVMOperand p) {
        if (n.ptr == p) return n;
        n = copyIfNeeded(n);
        n.ptr = p;
        return n;
    }

    @Override
    public LLVMTypeNode retType() {
        return typeNode;
    }

}
