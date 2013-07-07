package com.company;

interface BasicExpr {
    public BasicVal eval(BasicProgram program);
}

abstract class BinaryExpr implements BasicExpr {
    public BasicExpr left, right;
}

class SumExpr extends BinaryExpr {
    public SumExpr(BasicExpr l, BasicExpr r) {
        left = l;
        right = r;
    }

    public BasicVal eval(BasicProgram program) {
        return new IntValue(((IntValue) left.eval(program)).value +
                            ((IntValue) right.eval(program)).value);
    }
}

class DiffExpr extends BinaryExpr {
    public DiffExpr(BasicExpr l, BasicExpr r) {
        left = l;
        right = r;
    }

    public BasicVal eval(BasicProgram program) {
        return new IntValue(((IntValue) left.eval(program)).value -
                ((IntValue) right.eval(program)).value);
    }
}

class ProductExpr extends BinaryExpr {
    public ProductExpr(BasicExpr l, BasicExpr r) {
        left = l;
        right = r;
    }

    public BasicVal eval(BasicProgram program) {
        return new IntValue(((IntValue) left.eval(program)).value *
                ((IntValue) right.eval(program)).value);
    }
}