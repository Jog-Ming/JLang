package polyllvm.extension;

import polyglot.ast.ClassBody;
import polyglot.ast.ClassMember;
import polyglot.ast.MethodDecl;
import polyglot.ast.Node;
import polyglot.util.Position;
import polyglot.util.SerialVersionUID;
import polyllvm.ast.PolyLLVMExt;
import polyllvm.ast.PolyLLVMNodeFactory;
import polyllvm.ast.PseudoLLVM.LLVMFunction;
import polyllvm.ast.PseudoLLVM.LLVMSourceFile;
import polyllvm.visit.PseudoLLVMTranslator;

public class PolyLLVMClassBodyExt extends PolyLLVMExt {
    private static final long serialVersionUID = SerialVersionUID.generate();

    @Override
    public Node translatePseudoLLVM(PseudoLLVMTranslator v) {
        ClassBody n = (ClassBody) node();
        PolyLLVMNodeFactory nf = v.nodeFactory();
        LLVMSourceFile llf = nf.LLVMSourceFile(Position.compilerGenerated(),
                                               null,
                                               null,
                                               null,
                                               null,
                                               null);
//        LLVMSourceFile llf = nf.LLVMSourceFile (Position.compilerGenerated(),
//                                               null,
//                                               null,
//                                               null,
//                                               null);
        for (ClassMember cm : n.members()) {
            if (cm instanceof MethodDecl
                    && ((MethodDecl) cm).flags().isStatic()) {
                llf = llf.appendFunction((LLVMFunction) v.getTranslation(cm));
            }
            else {
                System.out.println("Could not translate " + cm
                        + " - Skipping it.");
            }
        }

        v.addTranslation(n, llf);

        return super.translatePseudoLLVM(v);
    }
}
