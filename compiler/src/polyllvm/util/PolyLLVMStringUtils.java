package polyllvm.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import polyglot.ast.Expr;
import polyglot.ast.Node;
import polyglot.ast.NodeFactory;
import polyglot.ast.StringLit;
import polyglot.types.TypeSystem;
import polyglot.util.Position;

public class PolyLLVMStringUtils {
    /**
     * @param v
     * @param n
     * @param nf
     * @param ts
     * @return
     */
    public static Node stringToConstructor(StringLit n, NodeFactory nf,
            TypeSystem ts) {
        List<Expr> bytes = new ArrayList<>();
        for (int i = 0; i < n.value().getBytes().length; i++) {
            byte b = n.value().getBytes()[i];
//            bytes.add(nf.IntLit(n.position(), IntLit.INT, b).type(ts.Byte()));
            bytes.add(nf.CharLit(n.position(), (char) b).type(ts.Byte()));

        }
        Expr byteArray = nf
                           .NewArray(Position.compilerGenerated(),
                                     nf.CanonicalTypeNode(Position.COMPILER_GENERATED,
                                                          ts.Byte()),
                                     1,
                                     nf.ArrayInit(n.position(), bytes))
                           .type(ts.arrayOf(ts.Byte()));
        return nf.New(n.position(),
                      nf.CanonicalTypeNode(Position.compilerGenerated(),
                                           ts.String()),
                      Arrays.asList(byteArray))
                 .type(ts.String());
    }
}
