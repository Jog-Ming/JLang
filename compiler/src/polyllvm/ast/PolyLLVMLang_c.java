package polyllvm.ast;

import polyglot.ast.*;
import polyglot.util.InternalCompilerError;
import polyllvm.ast.PseudoLLVM.Expressions.LLVMLabel;
import polyllvm.visit.*;
import sun.nio.ch.PollArrayWrapper;

public class PolyLLVMLang_c extends JLang_c implements PolyLLVMLang {
    public static final PolyLLVMLang_c instance = new PolyLLVMLang_c();

    public static PolyLLVMLang lang(NodeOps n) {
        while (n != null) {
            Lang lang = n.lang();
            if (lang instanceof PolyLLVMLang) return (PolyLLVMLang) lang;
            if (n instanceof Ext)
                n = ((Ext) n).pred();
            else return null;
        }
        throw new InternalCompilerError("Impossible to reach");
    }

    protected PolyLLVMLang_c() {
    }

    protected static PolyLLVMExt polyllvmExt(Node n) {
        return PolyLLVMExt.ext(n);
    }

    @Override
    protected NodeOps NodeOps(Node n) {
        return polyllvmExt(n);
    }

    protected PolyLLVMOps PolyLLVMOps(Node n) {
        return polyllvmExt(n);
    }

    @Override
    public final Node print(Node n, PrintVisitor v) {
        return PolyLLVMOps(n).print(v);
    }

    @Override
    public Node removeStringLiterals(Node n, StringLiteralRemover v) {
        return PolyLLVMOps(n).removeStringLiterals(v);
    }

    @Override
    public PseudoLLVMTranslator enterTranslatePseudoLLVM(Node n,
            PseudoLLVMTranslator v) {
        return PolyLLVMOps(n).enterTranslatePseudoLLVM(v);
    }

    @Override
    public Node translatePseudoLLVM(Node n, PseudoLLVMTranslator v) {
        return PolyLLVMOps(n).translatePseudoLLVM(v);
    }

    @Override
    public Node overrideTranslatePseudoLLVM(Node n, PseudoLLVMTranslator v) {
        return PolyLLVMOps(n).overrideTranslatePseudoLLVM(v);
    }

    @Override
    public Node addVoidReturn(Node n, AddVoidReturnVisitor v) {
        return PolyLLVMOps(n).addVoidReturn(v);
    }

    @Override
    public Node translatePseudoLLVMConditional(Node n, PseudoLLVMTranslator v,
            LLVMLabel trueLabel, LLVMLabel falseLabel) {
        return PolyLLVMOps(n).translatePseudoLLVMConditional(v,
                                                             trueLabel,
                                                             falseLabel);
    }

    @Override
    public Node llvmVarToStack(Node n, LLVMVarToStack v) {
        return PolyLLVMOps(n).llvmVarToStack(v);
    }

    @Override
    public Node removeESeq(Node n, RemoveESeqVisitor v) {
        return PolyLLVMOps(n).removeESeq(v);
    }

    @Override
    public AddPrimitiveWideningCastsVisitor enterAddPrimitiveWideningCasts(
            Node n, AddPrimitiveWideningCastsVisitor v) {
        return PolyLLVMOps(n).enterAddPrimitiveWideningCasts(v);
    }

    @Override
    public Node addPrimitiveWideningCasts(Node n,
            AddPrimitiveWideningCastsVisitor v) {
        return PolyLLVMOps(n).addPrimitiveWideningCasts(v);
    }

}
