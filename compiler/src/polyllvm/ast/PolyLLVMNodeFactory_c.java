package polyllvm.ast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import polyglot.ast.Ext;
import polyglot.ast.NodeFactory_c;
import polyglot.frontend.Source;
import polyglot.util.Pair;
import polyglot.util.Position;
import polyllvm.ast.PseudoLLVM.LLVMArgDecl;
import polyllvm.ast.PseudoLLVM.LLVMArgDecl_c;
import polyllvm.ast.PseudoLLVM.LLVMBlock;
import polyllvm.ast.PseudoLLVM.LLVMBlock_c;
import polyllvm.ast.PseudoLLVM.LLVMFunction;
import polyllvm.ast.PseudoLLVM.LLVMFunctionDeclaration;
import polyllvm.ast.PseudoLLVM.LLVMFunction_c;
import polyllvm.ast.PseudoLLVM.LLVMGlobalDeclaration;
import polyllvm.ast.PseudoLLVM.LLVMSourceFile;
import polyllvm.ast.PseudoLLVM.LLVMSourceFile_c;
import polyllvm.ast.PseudoLLVM.Expressions.LLVMIntLiteral;
import polyllvm.ast.PseudoLLVM.Expressions.LLVMIntLiteral_c;
import polyllvm.ast.PseudoLLVM.Expressions.LLVMOperand;
import polyllvm.ast.PseudoLLVM.Expressions.LLVMVariable;
import polyllvm.ast.PseudoLLVM.Expressions.LLVMVariable_c;
import polyllvm.ast.PseudoLLVM.Expressions.LLVMVariable_c.VarType;
import polyllvm.ast.PseudoLLVM.LLVMTypes.LLVMIntType;
import polyllvm.ast.PseudoLLVM.LLVMTypes.LLVMIntType_c;
import polyllvm.ast.PseudoLLVM.LLVMTypes.LLVMTypeNode;
import polyllvm.ast.PseudoLLVM.LLVMTypes.LLVMVoidType;
import polyllvm.ast.PseudoLLVM.LLVMTypes.LLVMVoidType_c;
import polyllvm.ast.PseudoLLVM.Statements.LLVMAdd;
import polyllvm.ast.PseudoLLVM.Statements.LLVMAdd_c;
import polyllvm.ast.PseudoLLVM.Statements.LLVMCall;
import polyllvm.ast.PseudoLLVM.Statements.LLVMCall_c;
import polyllvm.ast.PseudoLLVM.Statements.LLVMInstruction;
import polyllvm.ast.PseudoLLVM.Statements.LLVMRet;
import polyllvm.ast.PseudoLLVM.Statements.LLVMRet_c;

/**
 * NodeFactory for polyllvm extension.
 */
public class PolyLLVMNodeFactory_c extends NodeFactory_c
        implements PolyLLVMNodeFactory {
    public PolyLLVMNodeFactory_c(PolyLLVMLang lang,
            PolyLLVMExtFactory extFactory) {
        super(lang, extFactory);
    }

    @Override
    public PolyLLVMExtFactory extFactory() {
        return (PolyLLVMExtFactory) super.extFactory();
    }

    @Override
    public PolyLLVMExtFactory PolyLLVMExtFactory() {
        return new PolyLLVMExtFactory_c();
    }

    // TODO:  Implement factory methods for new AST nodes.

    @Override
    public LLVMAdd LLVAdd(Position pos, LLVMVariable r, LLVMIntType t,
            LLVMOperand left, LLVMOperand right, Ext e) {
        LLVMAdd n = new LLVMAdd_c(pos, r, t, left, right, e);
        return ext(n, extFactory().extLLVMAdd());
    }

    @Override
    public LLVMAdd LLVAdd(Position pos, LLVMIntType t, LLVMOperand left,
            LLVMOperand right, Ext e) {
        LLVMAdd n = new LLVMAdd_c(pos, t, left, right, e);
        return ext(n, extFactory().extLLVMAdd());
    }

    @Override
    public LLVMIntLiteral LLVMIntLiteral(Position pos, int value, Ext ext) {
        LLVMIntLiteral n = new LLVMIntLiteral_c(pos, value, ext);
        return ext(n, extFactory().extLLVMIntLiteral());
    }

    @Override
    public LLVMIntType LLVMIntType(Position pos, int intSize, Ext e) {
        LLVMIntType n = new LLVMIntType_c(pos, intSize, e);
        return ext(n, extFactory().extLLVMIntType());
    }

    @Override
    public LLVMVariable LLVMVariable(Position pos, String name, VarType t,
            Ext e) {
        LLVMVariable n = new LLVMVariable_c(pos, name, t, e);
        return ext(n, extFactory().extLLVMVariable());
    }

    @Override
    public LLVMBlock LLVMBlock(Position pos, List<LLVMInstruction> instructions,
            Ext e) {
        LLVMBlock n = new LLVMBlock_c(pos, instructions, e);
        return ext(n, extFactory().extLLVMBlock());
    }

    @Override
    public LLVMFunction LLVMFunction(Position pos, String name,
            List<LLVMArgDecl> args, LLVMTypeNode retType,
            List<LLVMBlock> blocks) {
        LLVMFunction n = new LLVMFunction_c(pos, name, args, retType, blocks);
        return ext(n, extFactory().extLLVMFunction());
    }

    @Override
    public LLVMFunction LLVMFunction(Position pos, String name,
            List<LLVMArgDecl> args, LLVMTypeNode retType, LLVMBlock code) {
        List<LLVMBlock> blocks = new ArrayList<>(Arrays.asList(code));
        return LLVMFunction(pos, name, args, retType, blocks);
    }

    @Override
    public LLVMArgDecl LLVMArgDecl(Position pos, LLVMTypeNode typeNode,
            String name) {
        LLVMArgDecl n = new LLVMArgDecl_c(pos, typeNode, name);
        return ext(n, extFactory().extLLVMArgDecl());
    }

    @Override
    public LLVMVoidType LLVMVoidType(Position pos) {
        LLVMVoidType n = new LLVMVoidType_c(pos);
        return ext(n, extFactory().extLLVMVoidType());
    }

    @Override
    public LLVMRet LLVMRet(Position pos) {
        LLVMRet n = new LLVMRet_c(pos);
        return ext(n, extFactory().extLLVMRet());
    }

    @Override
    public LLVMRet LLVMRet(Position pos, LLVMTypeNode t, LLVMOperand o) {
        LLVMRet n = new LLVMRet_c(pos, t, o);
        return ext(n, extFactory().extLLVMRet());
    }

    @Override
    public LLVMSourceFile LLVMSourceFile(Position pos, String name, Source s,
            List<LLVMFunction> funcs, List<LLVMFunctionDeclaration> funcdecls,
            List<LLVMGlobalDeclaration> globals) {
        LLVMSourceFile n =
                new LLVMSourceFile_c(pos, name, s, funcs, funcdecls, globals);
        return ext(n, extFactory().extLLVMSourceFile());
    }

    @Override
    public LLVMCall LLVMCall(Position pos, LLVMVariable function,
            List<Pair<LLVMTypeNode, LLVMOperand>> arguments,
            LLVMTypeNode retType, Ext e) {
        LLVMCall n = new LLVMCall_c(pos, function, arguments, retType, null);
        return ext(n, extFactory().extLLVMCall());
    }
    // TODO:  Override factory methods for overridden AST nodes.
    // TODO:  Override factory methods for AST nodes with new extension nodes.

}
