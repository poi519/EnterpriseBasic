package com.company;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

class ParensDoNotMatchException extends Exception {
    public ParensDoNotMatchException() { super(); }
}

class MalformedArithmeticExpressionException extends Exception {
    public MalformedArithmeticExpressionException() { super(); }
}

class ArithmeticExprParser {
    static Integer[][] table =
           {{6,   1,   1,   1,   5},
            {5,   1,   1,   1,   3},
            {4,   1,   1,   1,   4},
            {4,   1,   2,   1,   4},
            {4,   1,   4,   2,   4}};

    private Stack<BasicExpr> expressionStack = new Stack<BasicExpr>();
    private Stack<ArithmeticOperator> operatorStack = new Stack<ArithmeticOperator>();

    private void changeState(ArithmeticOperator f) throws ParensDoNotMatchException {
        Integer x = table[(operatorStack.isEmpty()) ? 0 : operatorStack.peek().index()][f.index()];
        switch(x) {
            case 1:
                operatorStack.push(f);
                break;
            case 2:
                BasicExpr right = expressionStack.pop(),
                          left  = expressionStack.pop();
                ArithmeticOperator g = operatorStack.pop();
                BasicExpr res = ((BinaryOperator) g).makeExpression(left, right);
                expressionStack.push(res);
                operatorStack.push(f);
                break;
            case 3:
                operatorStack.pop();
                break;
            case 4:
                right = expressionStack.pop();
                left  = expressionStack.pop();
                g = operatorStack.pop();
                res = ((BinaryOperator) g).makeExpression(left, right);
                expressionStack.push(res);
                changeState(f);
                break;
            case 5:
                throw new ParensDoNotMatchException();
            case 6:
                break;
        }
    }

    public void reset() {
        expressionStack = new Stack<BasicExpr>();
        operatorStack = new Stack<ArithmeticOperator>();
    }

    public BasicExpr parse(ArrayList<Lexeme> lexemes)
            throws ParensDoNotMatchException, MalformedArithmeticExpressionException {
        for(Lexeme lexeme : lexemes) {
           if(lexeme instanceof LexValue) {
                expressionStack.push(((LexValue) lexeme).toValue());
           } else if(lexeme instanceof ArithmeticOperator) {
                try {
                    changeState((ArithmeticOperator) lexeme);
                } catch (EmptyStackException e) {
                    throw new MalformedArithmeticExpressionException();
                }
           }
        }
        return expressionStack.peek();
    }
}