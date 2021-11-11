package parse;

import com.sun.marlin.DTransformingPathConsumer2D;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import parse.antlr.Java8Parser;
import parse.antlr.Java8ParserListener;
import scala.reflect.internal.tpe.TypeToStrings;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParserListener implements Java8ParserListener {

    private Java8Parser parser;
    private Stack<Java8Parser.ForUpdateContext> forUpdates = new Stack<>();
    private RuleContext NoPrint;// RC: Used to store the parent rule context of a branch you don't want to print
    private String arrayType; // RC: Used to store the type of an array for the arrayCreationExpression rule
    private int arrayDimIndex = -1;

    private static ArrayList<RuleContext> getChildrenContexts(RuleContext ctx) {
        int n = ctx.getChildCount();
        ArrayList<RuleContext> children = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            ParseTree childAsTree = ctx.getChild(i);
            if(!(childAsTree instanceof TerminalNodeImpl)) {
                children.add((RuleContext) childAsTree);
            }
        }
        return children;
    }


    public ParserListener(Java8Parser parser) {
        this.parser = parser;
    }

    @Override
    public void enterComment(Java8Parser.CommentContext ctx) {
        //User created, does not work  -TQ
        TranslationUnit.outputNoTab("#");
    }

    @Override
    public void exitComment(Java8Parser.CommentContext ctx) {
        //User created, does not work  -TQ
    }

    @Override
    public void enterLiteral(Java8Parser.LiteralContext ctx) {
        if(!(NoPrint instanceof Java8Parser.DimExprContext)) {
            String out = ctx.getText();
            if (out.equals("true")) {
                out = "True";
            } else if (out.equals("false")) {
                out = "False";
            }
            if ((ctx.IntegerLiteral() != null || ctx.FloatingPointLiteral() != null) && ctx.getText().charAt(ctx.getText().length() - 1) > 57) {
                out = out.substring(0, ctx.getText().length() - 1);
            }

            TranslationUnit.outputNoTab(out);
        }
    }

    @Override
    public void exitLiteral(Java8Parser.LiteralContext ctx) {

    }

    @Override
    public void enterPrimitiveType(Java8Parser.PrimitiveTypeContext ctx) {

    }

    @Override
    public void exitPrimitiveType(Java8Parser.PrimitiveTypeContext ctx) {

    }

    @Override
    public void enterNumericType(Java8Parser.NumericTypeContext ctx) {
  
    }

    @Override
    public void exitNumericType(Java8Parser.NumericTypeContext ctx) {

    }

    @Override
    public void enterIntegralType(Java8Parser.IntegralTypeContext ctx) {

    }

    @Override
    public void exitIntegralType(Java8Parser.IntegralTypeContext ctx) {

    }

    @Override
    public void enterFloatingPointType(Java8Parser.FloatingPointTypeContext ctx) {

    }

    @Override
    public void exitFloatingPointType(Java8Parser.FloatingPointTypeContext ctx) {

    }

    @Override
    public void enterReferenceType(Java8Parser.ReferenceTypeContext ctx) {

    }

    @Override
    public void exitReferenceType(Java8Parser.ReferenceTypeContext ctx) {

    }

    @Override
    public void enterClassOrInterfaceType(Java8Parser.ClassOrInterfaceTypeContext ctx) {

    }

    @Override
    public void exitClassOrInterfaceType(Java8Parser.ClassOrInterfaceTypeContext ctx) {

    }

    @Override
    public void enterClassType(Java8Parser.ClassTypeContext ctx) {

    }

    @Override
    public void exitClassType(Java8Parser.ClassTypeContext ctx) {

    }

    @Override
    public void enterClassType_lf_classOrInterfaceType(Java8Parser.ClassType_lf_classOrInterfaceTypeContext ctx) {
        TranslationUnit.outputNoTab(".");
        if (!ctx.annotation().isEmpty()){
            utilityWalker.walk(ctx.getChild(1));
        }
        TranslationUnit.outputNoTab(ctx.Identifier().getText());
        if (ctx.typeArguments() != null){
            utilityWalker.walk(ctx.getChild(ctx.getChildCount()-1));
        }
    }

    @Override
    public void exitClassType_lf_classOrInterfaceType(Java8Parser.ClassType_lf_classOrInterfaceTypeContext ctx) {

    }

    @Override
    public void enterClassType_lfno_classOrInterfaceType(Java8Parser.ClassType_lfno_classOrInterfaceTypeContext ctx) {
        if (!ctx.annotation().isEmpty()){
            utilityWalker.walk(ctx.getChild(0));
        }
        TranslationUnit.outputNoTab(ctx.Identifier().getText());
        if (ctx.typeArguments() != null){

            utilityWalker.walk(ctx.getChild(ctx.getChildCount()-1));
        }
    }

    @Override
    public void exitClassType_lfno_classOrInterfaceType(Java8Parser.ClassType_lfno_classOrInterfaceTypeContext ctx) {

    }

    @Override
    public void enterInterfaceType(Java8Parser.InterfaceTypeContext ctx) {

    }

    @Override
    public void exitInterfaceType(Java8Parser.InterfaceTypeContext ctx) {

    }

    @Override
    public void enterInterfaceType_lf_classOrInterfaceType(Java8Parser.InterfaceType_lf_classOrInterfaceTypeContext ctx) {

    }

    @Override
    public void exitInterfaceType_lf_classOrInterfaceType(Java8Parser.InterfaceType_lf_classOrInterfaceTypeContext ctx) {

    }

    @Override
    public void enterInterfaceType_lfno_classOrInterfaceType(Java8Parser.InterfaceType_lfno_classOrInterfaceTypeContext ctx) {

    }

    @Override
    public void exitInterfaceType_lfno_classOrInterfaceType(Java8Parser.InterfaceType_lfno_classOrInterfaceTypeContext ctx) {

    }

    @Override
    public void enterTypeVariable(Java8Parser.TypeVariableContext ctx) {

    }

    @Override
    public void exitTypeVariable(Java8Parser.TypeVariableContext ctx) {

    }

    @Override
    public void enterArrayType(Java8Parser.ArrayTypeContext ctx) {

    }

    @Override
    public void exitArrayType(Java8Parser.ArrayTypeContext ctx) {

    }

    @Override
    public void enterDims(Java8Parser.DimsContext ctx) {

    }

    @Override
    public void exitDims(Java8Parser.DimsContext ctx) {

    }

    @Override
    public void enterTypeParameter(Java8Parser.TypeParameterContext ctx) {

    }

    @Override
    public void exitTypeParameter(Java8Parser.TypeParameterContext ctx) {

    }

    @Override
    public void enterTypeParameterModifier(Java8Parser.TypeParameterModifierContext ctx) {

    }

    @Override
    public void exitTypeParameterModifier(Java8Parser.TypeParameterModifierContext ctx) {

    }

    @Override
    public void enterTypeBound(Java8Parser.TypeBoundContext ctx) {

    }

    @Override
    public void exitTypeBound(Java8Parser.TypeBoundContext ctx) {

    }

    @Override
    public void enterAdditionalBound(Java8Parser.AdditionalBoundContext ctx) {

    }

    @Override
    public void exitAdditionalBound(Java8Parser.AdditionalBoundContext ctx) {

    }

    @Override
    public void enterTypeArguments(Java8Parser.TypeArgumentsContext ctx) {

    }

    @Override
    public void exitTypeArguments(Java8Parser.TypeArgumentsContext ctx) {

    }

    @Override
    public void enterTypeArgumentList(Java8Parser.TypeArgumentListContext ctx) {

    }

    @Override
    public void exitTypeArgumentList(Java8Parser.TypeArgumentListContext ctx) {

    }

    @Override
    public void enterTypeArgument(Java8Parser.TypeArgumentContext ctx) {

    }

    @Override
    public void exitTypeArgument(Java8Parser.TypeArgumentContext ctx) {

    }

    @Override
    public void enterWildcard(Java8Parser.WildcardContext ctx) {

    }

    @Override
    public void exitWildcard(Java8Parser.WildcardContext ctx) {

    }

    @Override
    public void enterWildcardBounds(Java8Parser.WildcardBoundsContext ctx) {

    }

    @Override
    public void exitWildcardBounds(Java8Parser.WildcardBoundsContext ctx) {

    }

    @Override
    public void enterPackageName(Java8Parser.PackageNameContext ctx) {

    }

    @Override
    public void exitPackageName(Java8Parser.PackageNameContext ctx) {

    }

    @Override
    public void enterTypeName(Java8Parser.TypeNameContext ctx) {

    }

    @Override
    public void exitTypeName(Java8Parser.TypeNameContext ctx) {
        if(ctx.parent instanceof Java8Parser.MethodInvocationContext){
            String output = ctx.Identifier().getText();
          /*  if (!(ctx.parent.getChild(2) instanceof Java8Parser.TypeArgumentsContext)){
                output += '.';
            }

           */

            TranslationUnit.outputNoTab(output);
        }
    } // RC: Method Invocation printing

    @Override
    public void enterPackageOrTypeName(Java8Parser.PackageOrTypeNameContext ctx) {

    }

    @Override
    public void exitPackageOrTypeName(Java8Parser.PackageOrTypeNameContext ctx) {

    }

    @Override   // have not included ambig. defn.
    public void enterExpressionName(Java8Parser.ExpressionNameContext ctx) {
        if (ctx.Identifier().getText().equals("println")){
            TranslationUnit.outputNoTab("print(");
        }

    }

    @Override
    public void exitExpressionName(Java8Parser.ExpressionNameContext ctx) {
        String out = "";
        if(ctx.parent instanceof Java8Parser.MethodInvocationContext){
            String output = ctx.Identifier().getText();
            if (!(ctx.parent.getChild(2) instanceof Java8Parser.TypeArgumentsContext)){
                output += '.';
            }

            TranslationUnit.outputNoTab(output);
        }
        else if(ctx.getChildCount() > 1){
            out += '.';
        }
        out += ctx.Identifier().getText(); // RC removed trailing space here and individually added it to the required expressions to not effect output
        if (!(NoPrint instanceof Java8Parser.ForUpdateContext)) {
            if (!ctx.Identifier().getText().equals("println")) { // RC 10/26
                TranslationUnit.outputNoTab(out);
            }
            else TranslationUnit.outputNoTab(")"); // RC 10/26 added these statements to account for a special situation of array length access
        }
/*
        if (TranslationUnit.show().contains("[") && !TranslationUnit.show().contains("]")) {
            String out = "]";
            TranslationUnit.outputNoTab(out);
        }
 */
    }

    @Override
    public void enterMethodName(Java8Parser.MethodNameContext ctx) {
            TranslationUnit.outputNoTab(ctx.Identifier().getText());

    }

    @Override
    public void exitMethodName(Java8Parser.MethodNameContext ctx) {
        //TranslationUnit.outputNoTab("(");
    } //RC Method Invocation printing

    @Override
    public void enterAmbiguousName(Java8Parser.AmbiguousNameContext ctx) {

    }

    @Override
    public void exitAmbiguousName(Java8Parser.AmbiguousNameContext ctx) {
        String output = ctx.Identifier().getText();
        if(ctx.getChildCount() > 1){
            TranslationUnit.outputNoTab("." + output); //debug
        }
        else{
            TranslationUnit.outputNoTab(output);
        } // RC member access
    }

    @Override
    public void enterCompilationUnit(Java8Parser.CompilationUnitContext ctx) {

    }

    @Override
    public void exitCompilationUnit(Java8Parser.CompilationUnitContext ctx) {

    }

    @Override
    public void enterPackageDeclaration(Java8Parser.PackageDeclarationContext ctx) {

    }

    @Override
    public void exitPackageDeclaration(Java8Parser.PackageDeclarationContext ctx) {

    }

    @Override
    public void enterPackageModifier(Java8Parser.PackageModifierContext ctx) {

    }

    @Override
    public void exitPackageModifier(Java8Parser.PackageModifierContext ctx) {

    }

    @Override
    public void enterImportDeclaration(Java8Parser.ImportDeclarationContext ctx) {

    }

    @Override
    public void exitImportDeclaration(Java8Parser.ImportDeclarationContext ctx) {

    }

    @Override
    public void enterSingleTypeImportDeclaration(Java8Parser.SingleTypeImportDeclarationContext ctx) {

    }

    @Override
    public void exitSingleTypeImportDeclaration(Java8Parser.SingleTypeImportDeclarationContext ctx) {

    }

    @Override
    public void enterTypeImportOnDemandDeclaration(Java8Parser.TypeImportOnDemandDeclarationContext ctx) {

    }

    @Override
    public void exitTypeImportOnDemandDeclaration(Java8Parser.TypeImportOnDemandDeclarationContext ctx) {

    }

    @Override
    public void enterSingleStaticImportDeclaration(Java8Parser.SingleStaticImportDeclarationContext ctx) {

    }

    @Override
    public void exitSingleStaticImportDeclaration(Java8Parser.SingleStaticImportDeclarationContext ctx) {

    }

    @Override
    public void enterStaticImportOnDemandDeclaration(Java8Parser.StaticImportOnDemandDeclarationContext ctx) {

    }

    @Override
    public void exitStaticImportOnDemandDeclaration(Java8Parser.StaticImportOnDemandDeclarationContext ctx) {

    }

    @Override
    public void enterTypeDeclaration(Java8Parser.TypeDeclarationContext ctx) {

    }

    @Override
    public void exitTypeDeclaration(Java8Parser.TypeDeclarationContext ctx) {

    }

    @Override
    public void enterClassDeclaration(Java8Parser.ClassDeclarationContext ctx) {
    }

    @Override
    public void exitClassDeclaration(Java8Parser.ClassDeclarationContext ctx) {
    }

    @Override
    public void enterNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext ctx) {
        String out = "class" + " " + ctx.Identifier() + ": \n";
        TranslationUnit.outputWithTab(out);
    }

    @Override
    public void exitNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext ctx) {
        String out = "";

        List<Java8Parser.ClassBodyDeclarationContext> classMembers = ctx.classBody().classBodyDeclaration();
        for(int i = classMembers.size() - 1; i >= 0; i--){
        //I think conventionally, Java main method is at bottom, like C/C++
            if(classMembers.get(i).classMemberDeclaration() != null){
                if(classMembers.get(i).classMemberDeclaration().methodDeclaration() != null){
                    if(classMembers.get(i).classMemberDeclaration().methodDeclaration().methodHeader().methodDeclarator().Identifier().getText().equals("main")){
                        out = "Main = " + ctx.Identifier() + "()\nMain.main([])";
                    }
                }
            }
        }

        TranslationUnit.outputWithTab(out);
    }

    @Override
    public void enterClassModifier(Java8Parser.ClassModifierContext ctx) {

    }

    @Override
    public void exitClassModifier(Java8Parser.ClassModifierContext ctx) {

    }

    @Override
    public void enterTypeParameters(Java8Parser.TypeParametersContext ctx) {

    }

    @Override
    public void exitTypeParameters(Java8Parser.TypeParametersContext ctx) {

    }

    @Override
    public void enterTypeParameterList(Java8Parser.TypeParameterListContext ctx) {

    }

    @Override
    public void exitTypeParameterList(Java8Parser.TypeParameterListContext ctx) {

    }

    @Override
    public void enterSuperclass(Java8Parser.SuperclassContext ctx) {

    }

    @Override
    public void exitSuperclass(Java8Parser.SuperclassContext ctx) {

    }

    @Override
    public void enterSuperinterfaces(Java8Parser.SuperinterfacesContext ctx) {

    }

    @Override
    public void exitSuperinterfaces(Java8Parser.SuperinterfacesContext ctx) {

    }

    @Override
    public void enterInterfaceTypeList(Java8Parser.InterfaceTypeListContext ctx) {

    }

    @Override
    public void exitInterfaceTypeList(Java8Parser.InterfaceTypeListContext ctx) {

    }

    @Override
    public void enterClassBody(Java8Parser.ClassBodyContext ctx) {
        // causes scope increase
        TranslationUnit.enterScope();
    }

    @Override
    public void exitClassBody(Java8Parser.ClassBodyContext ctx) {
        // causes scope decrease
        TranslationUnit.exitScope();
    }

    @Override
    public void enterClassBodyDeclaration(Java8Parser.ClassBodyDeclarationContext ctx) {

    }

    @Override
    public void exitClassBodyDeclaration(Java8Parser.ClassBodyDeclarationContext ctx) {

    }

    @Override
    public void enterClassMemberDeclaration(Java8Parser.ClassMemberDeclarationContext ctx) {
    }

    @Override
    public void exitClassMemberDeclaration(Java8Parser.ClassMemberDeclarationContext ctx) {

    }

    @Override
    public void enterFieldDeclaration(Java8Parser.FieldDeclarationContext ctx) {

    }

    @Override
    public void exitFieldDeclaration(Java8Parser.FieldDeclarationContext ctx) {

    }

    @Override
    public void enterFieldModifier(Java8Parser.FieldModifierContext ctx) {

    }

    @Override
    public void exitFieldModifier(Java8Parser.FieldModifierContext ctx) {

    }

    @Override
    public void enterVariableDeclaratorList(Java8Parser.VariableDeclaratorListContext ctx) {

    }

    @Override
    public void exitVariableDeclaratorList(Java8Parser.VariableDeclaratorListContext ctx) {

    }

    @Override
    public void enterVariableDeclarator(Java8Parser.VariableDeclaratorContext ctx) {
        String optionalInitializer = ctx.variableInitializer() == null ? "None" : "";//ctx.variableInitializer().getText();
        String out = ctx.variableDeclaratorId().Identifier() + " " + "=" + " " + optionalInitializer; // expression
        TranslationUnit.outputWithTab(out);
    }

    @Override
    public void exitVariableDeclarator(Java8Parser.VariableDeclaratorContext ctx) {
        TranslationUnit.outputNoTab("\n");
    }

    @Override
    public void enterVariableDeclaratorId(Java8Parser.VariableDeclaratorIdContext ctx) {

    }

    @Override
    public void exitVariableDeclaratorId(Java8Parser.VariableDeclaratorIdContext ctx) {

    }

    @Override
    public void enterVariableInitializer(Java8Parser.VariableInitializerContext ctx) {
        if(ctx.parent instanceof Java8Parser.VariableInitializerListContext) {
            arrayDimIndex += 1;
            if(arrayDimIndex != 0) arrayDimIndex += 1;
          //  TranslationUnit.outputNoTab(arrayDimIndex + ": "); // debug statement
        }
    }

    @Override
    public void exitVariableInitializer(Java8Parser.VariableInitializerContext ctx) {

        if(ctx.parent instanceof Java8Parser.VariableInitializerListContext && arrayDimIndex < ctx.parent.getChildCount()-1) {
            TranslationUnit.outputNoTab(", ");
        }
    }

    @Override
    public void enterUnannType(Java8Parser.UnannTypeContext ctx) {

    }

    @Override
    public void exitUnannType(Java8Parser.UnannTypeContext ctx) {

    }

    @Override
    public void enterUnannPrimitiveType(Java8Parser.UnannPrimitiveTypeContext ctx) {

    }

    @Override
    public void exitUnannPrimitiveType(Java8Parser.UnannPrimitiveTypeContext ctx) {

    }

    @Override
    public void enterUnannReferenceType(Java8Parser.UnannReferenceTypeContext ctx) {

    }

    @Override
    public void exitUnannReferenceType(Java8Parser.UnannReferenceTypeContext ctx) {

    }

    @Override
    public void enterUnannClassOrInterfaceType(Java8Parser.UnannClassOrInterfaceTypeContext ctx) {

    }

    @Override
    public void exitUnannClassOrInterfaceType(Java8Parser.UnannClassOrInterfaceTypeContext ctx) {

    }

    @Override
    public void enterUnannClassType(Java8Parser.UnannClassTypeContext ctx) {

    }

    @Override
    public void exitUnannClassType(Java8Parser.UnannClassTypeContext ctx) {

    }

    @Override
    public void enterUnannClassType_lf_unannClassOrInterfaceType(Java8Parser.UnannClassType_lf_unannClassOrInterfaceTypeContext ctx) {

    }

    @Override
    public void exitUnannClassType_lf_unannClassOrInterfaceType(Java8Parser.UnannClassType_lf_unannClassOrInterfaceTypeContext ctx) {

    }

    @Override
    public void enterUnannClassType_lfno_unannClassOrInterfaceType(Java8Parser.UnannClassType_lfno_unannClassOrInterfaceTypeContext ctx) {

    }

    @Override
    public void exitUnannClassType_lfno_unannClassOrInterfaceType(Java8Parser.UnannClassType_lfno_unannClassOrInterfaceTypeContext ctx) {

    }

    @Override
    public void enterUnannInterfaceType(Java8Parser.UnannInterfaceTypeContext ctx) {

    }

    @Override
    public void exitUnannInterfaceType(Java8Parser.UnannInterfaceTypeContext ctx) {

    }

    @Override
    public void enterUnannInterfaceType_lf_unannClassOrInterfaceType(Java8Parser.UnannInterfaceType_lf_unannClassOrInterfaceTypeContext ctx) {

    }

    @Override
    public void exitUnannInterfaceType_lf_unannClassOrInterfaceType(Java8Parser.UnannInterfaceType_lf_unannClassOrInterfaceTypeContext ctx) {

    }

    @Override
    public void enterUnannInterfaceType_lfno_unannClassOrInterfaceType(Java8Parser.UnannInterfaceType_lfno_unannClassOrInterfaceTypeContext ctx) {

    }

    @Override
    public void exitUnannInterfaceType_lfno_unannClassOrInterfaceType(Java8Parser.UnannInterfaceType_lfno_unannClassOrInterfaceTypeContext ctx) {

    }

    @Override
    public void enterUnannTypeVariable(Java8Parser.UnannTypeVariableContext ctx) {

    }

    @Override
    public void exitUnannTypeVariable(Java8Parser.UnannTypeVariableContext ctx) {

    }

    @Override
    public void enterUnannArrayType(Java8Parser.UnannArrayTypeContext ctx) {

    }

    @Override
    public void exitUnannArrayType(Java8Parser.UnannArrayTypeContext ctx) {

    }

    @Override
    public void enterMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        String out = "def ";
        TranslationUnit.outputWithTab(out);
    }

    // methodDeclartion
    //

    @Override
    public void exitMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        String out = "\n";
        /*
        if(ctx.methodHeader().methodDeclarator().Identifier().getText().equals("main")) {
            out += "\tmain([])\n";
        }
        */
        TranslationUnit.outputNoTab(out);
    }

    @Override
    public void enterMethodModifier(Java8Parser.MethodModifierContext ctx) {

    }

    @Override
    public void exitMethodModifier(Java8Parser.MethodModifierContext ctx) {

    }

    @Override
    public void enterMethodHeader(Java8Parser.MethodHeaderContext ctx) {

    }

    @Override
    public void exitMethodHeader(Java8Parser.MethodHeaderContext ctx) {
        String out = ": \n";
        TranslationUnit.outputNoTab(out);
    }

    @Override
    public void enterResult(Java8Parser.ResultContext ctx) {

    }

    @Override
    public void exitResult(Java8Parser.ResultContext ctx) {

    }

    @Override
    public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
        String out = ctx.Identifier().getText() + "(self";
        TranslationUnit.outputNoTab(out);
    }

    @Override
    public void exitMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
        String out = ")";
        TranslationUnit.outputNoTab(out);
    }

    @Override
    public void enterFormalParameterList(Java8Parser.FormalParameterListContext ctx) {
    }

    @Override
    public void exitFormalParameterList(Java8Parser.FormalParameterListContext ctx) {
    }

    @Override
    public void enterFormalParameters(Java8Parser.FormalParametersContext ctx) {
        String out = "";
        for(int i = 0; i < ctx.formalParameter().size(); i++) {
            out += ", " + ctx.formalParameter(i).variableDeclaratorId().Identifier().getText();
        }
        TranslationUnit.outputNoTab(out);
    }

    @Override
    public void exitFormalParameters(Java8Parser.FormalParametersContext ctx) {

    }

    @Override
    public void enterFormalParameter(Java8Parser.FormalParameterContext ctx) {

    }

    @Override
    public void exitFormalParameter(Java8Parser.FormalParameterContext ctx) {

    }

    @Override
    public void enterVariableModifier(Java8Parser.VariableModifierContext ctx) {

    }

    @Override
    public void exitVariableModifier(Java8Parser.VariableModifierContext ctx) {

    }

    @Override
    public void enterLastFormalParameter(Java8Parser.LastFormalParameterContext ctx) {
        String out = ", " + ctx.formalParameter().variableDeclaratorId().Identifier().getText();
        TranslationUnit.outputNoTab(out);
    }

    @Override
    public void exitLastFormalParameter(Java8Parser.LastFormalParameterContext ctx) {

    }

    @Override
    public void enterReceiverParameter(Java8Parser.ReceiverParameterContext ctx) {

    }

    @Override
    public void exitReceiverParameter(Java8Parser.ReceiverParameterContext ctx) {

    }

    @Override
    public void enterThrows_(Java8Parser.Throws_Context ctx) {

    }

    @Override
    public void exitThrows_(Java8Parser.Throws_Context ctx) {

    }

    @Override
    public void enterExceptionTypeList(Java8Parser.ExceptionTypeListContext ctx) {

    }

    @Override
    public void exitExceptionTypeList(Java8Parser.ExceptionTypeListContext ctx) {

    }

    @Override
    public void enterExceptionType(Java8Parser.ExceptionTypeContext ctx) {

    }

    @Override
    public void exitExceptionType(Java8Parser.ExceptionTypeContext ctx) {

    }

    @Override
    public void enterMethodBody(Java8Parser.MethodBodyContext ctx) {

    }

    @Override
    public void exitMethodBody(Java8Parser.MethodBodyContext ctx) {

    }

    @Override
    public void enterInstanceInitializer(Java8Parser.InstanceInitializerContext ctx) {

    }

    @Override
    public void exitInstanceInitializer(Java8Parser.InstanceInitializerContext ctx) {

    }

    @Override
    public void enterStaticInitializer(Java8Parser.StaticInitializerContext ctx) {

    }

    @Override
    public void exitStaticInitializer(Java8Parser.StaticInitializerContext ctx) {

    }

    @Override
    public void enterConstructorDeclaration(Java8Parser.ConstructorDeclarationContext ctx) {

    }

    @Override
    public void exitConstructorDeclaration(Java8Parser.ConstructorDeclarationContext ctx) {

    }

    @Override
    public void enterConstructorModifier(Java8Parser.ConstructorModifierContext ctx) {

    }

    @Override
    public void exitConstructorModifier(Java8Parser.ConstructorModifierContext ctx) {

    }

    @Override
    public void enterConstructorDeclarator(Java8Parser.ConstructorDeclaratorContext ctx) {

    }

    @Override
    public void exitConstructorDeclarator(Java8Parser.ConstructorDeclaratorContext ctx) {

    }

    @Override
    public void enterSimpleTypeName(Java8Parser.SimpleTypeNameContext ctx) {

    }

    @Override
    public void exitSimpleTypeName(Java8Parser.SimpleTypeNameContext ctx) {

    }

    @Override
    public void enterConstructorBody(Java8Parser.ConstructorBodyContext ctx) {

    }

    @Override
    public void exitConstructorBody(Java8Parser.ConstructorBodyContext ctx) {

    }

    @Override
    public void enterExplicitConstructorInvocation(Java8Parser.ExplicitConstructorInvocationContext ctx) {

    }

    @Override
    public void exitExplicitConstructorInvocation(Java8Parser.ExplicitConstructorInvocationContext ctx) {

    }

    @Override
    public void enterEnumDeclaration(Java8Parser.EnumDeclarationContext ctx) {

    }

    @Override
    public void exitEnumDeclaration(Java8Parser.EnumDeclarationContext ctx) {

    }

    @Override
    public void enterEnumBody(Java8Parser.EnumBodyContext ctx) {

    }

    @Override
    public void exitEnumBody(Java8Parser.EnumBodyContext ctx) {

    }

    @Override
    public void enterEnumConstantList(Java8Parser.EnumConstantListContext ctx) {

    }

    @Override
    public void exitEnumConstantList(Java8Parser.EnumConstantListContext ctx) {

    }

    @Override
    public void enterEnumConstant(Java8Parser.EnumConstantContext ctx) {

    }

    @Override
    public void exitEnumConstant(Java8Parser.EnumConstantContext ctx) {

    }

    @Override
    public void enterEnumConstantModifier(Java8Parser.EnumConstantModifierContext ctx) {

    }

    @Override
    public void exitEnumConstantModifier(Java8Parser.EnumConstantModifierContext ctx) {

    }

    @Override
    public void enterEnumBodyDeclarations(Java8Parser.EnumBodyDeclarationsContext ctx) {

    }

    @Override
    public void exitEnumBodyDeclarations(Java8Parser.EnumBodyDeclarationsContext ctx) {

    }

    @Override
    public void enterInterfaceDeclaration(Java8Parser.InterfaceDeclarationContext ctx) {

    }

    @Override
    public void exitInterfaceDeclaration(Java8Parser.InterfaceDeclarationContext ctx) {

    }

    @Override
    public void enterNormalInterfaceDeclaration(Java8Parser.NormalInterfaceDeclarationContext ctx) {

    }

    @Override
    public void exitNormalInterfaceDeclaration(Java8Parser.NormalInterfaceDeclarationContext ctx) {

    }

    @Override
    public void enterInterfaceModifier(Java8Parser.InterfaceModifierContext ctx) {

    }

    @Override
    public void exitInterfaceModifier(Java8Parser.InterfaceModifierContext ctx) {

    }

    @Override
    public void enterExtendsInterfaces(Java8Parser.ExtendsInterfacesContext ctx) {

    }

    @Override
    public void exitExtendsInterfaces(Java8Parser.ExtendsInterfacesContext ctx) {

    }

    @Override
    public void enterInterfaceBody(Java8Parser.InterfaceBodyContext ctx) {

    }

    @Override
    public void exitInterfaceBody(Java8Parser.InterfaceBodyContext ctx) {

    }

    @Override
    public void enterInterfaceMemberDeclaration(Java8Parser.InterfaceMemberDeclarationContext ctx) {

    }

    @Override
    public void exitInterfaceMemberDeclaration(Java8Parser.InterfaceMemberDeclarationContext ctx) {

    }

    @Override
    public void enterConstantDeclaration(Java8Parser.ConstantDeclarationContext ctx) {

    }

    @Override
    public void exitConstantDeclaration(Java8Parser.ConstantDeclarationContext ctx) {

    }

    @Override
    public void enterConstantModifier(Java8Parser.ConstantModifierContext ctx) {

    }

    @Override
    public void exitConstantModifier(Java8Parser.ConstantModifierContext ctx) {

    }

    @Override
    public void enterInterfaceMethodDeclaration(Java8Parser.InterfaceMethodDeclarationContext ctx) {

    }

    @Override
    public void exitInterfaceMethodDeclaration(Java8Parser.InterfaceMethodDeclarationContext ctx) {

    }

    @Override
    public void enterInterfaceMethodModifier(Java8Parser.InterfaceMethodModifierContext ctx) {

    }

    @Override
    public void exitInterfaceMethodModifier(Java8Parser.InterfaceMethodModifierContext ctx) {

    }

    @Override
    public void enterAnnotationTypeDeclaration(Java8Parser.AnnotationTypeDeclarationContext ctx) {

    }

    @Override
    public void exitAnnotationTypeDeclaration(Java8Parser.AnnotationTypeDeclarationContext ctx) {

    }

    @Override
    public void enterAnnotationTypeBody(Java8Parser.AnnotationTypeBodyContext ctx) {

    }

    @Override
    public void exitAnnotationTypeBody(Java8Parser.AnnotationTypeBodyContext ctx) {

    }

    @Override
    public void enterAnnotationTypeMemberDeclaration(Java8Parser.AnnotationTypeMemberDeclarationContext ctx) {

    }

    @Override
    public void exitAnnotationTypeMemberDeclaration(Java8Parser.AnnotationTypeMemberDeclarationContext ctx) {

    }

    @Override
    public void enterAnnotationTypeElementDeclaration(Java8Parser.AnnotationTypeElementDeclarationContext ctx) {

    }

    @Override
    public void exitAnnotationTypeElementDeclaration(Java8Parser.AnnotationTypeElementDeclarationContext ctx) {

    }

    @Override
    public void enterAnnotationTypeElementModifier(Java8Parser.AnnotationTypeElementModifierContext ctx) {

    }

    @Override
    public void exitAnnotationTypeElementModifier(Java8Parser.AnnotationTypeElementModifierContext ctx) {

    }

    @Override
    public void enterDefaultValue(Java8Parser.DefaultValueContext ctx) {

    }

    @Override
    public void exitDefaultValue(Java8Parser.DefaultValueContext ctx) {

    }

    @Override
    public void enterAnnotation(Java8Parser.AnnotationContext ctx) {

    }

    @Override
    public void exitAnnotation(Java8Parser.AnnotationContext ctx) {

    }

    @Override
    public void enterNormalAnnotation(Java8Parser.NormalAnnotationContext ctx) {

    }

    @Override
    public void exitNormalAnnotation(Java8Parser.NormalAnnotationContext ctx) {

    }

    @Override
    public void enterElementValuePairList(Java8Parser.ElementValuePairListContext ctx) {

    }

    @Override
    public void exitElementValuePairList(Java8Parser.ElementValuePairListContext ctx) {

    }

    @Override
    public void enterElementValuePair(Java8Parser.ElementValuePairContext ctx) {

    }

    @Override
    public void exitElementValuePair(Java8Parser.ElementValuePairContext ctx) {

    }

    @Override
    public void enterElementValue(Java8Parser.ElementValueContext ctx) {

    }

    @Override
    public void exitElementValue(Java8Parser.ElementValueContext ctx) {

    }

    @Override
    public void enterElementValueArrayInitializer(Java8Parser.ElementValueArrayInitializerContext ctx) {

    }

    @Override
    public void exitElementValueArrayInitializer(Java8Parser.ElementValueArrayInitializerContext ctx) {

    }

    @Override
    public void enterElementValueList(Java8Parser.ElementValueListContext ctx) {

    }

    @Override
    public void exitElementValueList(Java8Parser.ElementValueListContext ctx) {

    }

    @Override
    public void enterMarkerAnnotation(Java8Parser.MarkerAnnotationContext ctx) {

    }

    @Override
    public void exitMarkerAnnotation(Java8Parser.MarkerAnnotationContext ctx) {

    }

    @Override
    public void enterSingleElementAnnotation(Java8Parser.SingleElementAnnotationContext ctx) {

    }

    @Override
    public void exitSingleElementAnnotation(Java8Parser.SingleElementAnnotationContext ctx) {

    }

    @Override
    public void enterArrayInitializer(Java8Parser.ArrayInitializerContext ctx) {
        String output = "[";
        TranslationUnit.outputNoTab(output);
    }

    @Override
    public void exitArrayInitializer(Java8Parser.ArrayInitializerContext ctx) {
        String output = "]";
        TranslationUnit.outputNoTab(output);
    }

    @Override
    public void enterVariableInitializerList(Java8Parser.VariableInitializerListContext ctx) {
       // TranslationUnit.outputNoTab("Children:  " + ctx.getChildCount()); // debug statement
        arrayDimIndex = -1;
    }

    @Override
    public void exitVariableInitializerList(Java8Parser.VariableInitializerListContext ctx) {
        arrayDimIndex = -1;
    }

    @Override
    public void enterBlock(Java8Parser.BlockContext ctx) {
        // causes scope increase
        TranslationUnit.enterScope();

        if(ctx.blockStatements() == null) {
            TranslationUnit.outputWithTab("pass\n");
        } //TQ
    }

    @Override
    public void exitBlock(Java8Parser.BlockContext ctx) {
        // causes scope decrease
        // might be able to new line here but idk if that would help
        TranslationUnit.exitScope();
    }

    @Override
    public void enterBlockStatements(Java8Parser.BlockStatementsContext ctx) {

    }

    @Override
    public void exitBlockStatements(Java8Parser.BlockStatementsContext ctx) {

    }

    @Override
    public void enterBlockStatement(Java8Parser.BlockStatementContext ctx) {
        //System.out.println("enterBlockStatement");
       // TranslationUnit.outputWithTab("");
    }

    @Override
    public void exitBlockStatement(Java8Parser.BlockStatementContext ctx) {
        TranslationUnit.outputNoTab("\n");
    }

    @Override
    public void enterLocalVariableDeclarationStatement(Java8Parser.LocalVariableDeclarationStatementContext ctx) {

    }

    @Override
    public void exitLocalVariableDeclarationStatement(Java8Parser.LocalVariableDeclarationStatementContext ctx) {

    }

    @Override
    public void enterLocalVariableDeclaration(Java8Parser.LocalVariableDeclarationContext ctx) {

    }

    @Override
    public void exitLocalVariableDeclaration(Java8Parser.LocalVariableDeclarationContext ctx) {

    }

    @Override
    public void enterStatement(Java8Parser.StatementContext ctx) {
        //System.out.println("enterStatement");
        String out;
        if(ctx.parent instanceof Java8Parser.IfThenElseStatementContext) {
            if (ctx.getChild(0) instanceof Java8Parser.IfThenElseStatementContext || ctx.getChild(0) instanceof Java8Parser.IfThenStatementContext || ctx.getChild(0) instanceof Java8Parser.IfThenElseStatementNoShortIfContext) {
                out = "el";
            } else {
                out = "else:\n";
            }
            TranslationUnit.outputWithTab(out);
        }
//        else if (ctx.parent instanceof Java8Parser.BasicForStatementContext) {
//            out = "\t" + forUpdates.pop() + "\n";
//            TranslationUnit.outputWithTab(out);
//        }
    }

    @Override
    public void exitStatement(Java8Parser.StatementContext ctx) {

    }

    @Override
    public void enterStatementNoShortIf(Java8Parser.StatementNoShortIfContext ctx) {
        //System.out.println("enterStatementNoShortIf");
    }

    @Override
    public void exitStatementNoShortIf(Java8Parser.StatementNoShortIfContext ctx) {

    }

    @Override
    public void enterStatementWithoutTrailingSubstatement(Java8Parser.StatementWithoutTrailingSubstatementContext ctx) {
        //System.out.println("enterStatementWithoutTrailingSubstatement");
    }

    @Override
    public void exitStatementWithoutTrailingSubstatement(Java8Parser.StatementWithoutTrailingSubstatementContext ctx) {

    }

    @Override
    public void enterEmptyStatement(Java8Parser.EmptyStatementContext ctx) {
        //System.out.println("enterEmptyStatement");
    }

    @Override
    public void exitEmptyStatement(Java8Parser.EmptyStatementContext ctx) {

    }

    @Override
    public void enterLabeledStatement(Java8Parser.LabeledStatementContext ctx) {
        //System.out.println("enterLabeledStatement");
    }

    @Override
    public void exitLabeledStatement(Java8Parser.LabeledStatementContext ctx) {

    }

    @Override
    public void enterLabeledStatementNoShortIf(Java8Parser.LabeledStatementNoShortIfContext ctx) {
        //System.out.println("enterLabeledStatementNoShortIf");
    }

    @Override
    public void exitLabeledStatementNoShortIf(Java8Parser.LabeledStatementNoShortIfContext ctx) {

    }

    @Override
    public void enterExpressionStatement(Java8Parser.ExpressionStatementContext ctx) {

    }

    @Override
    public void exitExpressionStatement(Java8Parser.ExpressionStatementContext ctx) {

    }

    @Override
    public void enterStatementExpression(Java8Parser.StatementExpressionContext ctx) {
        //System.out.println("enterStatementExpression");
    }

    @Override
    public void exitStatementExpression(Java8Parser.StatementExpressionContext ctx) {
    }

    @Override
    public void enterIfThenStatement(Java8Parser.IfThenStatementContext ctx) {
        //System.out.println("enterIfThenStatement");
        String out = "if(";
        if(ctx.parent.parent instanceof Java8Parser.IfThenElseStatementContext || ctx.parent.parent instanceof Java8Parser.IfThenElseStatementNoShortIfContext){
            TranslationUnit.outputNoTab(out);
        }else {
            TranslationUnit.outputWithTab(out);
        }
    }

    @Override
    public void exitIfThenStatement(Java8Parser.IfThenStatementContext ctx) {
        String out = "\n";
//        String out = "";
        TranslationUnit.outputWithTab(out);
    }

    @Override
    public void enterIfThenElseStatement(Java8Parser.IfThenElseStatementContext ctx) {
        //System.out.println("enterIfThenElseStatement");
        String out = "if(";
        if(ctx.parent.parent instanceof Java8Parser.IfThenElseStatementContext || ctx.parent.parent instanceof Java8Parser.IfThenElseStatementNoShortIfContext){
            TranslationUnit.outputNoTab(out);
        }else {
            TranslationUnit.outputWithTab(out);
        }
    }

    @Override
    public void exitIfThenElseStatement(Java8Parser.IfThenElseStatementContext ctx) {
        String out = "\n";
//        String out = "";
        TranslationUnit.outputWithTab(out);
    }

    @Override
    public void enterIfThenElseStatementNoShortIf(Java8Parser.IfThenElseStatementNoShortIfContext ctx) {    //Not sure when this is called. -TQ
        String out = "if(";
        TranslationUnit.outputWithTab(out);
    }

    @Override
    public void exitIfThenElseStatementNoShortIf(Java8Parser.IfThenElseStatementNoShortIfContext ctx) {
//        String out = "\n";
        String out = "";
        TranslationUnit.outputWithTab(out);
    }

    @Override
    public void enterAssertStatement(Java8Parser.AssertStatementContext ctx) {

    }

    @Override
    public void exitAssertStatement(Java8Parser.AssertStatementContext ctx) {

    }

    @Override
    public void enterSwitchStatement(Java8Parser.SwitchStatementContext ctx) {

    }

    @Override
    public void exitSwitchStatement(Java8Parser.SwitchStatementContext ctx) {

    }

    @Override
    public void enterSwitchBlock(Java8Parser.SwitchBlockContext ctx) {

    }

    @Override
    public void exitSwitchBlock(Java8Parser.SwitchBlockContext ctx) {

    }

    @Override
    public void enterSwitchBlockStatementGroup(Java8Parser.SwitchBlockStatementGroupContext ctx) {

    }

    @Override
    public void exitSwitchBlockStatementGroup(Java8Parser.SwitchBlockStatementGroupContext ctx) {

    }

    @Override
    public void enterSwitchLabels(Java8Parser.SwitchLabelsContext ctx) {

    }

    @Override
    public void exitSwitchLabels(Java8Parser.SwitchLabelsContext ctx) {

    }

    @Override
    public void enterSwitchLabel(Java8Parser.SwitchLabelContext ctx) {

    }

    @Override
    public void exitSwitchLabel(Java8Parser.SwitchLabelContext ctx) {

    }

    @Override
    public void enterEnumConstantName(Java8Parser.EnumConstantNameContext ctx) {

    }

    @Override
    public void exitEnumConstantName(Java8Parser.EnumConstantNameContext ctx) {

    }

    @Override
    public void enterWhileStatement(Java8Parser.WhileStatementContext ctx) {
        String out = "while ";
        TranslationUnit.outputWithTab(out);
    }

    @Override
    public void exitWhileStatement(Java8Parser.WhileStatementContext ctx) {

    }

    @Override
    public void enterWhileStatementNoShortIf(Java8Parser.WhileStatementNoShortIfContext ctx) {

    }

    @Override
    public void exitWhileStatementNoShortIf(Java8Parser.WhileStatementNoShortIfContext ctx) {

    }

    @Override
    public void enterDoStatement(Java8Parser.DoStatementContext ctx) {

    }

    @Override
    public void exitDoStatement(Java8Parser.DoStatementContext ctx) {

    }

    @Override
    public void enterForStatement(Java8Parser.ForStatementContext ctx) {

    }

    @Override
    public void exitForStatement(Java8Parser.ForStatementContext ctx) {

    }

    @Override
    public void enterForStatementNoShortIf(Java8Parser.ForStatementNoShortIfContext ctx) {

    }

    @Override
    public void exitForStatementNoShortIf(Java8Parser.ForStatementNoShortIfContext ctx) {

    }

    @Override
    public void enterBasicForStatement(Java8Parser.BasicForStatementContext ctx) {
        // BC: for now I am assuming that a forStatement always has a forUpdate
        Java8Parser.ForUpdateContext forUpdate = (Java8Parser.ForUpdateContext) getChildrenContexts(ctx)
                .stream()
                .filter(c -> c instanceof Java8Parser.ForUpdateContext)
                .findFirst()
                .get();
        forUpdates.push(forUpdate); // BC : used so the continue statement has access to the forUpdate context
    }

    @Override
    public void exitBasicForStatement(Java8Parser.BasicForStatementContext ctx) {
        forUpdates.pop();
    }

    @Override
    public void enterBasicForStatementNoShortIf(Java8Parser.BasicForStatementNoShortIfContext ctx) {

    }

    @Override
    public void exitBasicForStatementNoShortIf(Java8Parser.BasicForStatementNoShortIfContext ctx) {

    }

    @Override
    public void enterForInit(Java8Parser.ForInitContext ctx) {

    }

    @Override
    public void exitForInit(Java8Parser.ForInitContext ctx) {

    }

    @Override
    public void enterForUpdate(Java8Parser.ForUpdateContext ctx) {
        TranslationUnit.enterScope(); // BC: this forces the update statement to maintain tabbing as though it were a block statement
        TranslationUnit.outputWithTab(""); // BC: this will force tabs to be inserted
    }

    @Override
    public void exitForUpdate(Java8Parser.ForUpdateContext ctx) {
        TranslationUnit.outputNoTab("\n"); // BC: just make sure we newline and scope out and TU will handle tabbing
        TranslationUnit.exitScope();
    }

    @Override
    public void enterStatementExpressionList(Java8Parser.StatementExpressionListContext ctx) {

    }

    @Override
    public void exitStatementExpressionList(Java8Parser.StatementExpressionListContext ctx) {

    }

    @Override
    public void enterEnhancedForStatement(Java8Parser.EnhancedForStatementContext ctx) {

    }

    @Override
    public void exitEnhancedForStatement(Java8Parser.EnhancedForStatementContext ctx) {

    }

    @Override
    public void enterEnhancedForStatementNoShortIf(Java8Parser.EnhancedForStatementNoShortIfContext ctx) {

    }

    @Override
    public void exitEnhancedForStatementNoShortIf(Java8Parser.EnhancedForStatementNoShortIfContext ctx) {

    }

    @Override
    public void enterBreakStatement(Java8Parser.BreakStatementContext ctx) {
        TranslationUnit.outputWithTab("pass");
    }

    @Override
    public void exitBreakStatement(Java8Parser.BreakStatementContext ctx) {
        TranslationUnit.outputNoTab("\n");
    }

    @Override
    public void enterContinueStatement(Java8Parser.ContinueStatementContext ctx) {
        TranslationUnit.exitScope();        // BC: we need to manage the exit and enter scope for this special case
        if (forUpdates.size() > 0) {
            utilityWalker.walk(forUpdates.peek()); // BC: walk to the forUpdate at the top of the stack, but don't pop in-case other continue statements exist in scope
        }
        TranslationUnit.enterScope();       // BC: popping is handled at the level of exiting the basicForStatement
        TranslationUnit.outputWithTab("continue");
    }

    @Override
    public void exitContinueStatement(Java8Parser.ContinueStatementContext ctx) {

    }

    @Override
    public void enterReturnStatement(Java8Parser.ReturnStatementContext ctx) {
        String out = "return ";
        if(ctx.expression().getText().equals("this")){
            out = out + "self";
        }
        TranslationUnit.outputWithTab(out);
    }

    @Override
    public void exitReturnStatement(Java8Parser.ReturnStatementContext ctx) {

    }

    @Override
    public void enterThrowStatement(Java8Parser.ThrowStatementContext ctx) {
    String out = "raise ";
    TranslationUnit.outputWithTab(out);
    }

    @Override
    public void exitThrowStatement(Java8Parser.ThrowStatementContext ctx) {

    }

    @Override
    public void enterSynchronizedStatement(Java8Parser.SynchronizedStatementContext ctx) {

    }

    @Override
    public void exitSynchronizedStatement(Java8Parser.SynchronizedStatementContext ctx) {

    }

    @Override
    public void enterTryStatement(Java8Parser.TryStatementContext ctx) {
    String out = "try:\n";
    TranslationUnit.outputWithTab(out);
    }

    @Override
    public void exitTryStatement(Java8Parser.TryStatementContext ctx) {

    }

    @Override
    public void enterCatches(Java8Parser.CatchesContext ctx) {
        String out = "except " ;
    TranslationUnit.outputWithTab(out);
    }

    @Override
    public void exitCatches(Java8Parser.CatchesContext ctx) {

    }

    @Override
    public void enterCatchClause(Java8Parser.CatchClauseContext ctx) {

    }

    @Override
    public void exitCatchClause(Java8Parser.CatchClauseContext ctx) {

    }

    @Override
    public void enterCatchFormalParameter(Java8Parser.CatchFormalParameterContext ctx) {
        String out = ctx.variableDeclaratorId().getText() + ":\n";
        TranslationUnit.outputNoTab(out);
    }

    @Override
    public void exitCatchFormalParameter(Java8Parser.CatchFormalParameterContext ctx) {

    }

    @Override
    public void enterCatchType(Java8Parser.CatchTypeContext ctx) {

    }

    @Override
    public void exitCatchType(Java8Parser.CatchTypeContext ctx) {

    }

    @Override
    public void enterFinally_(Java8Parser.Finally_Context ctx) {
    String out = "finally:\n";
    TranslationUnit.outputWithTab(out);
    }

    @Override
    public void exitFinally_(Java8Parser.Finally_Context ctx) {

    }

    @Override
    public void enterTryWithResourcesStatement(Java8Parser.TryWithResourcesStatementContext ctx) {

    }

    @Override
    public void exitTryWithResourcesStatement(Java8Parser.TryWithResourcesStatementContext ctx) {

    }

    @Override
    public void enterResourceSpecification(Java8Parser.ResourceSpecificationContext ctx) {

    }

    @Override
    public void exitResourceSpecification(Java8Parser.ResourceSpecificationContext ctx) {

    }

    @Override
    public void enterResourceList(Java8Parser.ResourceListContext ctx) {

    }

    @Override
    public void exitResourceList(Java8Parser.ResourceListContext ctx) {

    }

    @Override
    public void enterResource(Java8Parser.ResourceContext ctx) {

    }

    @Override
    public void exitResource(Java8Parser.ResourceContext ctx) {

    }

    @Override
    public void enterPrimary(Java8Parser.PrimaryContext ctx) {

    }

    @Override
    public void exitPrimary(Java8Parser.PrimaryContext ctx) {

    }

    @Override
    public void enterPrimaryNoNewArray(Java8Parser.PrimaryNoNewArrayContext ctx) {

    }

    @Override
    public void exitPrimaryNoNewArray(Java8Parser.PrimaryNoNewArrayContext ctx) {

    }

    @Override
    public void enterPrimaryNoNewArray_lf_arrayAccess(Java8Parser.PrimaryNoNewArray_lf_arrayAccessContext ctx) {

    }

    @Override
    public void exitPrimaryNoNewArray_lf_arrayAccess(Java8Parser.PrimaryNoNewArray_lf_arrayAccessContext ctx) {

    }

    @Override
    public void enterPrimaryNoNewArray_lfno_arrayAccess(Java8Parser.PrimaryNoNewArray_lfno_arrayAccessContext ctx) {

    }

    @Override
    public void exitPrimaryNoNewArray_lfno_arrayAccess(Java8Parser.PrimaryNoNewArray_lfno_arrayAccessContext ctx) {

    }

    @Override
    public void enterPrimaryNoNewArray_lf_primary(Java8Parser.PrimaryNoNewArray_lf_primaryContext ctx) {

    }

    @Override
    public void exitPrimaryNoNewArray_lf_primary(Java8Parser.PrimaryNoNewArray_lf_primaryContext ctx) {

    }

    @Override
    public void enterPrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary(Java8Parser.PrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primaryContext ctx) {

    }

    @Override
    public void exitPrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary(Java8Parser.PrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primaryContext ctx) {

    }

    @Override
    public void enterPrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary(Java8Parser.PrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primaryContext ctx) {

    }

    @Override
    public void exitPrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary(Java8Parser.PrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primaryContext ctx) {

    }

    @Override
    public void enterPrimaryNoNewArray_lfno_primary(Java8Parser.PrimaryNoNewArray_lfno_primaryContext ctx) {

    }

    @Override
    public void exitPrimaryNoNewArray_lfno_primary(Java8Parser.PrimaryNoNewArray_lfno_primaryContext ctx) {

    }

    @Override
    public void enterPrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary(Java8Parser.PrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primaryContext ctx) {

    }

    @Override
    public void exitPrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary(Java8Parser.PrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primaryContext ctx) {

    }

    @Override
    public void enterPrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary(Java8Parser.PrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primaryContext ctx) {

    }

    @Override
    public void exitPrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary(Java8Parser.PrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primaryContext ctx) {

    }

    @Override
    public void enterClassInstanceCreationExpression(Java8Parser.ClassInstanceCreationExpressionContext ctx) {
        String output = "";
        int ident = 0;
        for(int i = 0; i < ctx.getChildCount(); i++){
            switch (ctx.getChild(i).getText()){
                case "new":
                    //output += "new";
                    break;
                case ".":
                    output += ".";
                    break;
                case "(":
                    output += "(";
                case ")":
                    output += ")";
                default:
                    utilityWalker.walk(ctx.getChild(i));
                    if (ctx.getChild(i) instanceof Java8Parser.AnnotationContext){
                        output += ctx.Identifier(ident).getText();
                        ident++;
                        TranslationUnit.outputNoTab(output);
                        output = "";
                    }
            }
        }
    }

    @Override
    public void exitClassInstanceCreationExpression(Java8Parser.ClassInstanceCreationExpressionContext ctx) {

    }

    @Override
    public void enterClassInstanceCreationExpression_lf_primary(Java8Parser.ClassInstanceCreationExpression_lf_primaryContext ctx) {
        String output = "";
        for(int i = 0; i < ctx.getChildCount(); i++){
            switch (ctx.getChild(i).getText()){
                case "new":
                   // output += "new";
                    break;
                case ".":
                    output += ".";
                    break;
                default:
                    utilityWalker.walk(ctx.getChild(i));
                    if (ctx.getChild(i) instanceof Java8Parser.AnnotationContext){
                        output += ctx.Identifier().getText();
                        TranslationUnit.outputNoTab(output);
                    }
            }
        }
    }

    @Override
    public void exitClassInstanceCreationExpression_lf_primary(Java8Parser.ClassInstanceCreationExpression_lf_primaryContext ctx) {

    }

    @Override
    public void enterClassInstanceCreationExpression_lfno_primary(Java8Parser.ClassInstanceCreationExpression_lfno_primaryContext ctx) {
        String output = "";
        int ident = 0;
        for(int i = 0; i < ctx.getChildCount(); i++){
            switch (ctx.getChild(i).getText()){
                case "new":
                    //TranslationUnit.outputNoTab("new ");\
                   // output += "new ";
                    if (ctx.typeArguments() == null && ctx.annotation().isEmpty())
                    {
                        //TranslationUnit.outputNoTab(ctx.Identifier().get(ident).getText());
                        output += ctx.Identifier().get(ident).getText();
                        ident++;
                    }
                    break;
                case ".":
                    output += ".";
                    break;
                case "(":
                    output += "(";
                    break;
                case ")":
                    output += ")";
                    break;
                default:
                    TranslationUnit.outputNoTab(output);
                    output = "";
                    utilityWalker.walk(ctx.getChild(i));
            }
        } TranslationUnit.outputNoTab(output);
    } // new ForEachLoop<Character>().setStuff(letters);
    // possibility of multiple identifiers that need to be printed

    @Override
    public void exitClassInstanceCreationExpression_lfno_primary(Java8Parser.ClassInstanceCreationExpression_lfno_primaryContext ctx) {

    }

    @Override
    public void enterTypeArgumentsOrDiamond(Java8Parser.TypeArgumentsOrDiamondContext ctx) {
        TranslationUnit.outputNoTab("<");
    }

    @Override
    public void exitTypeArgumentsOrDiamond(Java8Parser.TypeArgumentsOrDiamondContext ctx) {
        TranslationUnit.outputNoTab(">");
    }

    @Override
    public void enterFieldAccess(Java8Parser.FieldAccessContext ctx) {

    }

    @Override
    public void exitFieldAccess(Java8Parser.FieldAccessContext ctx) {

    }

    @Override
    public void enterFieldAccess_lf_primary(Java8Parser.FieldAccess_lf_primaryContext ctx) {

    }

    @Override
    public void exitFieldAccess_lf_primary(Java8Parser.FieldAccess_lf_primaryContext ctx) {

    }

    @Override
    public void enterFieldAccess_lfno_primary(Java8Parser.FieldAccess_lfno_primaryContext ctx) {

    }

    @Override
    public void exitFieldAccess_lfno_primary(Java8Parser.FieldAccess_lfno_primaryContext ctx) {

    }

    @Override
    public void enterArrayAccess(Java8Parser.ArrayAccessContext ctx) {

    }

    @Override
    public void exitArrayAccess(Java8Parser.ArrayAccessContext ctx) {
    }

    @Override
    public void enterArrayAccess_lf_primary(Java8Parser.ArrayAccess_lf_primaryContext ctx) {

    }

    @Override
    public void exitArrayAccess_lf_primary(Java8Parser.ArrayAccess_lf_primaryContext ctx) {

    }

    @Override
    public void enterArrayAccess_lfno_primary(Java8Parser.ArrayAccess_lfno_primaryContext ctx) {

    }

    @Override
    public void exitArrayAccess_lfno_primary(Java8Parser.ArrayAccess_lfno_primaryContext ctx) {

    }

    @Override
    public void enterMethodInvocation(Java8Parser.MethodInvocationContext ctx) {
        ArrayList<RuleContext> children = getChildrenContexts(ctx);
        TranslationUnit.outputWithTab("");
        String output = "";
        for (int i = 0; i < ctx.getChildCount(); i++){
            switch(ctx.getChild(i).getText()) {
                case "super":
                    output += "super";
                    break;
                case ".":
                      output += ".";
                    break;
                case "(":
                    if(ctx.Identifier() != null){
                        output += ctx.Identifier().getText();
                        TranslationUnit.outputNoTab(output);
                    }
                default:
                    utilityWalker.walk(ctx.getChild(i));
            }
        }

    }

    @Override
    public void exitMethodInvocation(Java8Parser.MethodInvocationContext ctx) {
        if (ctx.argumentList() == null){
            TranslationUnit.outputNoTab("()");
        }
    }

    @Override
    public void enterMethodInvocation_lf_primary(Java8Parser.MethodInvocation_lf_primaryContext ctx) {
        TranslationUnit.outputNoTab(".");
        if (ctx.typeArguments() == null){
            TranslationUnit.outputNoTab(ctx.Identifier().getText());
        }
        else utilityWalker.walk(ctx.getChild(1));
    }

    @Override
    public void exitMethodInvocation_lf_primary(Java8Parser.MethodInvocation_lf_primaryContext ctx) {

    }

    @Override
    public void enterMethodInvocation_lfno_primary(Java8Parser.MethodInvocation_lfno_primaryContext ctx) {
        String output = "";
        for (int i = 0; i < ctx.getChildCount(); i++){
            switch(ctx.getChild(i).getText()) {
                case "super":
                    output += "super";
                    break;
                case ".":
                      output += ".";
                    break;
                default:
                    if (ctx.getChild(i) instanceof Java8Parser.ArgumentListContext && ctx.Identifier() != null){
                        output += ctx.Identifier().getText() + "";
                        TranslationUnit.outputNoTab(output);
                    }
                    utilityWalker.walk(ctx.getChild(i));
            }
        }


    }

    @Override
    public void exitMethodInvocation_lfno_primary(Java8Parser.MethodInvocation_lfno_primaryContext ctx) {
        if (ctx.argumentList() == null){
            TranslationUnit.outputNoTab("()");
        }
    }

    @Override
    public void enterArgumentList(Java8Parser.ArgumentListContext ctx) {
        TranslationUnit.outputNoTab("(");
    }

    @Override
    public void exitArgumentList(Java8Parser.ArgumentListContext ctx) {
        TranslationUnit.outputNoTab(")");
    }

    @Override
    public void enterMethodReference(Java8Parser.MethodReferenceContext ctx) {

    }

    @Override
    public void exitMethodReference(Java8Parser.MethodReferenceContext ctx) {

    }

    @Override
    public void enterMethodReference_lf_primary(Java8Parser.MethodReference_lf_primaryContext ctx) {

    }

    @Override
    public void exitMethodReference_lf_primary(Java8Parser.MethodReference_lf_primaryContext ctx) {

    }

    @Override
    public void enterMethodReference_lfno_primary(Java8Parser.MethodReference_lfno_primaryContext ctx) {

    }

    @Override
    public void exitMethodReference_lfno_primary(Java8Parser.MethodReference_lfno_primaryContext ctx) {

    }

    @Override
    public void enterArrayCreationExpression(Java8Parser.ArrayCreationExpressionContext ctx) {
        String output = "";
        arrayType = ctx.getChild(1).getText();
        /*
        switch (ctx.getChild(1).getText())
        {
            case "byte":
            case "short":
            case "int":
                output += "0] * ";
                break;
            case "boolean":
                output += "False] * ";
                break;
            case "long":
                output += "0L] * ";
            case "double":
            case "float":
                output += "0.0] * ";
                break;
            case "char":
                output += "''] * ";
                break;
            default: output += "[] * ";
        }
        //RC
        TranslationUnit.outputNoTab(output);
        */
    }
    //Moved the definition from enterDimExprs to here because it was cleaner
    //added switch statement to detect for all primitive and boolean types and keep
    //explicit declaration of type to match Java
    //RC

    @Override
    public void exitArrayCreationExpression(Java8Parser.ArrayCreationExpressionContext ctx) {
        arrayType = null;
    }

    @Override
    public void enterDimExprs(Java8Parser.DimExprsContext ctx) {
        //This rule only gets called from ArrayCreationExpression
        String output = "";
        int first;
        int second;
        if (ctx.getChildCount() > 1) {
            first = Integer.parseInt(ctx.getChild(0).getChild(1).getText());
            second = Integer.parseInt(ctx.getChild(1).getChild(1).getText());
            output += "[";
        }
        else {
            first = 1;
            second = Integer.parseInt(ctx.getChild(0).getChild(1).getText());
        }
        for(int i = 0; i < first; i++){
            switch(arrayType) {
                case "byte":
                case "short":
                case "int":
                    output += "[0] * ";
                    break;
                case "boolean":
                    output += "[False] * ";
                    break;
                case "long":
                    output += "[0L] * ";
                case "double":
                case "float":
                    output += "[0.0] * ";
                    break;
                case "char":
                    output += "[''] * ";
                    break;
                default:
                    output += "[] * ";
            }
            output += second;
            if(i != first-1)
                output += ", ";
        }
        TranslationUnit.outputNoTab(output);
    }

    @Override
    public void exitDimExprs(Java8Parser.DimExprsContext ctx) {
        if(ctx.getChildCount() > 1){
            TranslationUnit.outputNoTab("]");
            arrayDimIndex = -1;
        }

    }

    @Override
    public void enterDimExpr(Java8Parser.DimExprContext ctx) {
        String output = "";
        NoPrint = ctx;
        /*
        arrayDimIndex += 1;
        switch(arrayType){
            case "byte":
            case "short":
            case "int":
                output += "[0] * ";
                break;
            case "boolean":
                output += "[False] * ";
                break;
            case "long":
                output += "[0L] * ";
            case "double":
            case "float":
                output += "[0.0] * ";
                break;
            case "char":
                output += "[''] * ";
                break;
            default: output += "[] * ";


        }
        TranslationUnit.outputNoTab(output);

         */
    }

    @Override
    public void exitDimExpr(Java8Parser.DimExprContext ctx) {
        if(ctx.parent instanceof Java8Parser.DimExprsContext && ctx.parent.getChildCount() > 1 && arrayDimIndex < ctx.parent.getChildCount()-1) {
            TranslationUnit.outputNoTab("");
        }
        NoPrint = null;
    }

    @Override
    public void enterConstantExpression(Java8Parser.ConstantExpressionContext ctx) {

    }

    @Override
    public void exitConstantExpression(Java8Parser.ConstantExpressionContext ctx) {

    }

    @Override
    public void enterExpression(Java8Parser.ExpressionContext ctx) {
        String out = "";
        if (ctx.parent instanceof Java8Parser.ArrayAccessContext || ctx.parent instanceof Java8Parser.ArrayAccess_lfno_primaryContext) {
            out = "[";
            TranslationUnit.outputNoTab(out);
        }
        else if (ctx.parent instanceof Java8Parser.BasicForStatementContext) {
            out = "while ";
            TranslationUnit.outputWithTab(out);
        }

    }

    @Override
    public void exitExpression(Java8Parser.ExpressionContext ctx) {
        if(ctx.parent instanceof Java8Parser.IfThenStatementContext || ctx.parent instanceof Java8Parser.IfThenElseStatementContext || ctx.parent instanceof Java8Parser.IfThenElseStatementNoShortIfContext){
            TranslationUnit.outputNoTab("):\n");
        }
        else if (ctx.parent instanceof Java8Parser.ArrayAccessContext || ctx.parent instanceof Java8Parser.ArrayAccess_lfno_primaryContext) {
            String out = "]";
            TranslationUnit.outputNoTab(out);
            //RC
        }
        else if (ctx.parent instanceof Java8Parser.BasicForStatementContext || ctx.parent instanceof Java8Parser.WhileStatementContext) {
            TranslationUnit.outputNoTab(":\n");
        }

    }

    @Override
    public void enterLambdaExpression(Java8Parser.LambdaExpressionContext ctx) {

    }

    @Override
    public void exitLambdaExpression(Java8Parser.LambdaExpressionContext ctx) {

    }

    @Override
    public void enterLambdaParameters(Java8Parser.LambdaParametersContext ctx) {

    }

    @Override
    public void exitLambdaParameters(Java8Parser.LambdaParametersContext ctx) {

    }

    @Override
    public void enterInferredFormalParameterList(Java8Parser.InferredFormalParameterListContext ctx) {

    }

    @Override
    public void exitInferredFormalParameterList(Java8Parser.InferredFormalParameterListContext ctx) {

    }

    @Override
    public void enterLambdaBody(Java8Parser.LambdaBodyContext ctx) {

    }

    @Override
    public void exitLambdaBody(Java8Parser.LambdaBodyContext ctx) {

    }

    @Override
    public void enterAssignmentExpression(Java8Parser.AssignmentExpressionContext ctx) {
        // Doesn't need anything for now since arrays are not yet implemented, go lower
    }

    @Override
    public void exitAssignmentExpression(Java8Parser.AssignmentExpressionContext ctx) {

    }

    @Override
    public void enterAssignment(Java8Parser.AssignmentContext ctx) {
        TranslationUnit.outputWithTab("");
    }

    @Override
    public void exitAssignment(Java8Parser.AssignmentContext ctx) {
        TranslationUnit.outputNoTab("\n");
    }

    @Override
    public void enterLeftHandSide(Java8Parser.LeftHandSideContext ctx) {
    }

    @Override
    public void exitLeftHandSide(Java8Parser.LeftHandSideContext ctx) {

    }

    @Override
    public void enterAssignmentOperator(Java8Parser.AssignmentOperatorContext ctx) {
        String out = " " + ctx.getText() + " ";
        TranslationUnit.outputNoTab(out);
    }

    @Override
    public void exitAssignmentOperator(Java8Parser.AssignmentOperatorContext ctx) {

    }

    @Override
    public void enterConditionalExpression(Java8Parser.ConditionalExpressionContext ctx) {

    }

    @Override
    public void exitConditionalExpression(Java8Parser.ConditionalExpressionContext ctx) {

    }

    @Override
    public void enterConditionalOrExpression(Java8Parser.ConditionalOrExpressionContext ctx) {

    }

    @Override
    public void exitConditionalOrExpression(Java8Parser.ConditionalOrExpressionContext ctx) {
        if(ctx.parent instanceof Java8Parser.ConditionalOrExpressionContext) {
            TranslationUnit.outputNoTab(" or ");
        }
        else if (ctx.parent instanceof Java8Parser.BasicForStatementContext) {
            TranslationUnit.outputNoTab("\n");
        }

    }

    @Override
    public void enterConditionalAndExpression(Java8Parser.ConditionalAndExpressionContext ctx) {

    }

    @Override
    public void exitConditionalAndExpression(Java8Parser.ConditionalAndExpressionContext ctx) {
        if(ctx.parent instanceof Java8Parser.ConditionalAndExpressionContext) {
            TranslationUnit.outputNoTab(" and ");
        }
    }

    @Override
    public void enterInclusiveOrExpression(Java8Parser.InclusiveOrExpressionContext ctx) {

    }

    @Override
    public void exitInclusiveOrExpression(Java8Parser.InclusiveOrExpressionContext ctx) {
        if (ctx.parent instanceof Java8Parser.InclusiveOrExpressionContext) {
            TranslationUnit.outputNoTab(" | ");
        }
    }

    @Override
    public void enterExclusiveOrExpression(Java8Parser.ExclusiveOrExpressionContext ctx) {

    }

    @Override
    public void exitExclusiveOrExpression(Java8Parser.ExclusiveOrExpressionContext ctx) {
        if (ctx.parent instanceof Java8Parser.ExclusiveOrExpressionContext){
            TranslationUnit.outputNoTab(" " + ctx.parent.getChild(1).getText() + " ");
        }
    }
    //RC

    @Override
    public void enterAndExpression(Java8Parser.AndExpressionContext ctx) {

    }

    @Override
    public void exitAndExpression(Java8Parser.AndExpressionContext ctx) {
        if (ctx.parent instanceof Java8Parser.AndExpressionContext) {
            TranslationUnit.outputNoTab(" & ");
        }
    }

    @Override
    public void enterEqualityExpression(Java8Parser.EqualityExpressionContext ctx) {

    }

    @Override
    public void exitEqualityExpression(Java8Parser.EqualityExpressionContext ctx) {
        if(ctx.parent instanceof Java8Parser.EqualityExpressionContext){//ctx.parent.getChildCount() > 1){
            TranslationUnit.outputNoTab(" " + ctx.parent.getChild(1).getText() + " ");
        }
    }

    @Override
    public void enterRelationalExpression(Java8Parser.RelationalExpressionContext ctx) {

    }

    @Override
    public void exitRelationalExpression(Java8Parser.RelationalExpressionContext ctx) {
        if(ctx.parent instanceof Java8Parser.RelationalExpressionContext){//ctx.parent.getChildCount() > 1){
            TranslationUnit.outputNoTab(" " + ctx.parent.getChild(1).getText() + " ");
        }
    }

    @Override
    public void enterShiftExpression(Java8Parser.ShiftExpressionContext ctx) {

    }

    @Override
    public void exitShiftExpression(Java8Parser.ShiftExpressionContext ctx) {
        String out = "";
        if (ctx.parent instanceof Java8Parser.ShiftExpressionContext) {
            String shiftExpr = "";
            if (ctx.parent.getChildCount() == 4) // << or >>
            {
               for (int i = 1; i < 3; i++){
                   shiftExpr += ctx.parent.getChild(i).getText();
               }
            }
            else if (ctx.parent.getChildCount() == 5)  // >>>
            {
                for (int j= 1; j < 4; j++){
                    shiftExpr += ctx.parent.getChild(j).getText();
                }
            }
            out = " " + shiftExpr + " ";
        }
        TranslationUnit.outputNoTab(out);
    }

    @Override
    public void enterAdditiveExpression(Java8Parser.AdditiveExpressionContext ctx) {
    }

    @Override
    public void exitAdditiveExpression(Java8Parser.AdditiveExpressionContext ctx) {
        if(ctx.parent instanceof Java8Parser.AdditiveExpressionContext){//ctx.parent.getChildCount() > 1){
            TranslationUnit.outputNoTab(" " +ctx.parent.getChild(1).getText() + " ");
        }
    }

        @Override
    public void enterMultiplicativeExpression(Java8Parser.MultiplicativeExpressionContext ctx) {

    }

    @Override
    public void exitMultiplicativeExpression(Java8Parser.MultiplicativeExpressionContext ctx) {
        if(ctx.parent instanceof Java8Parser.MultiplicativeExpressionContext){//ctx.parent.getChildCount() > 1){
            TranslationUnit.outputNoTab(" " + ctx.parent.getChild(1).getText() + " ");
        }
    }

    @Override
    public void enterUnaryExpression(Java8Parser.UnaryExpressionContext ctx) {
        //Can be revised like other expression operators? -TQ
        String out;
        if(ctx.unaryExpression() != null) {
            if(ctx.getChild(0).getText().equals("-")){
                out = "-";
            }else {
                out = "+";
            }
            TranslationUnit.outputNoTab(out);
        }
    }

    @Override
    public void exitUnaryExpression(Java8Parser.UnaryExpressionContext ctx) {

    }

    @Override
    public void enterPreIncrementExpression(Java8Parser.PreIncrementExpressionContext ctx) {

    }

    @Override
    public void exitPreIncrementExpression(Java8Parser.PreIncrementExpressionContext ctx) {
        TranslationUnit.outputNoTab("+1");
    }

    @Override
    public void enterPreDecrementExpression(Java8Parser.PreDecrementExpressionContext ctx) {

    }

    @Override
    public void exitPreDecrementExpression(Java8Parser.PreDecrementExpressionContext ctx) {
        TranslationUnit.outputNoTab("-1");
    }

    @Override
    public void enterUnaryExpressionNotPlusMinus(Java8Parser.UnaryExpressionNotPlusMinusContext ctx) {
        if(ctx.unaryExpression() != null) {
            String out = "not ";
            TranslationUnit.outputNoTab(out);
        }
    }

    @Override
    public void exitUnaryExpressionNotPlusMinus(Java8Parser.UnaryExpressionNotPlusMinusContext ctx) {

    }

    @Override
    public void enterPostfixExpression(Java8Parser.PostfixExpressionContext ctx) {

    }

    @Override
    public void exitPostfixExpression(Java8Parser.PostfixExpressionContext ctx) {

    }

    @Override
    public void enterPostIncrementExpression(Java8Parser.PostIncrementExpressionContext ctx) {

    }

    @Override
    public void exitPostIncrementExpression(Java8Parser.PostIncrementExpressionContext ctx) {

        TranslationUnit.outputNoTab(" += 1");
    }

    @Override
    public void enterPostIncrementExpression_lf_postfixExpression(Java8Parser.PostIncrementExpression_lf_postfixExpressionContext ctx) {
        if (!(NoPrint instanceof Java8Parser.ForUpdateContext)) {
            TranslationUnit.outputNoTab(" += 1");
        }
    }

    @Override
    public void exitPostIncrementExpression_lf_postfixExpression(Java8Parser.PostIncrementExpression_lf_postfixExpressionContext ctx) {

    }

    @Override
    public void enterPostDecrementExpression(Java8Parser.PostDecrementExpressionContext ctx) {

    }

    @Override
    public void exitPostDecrementExpression(Java8Parser.PostDecrementExpressionContext ctx) {
        if (!(NoPrint instanceof Java8Parser.ForUpdateContext)) {
            TranslationUnit.outputNoTab(" -= 1");
        }
    }

    @Override
    public void enterPostDecrementExpression_lf_postfixExpression(Java8Parser.PostDecrementExpression_lf_postfixExpressionContext ctx) {
        if (!(NoPrint instanceof Java8Parser.ForUpdateContext)) {
            TranslationUnit.outputNoTab(" -= 1");
        }
    }

    @Override
    public void exitPostDecrementExpression_lf_postfixExpression(Java8Parser.PostDecrementExpression_lf_postfixExpressionContext ctx) {

    }

    @Override
    public void enterCastExpression(Java8Parser.CastExpressionContext ctx) {

    }

    @Override
    public void exitCastExpression(Java8Parser.CastExpressionContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode node) {

    }

    @Override
    public void visitErrorNode(ErrorNode node) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {

    }
}