package com.company;

interface Lexeme {}

abstract class LexValue implements Lexeme {
    public abstract BasicVal toValue();
}

class LexInt extends LexValue {
    public Integer value;

    public LexInt(String text) {
        value = Integer.parseInt(text);
    }

    public BasicVal toValue() {
        return new IntValue(value);
    }
}

class LexStr implements Lexeme {
    public String value;

    public LexStr(String text) {
        value = text;
    }
}

class LexBool implements Lexeme {
    public Boolean value;

    public LexBool(String text) {
        value = Boolean.parseBoolean(text);
    }
}

abstract class ArithmeticOperator implements Lexeme {
    public abstract Integer index();
}

class LexLPar extends ArithmeticOperator { public Integer index() {return 1;}}
class LexRPar extends ArithmeticOperator { public Integer index() {return 5;}}
class LexEOS extends ArithmeticOperator { public Integer index() {return 0;}}

abstract class BinaryOperator extends ArithmeticOperator {
    public abstract BasicExpr makeExpression(BasicExpr l, BasicExpr r);
}

class LexGT extends BinaryOperator {
    public Integer index() {return 2;}

    public BasicExpr makeExpression(BasicExpr l, BasicExpr r) {
        return new GTExpr(l, r);
    }
}

class LexLT extends BinaryOperator {
    public Integer index() {return 2;}

    public BasicExpr makeExpression(BasicExpr l, BasicExpr r) {
        return new LTExpr(l, r);
    }
}

class LexEq extends BinaryOperator {
    public Integer index() {return 2;}

    public BasicExpr makeExpression(BasicExpr l, BasicExpr r) {
        return new EqExpr(l, r);
    }
}

class LexPlus extends BinaryOperator {
    public Integer index() {return 3;}

    public BasicExpr makeExpression(BasicExpr l, BasicExpr r) {
        return new SumExpr(l, r);
    }
}

class LexMinus extends BinaryOperator {
    public Integer index() {return 3;}

    public BasicExpr makeExpression(BasicExpr l, BasicExpr r) {
        return new DiffExpr(l, r);
    }
}
class LexTimes extends BinaryOperator {
    public Integer index() {return 4;}

    public BasicExpr makeExpression(BasicExpr l, BasicExpr r) {
        return new ProductExpr(l, r);
    }
}