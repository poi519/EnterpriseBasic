package com.company;

import java.util.List;

class MalformedBasicExpressionException extends ParseTimeException {
    public MalformedBasicExpressionException() {
        super();
    }
}

class BasicParser {
    static ArithmeticExprParser aParser = new ArithmeticExprParser();


    public static BasicExpr parseLine(List<Lexeme> lexemes) throws ParseTimeException {
        if((lexemes.get(0) instanceof LexInt)
                && !(lexemes.get(1) instanceof ArithmeticOperator)) {
            return new LabelledLine(((LexInt) lexemes.get(0)).value,
                                    parseExpression(lexemes.subList(1, lexemes.size())));
        } else
            return parseExpression(lexemes);
    }

    public static BasicExpr parseExpression(List<Lexeme> lexemes)
            throws ParseTimeException {
        Lexeme first = lexemes.get(0);
        BasicExpr res = new NoValue();

        if (first instanceof Keyword) {

            if (first instanceof LexLet) {
                if (!(lexemes.get(2) instanceof LexEq))
                    throw new MalformedBasicExpressionException();
                else
                    res = new LetExpr(new VarExpr(((LexVarName) lexemes.get(1)).name),
                            aParser.parse(lexemes.subList(3, lexemes.size())));

            } else if(first instanceof LexPrint) {
                res = new PrintExpr(aParser.parse(lexemes.subList(1, lexemes.size())));

            } else if(first instanceof LexInput) {
                res = new InputExpr((VarExpr) aParser.parse(lexemes.subList(1, lexemes.size())));

            } else if(first instanceof LexList) {
                res = new ListExpr();

            } else if(first instanceof LexRun) {
                res = new RunExpr();

            } else if(first instanceof LexGoTo) {
                if(lexemes.get(1) instanceof LexInt)
                    res = new GoToExpr(((LexInt) lexemes.get(1)).value);
                else
                    throw new MalformedBasicExpressionException();

            } else if(first instanceof LexIf) {
                if(lexemes.size() < 3)
                    throw new MalformedBasicExpressionException();
                Lexeme then = lexemes.get(lexemes.size() - 3);
                Lexeme lineNumber = lexemes.get(lexemes.size() - 2);
                if((then instanceof LexThen)
                        && (lineNumber instanceof  LexInt))
                    res = new IfExpr(aParser.parse(lexemes.subList(1, lexemes.size() - 3)),
                                    ((LexInt) lineNumber).value);
                else
                    throw new MalformedBasicExpressionException();

            } else if(first instanceof LexRem) {
                res = new NoValue();
            }

        } else {
            res = aParser.parse(lexemes);
        }
        return res;
    }
}