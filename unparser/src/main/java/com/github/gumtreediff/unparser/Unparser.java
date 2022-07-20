package com.github.gumtreediff.unparser;
import com.github.gumtreediff.tree.Tree;
import com.github.gumtreediff.gen.jdt.cd.EntityType;
import java.util.List;

/**
 * Created by @author @pillravi on 11/17/16.
 *
 * Originally created 3/8/16 by @pillravi as part of PANDA (Patterns of Arbitrary Nature Difference Applier).
 *
 * This class traverses a GumTree Tree and turns it back into code. It is not
 * a pretty printer, and therefore requires the IntelliJ code formatter (or
 * other code formatter) to be run on its output.
 */

public final class Unparser {

    public static String unparse(Tree treeToModify) {
        String result;
        StringBuilder sb = new StringBuilder();

        prettyPrintNode(treeToModify, sb);

        result = sb.toString();

        return result;
    }

    public static void prettyPrintNode(Tree node, StringBuilder sb) {
        String typeInteger = node.getType().name;
        //System.out.printf("%nOriginal nodeType: %s",typeInteger);
        if (!typeInteger.contains("_")) {
            String[] typeList = typeInteger.split("(?=[A-Z])");
            typeInteger = String.join("_",typeList);
        }
        typeInteger = typeInteger.toUpperCase();
        System.out.printf("%n\t\tNodeType: %s",typeInteger);
        NodeTypeEnum stype = NodeTypeEnum.valueOf(typeInteger);
        //System.out.printf("NodeTypeEnum: %s", NodeTypeEnum.valueOf(stype));
        //System.out.printf("Ordinal: %d", NodeTypeEnum.stype.ordinal());
        //NodeTypeEnum whichType = NodeTypeEnum.values()[NodeTypeEnum.valueOf(typeInteger).ordinal()];
        switch (stype) {
            case ANONYMOUS_CLASS_DECLARATION:
                unparseAnonymousClassDeclaration(node, sb);
                break;
            case ARRAY_ACCESS:
                unparseArrayAccess(node, sb);
                break;
            case ARRAY_CREATION:
                unparseArrayCreation(node, sb);
                break;
            case ARRAY_INITIALIZER:
                unparseArrayInitializer(node, sb);
                break;
            case ARRAY_TYPE:
                unparseArrayType(node, sb);
                break;
            case ASSERT_STATEMENT:
                unparseAssertStatement(node, sb);
                break;
            case ASSIGNMENT:
                unparseAssignment(node, sb);
                break;
            case BLOCK:
                unparseBlock(node, sb);
                break;
            case BOOLEAN_LITERAL:
                unparseBooleanLiteral(node, sb);
                break;
            case BREAK_STATEMENT:
                unparseBreakStatement(node, sb);
                break;
            case CAST_EXPRESSION:
                unparseCastExpression(node, sb);
                break;
            case CATCH_CLAUSE:
                unparseCatchClause(node, sb);
                break;
            case CHARACTER_LITERAL:
                unparseCharacterLiteral(node, sb);
                break;
            case CLASS_INSTANCE_CREATION:
                unparseClassInstanceCreation(node, sb);
                break;
            case COMPILATION_UNIT:
                unparseCompilationUnit(node, sb);
                break;
            case CONDITIONAL_EXPRESSION:
                unparseConditionalExpression(node, sb);
                break;
            case CONSTRUCTOR_INVOCATION:
                unparseConstructorInvocation(node, sb);
                break;
            case CONTINUE_STATEMENT:
                unparseContinueStatement(node, sb);
                break;
            case DO_STATEMENT:
                unparseDoStatement(node, sb);
                break;
            case EMPTY_STATEMENT:
                unparseEmptyStatement(node, sb);
                break;
            case EXPRESSION_STATEMENT:
                unparseExpressionStatement(node, sb);
                break;
            case FIELD_ACCESS:
                unparseFieldAccess(node, sb);
                break;
            case FIELD_DECLARATION:
                unparseFieldDeclaration(node, sb);
                break;
            case FOR_STATEMENT:
                unparseForStatement(node, sb);
                break;
            case IF_STATEMENT:
                unparseIfStatement(node, sb);
                break;
            case IMPORT_DECLARATION:
                unparseImportDeclaration(node, sb);
                break;
            case INFIX_EXPRESSION:
                unparseInfixExpression(node, sb);
                break;
            case INITIALIZER:
                unparseInitializer(node, sb);
                break;
            case JAVADOC:
                unparseJavadoc(node, sb);
                break;
            case LABELED_STATEMENT:
                unparseLabeledStatement(node, sb);
                break;
            case METHOD_DECLARATION:
                unparseMethodDeclaration(node, sb);
                break;
            case METHOD_INVOCATION:
                unparseMethodInvocation(node, sb);
                break;
            case NULL_LITERAL:
                unparseNullLiteral(node, sb);
                break;
            case NUMBER_LITERAL:
                unparseNumberLiteral(node, sb);
                break;
            case PACKAGE_DECLARATION:
                unparsePackageDeclaration(node, sb);
                break;
            case PARENTHESIZED_EXPRESSION:
                unparseParenthesizedExpression(node, sb);
                break;
            case POSTFIX_EXPRESSION:
                unparsePostfixExpression(node, sb);
                break;
            case PREFIX_EXPRESSION:
                unparsePrefixExpression(node, sb);
                break;
            case PRIMITIVE_TYPE:
                unparsePrimitiveType(node, sb);
                break;
            case QUALIFIED_NAME:
                unparseQualifiedName(node, sb);
                break;
            case RETURN_STATEMENT:
                unparseReturnStatement(node, sb);
                break;
            case SIMPLE_NAME:
                unparseSimpleName(node, sb);
                break;
            case SIMPLE_TYPE:
                unparseSimpleType(node, sb);
                break;
            case SINGLE_VARIABLE_DECLARATION:
                unparseSingleVariableDeclaration(node, sb);
                break;
            case STRING_LITERAL:
                unparseStringLiteral(node, sb);
                break;
            case SUPER_CONSTRUCTOR_INVOCATION:
                unparseSuperConstructorInvocation(node, sb);
                break;
            case SUPER_FIELD_ACCESS:
                unparseSuperFieldAccess(node, sb);
                break;
            case SUPER_METHOD_INVOCATION:
                unparseSuperMethodInvocation(node, sb);
                break;
            case SWITCH_CASE:
                unparseSwitchCase(node, sb);
                break;
            case SWITCH_STATEMENT:
                unparseSwitchStatement(node, sb);
                break;
            case SYNCHRONIZED_STATEMENT:
                unparseSynchronizedStatement(node, sb);
                break;
            case THIS_EXPRESSION:
                unparseThisExpression(node, sb);
                break;
            case THROW_STATEMENT:
                unparseThrowStatement(node, sb);
                break;
            case TRY_STATEMENT:
                unparseTryStatement(node, sb);
                break;
            case TYPE_DECLARATION:
                unparseTypeDeclaration(node, sb);
                break;
            case TYPE_DECLARATION_KIND:
                unparseTypeDeclaration(node,sb);
                break;
            case TYPE_DECLARATION_STATEMENT:
                unparseTypeDeclarationStatement(node, sb);
                break;
            case TYPE_LITERAL:
                unparseTypeLiteral(node, sb);
                break;
            case VARIABLE_DECLARATION_EXPRESSION:
                unparseVariableDeclarationExpression(node, sb);
                break;
            case VARIABLE_DECLARATION_FRAGMENT:
                unparseVariableDeclarationFragment(node, sb);
                break;
            case VARIABLE_DECLARATION_STATEMENT:
                unparseVariableDeclarationStatement(node, sb);
                break;
            case WHILE_STATEMENT:
                unparseWhileStatement(node, sb);
                break;
            case INSTANCEOF_EXPRESSION:
                unparseInstanceofExpression(node, sb);
                break;
            case LINE_COMMENT:
                unparseLineComment(node, sb);
                break;
            case BLOCK_COMMENT:
                unparseBlockComment(node, sb);
                break;
            case TAG_ELEMENT:
                unparseTagElement(node, sb);
                break;
            case TEXT_ELEMENT:
                unparseTextElement(node, sb);
                break;
            case MEMBER_REF:
                unparseMemberRef(node, sb);
                break;
            case METHOD_REF:
                unparseMethodRef(node, sb);
                break;
            case METHOD_REF_PARAMETER:
                unparseMethodRefParameter(node, sb);
                break;
            case ENHANCED_FOR_STATEMENT:
                unparseEnhancedForStatement(node, sb);
                break;
            case ENUM_DECLARATION:
                unparseEnumDeclaration(node, sb);
                break;
            case ENUM_CONSTANT_DECLARATION:
                unparseEnumConstantDeclaration(node, sb);
                break;
            case TYPE_PARAMETER:
                unparseTypeParameter(node, sb);
                break;
            case PARAMETERIZED_TYPE:
                unparseParameterizedType(node, sb);
                break;
            case QUALIFIED_TYPE:
                unparseQualifiedType(node, sb);
                break;
            case WILDCARD_TYPE:
                unparseWildcardType(node, sb);
                break;
            case NORMAL_ANNOTATION:
                unparseNormalAnnotation(node, sb);
                break;
            case MARKER_ANNOTATION:
                unparseMarkerAnnotation(node, sb);
                break;
            case SINGLE_MEMBER_ANNOTATION:
                unparseSingleMemberAnnotation(node, sb);
                break;
            case MEMBER_VALUE_PAIR:
                unparseMemberValuePair(node, sb);
                break;
            case ANNOTATION_TYPE_DECLARATION:
                unparseAnnotationTypeDeclaration(node, sb);
                break;
            case ANNOTATION_TYPE_MEMBER_DECLARATION:
                unparseAnnotationTypeMemberDeclaration(node, sb);
                break;
            case MODIFIER:
                unparseModifier(node, sb);
                break;
            case UNION_TYPE:
                unparseUnionType(node, sb);
                break;
            case DIMENSION:
                unparseDimension(node, sb);
                break;
            case LAMBDA_EXPRESSION:
                unparseLambdaExpression(node, sb);
                break;
            case INTERSECTION_TYPE:
                unparseIntersectionType(node, sb);
                break;
            case NAME_QUALIFIED_TYPE:
                unparseNameQualifiedType(node, sb);
                break;
            case CREATION_REFERENCE:
                unparseCreationReference(node, sb);
                break;
            case EXPRESSION_METHOD_REFERENCE:
                unparseExpressionMethodReference(node, sb);
                break;
            case SUPER_METHOD_REFERENCE:
                unparseSuperMethodReference(node, sb);
                break;
            case TYPE_METHOD_REFERENCE:
                unparseTypeMethodReference(node, sb);
                break;
            default:
                /* Other than if somehow a type 0 gets through (which would be
                 * a "dummy type," this will never happen; it would crash on the
                 * array access above before this ever happened. */
                sb.append("UNKNOWN_TYPE_ENCOUNTERED");
        }
    } /* end prettyPrintNode */

    private static void unparseAnonymousClassDeclaration(Tree node,
                                                         StringBuilder sb) {

    }

    private static void unparseArrayAccess(Tree node, StringBuilder sb) {

    }

    private static void unparseArrayCreation(Tree node, StringBuilder sb) {

    }

    private static void unparseArrayInitializer(Tree node, StringBuilder sb) {

    }

    private static void unparseArrayType(Tree node, StringBuilder sb) {
//        List<Tree> children = node.getChildren();
//
//        for (Tree child : children) {
//            prettyPrintNode(child, sb);
//        }
        /* TODO: check if it is ever necessary to print each child */
        sb.append(node.getLabel());
    }

    private static void unparseAssertStatement(Tree node, StringBuilder sb) {

    }

    private static void unparseAssignment(Tree node, StringBuilder sb) {
        List<Tree> children = node.getChildren();
        boolean equalsPrinted = false;

        for (Tree child : children) {
            prettyPrintNode(child, sb);

            /* After the first child, print an equals sign */
            if (!equalsPrinted) {
                sb.append("= ");
                equalsPrinted = true;
            }
        }

    }

    private static void unparseBlock(Tree node, StringBuilder sb) {
        List<Tree> children = node.getChildren();

        sb.append("{\n");
        for (Tree child : children) {
            prettyPrintNode(child, sb);
        }
        sb.append("}\n");
    }

    private static void unparseBooleanLiteral(Tree node, StringBuilder sb) {

    }

    private static void unparseBreakStatement(Tree node, StringBuilder sb) {

    }

    private static void unparseCastExpression(Tree node, StringBuilder sb) {

    }

    private static void unparseCatchClause(Tree node, StringBuilder sb) {
        List<Tree> children = node.getChildren();
        boolean endCatchObjParenPrinted = false;

        sb.append("catch (");
        for (Tree child : children) {
            prettyPrintNode(child, sb);

            if (!endCatchObjParenPrinted) {
                sb.append(") ");
                endCatchObjParenPrinted = true;
            }
        }
    }

    private static void unparseCharacterLiteral(Tree node, StringBuilder sb) {

    }

    private static void unparseClassInstanceCreation(Tree node,
                                                     StringBuilder sb) {
        List<Tree> children = node.getChildren();

        sb.append("new ");
        /* print the name of the new object's class */
        prettyPrintNode(children.get(0), sb);

        printArgList(1, children, sb);
    }

    private static void unparseCompilationUnit(Tree node, StringBuilder sb) {
        List<Tree> children = node.getChildren();

        for (Tree child : children) {
            prettyPrintNode(child, sb);
        }
    }

    private static void unparseConditionalExpression(Tree node,
                                                     StringBuilder sb) {

    }

    private static void unparseConstructorInvocation(Tree node,
                                                     StringBuilder sb) {

    }

    private static void unparseContinueStatement(Tree node, StringBuilder sb) {

    }

    private static void unparseDoStatement(Tree node, StringBuilder sb) {

    }

    private static void unparseEmptyStatement(Tree node, StringBuilder sb) {

    }

    private static void unparseExpressionStatement(Tree node,
                                                   StringBuilder sb) {
        List<Tree> children = node.getChildren();

        for (Tree child : children) {
            prettyPrintNode(child, sb);
        }
        sb.append(";\n");
    }

    private static void unparseFieldAccess(Tree node, StringBuilder sb) {

    }

    private static void unparseFieldDeclaration(Tree node, StringBuilder sb) {
        List<Tree> children = node.getChildren();

        for (Tree child : children) {
            prettyPrintNode(child, sb);
            sb.append(" ");
        }
        deleteTrailingSpace(sb);
        sb.append(";\n");
    }

    private static void unparseForStatement(Tree node, StringBuilder sb) {

    }

    private static void unparseIfStatement(Tree node, StringBuilder sb) {
        // TODO: test what happens if for statement is only one line
        // could possibly test last child to see if it's a block
        List<Tree> children = node.getChildren();

        sb.append("if (");
        for (Tree child : children) {
            if (child.getType().name.equals(EntityType.BLOCK.name())) {
                /* the conditions have ended */
                sb.append(") ");
            }
            prettyPrintNode(child, sb);
        }
    }

    private static void unparseImportDeclaration(Tree node, StringBuilder sb) {
        List<Tree> children = node.getChildren();

        sb.append("import ");
        for (Tree child : children) {
            prettyPrintNode(child, sb);
        }
        sb.append(";\n");
    }

    private static void unparseInfixExpression(Tree node, StringBuilder sb) {
        List<Tree> children = node.getChildren();

        for (Tree child : children) {
            prettyPrintNode(child, sb);
            sb.append(node.getLabel() + " ");
        }
        /* remove the last infix label and space so that they will only be in
         * between each child */
        sb.delete(sb.length() - 2, sb.length());
    }

    private static void unparseInitializer(Tree node, StringBuilder sb) {

    }

    private static void unparseJavadoc(Tree node, StringBuilder sb) {
        sb.append("/**\n*/\n");
    }

    private static void unparseLabeledStatement(Tree node, StringBuilder sb) {

    }

    private static void unparseMethodDeclaration(Tree node, StringBuilder sb) {
        List<Tree> children = node.getChildren();
        boolean methodHasBeenNamed = false;

        for (Tree child : children) {
            /* The last child of a method decl should be a block, before which
             * the parameter list should end with a closing ")" */
            if (child.getType().name.equals(EntityType.BLOCK.name())) {
                sb.append(") ");
            }

            prettyPrintNode(child, sb);
            sb.append(" ");

            /* The first simple name child of a method decl should be the name
             * of the method. This should be followed by the parameter list
             * surrounded in parentheses */
            if (child.getType().name.equals(EntityType.SIMPLE_NAME.name()) &&
                    !methodHasBeenNamed) {
                methodHasBeenNamed = true;
                deleteTrailingSpace(sb);
                sb.append("(");
            }
        }
    }

    private static void unparseMethodInvocation(Tree node, StringBuilder sb) {
        List<Tree> children = node.getChildren();

        /* Print the receiver (always the 1st child) */
        prettyPrintNode(children.get(0), sb);
        /* After the receiver of the message send, add a "." */
        sb.append(".");
        /* Print the name of the method (always the 2nd child) */
        prettyPrintNode(children.get(1), sb);
        /* After the name, start the parameter list */
        printArgList(2, children, sb);
    }

    private static void printArgList(int startIdx, List<Tree> children,
                                     StringBuilder sb) {
        sb.append("(");

        List<Tree> arguments = children.subList(startIdx, children.size());
        for (Tree child : arguments) {
            prettyPrintNode(child, sb);
            sb.append(", ");
        }
        if (!arguments.isEmpty()) {
            /* remove the last comma and space so that they will only be in
             * between each parameter */
            sb.delete(sb.length() - 2, sb.length());
        }

        /* end the parameter list */
        sb.append(")");
    }

    private static void unparseNullLiteral(Tree node, StringBuilder sb) {
        sb.append("null");
    }

    private static void unparseNumberLiteral(Tree node, StringBuilder sb) {
        sb.append(node.getLabel());
    }

    private static void unparsePackageDeclaration(Tree node,
                                                  StringBuilder sb) {
        List<Tree> children = node.getChildren();

        sb.append("package ");
        for (Tree child : children) {
            prettyPrintNode(child, sb);
        }

        /* after a package decl, there's more often than not a blank line */
        sb.append(";\n\n");
    }

    private static void unparseParenthesizedExpression(Tree node,
                                                       StringBuilder sb) {
        List<Tree> children = node.getChildren();

        sb.append("(");
        for (Tree child : children) {
            prettyPrintNode(child, sb);
        }
        sb.append(")");
    }

    private static void unparsePostfixExpression(Tree node, StringBuilder sb) {

    }

    private static void unparsePrefixExpression(Tree node, StringBuilder sb) {
        List<Tree> children = node.getChildren();

        sb.append(node.getLabel());
        for (Tree child : children) {
            prettyPrintNode(child, sb);
        }
    }

    private static void unparsePrimitiveType(Tree node, StringBuilder sb) {
        sb.append(node.getLabel());
    }

    private static void unparseQualifiedName(Tree node, StringBuilder sb) {
        sb.append(node.getLabel());
    }

    private static void unparseReturnStatement(Tree node, StringBuilder sb) {
        List<Tree> children = node.getChildren();

        sb.append("return ");
        for (Tree child : children) {
            prettyPrintNode(child, sb);
        }
        sb.append(";\n");
    }

    private static void unparseSimpleName(Tree node, StringBuilder sb) {
        sb.append(node.getLabel());
    }

    private static void unparseSimpleType(Tree node, StringBuilder sb) {
        if (node.getParent().getType().name.equals(EntityType.TYPE_DECLARATION.name())) {
            /* then it is a type being extended or implemented */
            /* TODO: figure out how to differentiate b/w extends/implements */
            sb.append("extends ");
        }

        List<Tree> children = node.getChildren();
        /* should only have one child, a simple name */
        for (Tree child : children) {
            prettyPrintNode(child, sb);
        }
    }

    private static void unparseSingleVariableDeclaration(Tree node,
                                                         StringBuilder sb) {
        List<Tree> children = node.getChildren();

        for (Tree child : children) {
            prettyPrintNode(child, sb);
            sb.append(" ");
        }
        deleteTrailingSpace(sb);
    }

    private static void unparseStringLiteral(Tree node, StringBuilder sb) {
        sb.append(node.getLabel());
    }

    private static void unparseSuperConstructorInvocation(Tree node,
                                                          StringBuilder sb) {

    }

    private static void unparseSuperFieldAccess(Tree node, StringBuilder sb) {

    }

    private static void unparseSuperMethodInvocation(Tree node,
                                                     StringBuilder sb) {

    }

    private static void unparseSwitchCase(Tree node, StringBuilder sb) {

    }

    private static void unparseSwitchStatement(Tree node, StringBuilder sb) {

    }

    private static void unparseSynchronizedStatement(Tree node,
                                                     StringBuilder sb) {

    }

    private static void unparseThisExpression(Tree node, StringBuilder sb) {
        sb.append("this");
    }

    private static void unparseThrowStatement(Tree node, StringBuilder sb) {

    }

    private static void unparseTryStatement(Tree node, StringBuilder sb) {
        List<Tree> children = node.getChildren();

        sb.append("try ");
        for (Tree child : children) {
            prettyPrintNode(child, sb);
        }
    }

    private static void unparseTypeDeclaration(Tree node, StringBuilder sb) {
        List<Tree> children = node.getChildren();
        boolean classKeywordPrinted = false;
        boolean openingBracePrinted = false;

        for (Tree child : children) {
            /* if there are no more modifiers like public, static, final */
            if (!child.getType().name.equals(EntityType.JAVADOC.name()) && !child.getType().name.equals(EntityType.MODIFIER.name()) && !classKeywordPrinted) {
                sb.append("class ");
                classKeywordPrinted = true;
            }

            /* If this next node a simple type or name, we've started the
             * class */
            boolean isClassNameOrExtendedType =
                    (child.getType().name.equals(EntityType.SIMPLE_NAME.name()) || child.getType().name.equals(EntityType.SIMPLE_TYPE.name()));

            if (classKeywordPrinted && !isClassNameOrExtendedType && !openingBracePrinted) {
                sb.append("{\n\t");
                openingBracePrinted = true;
            }

            prettyPrintNode(child, sb);
            sb.append(" ");

        }

        /* at the end of a class, there should be a closing brace */
        sb.append("}\n");
    }

    private static void unparseTypeDeclarationStatement(Tree node,
                                                        StringBuilder sb) {

    }

    private static void unparseTypeLiteral(Tree node, StringBuilder sb) {

    }

    private static void unparseVariableDeclarationExpression(Tree node,
                                                             StringBuilder sb) {

    }

    private static void unparseVariableDeclarationFragment(Tree node,
                                                           StringBuilder sb) {
        List<Tree> children = node.getChildren();

        for (Tree child : children) {
            prettyPrintNode(child, sb);
            sb.append(" ");

            /* If the Variable Declaration Fragment has more than just one child
             * it means that after the variable has been named, it will be
             * initialized so it needs an = sign */
            if (child.getType().name.equals(EntityType.SIMPLE_NAME.name()) && nextSiblingExistsFor(child)) {
                sb.append("= ");
            }
        }
        deleteTrailingSpace(sb);
    }

    private static void unparseVariableDeclarationStatement(Tree node,
                                                            StringBuilder sb) {
        List<Tree> children = node.getChildren();

        for (Tree child : children) {
            prettyPrintNode(child, sb);
            sb.append(" ");
        }
        deleteTrailingSpace(sb);
        sb.append(";\n");
    }

    private static void unparseWhileStatement(Tree node, StringBuilder sb) {

    }

    private static void unparseInstanceofExpression(Tree node,
                                                    StringBuilder sb) {

    }

    private static void unparseLineComment(Tree node, StringBuilder sb) {

    }

    private static void unparseBlockComment(Tree node, StringBuilder sb) {

    }

    private static void unparseTagElement(Tree node, StringBuilder sb) {

    }

    private static void unparseTextElement(Tree node, StringBuilder sb) {

    }

    private static void unparseMemberRef(Tree node, StringBuilder sb) {

    }

    private static void unparseMethodRef(Tree node, StringBuilder sb) {

    }

    private static void unparseMethodRefParameter(Tree node,
                                                  StringBuilder sb) {

    }

    private static void unparseEnhancedForStatement(Tree node,
                                                    StringBuilder sb) {
        List<Tree> children = node.getChildren();
        sb.append("for (");
        /* Unparse loop control variable */
        prettyPrintNode(children.get(0), sb);
        sb.append(" : ");
        /* Unparse collection being looped through */
        prettyPrintNode(children.get(1), sb);
        sb.append(") ");
        /* Unparse block */
        // todo: what if an enhanced for only has one statement inside
        // and therefore doesn't have a block?
        prettyPrintNode(children.get(2), sb);

    }

    private static void unparseEnumDeclaration(Tree node, StringBuilder sb) {

    }

    private static void unparseEnumConstantDeclaration(Tree node,
                                                       StringBuilder sb) {

    }

    private static void unparseTypeParameter(Tree node, StringBuilder sb) {

    }

    private static void unparseParameterizedType(Tree node, StringBuilder sb) {
        sb.append(node.getLabel());
    }

    private static void unparseQualifiedType(Tree node, StringBuilder sb) {

    }

    private static void unparseWildcardType(Tree node, StringBuilder sb) {

    }

    private static void unparseNormalAnnotation(Tree node, StringBuilder sb) {

    }

    private static void unparseMarkerAnnotation(Tree node, StringBuilder sb) {
        List<Tree> children = node.getChildren();

        sb.append("@");
        for (Tree child : children) {
            prettyPrintNode(child, sb);
        }
        sb.append("\n");
    }

    private static void unparseSingleMemberAnnotation(Tree node,
                                                      StringBuilder sb) {

    }

    private static void unparseMemberValuePair(Tree node, StringBuilder sb) {

    }

    private static void unparseAnnotationTypeDeclaration(Tree node,
                                                         StringBuilder sb) {

    }

    private static void unparseAnnotationTypeMemberDeclaration(Tree node,
                                                               StringBuilder sb) {

    }

    private static void unparseModifier(Tree node, StringBuilder sb) {
        sb.append(node.getLabel());
    }

    private static void unparseUnionType(Tree node, StringBuilder sb) {

    }

    private static void unparseDimension(Tree node, StringBuilder sb) {

    }

    private static void unparseLambdaExpression(Tree node, StringBuilder sb) {

    }

    private static void unparseIntersectionType(Tree node, StringBuilder sb) {

    }

    private static void unparseNameQualifiedType(Tree node, StringBuilder sb) {

    }

    private static void unparseCreationReference(Tree node, StringBuilder sb) {

    }

    private static void unparseExpressionMethodReference(Tree node,
                                                         StringBuilder sb) {

    }

    private static void unparseSuperMethodReference(Tree node,
                                                    StringBuilder sb) {

    }

    private static void unparseTypeMethodReference(Tree node,
                                                   StringBuilder sb) {

    }

    /* Util methods used by case methods */
    private static void deleteTrailingSpace(StringBuilder sb) {
        sb.delete(sb.length()-1, sb.length());
    }

    private static Tree getPreviousSibling(Tree child) {
        int pos = child.positionInParent();
        int siblingPos = pos - 1;
        if (pos < 0) {
            throw new IndexOutOfBoundsException("no previous sibling");
        }
        return child.getParent().getChild(siblingPos);
    }


    private static boolean nextSiblingExistsFor(Tree child) {
        int pos = child.positionInParent();
        int siblingPos = pos + 1;
        Tree parent = child.getParent();
        try {
            parent.getChild(siblingPos);
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

} /* end class Unparser */
